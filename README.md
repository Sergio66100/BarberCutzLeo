# BarberCutzLeo

BarberCutzLeo es una aplicación multiplataforma desarrollada como parte de tus estudios en Desarrollo de Aplicaciones Multiplataforma. Su objetivo es facilitar la gestión de citas y servicios en barberías, optimizando la experiencia tanto para clientes como para barberos.

Características:

    Gestión de Citas: Permite a los clientes reservar y gestionar sus citas de manera sencilla.

    Catálogo de Servicios: Visualización de los servicios ofrecidos por la barbería.

    Perfil de Usuario: Cada usuario puede gestionar su información personal y preferencias.

    Notificaciones: Alertas para recordar citas próximas o promociones especiales.

Tecnologías Utilizadas:

    Java: Lógica de negocio y funcionalidades principales.

    HTML & CSS: Estructura y estilo de la interfaz de usuario.

Como testear la Web:
Puedes registrarte con cualquier ususario o Iniciar Sesion con este usuario:
usuario: pedro@gmail.com
contraseña: 1234

Funcionalidades:

Registarte
Iniciar Sesión
Cerrar Sesion
Reservar Cita

Cosas a Saber:
- No puedes reservar cita sin Iniciar Sesion
- Las citas se reservan de 45 min en 45 min, es decir, si hay una cita a las 17.00, mo podras reservar otra a las 17.30, te dara un error
- Puedes comprobar la Base de Datos con este enlace http://localhost:9000/h2-console/login.jsp
  user name: sa
  password: password
  JDBC URL: jdbc:h2:file:~/testdb001


Modo de Ejecución:
# 🚀 Guía para levantar el proyecto

Este proyecto incluye un backend desarrollado en Spring Boot y un frontend desplegado en Firebase. A continuación, te mostramos cómo levantar todo el entorno paso a paso.

## 1. 🔁 Clonar el repositorio

Primero, clona el repositorio para obtener una copia local del código.

```bash
git clone <URL-del-repositorio>
Este comando descargará todo el contenido del repositorio en tu equipo.

2. 🧱 Ejecutar el backend con Docker
🔧 Requisitos previos:
Tener instalado Docker Desktop
👉 Descárgalo desde: https://www.docker.com/products/docker-desktop

Tener instalado IntelliJ IDEA (opcional, solo si quieres ver o modificar el código desde un IDE)

2.1. 📁 Navegar a la carpeta del backend
Abre una terminal (CMD, PowerShell o Git Bash) y ve a la carpeta del backend:

bash
Copiar
Editar
cd ruta/del/proyecto/backend
Asegúrate de que dentro de esta carpeta exista el archivo Dockerfile.

2.2. 🏗️ Construir la imagen de Docker
bash
Copiar
Editar
docker build -t mi-springboot-app .
Este comando crea una imagen de Docker a partir del Dockerfile ubicado en el directorio actual.
-t mi-springboot-app asigna un nombre personalizado a la imagen.

2.3. ▶️ Ejecutar el contenedor
bash
Copiar
Editar
docker run -p 9000:9000 mi-springboot-app
Este comando inicia un contenedor con la imagen creada.
-p 9000:9000 expone el puerto 9000 de tu máquina al puerto 9000 del contenedor.
Puedes acceder al backend desde: http://localhost:9000

3. 🌐 Acceder al frontend (Firebase)
El frontend del proyecto está desplegado en Firebase. Solo tienes que entrar al siguiente enlace:

🔗 Ir a la página web

Ahí podrás ver el sitio en funcionamiento con HTML y CSS conectándose al backend que levantaste con Docker.



