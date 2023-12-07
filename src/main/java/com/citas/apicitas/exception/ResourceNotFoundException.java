package com.citas.apicitas.exception;

public class ResourceNotFoundException extends RuntimeException{
  public ResourceNotFoundException(String msg){
    super(msg);
  }
}