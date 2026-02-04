package com.aluracursos.literatura.model;

public enum Idioma {
    ESPAÑOL("es"),
    INGLES("en"),
    FRANCES("fr"),
    PORTUGUES("pt"),
    DESCONOCIDO("unknown");

    private String codigo;

    Idioma(String codigo) {
        this.codigo = codigo;
    }

    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.codigo.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        // Esto evita el error si el texto ya es "ESPAÑOL"
        try {
            return Idioma.valueOf(text.toUpperCase());
        } catch (IllegalArgumentException e) {
            return DESCONOCIDO;
        }
    }

    public String getNombreEspanol() {
        // Capitaliza el nombre para que se vea bien: "Español"
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}