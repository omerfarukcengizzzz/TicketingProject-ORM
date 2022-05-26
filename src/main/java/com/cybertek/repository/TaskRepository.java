package com.cybertek.repository;

import com.cybertek.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "select count(t) from Task t where t.project.projectCode = ?1 and t.status = 'COMPLETED'")
    Integer totalCompletedTasks(String projectCode);

    @Query(value = "select count(t) from Task t where t.project.projectCode = ?1 and t.status <> 'COMPLETED'")
    Integer totalNonCompletedTasks(String projectCode);

}