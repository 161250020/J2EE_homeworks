package models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class user implements Serializable{
	private String username;
	private String password;
	private int summoney;
	
	@Id
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSummoney() {
		return summoney;
	}
	public void setSummoney(int summoney) {
		this.summoney = summoney;
	}
	
}
