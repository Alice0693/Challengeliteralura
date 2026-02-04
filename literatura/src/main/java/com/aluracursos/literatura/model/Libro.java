package com.aluracursos.literatura.model;

import jakarta.persistence.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;

    private Double numeroDeDescargas;

    public Libro() {}

    public Libro(DatosLibro datosLibro, Autor autor) {
        this.titulo = datosLibro.titulo();
        this.autor = autor;
        String codigo = datosLibro.idiomas().stream().limit(1).collect(Collectors.joining());
        this.idioma = Idioma.fromString(codigo);
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }
    public Idioma getIdioma() { return idioma; }
    public void setIdioma(Idioma idioma) { this.idioma = idioma; }
    public Double getNumeroDeDescargas() { return numeroDeDescargas; }
    public void setNumeroDeDescargas(Double numeroDeDescargas) { this.numeroDeDescargas = numeroDeDescargas; }

    @Override
    public String toString() {
        String nombreDelAutor = (autor != null) ? autor.getNombre() : "Autor no disponible";
        return  "---------- LIBRO ----------" +
                "\nTítulo: " + titulo +
                "\nAutor: " + nombreDelAutor +
                "\nIdioma: " + (idioma != null ? idioma.getNombreEspanol() : "N/A") +
                "\nDescargas: " + numeroDeDescargas +
                "\n---------------------------";
    }
}