package com.example.proveedor.Helpers;

public class ValidacionPais{

    // Método para validar que un string no sea null
    public static void validateNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " no puede ser null");
        }
    }

    // Método para validar que el nombre no contenga números
    public static void validateNoNumbers(String value, String fieldName) {
        if (value != null && value.matches(".*\\d.*")) {
            throw new IllegalArgumentException(fieldName + " no puede contener números");
        }
    }
}