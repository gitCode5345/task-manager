package com.project.taskmanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "avatar_url", length = 2048)
    private String avatarUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "lead")
    private List<Project> leadProjects = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ProjectMember> memberships = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "assignee")
    private List<Issue> assignedIssues = new ArrayList<>();

    @OneToMany(mappedBy = "reporter")
    private List<Issue> reportedIssues = new ArrayList<>();

    @OneToMany(mappedBy = "uploadedBy")
    private List<Attachment> uploadedAttachments = new ArrayList<>();
}
