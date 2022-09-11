package com.christiangullord.househunter.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="houses")
public class HouseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=5, message="Address must be at least 5 characters long")
	private String address;
	
	@NotNull(message="Please enter valid zipcode")
	@Min(5)
	private Integer zipcode;
	
	@Size(min=3, message="Please list a city")
	private String city;
	
	@Size(min=3, message="Please list a state")
	private String state;
	
	@Size(min=6, message="Please enter a price")
	private String price;
	
	@NotNull(message="Please enter number of bedrooms")
	@Min(1)
	private Integer bedrooms;
	
	@NotNull(message="Please enter number of bathrooms")
	@Min(1)
	private Integer bathrooms;
	
	@NotNull(message="Please enter square footage")
	@Min(1)
	private Integer squarefootage;
	
//	@NotNull(message="Please submit image")
//	@Min(1)
//	private Integer houseimg;
//	
//	private String imgFileName;
	
	@Past
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date listingDate;
	
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	// many houses can belong to one user
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private UserModel user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="houseSaver_id")
	private UserModel houseSaver;

	@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(
				name = "users_houses",
				joinColumns = @JoinColumn(name = "house_id"),
				inverseJoinColumns = @JoinColumn(name = "user_id")
				)
	private List<NoteModel> notes;
	
	public HouseModel() {}
	
	public HouseModel(String address, String price, Date listingDate, int bedrooms, int bathrooms, 
					  String city, String state, int squarefootage, int zipcode, UserModel user) {
		this.address = address;
		this.price = price;
		this.listingDate = listingDate;
		this.user = user;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.city = city;
		this.state = state;
		this.squarefootage = squarefootage;
		this.zipcode = zipcode;
//		this.houseimg = houseimg;
//		this.imgFileName = imgFileName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getZipcode() {
		return zipcode;
	}
	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Integer getBedrooms() {
		return bedrooms;
	}
	public void setBedrooms(Integer bedrooms) {
		this.bedrooms = bedrooms;
	}
	public Integer getBathrooms() {
		return bathrooms;
	}
	public void setBathrooms(Integer bathrooms) {
		this.bathrooms = bathrooms;
	}
	public Integer getSquarefootage() {
		return squarefootage;
	}
	public void setSquarefootage(Integer squarefootage) {
		this.squarefootage = squarefootage;
	}
	public Date getListingDate() {
		return listingDate;
	}
	public void setListingDate(Date listingDate) {
		this.listingDate = listingDate;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	public UserModel getHouseSaver() {
		return houseSaver;
	}
	public void setHouseSaver(UserModel houseSaver) {
		this.houseSaver = houseSaver;
	}
//	public Integer getHouseimg() {
//		return houseimg;
//	}
//	public void setHouseimg(Integer houseimg) {
//		this.houseimg = houseimg;
//	}
//	public String getImgFileName() {
//		return imgFileName;
//	}
//	public void setImgFileName(String imgFileName) {
//		this.imgFileName = imgFileName;
//	}

	
}
