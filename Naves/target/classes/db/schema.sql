CREATE DATABASE IF NOT EXISTS CURSOS;

CREATE TABLE Cursos (
    id INTEGER PRIMARY KEY,
    nombre TEXT NOT NULL,
    fecha_inicio DATETIME NOT NULL,
    fecha_fin DATETIME NOT NULL,
    num_max_alumnos INTEGER NOT NULL,
    fecha_registro DATETIME NOT NULL
);

CREATE TABLE Alumnos (
    id INTEGER PRIMARY KEY,
    nombre TEXT NOT NULL,
    apellido TEXT NOT NULL,
    num_documento TEXT NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    fecha_registro DATETIME NOT NULL
);

CREATE TABLE Curso_alumno (
    id INTEGER PRIMARY KEY,
    curso_id INTEGER NOT NULL,
    alumno_id INTEGER NOT NULL,
    fecha_inscripcion DATETIME NOT NULL,
    fecha_baja DATETIME,
    FOREIGN KEY (curso_id) REFERENCES Cursos(id),
    FOREIGN KEY (alumno_id) REFERENCES Alumnos(id)
);


INSERT INTO Curso (nombre, fecha_inicio, fecha_fin, max_alumnos, fecha_registro)
VALUES ('Matematicas', '2022-01-01 09:00:00', '2023-06-01 18:00:00', 30, '2021-12-01 12:00:00'),
       ('Historia', '2022-01-05 09:00:00', '2023-07-01 18:00:00', 25, '2021-12-15 12:00:00'),
       ('Lengua', '2022-01-03 09:00:00', '2023-08-01 18:00:00', 20, '2022-01-01 12:00:00'),
       ('Fisica', '2022-01-06 09:00:00', '2023-09-01 18:00:00', 15, '2022-01-15 12:00:00'),
       ('Tecnologia', '2022-02-01 09:00:00', '2023-10-01 18:00:00', 10, '2022-02-01 12:00:00');


INSERT INTO Alumno (nombre, apellido, documento, fecha_nacimiento, fecha_registro)
VALUES ('Juan', 'Pérez', '12345678A', '2000-01-01', '2021-12-01 12:00:00'),
       ('María', 'García', '23456789B', '2000-02-02', '2021-12-15 12:30:30'),
       ('Carlos', 'Rodríguez', '34567890C', '2000-03-03', '2022-01-01 13:00:00'),
       ('Ana', 'Fernández', '45678901D', '2003-04-04', '2022-01-15 13:30:30'),
       ('David', 'González', '56789012E', '2004-05-05', '2022-02-01 14:00:00');

INSERT INTO Curso_alumno (id_curso, id_alumno, fecha_inscripcion,alumno_id,curso_id)
VALUES (1, 1, '2022-01-01 09:00:00',1,1),
       (1, 2, '2022-01-01 09:30:00',1,2),
       (1, 3, '2022-01-01 10:00:00',1,3),
       (1, 4, '2022-01-01 10:30:00',1,4),
       (1, 5, '2022-01-01 11:00:00',1,5),
       (2, 1, '2022-02-01 09:00:00',2,1),
       (2, 2, '2022-02-01 09:30:00',2,2),
       (2, 3, '2022-02-01 10:00:00',2,3),
       (2, 4, '2022-02-01 10:30:00',2,4),
       (2, 5, '2022-02-01 11:00:00',2,5),
       (3, 1, '2022-03-01 09:00:00',3,1),
       (3, 2, '2022-03-01 09:30:00',3,2);