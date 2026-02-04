package com.aluracursos.literatura.principal;

import com.aluracursos.literatura.model.*;
import com.aluracursos.literatura.repository.AutorRepository;
import com.aluracursos.literatura.repository.LibroRepository;
import com.aluracursos.literatura.service.ConsumoAPI;
import com.aluracursos.literatura.service.ConvierteDatos;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/";
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por título 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            try {
                opcion = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida.");
                continue;
            }

            switch (opcion) {
                case 1 -> buscarLibroWeb();
                case 2 -> mostrarLibrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosPorAnio();
                case 5 -> buscarLibrosPorIdioma();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibroWeb() {
        System.out.println("Ingrese el título del libro:");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        var datos = conversor.obtenerDatos(json, Datos.class);

        if (!datos.resultados().isEmpty()) {
            DatosLibro datosLibro = datos.resultados().get(0);
            DatosAutor datosAutor = datosLibro.autor().get(0);

            Optional<Libro> libroExistente = libroRepository.findByTituloContainsIgnoreCase(datosLibro.titulo());
            if (libroExistente.isPresent()) {
                System.out.println("El libro ya está registrado.");
            } else {
                Autor autor = autorRepository.findByNombreIgnoreCase(datosAutor.nombre())
                        .orElseGet(() -> autorRepository.save(new Autor(datosAutor)));

                Libro libro = new Libro(datosLibro, autor);
                libroRepository.save(libro);
                System.out.println(libro);
            }
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private void mostrarLibrosRegistrados() {
        libroRepository.findAll().forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        autorRepository.findAll().forEach(System.out::println);
    }

    // DENTRO DE PRINCIPAL.JAVA
    private void listarAutoresVivosPorAnio() {
        System.out.println("Ingrese el año:");
        var anio = Integer.parseInt(teclado.nextLine());

        // LLAMADA CORRECTA AL REPOSITORIO DE AUTORES
        List<Autor> autores = autorRepository.buscarAutoresVivosEnDeterminadoAnio(anio);

        if (autores.isEmpty()) {
            System.out.println("No se hallaron autores vivos.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void buscarLibrosPorIdioma() {
        System.out.println("Ingrese el código (es, en, fr, pt):");
        var codigo = teclado.nextLine();
        Idioma idioma = Idioma.fromString(codigo);
        if (idioma == Idioma.DESCONOCIDO) {
            System.out.println("Idioma no soportado.");
        } else {
            libroRepository.findByIdioma(idioma).forEach(System.out::println);
        }
    }
}