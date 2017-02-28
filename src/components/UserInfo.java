package components;

import java.time.LocalDate;

import javax.swing.ImageIcon;

import java.awt.Image;

public class UserInfo {
	private String password, name, phone, email, biography;
	private LocalDate bday;
	private Object avatar;

	public UserInfo(String password, String name, LocalDate bday, String phone, String email/*String biography*/){//, Object object) {
		this.password = password;
		this.name = name;
		this.bday = bday;
		this.phone = phone;
		this.email = email;
		//this.biography = biography; comment out because haven't implement in GUI
		//this.avatar = object;
	}


	public String getPassword(){return this.password;}
	public String getName(){return this.name;}
	public String getPhone(){return this.phone;}
	public String getEmail(){return this.email;}
	public LocalDate getBday(){return this.bday;}
	public String getBiography(){return this.biography;}
	public Object getAvatar(){return this.avatar;}
	
	public void setPassword(String newPassword) {
		password = newPassword;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public void setPhone(String newPhoneNumber) {
		phone = newPhoneNumber;
	}
	
	public void setEmail(String newEmail) {
		email = newEmail;
	}
	
	public void setBday(LocalDate newBday) {
		bday = newBday;
	}
	
	public void setBiography(String newBiography) {
		biography = newBiography;
	}
	
	public void setAvatar(Object newAvatar) {
		avatar = newAvatar;
	}
	


}
