package com.codecrafters.todoapp.students;

import java.time.LocalDateTime;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

class TaskDocumentMapper {

  private TaskDocumentMapper() {}

  static Task documentAsTask(Document document) {

    return new Task(
        document.get(TaskCollectionFields.ID, ObjectId.class),
        document.get(TaskCollectionFields.TITLE, String.class),
        document.get(TaskCollectionFields.DESCRIPTION, String.class),
        document.get(TaskCollectionFields.DUE_DATE, LocalDateTime.class),
        document.get(TaskCollectionFields.CATEGORY, String.class),
        document.get(TaskCollectionFields.COMPLETED, Boolean.class),
        document.get(TaskCollectionFields.TAGS, List.class));
  }
}
