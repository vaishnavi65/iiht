package com.programcreek.helloworld.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "BOOK")
public class Book implements java.io.Serializable{
	Long bookid;
	String title;
	Double price;
	Integer volume;
	Date publishDate;
	//Long subjectid;
	private Subject subject;
	
	@Id
	@Column(name = "bookid")
	public Long getBookId() {
		return bookid;
	}
	public void setBookId(Long bookid) {
		this.bookid = bookid;
	}
	@Column
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Column
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	@Column
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
@JsonIgnore
@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name="subjectid")
	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}

