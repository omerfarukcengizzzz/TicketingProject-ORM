package com.cybertek.entity;

import com.cybertek.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted = false")
public class Project extends BaseEntity {

    private String projectName;

    @Column(unique = true)  // validation on database
    private String projectCode;

    private LocalDate startDate;
    private LocalDate endDate;

    private String projectDetails;
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private User assignedManager;

}
