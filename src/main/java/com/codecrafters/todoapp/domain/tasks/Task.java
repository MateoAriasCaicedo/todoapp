package com.codecrafters.todoapp.domain.tasks;

import java.util.List;

import com.codecrafters.todoapp.db.collections.fields.TaskFields;
import org.bson.Document;
import org.bson.types.ObjectId;

public record Task(
    ObjectId id,
    String title,
    String description,
    String dueDate,
    String category,
    Boolean completed,
    List<String> tags) {

  static Task fromDocument(Document document) {
    return new Task(
        document.get(TaskFields.ID, ObjectId.class),
        document.get(TaskFields.TITLE, String.class),
        document.get(TaskFields.DESCRIPTION, String.class),
        document.get(TaskFields.DUE_DATE, String.class),
        document.get(TaskFields.CATEGORY, String.class),
        document.get(TaskFields.COMPLETED, Boolean.class),
        document.getList(TaskFields.TAGS, String.class));
  }

  TaskCreationDTO asCompletedTaskCreationDTO() {
    return new TaskCreationDTO(title, description, dueDate, category, completed, tags);
  }
}
