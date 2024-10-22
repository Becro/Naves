package com.adrian.exception;

public class NaveException  extends RuntimeException{
    public NaveException(Long id){
        super("La nave con el id" + id+ "no se ha encontrado");
    }
}
