package com.adrian.service.impl;

import com.adrian.dtos.NaveDTO;
import com.adrian.exception.NaveException;
import com.adrian.model.Nave;
import com.adrian.repository.NaveRepository;
import com.adrian.service.NaveService;
import com.adrian.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;


public class NaveServiceImpl implements NaveService {

    private final NaveRepository naveRepository;

    @Autowired
    public NaveServiceImpl(NaveRepository naveRepository) {
        this.naveRepository = naveRepository;
    }

    @Override
    public NaveDTO crearNave(NaveDTO nave) {
      alta(MapperUtils.dtoToEntity(nave));
      return nave;
    }

    @Override
    public Nave alta(Nave nave) {
        return this.naveRepository.save(nave);
    }

    @Override
    public NaveDTO modificarNave(NaveDTO nave) {
        Nave naveEntity= this.consultaPorId(nave.getIdNave());
        this.modificarNave(naveEntity);
        return nave;
    }

    @Override
    public Nave modificarNave(Nave nave) {
       return this.naveRepository.save(nave);
    }

    @Override
    public Long eliminarNave(Long id) {
        Nave nave = this.consultaPorId(id);
        this.naveRepository.delete(nave);
        return id;
    }

    @Override
    public Nave consultaPorId(Long id) {
        return naveRepository.findById(id)
                .orElseThrow(() -> new NaveException(id));
    }

    @Override
    public NaveDTO consultaNavePorId(Long idNave) {
        return MapperUtils.entityToDto(this.consultaPorId(idNave));
    }

    @Override
    public Page<NaveDTO> consultaPorNombre(String nombre, Pageable pageable) {
        Page<Nave> naves = naveRepository.findByNombreContaining(nombre, pageable);
        return naves.map(nave -> MapperUtils.entityToDto(nave));
    }

    @Override
    public Page<NaveDTO> consultaTodos() {
        Page<Nave> naves= (Page<Nave>) this.naveRepository.findAll();
        return naves.map(nave -> MapperUtils.entityToDto(nave));
    }
}
