package com.codecrafters.todoapp.students;

import java.time.LocalDateTime;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public record TaskCreationDTO(
    String title,
    String description,
    LocalDateTime dueDate,
    String category,
    Boolean completed,
    List<String> tags) {

  public static TaskCreationDTO fromTask(Task task) {
    return new TaskCreationDTO(
        task.title(),
        task.description(),
        task.dueDate(),
        task.category(),
        task.completed(),
        task.tags());
  }

  public Document asMongoDocument() {
    return new Document()
        .append("title", title)
        .append("description", description)
        .append("dueDate", dueDate)
        .append("category", category)
        .append("completed", completed)
        .append("tags", tags);
  }

  public Task withId(ObjectId id) {
    return new Task(id, title, description, dueDate, category, completed, tags);
  }
}
