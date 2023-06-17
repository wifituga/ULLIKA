CREATE TABLE caracter (
    caracter CHAR(1 CHAR) NOT NULL
);

ALTER TABLE caracter ADD CONSTRAINT caracter_pk PRIMARY KEY ( caracter );

CREATE TABLE carrera (
    id_carrera     NUMBER(2) NOT NULL,
    nombre_carrera VARCHAR2(50 CHAR) NOT NULL,
    id_facultad    VARCHAR2(5 CHAR) NOT NULL
);

ALTER TABLE carrera ADD CONSTRAINT carrera_pk PRIMARY KEY ( id_carrera );

CREATE TABLE ciclo (
    cod_ciclo VARCHAR2(5 CHAR) NOT NULL
);

ALTER TABLE ciclo ADD CONSTRAINT ciclo_pk PRIMARY KEY ( cod_ciclo );

CREATE TABLE cicloclase (
    cod_clase NUMBER(6) NOT NULL,
    cod_ciclo VARCHAR2(5 CHAR) NOT NULL
);

ALTER TABLE cicloclase ADD CONSTRAINT cicloclase_pk PRIMARY KEY ( cod_clase,
                                                                  cod_ciclo );

CREATE TABLE clase (
    cod_clase        NUMBER(6) NOT NULL,
    cod_curso NUMBER(15) NOT NULL
);

ALTER TABLE clase ADD CONSTRAINT clase_pk PRIMARY KEY ( cod_clase );

CREATE TABLE cursos (
    cod_curso    NUMBER(15) NOT NULL,
    nombre_curso VARCHAR2(100 CHAR) NOT NULL,
    creditos     NUMBER(1) NOT NULL,
    id_plan      VARCHAR2(10 CHAR) NOT NULL,
    cod_curso1   NUMBER(15) NULL,
    cod_curso2   NUMBER(15) NULL,
    caracter     CHAR(1 CHAR) NOT NULL,
    nivel  NUMBER(2) NOT NULL
);

ALTER TABLE CURSOS
    MODIFY NOMBRE_CURSO VARCHAR2(100 CHAR);

ALTER TABLE cursos ADD CONSTRAINT cursos_pk PRIMARY KEY ( cod_curso );

CREATE TABLE estudclase (
    cod_clase      NUMBER(6) NOT NULL,
    cod_estudiante NUMBER(8) NOT NULL
);

ALTER TABLE estudclase ADD CONSTRAINT estudclase_pk PRIMARY KEY ( cod_clase,
                                                                  cod_estudiante );

CREATE TABLE estudiante (
    cod_estudiante       NUMBER(8) NOT NULL,
    nombre_estudiante    VARCHAR2(30 CHAR) NOT NULL,
    apellidop_estudiante VARCHAR2(30 CHAR) NOT NULL,
    apellidom_estudiante VARCHAR2(30 CHAR) NOT NULL,
    id_carrera           NUMBER(2) NOT NULL
);

ALTER TABLE estudiante ADD CONSTRAINT estudiante_pk PRIMARY KEY ( cod_estudiante );

CREATE TABLE facultad (
    id_facultad     VARCHAR2(5 CHAR) NOT NULL,
    nombre_facultad VARCHAR2(50 CHAR) NOT NULL
);

ALTER TABLE facultad ADD CONSTRAINT facultad_pk PRIMARY KEY ( id_facultad );

CREATE TABLE nivel (
    nivel NUMBER(2) NOT NULL
);

ALTER TABLE nivel ADD CONSTRAINT nivel_pk PRIMARY KEY ( nivel );

CREATE TABLE plan_estudios (
    id_plan     VARCHAR2(10 CHAR) NOT NULL,
    nombre_plan VARCHAR2(100 CHAR) NOT NULL
);

ALTER TABLE plan_estudios ADD CONSTRAINT plan_estudios_pk PRIMARY KEY ( id_plan );

CREATE TABLE profcur (
    cod_curso    NUMBER(15) NOT NULL,
    cod_profesor NUMBER(6) NOT NULL
);

