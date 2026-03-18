package com.cmg.ifpa.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String correoElectronico;
    private String contrasena;
}
