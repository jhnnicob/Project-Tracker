package com.projecttracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "todo")
public class Todo {
	
	public enum Priority {
		
		LOW {
			public String toString() {
				return "Low";
			}
		},
		
		MEDIUM {
			public String toString() {
				return "Medium";
			}
		},
		
		HIGH {
			public String toString() {
				return "Low";
			}
		}
	}
	
	public enum Status {
		ONTRACK {
			public String toString() {
				return "On Track";
			}
		},
		
		ATRISK {
			public String toString() {
				return "At Risk";
			}
		},
		
		OFFTRACK {
			public String toString() {
				return "Off Track";
			}
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "task_name")
	private String taskName;
	
	@Column(name = "assignee")
	private String assignee;
	
	@Column(name = "due_date")
	private String dueDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "priority")
	private Priority priority;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;

	public Long getId() {
		return id;
	}
	
	@ManyToOne
	@JoinColumn(name="project_id")
	@JsonBackReference 
	private Project project;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
}