ALTER TABLE profcur ADD CONSTRAINT profcur_pk PRIMARY KEY ( cod_curso,
                                                            cod_profesor );

CREATE TABLE profesor (
    cod_profesor       NUMBER(6) NOT NULL,
    nombre_profesor    VARCHAR2(30 CHAR) NOT NULL,
    apellidop_profesor VARCHAR2(30 CHAR) NOT NULL,
    apellidom_profesor VARCHAR2(30 CHAR) NOT NULL
);

ALTER TABLE profesor ADD CONSTRAINT profesor_pk PRIMARY KEY ( cod_profesor );

CREATE TABLE valoracion (
    puntaje        NUMBER(2),
    cod_estudiante NUMBER(8) NOT NULL,
    cod_profesor   NUMBER(6) NOT NULL
);

ALTER TABLE valoracion ADD CONSTRAINT valoracion_pk PRIMARY KEY ( cod_estudiante,
                                                                  cod_profesor );

ALTER TABLE carrera
    ADD CONSTRAINT carrera_facultad_fk FOREIGN KEY ( id_facultad )
        REFERENCES facultad ( id_facultad );

ALTER TABLE cicloclase
    ADD CONSTRAINT cicloclase_ciclo_fk FOREIGN KEY ( cod_ciclo )
        REFERENCES ciclo ( cod_ciclo );

ALTER TABLE cicloclase
    ADD CONSTRAINT cicloclase_clase_fk FOREIGN KEY ( cod_clase )
        REFERENCES clase ( cod_clase );

ALTER TABLE clase
    ADD CONSTRAINT clase_cursos_fk FOREIGN KEY ( cod_curso )
        REFERENCES cursos ( cod_curso );

ALTER TABLE cursos
    ADD CONSTRAINT cursos_caracter_fk FOREIGN KEY ( caracter )
        REFERENCES caracter ( caracter );

ALTER TABLE cursos
    ADD CONSTRAINT cursos_cursos_fk FOREIGN KEY ( cod_curso1 )
        REFERENCES cursos ( cod_curso );

ALTER TABLE cursos
    ADD CONSTRAINT cursos_cursos_fkv2 FOREIGN KEY ( cod_curso2 )
        REFERENCES cursos ( cod_curso );

ALTER TABLE cursos
    ADD CONSTRAINT cursos_nivel_fk FOREIGN KEY ( nivel_nivel )
        REFERENCES nivel ( nivel );

ALTER TABLE cursos
    ADD CONSTRAINT cursos_plan_estudios_fk FOREIGN KEY ( id_plan )
        REFERENCES plan_estudios ( id_plan );

ALTER TABLE estudclase
    ADD CONSTRAINT estudclase_clase_fk FOREIGN KEY ( cod_clase )
        REFERENCES clase ( cod_clase );

ALTER TABLE estudclase
    ADD CONSTRAINT estudclase_estudiante_fk FOREIGN KEY ( cod_estudiante )
        REFERENCES estudiante ( cod_estudiante );

ALTER TABLE estudiante
    ADD CONSTRAINT estudiante_carrera_fk FOREIGN KEY ( id_carrera )
        REFERENCES carrera ( id_carrera );

ALTER TABLE profcur
    ADD CONSTRAINT profcur_cursos_fk FOREIGN KEY ( cod_curso )
        REFERENCES cursos ( cod_curso );

ALTER TABLE profcur
    ADD CONSTRAINT profcur_profesor_fk FOREIGN KEY ( cod_profesor )
        REFERENCES profesor ( cod_profesor );

ALTER TABLE valoracion
    ADD CONSTRAINT valoracion_estudiante_fk FOREIGN KEY ( cod_estudiante )
        REFERENCES estudiante ( cod_estudiante );

ALTER TABLE valoracion
    ADD CONSTRAINT valoracion_profesor_fk FOREIGN KEY ( cod_profesor )
        REFERENCES profesor ( cod_profesor );

