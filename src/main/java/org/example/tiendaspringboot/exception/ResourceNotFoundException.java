package org.example.tiendaspringboot.exception;


public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String resourceName, Integer id){
        super(String.format("%s no encontrado con ID: %d", resourceName, id));
    }

}
