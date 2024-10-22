package com.adrian.utils;

import org.springframework.stereotype.Component;
import com.adrian.model.Nave;
import com.adrian.dtos.NaveDTO;
@Component
public class MapperUtils {
    public static NaveDTO entityToDto(Nave nave) {
        NaveDTO dto = new NaveDTO();
        dto.setIdNave(nave.getIdNave());
        dto.setNombre(nave.getNombre());
        return dto;
    }

    public static Nave dtoToEntity(NaveDTO dto) {
        Nave nave = new Nave();
        nave.setIdNave(dto.getIdNave());
        nave.setNombre(dto.getNombre());
        return nave;
    }
}
