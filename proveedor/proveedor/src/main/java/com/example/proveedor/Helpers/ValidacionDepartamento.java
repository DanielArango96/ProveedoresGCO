package com.example.proveedor.Helpers;

public class ValidacionDepartamento {

    // Método para validar que un objeto no sea null
    public static void validarNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " no puede ser null");
        }
    }

    // Método para validar que un string no contenga números
    public static void validateNoNumbers(String value, String fieldName) {
        if (value != null && value.matches(".*\\d.*")) {
            throw new IllegalArgumentException(fieldName + " no puede contener números");
        }
    }
}