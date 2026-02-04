# Challengeliteralura

# LiterAlura - Catálogo de Libros 📚

¡Bienvenido al desafío LiterAlura! Este proyecto es un catálogo de libros interactivo que consume datos de la API Gutendex, procesa información en formato JSON y la almacena en una base de datos relacional para su consulta persistente.

## 🚀 Funcionalidades
- **Búsqueda de libros por título**: Consulta datos directamente desde la API Gutendex.
- **Persistencia de datos**: Guarda libros y autores en PostgreSQL para consultas futuras.
- **Listado de libros**: Muestra todos los libros registrados en la base de datos local.
- **Listado de autores**: Muestra los autores registrados, incluyendo sus fechas de vida.
- **Filtro de autores vivos**: Permite consultar qué autores estaban vivos en un año determinado.
- **Filtro por idioma**: Filtra los libros guardados por código de idioma (es, en, fr, pt).

## 🛠️ Tecnologías utilizadas
- **Java 17**
- **Spring Boot 4.x** (Spring Data JPA)
- **PostgreSQL** (Base de datos relacional)
- **Jackson** (Manipulación de JSON)
- **Maven** (Gestión de dependencias)

## 📦 Configuración del Proyecto

### 1. Base de Datos
Asegúrate de tener PostgreSQL instalado y crea una base de datos llamada `literatura_db`.

### 2. Variables de Entorno
Configura tu archivo `src/main/resources/application.properties` con tus credenciales:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literatura_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

🖥️ Cómo ejecutar la aplicación
Clona este repositorio.

Abre el proyecto en tu IDE preferido (IntelliJ IDEA recomendado).

Ejecuta la clase LiteraturaApplication.java.

Interactúa con el menú a través de la consola.

Desarrollado por Alicia Segovia como parte del programa Oracle Next Education.


## 💡 Aprendizajes y Desafíos

Durante el desarrollo de este proyecto, logré fortalecer mis habilidades técnicas en los siguientes puntos clave:

- **Manejo de consultas JPQL avanzadas**: Implementé lógica personalizada para filtrar autores vivos, gestionando correctamente los valores nulos (`null`) en las fechas de fallecimiento.
- **Mapeo de relaciones JPA**: Configuré relaciones de tipo **@ManyToOne** y **@OneToMany** para garantizar la integridad referencial entre libros y autores.
- **Consumo de APIs externas**: Utilicé la API Gutendex para extraer datos dinámicos, procesando respuestas complejas mediante el uso de **Records** y la librería **Jackson**.
- **Persistencia de datos**: Dominé la configuración de **Spring Data JPA** para interactuar con una base de datos **PostgreSQL**, asegurando que la información se guarde de forma eficiente.


spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
