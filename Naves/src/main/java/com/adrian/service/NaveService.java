package com.adrian.service;


import com.adrian.dtos.NaveDTO;
import com.adrian.model.Nave;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NaveService {

     NaveDTO crearNave(NaveDTO nave);
     Nave alta(Nave nave);
     NaveDTO modificarNave(NaveDTO nave);
     Nave modificarNave(Nave nave);
	 Long eliminarNave(Long id);

     Nave consultaPorId(Long id);

     NaveDTO consultaNavePorId(Long idNave);

     Page<NaveDTO> consultaPorNombre(String nombre, Pageable pageable);
     Page<NaveDTO>consultaTodos();
	
	
}
