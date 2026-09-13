API REST para gestion de un centro de salud, esta desarrollada con Java+SpringBoot,
autenticacion JWT, autorizacion de roles jerarquicos y reglas de negocio (turnos,
disponibilidad medica, bajas de usuario).

Tabla de contenidos:
- stack tecnologico
- arquitectura
- modelo de datos
- seguridad y autenticacion
- roles y permisos
- instalacion y configuracion
- endpoints
- flujos de prueba
- reglas de negocio

  Stack:
  -Java 21
  - Spring Boot (Spring Data JPA/Hibernate, Spring Security)
  - PosgrSQL
  - JWT(jjwt)
  - BCrypt
  - Maven

  Arquitectura:
  Cliente (Postman / Frontend)
        │
        ▼
   Controller      → Recibe requests HTTP, valida permisos por rol (@PreAuthorize)
        │
        ▼
    Service        → Lógica de negocio y validaciones
        │
        ▼
   Repository      → Acceso a datos (Spring Data JPA)
        │
        ▼
   PostgreSQL

  Modelo de datos
  - usuario: entidad base de autenticacion (email, contrasena, hasheada, rol) se relaciona
    por composicion con las entidades de dominio
  - Paciente/Doctor/Enfermero/administrador: vinculada 1 a 1 con usuario
  - Especialidad: catalogo de especialidades medicas
  - DoctorEspecialidad/EnfermeroSector/AdministradorSector: tablas intermedias con clave compuesta (@EmbeddedId)
  modelando que un doctor puede atender varias especialidades y que enfermeros/admin rotan por distintos sectores.
  - DisponibilidadDOctor: bloques de horarios en los que el doctor atiende
  - Turno: entidad central del sistema - esta conecta al pacaiente y doctor en una fecha/hora, con trazabilidad de auditoria
    quien lo creo/modifico)

    Diagrama simplificado
    Usuario (1) ─── (1) Paciente
Usuario (1) ─── (1) Doctor ─── (N) DoctorEspecialidad ─── (N) Especialidad
Usuario (1) ─── (1) Enfermero ─── (N) EnfermeroSector ─── (N) Especialidad
Usuario (1) ─── (1) Administrador ─── (N) AdministradorSector ─── (N) Especialidad

Doctor (1) ─── (N) DisponibilidadDoctor
Doctor (1) ─── (N) Turno ─── (N) Paciente (1)

Seguridad y Autenticacion
Implementa autenticacion stateless basada en JW
filtro (JwtFilter) intercepta cada request
validacion de token
autenticacion de usuario

Roles y permisos
Director - maxima autoridad, unico rol habilitado para dar de baja a un Doctor
ADMIN - personal de administracion, gestiona pacientes, doctores y turnos
Doctor - ve y gestiona sus propios turnos
Enfermero - Rota por distintos sectores
Paciente - ve solo sus propios turnos

Instalacion y configuracion
JDK 32
PostgreSQL18
Maven

Mejoras a futuro
- Documentacion interactiva de la API con OpenAPI
- Test automatizados para services y controllers
- migraciones de base de datos versionadas mas apropiado para un entorno de produccion
- modulo de triage/emergencias
- variables de entorno para credenciales en lugar de valores directo

Autora

Proyecto desarrollado por Jacqueline Jantman como parte de mi portfolio de desarrollo backend.

  - 
