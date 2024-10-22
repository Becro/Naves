package com.adrian.model;

import jakarta.persistence.*;
import lombok.NonNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
public class NaveTest {

    @Test
    public void testEntityAnnotation() {
        // Assert that the class is annotated with @Entity
        assertTrue(Nave.class.isAnnotationPresent(Entity.class));
    }

    @Test
    public void testIdField() {
        Field idField = null;
        try {
            idField = Nave.class.getDeclaredField("idNave");
        } catch (NoSuchFieldException e) {
            fail("No existe el campo 'idNave'");
        }

        // Assert that the id field is annotated with @Id and @GeneratedValue
        assertTrue(idField.isAnnotationPresent(Id.class));
        assertTrue(idField.isAnnotationPresent(GeneratedValue.class));

        GeneratedValue generationAnnotation = idField.getAnnotation(GeneratedValue.class);
        assertEquals(GenerationType.IDENTITY, generationAnnotation.strategy());
    }

    @Test
    public void testNombreField() {
        Field nombreField = null;
        try {
            nombreField = Nave.class.getDeclaredField("nombre");
        } catch (NoSuchFieldException e) {
            fail("No field named 'nombre' found in Nave class");
        }

        // Assert that the nombre field is annotated with @Column and @NonNull
        assertTrue(nombreField.isAnnotationPresent(Column.class));
        assertTrue(nombreField.isAnnotationPresent(NonNull.class));

        Column columnAnnotation = nombreField.getAnnotation(Column.class);
        assertEquals("nombre", columnAnnotation.name());
    }
}

