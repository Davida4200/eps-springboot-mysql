package com.citas.apicitas.exception;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorMessage {
  private int statusCode;
  private Date timeStamp;
  private String message;
  private String description;

  public ErrorMessage(int statusCode, Date timeStamp, String message, String description){
    this.statusCode = statusCode;
    this.timeStamp = timeStamp;
    this.message = message;
    this.description = description;
  }
}
