package com.codecrafters.todoapp.students;

import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.util.Assert;

class TaskFilters {

  private TaskFilters() {}

  /**
   * Creates a Bson equality filter that matches the given student ID with the student ID collection
   * field
   *
   * @param taskID the ID of the task to be used in the filter
   * @return the Bson equality filter
   */
  static Bson equalIDFilter(ObjectId taskID) {
    Assert.notNull(taskID, "The task ID must not be null");
    return Filters.eq(TaskCollectionFields.ID, taskID);
  }
}
