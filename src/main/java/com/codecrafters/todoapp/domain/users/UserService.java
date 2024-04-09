package com.codecrafters.todoapp.domain.users;

import org.bson.types.ObjectId;

public class UserService {

  ObjectId singIn(String username, String password)
      throws UserDoesNotExistsException, IncorrectPasswordException {
    return null; // TODO: implement this method.
  }

  ObjectId signUpUser(UserCreationDTO user) {
    return null; // TODO: implement this method.
  }
}
