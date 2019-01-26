package com.programcreek.helloworld.model;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "SUBJECT")
public class Subject {
	
	Long subjectId;
	String subTitle;
	Integer durationInHours;
	Set<Book> references;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	@Column
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	@Column
	public Integer getDurationInHours() {
		return durationInHours;
	}
	public void setDurationInHours(Integer durationInHours) {
		this.durationInHours = durationInHours;
	}
	@OneToMany(fetch = FetchType.EAGER,mappedBy="subject")
	public Set<Book> getReferences() {
		return references;
	}
	public void setReferences(Set<Book> references) {
		this.references = references;
	}	
	
	}
