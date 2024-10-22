package com.adrian.service;

import com.adrian.dtos.NaveDTO;
import com.adrian.exception.NaveException;
import com.adrian.model.Nave;
import com.adrian.repository.NaveRepository;
import com.adrian.service.impl.NaveServiceImpl;
import com.adrian.utils.MapperUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class NaveServiceTest {
    @Mock
    private NaveRepository naveRepository;

    @Mock
    private MapperUtils mapperUtils;

    @InjectMocks
    private NaveService naveService = new NaveServiceImpl(naveRepository);

    @Test
    public void testCrearNave() {
        NaveDTO naveDTO = new NaveDTO();
        Nave mockNave = new Nave();

        when(MapperUtils.dtoToEntity(naveDTO)).thenReturn(mockNave);
        when(naveRepository.save(mockNave)).thenReturn(mockNave);

        NaveDTO createdNaveDTO = naveService.crearNave(naveDTO);

        assertEquals(naveDTO, createdNaveDTO);
    }

    @Test
    public void testModificarNave() {
        Long id = 1L;
        NaveDTO naveDTO = new NaveDTO();
        naveDTO.setIdNave(id);
        Nave mockNave = new Nave();
        mockNave.setIdNave(id);

        when(naveService.consultaPorId(id)).thenReturn(mockNave);
        // No need to mock mapperUtils.dtoToEntity as modification happens on existing entity
        when(naveRepository.save(mockNave)).thenReturn(mockNave);

        NaveDTO modifiedNaveDTO = naveService.modificarNave(naveDTO);

        assertEquals(naveDTO, modifiedNaveDTO);
    }

    @Test
    public void testEliminarNave() {
        Long id = 1L;
        Nave mockNave = new Nave();
        mockNave.setIdNave(id);

        when(naveService.consultaPorId(id)).thenReturn(mockNave);

        Long deletedId = naveService.eliminarNave(id);

        assertEquals(id, deletedId);
        verify(naveRepository).delete(mockNave);
    }

    @Test(expected = NaveException.class)
    public void testConsultaPorId_notFound() {
        Long id = 1L;

        when(naveRepository.findById(id)).thenReturn(Optional.empty());

        naveService.consultaPorId(id);
    }

    @Test
    public void testConsultaNavePorId() {
        Long id = 1L;
        NaveDTO mockNaveDTO = new NaveDTO();
        Nave mockNave = new Nave();

        when(naveService.consultaPorId(id)).thenReturn(mockNave);
        when(MapperUtils.entityToDto(mockNave)).thenReturn(mockNaveDTO);

        NaveDTO retrievedNaveDTO = naveService.consultaNavePorId(id);

        assertEquals(mockNaveDTO, retrievedNaveDTO);
    }

    @Test
    public void testConsultaPorNombre() {
        String nombre = "Enterprise";
        Pageable pageable = Pageable.unpaged();
        List<Nave> mockNaves = new ArrayList<>();
        Page<Nave> mockPage = new PageImpl<>(mockNaves);
        List<NaveDTO> mockNaveDTOs = mockNaves.stream()
                .map(nave -> new NaveDTO())
                .collect(Collectors.toList());
        Page<NaveDTO> expectedPage = new PageImpl<>(mockNaveDTOs);

        when(naveRepository.findByNombreContaining(nombre, pageable)).thenReturn(mockPage);
        when(MapperUtils.entityToDto(any(Nave.class))).thenReturn(new NaveDTO());

        Page<NaveDTO> actualPage = naveService.consultaPorNombre(nombre, pageable);

        assertEquals(expectedPage, actualPage);
    }

    @Test
    public void testConsultaTodos() {
        List<Nave> mockNaves = new ArrayList<>();
        List<NaveDTO> mockNaveDTOs = mockNaves.stream()
                .map(nave -> new NaveDTO())
                .collect(Collectors.toList());

        when(naveRepository.findAll()).thenReturn(mockNaves);
        when(MapperUtils.entityToDto(any(Nave.class))).thenReturn(new NaveDTO());

        Page<NaveDTO> actualNaveDTOs = naveService.consultaTodos();

        assertEquals(mockNaveDTOs, actualNaveDTOs);
    }
}
