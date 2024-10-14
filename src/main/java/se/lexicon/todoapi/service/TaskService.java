package se.lexicon.todoapi.service;

import se.lexicon.todoapi.domain.dto.TaskDTOForm;
import se.lexicon.todoapi.domain.dto.TaskDTOView;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    //todo: create
    TaskDTOView create(TaskDTOForm taskDTOForm);
    // findById
    TaskDTOView findById(Long taskId);
    // UPDATE
    void update(TaskDTOForm taskDTOForm);
    // delete
    void delete(Long id);
    // findTasksByPersonId
    List<TaskDTOView> findTasksByPersonId(Long personId);
    // find TasksBetweenStartAndEndDate
    List<TaskDTOView> findTasksBetweenStartAndEndDate(LocalDate start, LocalDate end);
    // findAllUnassignedTasks
    List<TaskDTOView> findAllUnassignedTodoItems();
    // findAllUnfinishedAndOverdueTasks
    List<TaskDTOView> findAllUnfinishedAndOverdue();

    TaskDTOView addTaskToPerson(Long personId, TaskDTOForm taskDTOForm);
}
