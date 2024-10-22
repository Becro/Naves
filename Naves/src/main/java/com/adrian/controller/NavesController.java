package com.adrian.controller;

import com.adrian.dtos.NaveDTO;
import com.adrian.service.NaveService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/naves")
public class NavesController {
    private NaveService naveService;

    @GetMapping("/consulta")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Page<NaveDTO>> consultaTodos() {
       Page<NaveDTO>navesDTO = this.naveService.consultaTodos();
        return ResponseEntity.ok(navesDTO);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    @Cacheable(value = "id", key = "#id")
    public ResponseEntity<NaveDTO> consultaPorId(@PathVariable Long id) {
        NaveDTO dto= this.naveService.consultaNavePorId(id);
        if(dto!=null){
            return ResponseEntity.ok(dto);
        }else{
            return (ResponseEntity<NaveDTO>) ResponseEntity.notFound();
        }
    }

    @GetMapping("/buscar/{nombre}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Page<NaveDTO>> buscarPorNombre(@PathVariable String nombre, Pageable pageable) {
        Page<NaveDTO> navesDTO = this.naveService.consultaPorNombre(nombre,pageable);
        return ResponseEntity.ok(navesDTO);
    }

    @PostMapping("/crearNave")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<NaveDTO> crearNave(@RequestBody NaveDTO naveDTO) {
        NaveDTO nave = this.naveService.crearNave(naveDTO);
        return ResponseEntity.created(URI.create("/api/naves/" + nave.getIdNave()))
                .body(nave);
    }

    @PutMapping("modificar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Cacheable(value = "naveDTO", key = "#naveDTO")
    public ResponseEntity<NaveDTO> modificar(@PathVariable Long id, @RequestBody NaveDTO naveDTO) {
        NaveDTO naveActualizada = this.naveService.modificarNave(naveDTO);
        if (naveActualizada != null) {
            return ResponseEntity.ok(naveActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        this.naveService.eliminarNave(id);
        return ResponseEntity.ok(id);
    }


}
