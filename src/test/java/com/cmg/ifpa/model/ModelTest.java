package com.cmg.ifpa.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.util.List;
import java.time.LocalDateTime;

public class ModelTest {

    @Test
    void testEntities() throws Exception {
        List<Class<?>> classes = List.of(
                AccionCapacitacion.class,
                Artesano.class,
                ComprobacionCapacitacion.class,
                Distrito.class,
                Evento.class,
                GrupoEtnico.class,
                InscripcionCapacitacion.class,
                LenguaIndigena.class,
                Localidad.class,
                MateriaPrima.class,
                Municipio.class,
                Organizacion.class,
                ProgramaCapacitacion.class,
                RamaArtesanal.class,
                Region.class,
                Tecnica.class,
                TipoComprador.class,
                TrimestreCapacitacion.class,
                Usuario.class);

        for (Class<?> clazz : classes) {
            Object instance = clazz.getDeclaredConstructor().newInstance();
            Object allArgsInstance = null;

            // Test getters and setters via reflection
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.getName().startsWith("set") && method.getParameterCount() == 1) {
                    String getterName = "get" + method.getName().substring(3);
                    try {
                        Method getter = clazz.getMethod(getterName);
                        Object value = getDefaultValue(method.getParameterTypes()[0]);
                        method.invoke(instance, value);
                        assertEquals(value, getter.invoke(instance),
                                "Getter/Setter failed for " + clazz.getSimpleName() + "." + method.getName());
                    } catch (NoSuchMethodException e) {
                        // try is... for boolean
                        getterName = "is" + method.getName().substring(3);
                        try {
                            Method getter = clazz.getMethod(getterName);
                            Object value = getDefaultValue(method.getParameterTypes()[0]);
                            method.invoke(instance, value);
                            assertEquals(value, getter.invoke(instance),
                                    "Getter/Setter failed for " + clazz.getSimpleName() + "." + method.getName());
                        } catch (NoSuchMethodException e2) {
                            // No getter found, skipping
                        }
                    }
                }
            }

            // Test toString, equals, hashCode (Lombok generated)
            assertNotNull(instance.toString());
            assertEquals(instance, instance);
            assertNotEquals(instance, new Object());
            assertEquals(instance.hashCode(), instance.hashCode());

            // Test prePersist if it exists
            try {
                Method prePersist = clazz.getDeclaredMethod("prePersist");
                prePersist.setAccessible(true);
                
                Object freshInstance = clazz.getDeclaredConstructor().newInstance();
                
                // Test transition from null to 'A'
                prePersist.invoke(freshInstance);
                Method getEstatus = clazz.getMethod("getEstatus");
                assertEquals("A", getEstatus.invoke(freshInstance), "prePersist failed to set default estatus for " + clazz.getSimpleName());
                
                // Test it doesn't override if already set
                Method setEstatus = clazz.getMethod("setEstatus", String.class);
                setEstatus.invoke(freshInstance, "I");
                prePersist.invoke(freshInstance);
                assertEquals("I", getEstatus.invoke(freshInstance), "prePersist overrode existing estatus for " + clazz.getSimpleName());
            } catch (NoSuchMethodException e) {
                // No prePersist method, skipping
            }
        }
    }

    private Object getDefaultValue(Class<?> type) throws Exception {
        if (type == String.class)
            return "test";
        if (type == Long.class || type == long.class)
            return 1L;
        if (type == Integer.class || type == int.class)
            return 1;
        if (type == Double.class || type == double.class)
            return 1.0;
        if (type == Float.class || type == float.class)
            return 1.0f;
        if (type == Boolean.class || type == boolean.class)
            return true;
        if (type == Character.class || type == char.class)
            return 'A';
        if (type == java.time.LocalDate.class)
            return java.time.LocalDate.now();
        if (type == LocalDateTime.class)
            return LocalDateTime.now();
        if (type.isEnum())
            return type.getEnumConstants()[0];
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
