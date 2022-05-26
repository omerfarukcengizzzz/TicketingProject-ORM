package com.cybertek.repository;

import com.cybertek.entity.Project;
import com.cybertek.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "select count(t) from Task t where t.project.projectCode = ?1 and t.status = 'COMPLETED'")
    Integer totalCompletedTasks(String projectCode);

    @Query(value = "select count(*) from tasks t join projects p on t.project_id = p.id where p.project_code = ?1 and t.status != 'COMPLETED'",
            nativeQuery = true)
    Integer totalNonCompletedTasks(String projectCode);

    List<Task> findAllByProjectId(Project project);

}
