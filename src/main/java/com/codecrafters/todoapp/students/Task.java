package com.codecrafters.todoapp.students;

import java.time.LocalDateTime;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

public record Task(
    ObjectId id,
    String title,
    String description,
    LocalDateTime dueDate,
    String category,
    Boolean completed,
    List<String> tags) {



  public Document asDocument() {
    return new Document()
        .append("id", id)
        .append("title", title)
        .append("description", description)
        .append("dueDate", dueDate)
        .append("category", category)
        .append("completed", completed)
        .append("tags", tags);
  }
}
