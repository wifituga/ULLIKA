create or replace TRIGGER TRG_ALUMNOS
BEFORE INSERT OR UPDATE ON ALUMNOS
FOR EACH ROW
DECLARE
    OBLIGATORIO EXCEPTION;
    IDL EXCEPTION;
BEGIN
    IF :NEW.ID_ALUMNOS IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_ALUMNOS) <> 8 THEN
        RAISE IDL;
    ELSIF :NEW.NOMBRE_ALUMNO IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF :NEW.ID_CARRERA IS NULL THEN
        RAISE OBLIGATORIO;
    END IF;
EXCEPTION
    WHEN OBLIGATORIO THEN
        raise_application_error(-20001, 'El campo es obligatorio');
    WHEN IDL THEN
        raise_application_error(-20002, 'El campo "ID_ALUMNOS" debe ser de longitud 8');
    WHEN OTHERS THEN
        NULL;
END;

create or replace TRIGGER TRG_CARACTER
BEFORE INSERT OR UPDATE ON CARACTER
FOR EACH ROW
DECLARE
    OBLIGATORIO EXCEPTION;
    IDL EXCEPTION;
    IDL2 EXCEPTION;
BEGIN
    IF :NEW.ID_CARACTER IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_CARACTER) <> 1 THEN
        RAISE IDL;
    ELSIF :NEW.NOMBRE_CARACTER IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.NOMBRE_CARACTER) > 20 THEN
        RAISE IDL2;
    END IF;
EXCEPTION
    WHEN OBLIGATORIO THEN
        raise_application_error(-20001, 'El campo es obligatorio');
    WHEN IDL THEN
        raise_application_error(-20002, 'El campo "ID_CARACTER" debe tener una longitud de 1');
    WHEN IDL2 THEN
        raise_application_error(-20003, 'El campo "NOMBRE_CARACTER" debe tener una longitud máxima de 20 caracteres');
END;

create or replace TRIGGER TRG_CARRERA
BEFORE INSERT OR UPDATE ON CARRERAS
FOR EACH ROW
DECLARE
    OBLIGATORIO EXCEPTION;
    IDL EXCEPTION;
    IDL2 EXCEPTION;
    IDL3 EXCEPTION;
    IDL4 EXCEPTION;
BEGIN
    IF :NEW.ID_CARRERA IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_CARRERA) > 2 THEN
        RAISE IDL;
    ELSIF :NEW.NOMBRE_CARRERA IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.NOMBRE_CARRERA) > 100 THEN
        RAISE IDL2;
    ELSIF :NEW.ID_FACULTAD IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_FACULTAD) > 10 THEN
        RAISE IDL3;
    ELSIF :NEW.ID_PLAN_ESTUDIOS IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_PLAN_ESTUDIOS) > 10 THEN
        RAISE IDL4;
    END IF;
EXCEPTION
    WHEN OBLIGATORIO THEN
        raise_application_error(-20001, 'El campo es obligatorio');
    WHEN IDL THEN
        raise_application_error(-20002, 'El campo "ID_CARRERA" debe tener una longitud máxima de 2');
    WHEN IDL2 THEN
        raise_application_error(-20003, 'El campo "NOMBRE_CARRERA" debe tener una longitud máxima de 100 caracteres');
    WHEN IDL3 THEN
        raise_application_error(-20004, 'El campo "ID_FACULTAD" debe tener una longitud máxima de 10');
    WHEN IDL4 THEN
        raise_application_error(-20005, 'El campo "ID_PLAN_ESTUDIOS" debe tener una longitud máxima de 10');
END;

create or replace TRIGGER TRG_CICLO
BEFORE INSERT OR UPDATE ON CICLO
FOR EACH ROW
DECLARE
    OBLIGATORIO EXCEPTION;
    IDL EXCEPTION;
BEGIN
    IF :NEW.ID_CICLO IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_CICLO) > 20 THEN
        RAISE IDL;
    END IF;
EXCEPTION
    WHEN OBLIGATORIO THEN
        raise_application_error(-20001, 'El campo es obligatorio');
    WHEN IDL THEN
        raise_application_error(-20002, 'El campo "ID_CICLO" debe tener una longitud de máxima de 20');
END;

