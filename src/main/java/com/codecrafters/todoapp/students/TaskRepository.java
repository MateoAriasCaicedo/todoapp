package com.codecrafters.todoapp.students;

import com.codecrafters.todoapp.db.DBCollections;
import com.codecrafters.todoapp.db.DBNames;
import com.mongodb.client.*;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class TaskRepository {

  private final MongoClient client;

  public TaskRepository(MongoClient client) {
    this.client = client;
  }

  /**
   * Creates a task in the database.
   *
   * @param task The task to be created.
   */
  public void createTask(TaskCreationDTO task) {
    Assert.notNull(task, "The task must not be null");

    client
        .getDatabase(DBNames.TODO)
        .getCollection(DBCollections.TASKS)
        .insertOne(task.asMongoDocument());
  }

  /**
   * Updates all the task fields in the database
   *
   * @param task the task to update
   * @return true if the task was modified, otherwise false
   */
  public boolean updateTask(Task task) {
    Assert.notNull(task, "The task must not be null");

    var equalIDFilter = TaskFilters.equalIDFilter(task.id());

    var modifiedTasks =
        client
            .getDatabase(DBNames.TODO)
            .getCollection(DBCollections.TASKS)
            .updateOne(equalIDFilter, TaskUpdates.updateAllTaskFields(task))
            .getModifiedCount();

    /* Return true if one task was modified, otherwise no task was modified and therefore we return false(The ID is
    ensured to be unique) */
    return modifiedTasks == 1;
  }

  /**
   * Deletes a task from the database
   *
   * @param taskID the ID of the task to be deleted
   * @return true if the task existed and could be deleted, false otherwise
   */
  public boolean deleteTask(ObjectId taskID) {
    Assert.notNull(taskID, "The task ID must not be null");

    var deletedTasks =
        client
            .getDatabase(DBCollections.TASKS)
            .getCollection(DBCollections.TASKS)
            .deleteOne(TaskFilters.equalIDFilter(taskID))
            .getDeletedCount();

    /* Return true if one task was deleted, otherwise no task was deleted and, therefore, we return
    false(The ID is ensured to be unique) */
    return deletedTasks == 1;
  }

  /**
   * Retrieves all tasks from the database and converts them into a list of Task objects.
   *
   * @return List of Task objects containing all tasks
   */
  public List<Task> findAllTasks() {

    var allTasks = client.getDatabase(DBNames.TODO).getCollection(DBCollections.TASKS).find();

    List<Task> tasksList = new LinkedList<>();

    for (Document taskMongoDocument : allTasks) {
      tasksList.add(TaskDocumentMapper.documentAsTask(taskMongoDocument));
    }

    return tasksList;
  }
}
