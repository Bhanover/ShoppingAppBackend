# SHOPPING APP BACKEND



[![Download IntelliJ IDEA](https://img.shields.io/badge/download-IntelliJ%20IDEA-blue)](https://www.jetbrains.com/idea/download/)
[![React](https://img.shields.io/badge/React-^17.0.0-blue)](https://reactjs.org/)
[![Node.js](https://img.shields.io/badge/Node.js-^14.0.0-green)](https://nodejs.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-^2.5.0-brightgreen)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-^3.8.1-yellow)](https://maven.apache.org/)
[![HTML](https://img.shields.io/badge/HTML-5-orange)](https://developer.mozilla.org/en-US/docs/Web/HTML)
[![CSS](https://img.shields.io/badge/CSS-3-blue)](https://developer.mozilla.org/en-US/docs/Web/CSS)
[![Java](https://img.shields.io/badge/Java-^11.0-orange)](https://www.java.com/)

<p align="center">
    <a href="https://www.youtube.com/watch?v=haPTJ8KhQ7k">
  <img src="https://github.com/Bhanover/ShoppingAppFrontend/assets/127310131/12f91de9-c497-4bc4-890d-7de6933fd7ff" alt="logo" width="300">
    </a>
</p>



## Índice

- [Descripción](#descripción)
- [Video del Proyecto](#video-del-proyecto)
- [Características Principales](#características-principales)
  - [Para Usuarios](#para-usuarios)
  - [Para Administradores](#para-administradores)
  - [Adaptabilidad y Seguridad](#adaptabilidad-y-seguridad)
- [Bibliotecas y Dependencias](#bibliotecas-y-dependencias)
  - [Servidor (usando Spring Boot)](#servidor-usando-spring-boot)
- [Requisitos del Sistema](#requisitos-del-sistema)
- [Configuración de la Base de Datos](#configuración-de-la-base-de-datos)
  - [Configuración de la Base de Datos para Importar un Archivo .sql](#configuración-de-la-base-de-datos-para-importar-un-archivo-sql)
- [Cómo Empezar](#cómo-empezar)
  - [Servidor (Spring Boot)](#servidor-spring-boot)
- [Guía de Uso Detallada](#guía-de-uso-detallada)
  - [Para Usuarios](#para-usuarios-1)
    - [Navegación y Exploración de Productos](#navegación-y-exploración-de-productos)
    - [Registro e Inicio de Sesión](#registro-e-inicio-de-sesión)
    - [Carrito de Compras](#carrito-de-compras)
    - [Página de Contacto](#página-de-contacto)
  - [Para Administradores](#para-administradores-1)
    - [Gestión de Productos](#gestión-de-productos)
    - [Gestión de Categorías y Subcategorías](#gestión-de-categorías-y-subcategorías)
    - [Panel de Administración](#panel-de-administración)
  - [Adaptabilidad y Seguridad](#adaptabilidad-y-seguridad-1)
- [Información Importante](#información-importante)
- [Cómo Contribuir](#cómo-contribuir)
- [Contacto](#contacto)
- [Licencia](#licencia)

## Descripción
Store es una página enfocada en la venta de ropa. Se busca proporcionar una experiencia de compra segura, eficiente y visualmente atractiva, permitiendo a los usuarios explorar y adquirir productos desde cualquier dispositivo.

## Video del Proyecto
Aquí puedes ver un video demostrativo del proyecto:

[![Video del Proyecto](https://img.youtube.com/vi/haPTJ8KhQ7k/0.jpg)](https://www.youtube.com/watch?v=haPTJ8KhQ7k)

## Características Principales

### Para Usuarios
- **Navegación de Productos**:
  - Explora ropa y accesorios por categorías y subcategorías.
  - Visualiza detalles, descripciones, precios y tamaños.

- **Cuenta de Usuario**:
  - Regístrate e inicia sesión para una experiencia personalizada.

- **Carrito de Compras**:
  - Añade productos al carrito y gestiona tu compra fácilmente.

- **Página de Contacto**:
  - Rellena un formulario o envía un mensaje a través de WhatsApp.

### Para Administradores
- **Gestión de Productos**:
  - Crea, edita y elimina productos.

- **Gestión de Categorías**:
  - Crea, edita y elimina categorías y subcategorías.

- **Panel de Administración**:
  - Administra la tienda desde un panel centralizado.

### Adaptabilidad y Seguridad
- **Adaptabilidad a Dispositivos Móviles**:
  - Experiencia optimizada en dispositivos móviles y de escritorio.

- **Plataforma Segura**:
  - Navega y compra con confianza en una plataforma segura.

## Bibliotecas y Dependencias

### Servidor (usando Spring Boot):
- Spring Web: Facilita el desarrollo de aplicaciones web RESTful y la interacción con la base de datos.
- Spring Security: Maneja la autenticación y autorización en la aplicación.
- Spring Data JPA: Simplifica la persistencia de datos en la base de datos.
- MySQL Driver: Permite la comunicación con la base de datos MySQL.
- spring-boot-starter-validation: Permite la validación de los datos de entrada.
- spring-boot-starter-actuator: Brinda características de gestión y supervisión de la aplicación.
- spring-security-messaging: Asegura el envío de mensajes y soporte de WebSocket en Spring.
- webjars-locator-core: Permite localizar y servir bibliotecas alojadas en WebJars.
- sockjs-client, stomp-websocket: Proporcionan funcionalidad de WebSocket en el cliente.
- cloudinary-http44: Gestiona archivos y su carga a la nube.
- jjwt: Genera y valida JSON Web Tokens (JWT) en la aplicación.

## Requisitos del Sistema
Para ejecutar este proyecto, necesitarás lo siguiente instalado en tu sistema:
- Java 11 o superior
- MySQL 5.7 o superior
- Git
- Un IDE adecuado como IntelliJ IDEA para Spring Boot.

#### IntelliJ IDEA
1. Descarga e instala [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) Community Edition.
2. Clona el repositorio en tu máquina local.
3. Abre IntelliJ IDEA y selecciona "Open" en el diálogo inicial. Busca y selecciona la carpeta del proyecto que acabas de clonar.
4. IntelliJ IDEA detectará automáticamente el archivo `pom.xml` y configurará tu proyecto en base a él. Si se te solicita, permite que IntelliJ IDEA descargue e instale los plugins y dependencias necesarios.

### Configuración de la base de datos

Es necesario tener MySQL instalado y configurado en tu máquina local. Una vez hecho esto, debes configurar las credenciales y otros parámetros de la base de datos siguiendo el archivo `application.properties` del servidor de Spring Boot donde se encuentra el nombre que debes usar en la base de datos y el usuario que debes usar en Mysql junto con su contraseña.

```properties
spring.datasource.url= jdbc:mysql://localhost:3306/ShoppingAppDB?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username= root
spring.datasource.password= 1234
server.port=8081
```
Una ves creado en Mysql el usuario "root" con password "1234" no olvides de crear la base de datos con el comando `create database ShoppingAppDB`;


### Configuración de la base de datos para importar un archivo .sql

Para importar una base de datos a MySQL Workbench, puedes seguir los siguientes pasos:

Paso 1: Abre MySQL Workbench

- Lanza la aplicación MySQL Workbench en tu ordenador.

Paso 2: Conéctate a tu instancia de MySQL

- En la pantalla inicial, haz clic en la conexión a la instancia de MySQL donde quieres importar la base de datos.

Paso 3: Abre la herramienta de importación de datos

- Una vez que estás conectado a tu instancia de MySQL, selecciona "Server" en la barra de menú, luego "Data Import".

Paso 4: Selecciona las opciones de importación

- En la ventana "Data Import", selecciona "Import from Self-Contained File". Luego, navega hasta el archivo de la base de datos que deseas importar (debería ser un archivo .sql).

Paso 5: Selecciona la base de datos de destino

- En la sección "Default Target Schema", selecciona la base de datos en la que deseas importar tus datos. Si no tienes una base de datos para este propósito, tendrás que crear una nueva: puedes hacerlo seleccionando "Create New" en el menú desplegable.

Paso 6: Comienza la importación

- Haz clic en "Start Import" en la parte inferior de la pantalla para comenzar a importar tus datos o puede encontrarse en import progress.

La importación puede llevar bastante tiempo. Asegúrate de que tu computadora no se apague ni entre en modo de suspensión durante este tiempo, ya que podría interrumpir la importación.

## Cómo Empezar
Después de configurar el entorno de desarrollo y la base de datos, puedes iniciar el servidor:

### Puedes empezar con una base de datos ya creada(Mysql)
 - Aqui tienes shop.sql en el repositorio el cual podrías importar.
 - Ahora solo nesesitarias importar la base de datos.
 - Este es el usuario admin creado "admin".
 - La contraseña es : "balto123".

### También puedes empezar desde 0 (Mysql)
 - Spring Boot ya te crea las tablas (solo nesesitarias crear la base de datos)

### Servidor (Spring Boot)
1. En IntelliJ IDEA, busca la clase principal de la aplicación (usualmente nombrada `Application` o `Main`) en el panel de archivos del proyecto `/src/main/java/com/project/ShoppingAppBackend/ShoppingAppBackendApplication.java` ahí encontraras la clase `ShoppingAppBackendApplication.java`.
2. Haz clic derecho en la clase principal y selecciona 'Run `Application.main()`' en el menú contextual. Esto iniciará el servidor en el puerto que se especificó en el archivo `application.properties`.

![image](https://github.com/Bhanover/ShoppingAppBackend/assets/127310131/ca715331-adcd-4173-837f-48e2176b5789)


## Guía de Uso Detallada

### Para Usuarios

#### Navegación y Exploración de Productos
1. **Inicio**:
   - Accede a la página de inicio para ver las últimas tendencias y productos destacados.
2. **Categorías y Subcategorías**:
   - Utiliza el menú de navegación para explorar productos por categorías y subcategorías.
3. **Detalles del Producto**:
   - Haz clic en cualquier producto para ver detalles completos, incluyendo descripción, precio y opciones de tamaño.

#### Registro e Inicio de Sesión
1. **Registro**:
   - Haz clic en "Registrarse" en la esquina superior derecha.
   - Completa el formulario de registro con tu información personal y crea una cuenta.
2. **Inicio de Sesión**:
   - Si ya tienes una cuenta, haz clic en "Iniciar Sesión" e ingresa tus credenciales.

#### Carrito de Compras
1. **Añadir al Carrito**:
   - Selecciona el tamaño y otras opciones del producto.
   - Haz clic en "Añadir al Carrito".
2. **Revisar Carrito**:
   - Haz clic en el ícono del carrito para revisar los productos añadidos.
   - Modifica las cantidades o elimina productos según sea necesario.
3. **Proceder al Pago**:
   - Una vez satisfecho con tu selección, haz clic en "Proceder al Pago" para completar tu compra.

#### Página de Contacto
1. **Formulario de Contacto**:
   - Accede a la página de contacto desde el menú principal.
   - Completa el formulario con tu consulta o mensaje.
2. **WhatsApp**:
   - Alternativamente, envía un mensaje directo a través de WhatsApp para soporte rápido.

### Para Administradores

#### Gestión de Productos
1. **Crear Producto**:
   - Accede al panel de administración y selecciona "Productos".
   - Haz clic en "Crear Nuevo Producto".
   - Completa los campos obligatorios como nombre, descripción, precio e imágenes.
   - Selecciona la categoría y subcategoría correspondiente.
   - Guarda el producto.
2. **Editar Producto**:
   - En la lista de productos, selecciona el producto a editar.
   - Haz los cambios necesarios y guarda.
3. **Eliminar Producto**:
   - En la lista de productos, selecciona el producto a eliminar.
   - Confirma la eliminación.

#### Gestión de Categorías y Subcategorías
1. **Crear Categoría/Subcategoría**:
   - Accede al panel de administración y selecciona "Categorías".
   - Haz clic en "Crear Nueva Categoría" o "Crear Nueva Subcategoría".
   - Completa los campos necesarios y guarda.
2. **Editar Categoría/Subcategoría**:
   - En la lista de categorías, selecciona la categoría o subcategoría a editar.
   - Haz los cambios necesarios y guarda.
3. **Eliminar Categoría/Subcategoría**:
   - En la lista de categorías, selecciona la categoría o subcategoría a eliminar.
   - Confirma la eliminación.

#### Panel de Administración
1. **Acceso**:
   - Inicia sesión con tus credenciales de administrador.
2. **Dashboard**:
   - Desde el dashboard, accede a la gestión de productos, categorías, pedidos y usuarios.
3. **Actualización de Información**:
   - Mantén la tienda actualizada con las últimas novedades y cambios de productos.

### Adaptabilidad y Seguridad
1. **Adaptabilidad a Dispositivos Móviles**:
   - La tienda está diseñada para ser completamente adaptable, permitiendo una experiencia óptima en móviles, tablets y escritorios.
2. **Seguridad**:
   - Asegúrate de que la conexión sea segura (HTTPS) para proteger la información personal y de pago de los usuarios.


## Información Importante

- **Subida de imágenes:** Es importante notar que solo se pueden subir imágenes con un tamaño máximo de 10MB debido a las restricciones de Cloudinary, que es el servicio que utilizamos para almacenar este tipo de archivos. Esto se debe a que estamos utilizando la versión gratuita de Cloudinary, que tiene un límite de tamaño de archivo.
- **Conexión con la base de datos:** Es importante saber que tienes que tener bien conectado la base de datos con spring boot en el caso de que no lo este te saldra este error:

![image](https://github.com/Bhanover/MyProject/assets/127310131/1c082f26-aadb-4338-9905-e57984bf196e)

Una posible solución es que si hiciste todos los pasos bien , ademas de eso tienes que tener el programa de Mysql abierto.


- **Activar Lombook:** En Spring Boot al iniciar el proyecto por primera vez , Spring Boot te pedira que actives la Lombook y en ese caso das click a `enabled lombook`



 ## Cómo Contribuir
Las contribuciones son bienvenidas y apreciadas. Sigue estos pasos para contribuir:
1. Haz un "Fork" del repositorio.
2. Clona el fork a tu máquina local.
3. Crea una nueva rama para tu cambio.
4. Haz tus cambios y asegúrate de probarlos.
5. Haz un "commit" de tus cambios a tu rama.
6. Haz un "push" de tus cambios a tu fork en GitHub.
7. Abre un "Pull Request" en el repositorio original.

Por favor, asegúrate de que tu código sigue las convenciones de estilo del proyecto y que has añadido pruebas para cualquier cambio que hagas, si es aplicable.


## Contacto
Si tienes preguntas o deseas discutir algo sobre el proyecto, no dudes en contactarme a través de mi correo electrónico: billydht5@gmail.com

## Licencia
Este proyecto está licenciado bajo [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0).

