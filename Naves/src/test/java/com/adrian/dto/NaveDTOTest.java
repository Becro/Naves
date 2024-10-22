package com.adrian.dto;

import com.adrian.dtos.NaveDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class NaveDTOTest {

    @Test
    public void testGetterSetter() {
        NaveDTO dto = new NaveDTO();

        dto.setIdNave(1L);
        dto.setNombre("Enterprise");
        assertEquals(Long.valueOf(1), dto.getIdNave());
        assertEquals("Enterprise", dto.getNombre());
    }
}
