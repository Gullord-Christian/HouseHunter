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

@Entity
@Table(name="users")
public class UserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    @Size(min=3, max=30, message="First Name must be at least 3 characters")
    private String firstName;

	@NotEmpty
    @Size(min=3, max=30, message="Last Name must be at least 3 characters")
    private String lastName;    
    
    @NotEmpty(message="Please enter a valid email!")
    @Email
    private String email;
    
    @NotEmpty
    @Size(min=8, max=128, message="Password must be at least 8 characters") 
    private String password;     // will be hashed, make sure the max is at least 128
    
    @Transient 
    @NotEmpty
    @Size(min=8, max=128, message="Passwords must match")
    private String confirm;
  
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
    
}
