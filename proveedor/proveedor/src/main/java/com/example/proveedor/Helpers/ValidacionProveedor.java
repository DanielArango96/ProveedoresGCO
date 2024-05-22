package com.example.proveedor.Helpers;

public class ValidacionProveedor {

    // Método para validar que un objeto no sea null
    public static void validateNotNull(Object value, String fieldName) {
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

    // Método para validar el formato del correo
    public static void validateEmailFormat(String email, String fieldName) {
        if (email != null && !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException(fieldName + " debe tener un formato válido de correo");
        }
    }

    // Método para validar la longitud máxima de un string
    public static void validateMaxLength(String value, int maxLength, String fieldName) {
        if (value != null && value.length() > maxLength) {
            throw new IllegalArgumentException(fieldName + " no puede tener más de " + maxLength + " caracteres");
        }
    }

    // Método para validar que un string solo contenga números
    public static void validateOnlyNumbers(String value, String fieldName) {
        if (value != null && !value.matches("\\d+")) {
            throw new IllegalArgumentException(fieldName + " solo puede contener números");
        }
    }
}
