package com.adrian.controller;

import com.adrian.dtos.NaveDTO;
import com.adrian.service.NaveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class NavesControllerTest {

    @InjectMocks
    private NavesController navesController;

    @Mock
    private NaveService naveService;

    private NaveDTO mockNaveDTO;
    private Page<NaveDTO> mockPage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        mockNaveDTO = new NaveDTO();
        mockNaveDTO.setIdNave(1L);
        mockNaveDTO.setNombre("Nave Test");

        List<NaveDTO> listaNaves = new ArrayList<>();
        listaNaves.add(mockNaveDTO);
        mockPage = new PageImpl<>(listaNaves);
    }

    @Test
    public void testConsultaTodos() {

        when(naveService.consultaTodos()).thenReturn(mockPage);
        ResponseEntity<Page<NaveDTO>> response = navesController.consultaTodos();
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockPage, response.getBody());
    }

    @Test
    public void testConsultaPorIdExistente() {

        when(naveService.consultaNavePorId(1L)).thenReturn(mockNaveDTO);
        ResponseEntity<NaveDTO> response = navesController.consultaPorId(1L);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockNaveDTO, response.getBody());
    }

    @Test
    public void testConsultaPorIdNoExistente() {
        when(naveService.consultaNavePorId(2L)).thenReturn(null);
        ResponseEntity<NaveDTO> response = navesController.consultaPorId(2L);
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testBuscarPorNombre() {

        when(naveService.consultaPorNombre("Enterprise", Pageable.unpaged())).thenReturn(mockPage);
        ResponseEntity<Page<NaveDTO>> response = navesController.buscarPorNombre("Enterprise", Pageable.unpaged());
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockPage, response.getBody());
    }

    @Test
    public void testCrearNave() {
        when(naveService.crearNave(mockNaveDTO)).thenReturn(mockNaveDTO);
        ResponseEntity<NaveDTO> response = navesController.crearNave(mockNaveDTO);
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("/api/naves/1", response.getHeaders().getLocation().toString());
        assertEquals(mockNaveDTO, response.getBody());
    }

    @Test
    public void testModificar() {
        when(naveService.modificarNave(mockNaveDTO)).thenReturn(null);
        ResponseEntity<NaveDTO> response = navesController.modificar(1L, mockNaveDTO);
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        verify(naveService, times(1)).modificarNave(mockNaveDTO);
    }
    @Test
    public void testDelete() {
        ResponseEntity<Long> response = navesController.delete(1L);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().longValue());
        verify(naveService, times(1)).eliminarNave(1L);
    }

}
