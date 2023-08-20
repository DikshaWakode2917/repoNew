package com.successStory.main.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Banner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int banner_id;
	
	private String banner_heading;
	private String banner_text;
	private String button_text;
	private String button_url;
	private boolean status;
	
	public Banner() {
		super();
	}

	public Banner(int banner_id, String banner_heading, String banner_text, String button_text, String button_url,
			boolean status) {
		super();
		this.banner_id = banner_id;
		this.banner_heading = banner_heading;
		this.banner_text = banner_text;
		this.button_text = button_text;
		this.button_url = button_url;
		this.status = status;
	}

	public int getBanner_id() {
		return banner_id;
	}

	public void setBanner_id(int banner_id) {
		this.banner_id = banner_id;
	}

	public String getBanner_heading() {
		return banner_heading;
	}

	public void setBanner_heading(String banner_heading) {
		this.banner_heading = banner_heading;
	}

	public String getBanner_text() {
		return banner_text;
	}

	public void setBanner_text(String banner_text) {
		this.banner_text = banner_text;
	}

	public String getButton_text() {
		return button_text;
	}

	public void setButton_text(String button_text) {
		this.button_text = button_text;
	}

	public String getButton_url() {
		return button_url;
	}

	public void setButton_url(String button_url) {
		this.button_url = button_url;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}	
	
	
}
