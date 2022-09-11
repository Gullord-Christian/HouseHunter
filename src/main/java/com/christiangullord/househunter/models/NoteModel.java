package com.christiangullord.househunter.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="notes")
public class NoteModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(min=3, message="Note must be at least 3 characters long")
    private String note;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="note_id")
    private UserModel creator;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="house_id")
    private HouseModel house; 
    
    @PrePersist
    protected void onCreate() {
    	this.createdAt = new Date();
    }
    
    public NoteModel() {}
    
    public NoteModel(String note) {
    	this.note = note;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public UserModel getCreator() {
		return creator;
	}

	public void setCreator(UserModel creator) {
		this.creator = creator;
	}

	public HouseModel getHouse() {
		return house;
	}

	public void setHouse(HouseModel house) {
		this.house = house;
	}

    
}