create or replace TRIGGER TRG_CURS_ALUM
BEFORE INSERT OR UPDATE ON CURSOS_ALUMNOS
FOR EACH ROW
DECLARE
    OBLIGATORIO EXCEPTION;
    IDL EXCEPTION;
    IDL2 EXCEPTION;
    IDL3 EXCEPTION;
    IDL4 EXCEPTION;
    IDL5 EXCEPTION;
BEGIN
    IF :NEW.ID_CURSOS_ALUMNOS IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_CURSOS_ALUMNOS) > 20 THEN
        RAISE IDL;
    ELSIF :NEW.ID_ALUMNOS IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_ALUMNOS) <> 8 THEN
        RAISE IDL2;
    ELSIF :NEW.COD_CURSO IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.COD_CURSO) > 10 THEN
        RAISE IDL3;
    ELSIF :NEW.ID_CICLO IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_CICLO) > 20 THEN
        RAISE IDL4;
    ELSIF :NEW.ID_SECCION IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_SECCION) > 10 THEN
        RAISE IDL5;
    END IF;
EXCEPTION
    WHEN OBLIGATORIO THEN
        raise_application_error(-20001, 'El campo es obligatorio');
    WHEN IDL THEN
        raise_application_error(-20002, 'El campo "ID_CURSOS_ALUMNOS" debe tener una longitud de máxima de 20');
    WHEN IDL2 THEN
        raise_application_error(-20003, 'El campo "ID_ALUMNOS" debe tener una longitud de 8');
    WHEN IDL3 THEN
        raise_application_error(-20004, 'El campo "COD_CURSO" debe tener una longitud máxima de 10');
    WHEN IDL4 THEN
        raise_application_error(-20005, 'El campo "ID_CICLO" debe tener una longitud de máxima de 20');
    WHEN IDL5 THEN
        raise_application_error(-20006, 'El campo "ID_SECCION" debe tener una longitud máxima de 10');
END;

create or replace TRIGGER TRG_CURSOS
BEFORE INSERT OR UPDATE ON CURSOS
FOR EACH ROW
DECLARE
    OBLIGATORIO EXCEPTION;
    IDL EXCEPTION;
    IDL2 EXCEPTION;
    IDL3 EXCEPTION;
    IDL4 EXCEPTION;
    IDL5 EXCEPTION;
BEGIN
    IF :NEW.COD_CURSO IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.COD_CURSO) > 10 THEN
        RAISE IDL;
    ELSIF :NEW.NOMBRE_CURSO IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.NOMBRE_CURSO) > 100 THEN
        RAISE IDL2;
    ELSIF :NEW.ID_CARACTER IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_CARACTER) <> 1 THEN
        RAISE IDL3;
    ELSIF :NEW.ID_NIVEL IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_NIVEL) > 2 THEN
        RAISE IDL4;
    ELSIF :NEW.ID_PLAN_ESTUDIOS IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_PLAN_ESTUDIOS) > 10 THEN
        RAISE IDL5;
    END IF;
EXCEPTION
    WHEN OBLIGATORIO THEN
        raise_application_error(-20001, 'El campo es obligatorio');
    WHEN IDL THEN
        raise_application_error(-20002, 'El campo "COD_CURSO" debe tener una longitud de máxima de 10');
    WHEN IDL2 THEN
        raise_application_error(-20003, 'El campo "NOMBRE_CURSO" debe tener una longitud máxima de 100 caracteres');
    WHEN IDL3 THEN
        raise_application_error(-20004, 'El campo "ID_CARACTER" debe tener una longitud de 1');
    WHEN IDL4 THEN
        raise_application_error(-20005, 'El campo "ID_NIVEL" debe tener una longitud de máxima de 2');
    WHEN IDL5 THEN
        raise_application_error(-20006, 'El campo "ID_PLAN_ESTUDIOS" debe tener una longitud máxima de 10');
END;

create or replace TRIGGER TRG_FACU
BEFORE INSERT OR UPDATE ON FACULTADES
FOR EACH ROW
DECLARE
    OBLIGATORIO EXCEPTION;
    IDL EXCEPTION;
    IDL2 EXCEPTION;
