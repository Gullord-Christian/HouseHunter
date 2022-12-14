package com.christiangullord.househunter.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="users")
public class UserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="First Name must be at least 3 characters")
    @Size(min=3, max=30, message="")
    private String firstName;

	@NotEmpty(message="Last Name must be at least 3 characters")
    @Size(min=3, max=30, message="")
    private String lastName;    
    
    @NotEmpty(message="Please enter a valid email!")
    @Email
    private String email;
    
    @NotEmpty(message="Password must be at least 8 characters")
    @Size(min=8, max=128, message="") 
    private String password;     // will be hashed, make sure the max is at least 128
    
    @Transient 
    @NotEmpty(message="Passwords must match")
    @Size(min=8, max=128, message="")
    private String confirm;
    
    @NotEmpty(message="Must select buyer or realtor")
    private String realtor_buyer;
    
    // one user can have many houses listed
    @Column(updatable=false)
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<HouseModel> houses;
    
    @Column(updatable=false)
    @OneToMany(mappedBy="creator", fetch = FetchType.LAZY)
    private List<NoteModel> notesCreated;
    
    @Column(updatable=false)
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<HouseModel> savedHouses;
    
    public UserModel() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
    public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public List<HouseModel> getHouses() {
		return houses;
	}

	public void setHouses(List<HouseModel> houses) {
		this.houses = houses;
	}

	public String getRealtor_buyer() {
		return realtor_buyer;
	}

	public void setRealtor_buyer(String realtor_buyer) {
		this.realtor_buyer = realtor_buyer;
	}

	public List<NoteModel> getNotesCreated() {
		return notesCreated;
	}

	public void setNotesCreated(List<NoteModel> notesCreated) {
		this.notesCreated = notesCreated;
	}

	public List<HouseModel> getSavedHouses() {
		return savedHouses;
	}

	public void setSavedHouses(List<HouseModel> savedHouses) {
		this.savedHouses = savedHouses;
	}
}
