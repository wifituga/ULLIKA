CREATE TABLE alumnos (
    id_alumnos    NUMBER(8) NOT NULL,
    nombre_alumno VARCHAR2(200 CHAR) NOT NULL,
    id_carrera    NUMBER(2) NOT NULL);
            
ALTER TABLE alumnos ADD CONSTRAINT alumnos_pk PRIMARY KEY ( id_alumnos );
            
CREATE TABLE caracter (
    id_caracter     CHAR(1 CHAR) NOT NULL,
    nombre_caracter VARCHAR2(20 CHAR) NOT NULL);
            
ALTER TABLE caracter ADD CONSTRAINT caracter_pk PRIMARY KEY ( id_caracter );
            
CREATE TABLE carreras (
    id_carrera       NUMBER(2) NOT NULL,
    nombre_carrera   VARCHAR2(100 CHAR) NOT NULL,
    id_facultad      VARCHAR2(10 CHAR) NOT NULL,
    id_plan_estudios VARCHAR2(10 CHAR) NOT NULL);
            
CREATE UNIQUE INDEX carreras__idx ON carreras (
    id_plan_estudios
    ASC );
            
ALTER TABLE carreras ADD CONSTRAINT carreras_pk PRIMARY KEY ( id_carrera );
            
CREATE TABLE ciclo (
    id_ciclo VARCHAR2(20 CHAR) NOT NULL);
            
ALTER TABLE ciclo ADD CONSTRAINT ciclo_pk PRIMARY KEY ( id_ciclo );
            
CREATE TABLE cursos (
    cod_curso        NUMBER(10) NOT NULL,
    nombre_curso     VARCHAR2(100 CHAR) NOT NULL,
    id_caracter      CHAR(1 CHAR) NOT NULL,
    id_nivel         NUMBER(2) NOT NULL,
    id_plan_estudios VARCHAR2(10 CHAR) NOT NULL,
    cod_curso1        NUMBER(10) NULL,
    cod_curso2       NUMBER(10) NULL,
    CREDITOS        NUMBER(1) NOT NULL);
                
ALTER TABLE cursos ADD CONSTRAINT cursos_pk PRIMARY KEY ( cod_curso );
            
CREATE TABLE cursos_alumnos (
    id_cursos_alumnos NUMBER(20) NOT NULL,
    id_alumnos        NUMBER(8) NOT NULL,
    cod_curso         NUMBER(10) NOT NULL,
    id_ciclo          VARCHAR2(20 CHAR) NOT NULL,
    id_seccion        NUMBER(10) NOT NULL);
            
ALTER TABLE cursos_alumnos ADD CONSTRAINT cursos_alumnos_pk PRIMARY KEY ( id_cursos_alumnos );
            
CREATE TABLE facultades (
    id_facultad     VARCHAR2(10 CHAR) NOT NULL,
    nombre_facultad VARCHAR2(100 CHAR) NOT NULL);
            
ALTER TABLE facultades ADD CONSTRAINT facultades_pk PRIMARY KEY ( id_facultad );
            
CREATE TABLE niveles (
    id_nivel     NUMBER(2) NOT NULL,
    nombre_nivel VARCHAR2(50 CHAR) NOT NULL);
            
ALTER TABLE niveles ADD CONSTRAINT niveles_pk PRIMARY KEY ( id_nivel );
            
CREATE TABLE plan_estudios (
    id_plan_estudios     VARCHAR2(10 CHAR) NOT NULL,
    nombre_plan_estudios VARCHAR2(100 CHAR) NOT NULL,
    id_carrera           NUMBER(2) NOT NULL);
            
CREATE UNIQUE INDEX plan_estudios__idx ON plan_estudios (
    id_carrera
    ASC );
            
ALTER TABLE plan_estudios ADD CONSTRAINT plan_estudios_pk PRIMARY KEY ( id_plan_estudios );
            
CREATE TABLE prof_cursos (
    id_prof_cursos NUMBER(20) NOT NULL,
    cod_profesor   NUMBER(8) NOT NULL,
    cod_curso      NUMBER(10) NOT NULL);
            
ALTER TABLE prof_cursos ADD CONSTRAINT profesores_cursos_pk PRIMARY KEY ( id_prof_cursos );
CREATE SEQUENCE SEQ_PROF_CURSOS START WITH 1 INCREMENT BY 1;
CREATE OR REPLACE TRIGGER TRG_PROF_CURSOS
BEFORE INSERT ON PROF_CURSOS
FOR EACH ROW
BEGIN
  :NEW.ID_PROF_CURSOS := :NEW.COD_PROFESOR * 1000 + SEQ_PROF_CURSOS.NEXTVAL;