BEGIN
    IF :NEW.ID_FACULTAD IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_FACULTAD) > 10 THEN
        RAISE IDL;
    ELSIF :NEW.NOMBRE_FACULTAD IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.NOMBRE_FACULTAD) > 100 THEN
        RAISE IDL2;
    END IF;
EXCEPTION
    WHEN OBLIGATORIO THEN
        raise_application_error(-20001, 'El campo es obligatorio');
    WHEN IDL THEN
        raise_application_error(-20002, 'El campo "ID_FACULTAD" debe tener una longitud máxima de 10');
    WHEN IDL2 THEN
        raise_application_error(-20003, 'El campo "NOMBRE_FACULTAD" debe tener una longitud máxima de 100 caracteres');
END;

create or replace TRIGGER TRG_NIVELES
BEFORE INSERT OR UPDATE ON NIVELES
FOR EACH ROW
DECLARE
    OBLIGATORIO EXCEPTION;
    IDL EXCEPTION;
    IDL2 EXCEPTION;
BEGIN
    IF :NEW.ID_NIVEL IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_NIVEL) > 2 THEN
        RAISE IDL;
    ELSIF :NEW.NOMBRE_NIVEL IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.NOMBRE_NIVEL) > 50 THEN
        RAISE IDL2;
    END IF;
EXCEPTION
    WHEN OBLIGATORIO THEN
        raise_application_error(-20001, 'El campo es obligatorio');
    WHEN IDL THEN
        raise_application_error(-20002, 'El campo "ID_NIVEL" debe tener una longitud de máxima de 2');
    WHEN IDL2 THEN
        raise_application_error(-20003, 'El campo "NOMBRE_NIVEL" debe tener una longitud máxima de 50 caracteres');
END;

create or replace TRIGGER TRG_PLAN
BEFORE INSERT OR UPDATE ON PLAN_ESTUDIOS
FOR EACH ROW
DECLARE
    OBLIGATORIO EXCEPTION;
    IDL EXCEPTION;
    IDL2 EXCEPTION;
    IDL3 EXCEPTION;
    IDL4 EXCEPTION;
BEGIN
    IF :NEW.ID_PLAN_ESTUDIOS IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_PLAN_ESTUDIOS) > 10 THEN
        RAISE IDL;
    ELSIF :NEW.NOMBRE_PLAN_ESTUDIOS IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.NOMBRE_PLAN_ESTUDIOS) > 100 THEN
        RAISE IDL2;
    ELSIF :NEW.ID_CARRERA IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_CARRERA) > 2 THEN
        RAISE IDL3;
    END IF;
EXCEPTION
    WHEN OBLIGATORIO THEN
        raise_application_error(-20001, 'El campo es obligatorio');
    WHEN IDL THEN
        raise_application_error(-20002, 'El campo "ID_PLAN_ESTUDIOS" debe tener una longitud máxima de 10');
    WHEN IDL2 THEN
        raise_application_error(-20003, 'El campo "NOMBRE_PLAN_ESTUDIOS" debe tener una longitud máxima de 100 caracteres');
    WHEN IDL3 THEN
        raise_application_error(-20004, 'El campo "ID_CARRERA" debe tener una longitud máxima de 2');
END;

create or replace TRIGGER TRG_PROFESOR
BEFORE INSERT OR UPDATE ON PROFESORES
FOR EACH ROW
DECLARE
    OBLIGATORIO EXCEPTION;
    IDL EXCEPTION;
    IDL2 EXCEPTION;
    IDL3 EXCEPTION;
    IDL4 EXCEPTION;
BEGIN
    IF :NEW.COD_PROFESOR IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.COD_PROFESOR) <> 8 THEN
        RAISE IDL;
    ELSIF :NEW.NOMBRE_PROFESOR IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.NOMBRE_PROFESOR) > 100 THEN
        RAISE IDL2;
    END IF;
EXCEPTION
    WHEN OBLIGATORIO THEN
        raise_application_error(-20001, 'El campo es obligatorio');
    WHEN IDL THEN
        raise_application_error(-20002, 'El campo "COD_PROFESOR" debe tener una longitud de 8');
    WHEN IDL2 THEN
        raise_application_error(-20003, 'El campo "NOMBRE_PROFESOR" debe tener una longitud máxima de 100 caracteres');
END;

