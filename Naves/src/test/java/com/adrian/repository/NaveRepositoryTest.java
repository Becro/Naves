package com.adrian.repository;

import com.adrian.model.Nave;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class NaveRepositoryTest {

    @Mock
    private NaveRepository naveRepository;

    @Test
    public void testFindAll() {
        Pageable pageable = Pageable.unpaged();
        List<Nave> mockNaves = new ArrayList<>();
        mockNaves.add(new Nave());
        mockNaves.add(new Nave());
        Page<Nave> mockPage = new PageImpl<>(mockNaves);

        // Mock repository behavior
        when(naveRepository.findAll(pageable)).thenReturn(mockPage);

        // Call the repository method
        Page<Nave> naves = naveRepository.findAll(pageable);

        // Assert the results
        assertEquals(mockPage, naves);
    }

    @Test
    public void testFindById_success() {
        Long id = 1L;
        Nave mockNave = new Nave();

        when(naveRepository.findById(id)).thenReturn(Optional.of(mockNave));

        Optional<Nave> optionalNave = naveRepository.findById(id);

        assertTrue(optionalNave.isPresent());
        assertEquals(mockNave, optionalNave.get());
    }

    @Test
    public void testFindById_notFound() {
        Long id = 1L;

        when(naveRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Nave> optionalNave = naveRepository.findById(id);

        assertFalse(optionalNave.isPresent());
    }

    @Test
    public void testFindByNombreContaining() {
        String nombre = "Enterprise";
        Pageable pageable = Pageable.unpaged();
        List<Nave> mockNaves = new ArrayList<>();
        mockNaves.add(new Nave());
        Page<Nave> mockPage = new PageImpl<>(mockNaves);

        when(naveRepository.findByNombreContaining(nombre, pageable)).thenReturn(mockPage);

        Page<Nave> naves = naveRepository.findByNombreContaining(nombre, pageable);

        assertEquals(mockPage, naves);
    }
}
