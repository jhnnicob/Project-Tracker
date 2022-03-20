package com.projecttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projecttracker.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