END;
/
            
    CREATE TABLE profesores (
    cod_profesor    NUMBER(8) NOT NULL,
    nombre_profesor VARCHAR2(100 CHAR) NOT NULL);
            
ALTER TABLE profesores ADD CONSTRAINT profesores_pk PRIMARY KEY ( cod_profesor );
                
CREATE TABLE seccion (
    id_seccion NUMBER(10) NOT NULL);
            
ALTER TABLE seccion ADD CONSTRAINT seccion_pk PRIMARY KEY ( id_seccion );
            
CREATE TABLE valoraciones (
    id_valoraciones NUMBER(10) NOT NULL,
    comentario      VARCHAR2(500 CHAR),
    id_alumnos      NUMBER(8) NOT NULL,
    puntaje         NUMBER(2, 1),
    cod_profesor    NUMBER(8) NOT NULL);
            
ALTER TABLE valoraciones ADD CONSTRAINT valoraciones_pk PRIMARY KEY ( id_valoraciones );
CREATE SEQUENCE seq_id_valoraciones START WITH 1 INCREMENT BY 1;
ALTER TABLE valoraciones MODIFY id_valoraciones DEFAULT seq_id_valoraciones.NEXTVAL;
            
ALTER TABLE alumnos
    ADD CONSTRAINT alumnos_carreras_fk FOREIGN KEY ( id_carrera )
    REFERENCES carreras ( id_carrera );
            
ALTER TABLE carreras
    ADD CONSTRAINT carreras_facultades_fk FOREIGN KEY ( id_facultad )
    REFERENCES facultades ( id_facultad );
            
ALTER TABLE carreras
    ADD CONSTRAINT carreras_plan_estudios_fk FOREIGN KEY ( id_plan_estudios )
    REFERENCES plan_estudios ( id_plan_estudios );
            
ALTER TABLE cursos_alumnos
    ADD CONSTRAINT cursos_alumnos_alumnos_fk FOREIGN KEY ( id_alumnos )
    REFERENCES alumnos ( id_alumnos );
            
ALTER TABLE cursos_alumnos
    ADD CONSTRAINT cursos_alumnos_ciclo_fk FOREIGN KEY ( id_ciclo )
    REFERENCES ciclo ( id_ciclo );
            
ALTER TABLE cursos_alumnos
    ADD CONSTRAINT cursos_alumnos_cursos_fk FOREIGN KEY ( cod_curso )
    REFERENCES cursos ( cod_curso );
            
ALTER TABLE cursos_alumnos
    ADD CONSTRAINT cursos_alumnos_seccion_fk FOREIGN KEY ( id_seccion )
    REFERENCES seccion ( id_seccion );
            
ALTER TABLE cursos
    ADD CONSTRAINT cursos_caracter_fk FOREIGN KEY ( id_caracter )
    REFERENCES caracter ( id_caracter );
                    
ALTER TABLE cursos
    ADD CONSTRAINT cursos_CURSO1_fk FOREIGN KEY ( COD_CURSO1 )
    REFERENCES CURSOS ( COD_CURSO);
            
ALTER TABLE cursos
    ADD CONSTRAINT cursos_CURSO2_fk FOREIGN KEY ( COD_CURSO2 )
    REFERENCES CURSOS ( COD_CURSO);
            
ALTER TABLE cursos
    ADD CONSTRAINT cursos_niveles_fk FOREIGN KEY ( id_nivel )
    REFERENCES niveles ( id_nivel );
            
ALTER TABLE cursos
    ADD CONSTRAINT cursos_plan_estudios_fk FOREIGN KEY ( id_plan_estudios )
    REFERENCES plan_estudios ( id_plan_estudios );
            
ALTER TABLE plan_estudios
    ADD CONSTRAINT plan_estudios_carreras_fk FOREIGN KEY ( id_carrera )
    REFERENCES carreras ( id_carrera );
            
ALTER TABLE prof_cursos
    ADD CONSTRAINT prof_cursos_cursos_fk FOREIGN KEY ( cod_curso )
    REFERENCES cursos ( cod_curso );
            
ALTER TABLE prof_cursos
    ADD CONSTRAINT prof_cursos_profesores_fk FOREIGN KEY ( cod_profesor )
    REFERENCES profesores ( cod_profesor );
            
ALTER TABLE valoraciones
    ADD CONSTRAINT valoraciones_alumnos_fk FOREIGN KEY ( id_alumnos )
    REFERENCES alumnos ( id_alumnos );
            
ALTER TABLE valoraciones
    ADD CONSTRAINT valoraciones_profesores_fk FOREIGN KEY ( cod_profesor )
    REFERENCES profesores ( cod_profesor );