create or replace TRIGGER TRG_PROF_CURSOS
BEFORE INSERT OR UPDATE ON PROF_CURSOS
FOR EACH ROW
DECLARE
    OBLIGATORIO EXCEPTION;
    IDL EXCEPTION;
    IDL2 EXCEPTION;
    IDL3 EXCEPTION;
BEGIN
    IF :NEW.ID_PROF_CURSOS IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_PROF_CURSOS) > 10 THEN
        RAISE IDL;
    ELSIF :NEW.COD_PROFESOR IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.COD_PROFESOR) <> 8 THEN
        RAISE IDL2;
    ELSIF :NEW.COD_CURSO IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.COD_CURSO) > 10 THEN
        RAISE IDL3;
    END IF;
EXCEPTION
    WHEN OBLIGATORIO THEN
        raise_application_error(-20001, 'El campo es obligatorio');
    WHEN IDL THEN
        raise_application_error(-20002, 'El campo "ID_PROF_CURSOS" debe tener una longitud de máxima de 10');
    WHEN IDL2 THEN
        raise_application_error(-20003, 'El campo "COD_PROFESOR" debe tener una longitud de 8');
    WHEN IDL3 THEN
        raise_application_error(-20004, 'El campo "COD_CURSO" debe tener una longitud máxima de 10');
END;

create or replace TRIGGER TRG_SECCION
BEFORE INSERT OR UPDATE ON SECCION
FOR EACH ROW
DECLARE
    OBLIGATORIO EXCEPTION;
    IDL EXCEPTION;
BEGIN
    IF :NEW.ID_SECCION IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_SECCION) > 4 THEN
        RAISE IDL;
    END IF;
EXCEPTION
    WHEN OBLIGATORIO THEN
        raise_application_error(-20001, 'El campo es obligatorio');
    WHEN IDL THEN
        raise_application_error(-20002, 'El campo "ID_SECCION" debe tener una longitud de máxima de 4');
END;

create or replace TRIGGER TRG_VALORACIONES
BEFORE INSERT OR UPDATE ON VALORACIONES
FOR EACH ROW
DECLARE
    OBLIGATORIO EXCEPTION;
    IDL EXCEPTION;
    IDL2 EXCEPTION;
    IDL3 EXCEPTION;
    IDL4 EXCEPTION;
    IDL5 EXCEPTION;
    IDL6 EXCEPTION;
BEGIN
    IF :NEW.ID_VALORACIONES IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_VALORACIONES) > 10 THEN
        RAISE IDL;
    ELSIF :NEW.COMENTARIO IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.COMENTARIO) > 500 THEN
        RAISE IDL2;
    ELSIF :NEW.ID_ALUMNOS IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.ID_ALUMNOS) <> 8 THEN
        RAISE IDL3;
    ELSIF :NEW.COD_PROFESOR IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.COD_PROFESOR) <> 8 THEN
        RAISE IDL4;
    ELSIF :NEW.PUNTAJE IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.PUNTAJE) > 1 THEN
        RAISE IDL5;
    ELSIF :NEW.COD_CURSO IS NULL THEN
        RAISE OBLIGATORIO;
    ELSIF LENGTH(:NEW.COD_CURSO) > 10 THEN
        RAISE IDL6;
    END IF;
EXCEPTION
    WHEN OBLIGATORIO THEN
        raise_application_error(-20001, 'El campo es obligatorio');
    WHEN IDL THEN
        raise_application_error(-20002, 'El campo "ID_VALORACIONES" debe tener una longitud de máxima de 10');
    WHEN IDL2 THEN
        raise_application_error(-20003, 'El campo "COMENTARIO" debe tener una longitud máxima de 500 caracteres');
    WHEN IDL3 THEN
        raise_application_error(-20004, 'El campo "ID_ALUMNOS" debe tener una longitud de 8');
    WHEN IDL4 THEN
        raise_application_error(-20005, 'El campo "COD_PROFESOR" debe tener una longitud de 8');
    WHEN IDL5 THEN
        raise_application_error(-20006, 'El campo "PUNTAJE" debe tener una longitud máxima de 1');
    WHEN IDL6 THEN
        raise_application_error(-20007, 'El campo "COD_CURSO" debe tener una longitud máxima de 10');
END;