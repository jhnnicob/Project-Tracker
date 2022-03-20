package com.projecttracker.model.constant;

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