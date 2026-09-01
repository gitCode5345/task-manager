package com.project.taskmanager.repository;

import com.project.taskmanager.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {

}
