package com.codecrafters.todoapp.domain.tasks;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "There is no user with the specified ID")
public class UserDoesNotExistsException extends RuntimeException {

  public UserDoesNotExistsException(ObjectId userID) {
    super("There is no user with ID: " + userID);
  }
}
