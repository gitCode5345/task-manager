package com.project.taskmanager.repository;

import com.project.taskmanager.entity.ProjectMember;
import com.project.taskmanager.entity.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {

}
