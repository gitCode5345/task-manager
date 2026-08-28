package com.project.taskmanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.project.taskmanager.enums.IssuePriority;
import com.project.taskmanager.enums.IssueStatus;
import com.project.taskmanager.enums.IssueType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(
        name = "issues",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "issue_number_project_id",
                        columnNames = {"project_id", "issue_number"}
                )
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "issue_number", nullable = false)
    private Integer issueNumber;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IssueType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IssueStatus status;

    @Enumerated(EnumType.STRING)
    private IssuePriority priority;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "parent_issue_id")
    private Issue parentIssue;

    @ManyToMany
    @JoinTable(
            name = "issue_labels",
            joinColumns = @JoinColumn(name = "issue_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private Set<Label> labels = new HashSet<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @OneToMany(mappedBy = "issue")
    private List<Comment> comments = new ArrayList<>();
}
