package com.codecrafters.todoapp.students;

import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;
import org.springframework.util.Assert;

class TaskUpdates {

  private TaskUpdates() {}

  /**
   * Updates all fields of a task in the database.
   *
   * @param task The task object containing updated fields
   * @return The updated Bson object with all task fields set
   */
  static Bson updateAllTaskFields(Task task) {
    Assert.notNull(task, "The task must not be null");

    return Updates.combine(
        Updates.set(TaskCollectionFields.TITLE, task.title()),
        Updates.set(TaskCollectionFields.DESCRIPTION, task.description()),
        Updates.set(TaskCollectionFields.DUE_DATE, task.dueDate()),
        Updates.set(TaskCollectionFields.CATEGORY, task.category()),
        Updates.set(TaskCollectionFields.COMPLETED, task.completed()),
        Updates.set(TaskCollectionFields.TAGS, task.tags()));
  }
}
