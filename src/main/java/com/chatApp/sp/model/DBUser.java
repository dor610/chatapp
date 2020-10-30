package com.chatApp.sp.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class DBUser {
	
	@Id
	private String id;
	
	@Indexed(unique = true)
	private String email;
	private String password;
	private String age;
	private Gender gender;
	private String userName;
	private String role;
	private List<String> friend;
	private List<String> group;
	private List<String> friendRequest;
	private List<String> acceptFriendRequest;
	

	public DBUser() {
		
	}
	
	public DBUser(String email, String password, String age, int gender, String userName) {
		this.age = age;
		this.email = email;
		this.gender = (gender == 0)? Gender.HIDDEN: (gender == 1)? Gender.MALE: Gender.FEMALE;
		this.userName = userName;
		this.role ="ROLE_USER";
	}
	
	public void setUser(DBUser user) {
		this.gender = user.getGender();
		this.age = user.getAge();
		this.password = user.getPassword();
		this.userName = user.getUserName();
	}
	
	public boolean addFriend(String email) {
		if(!friend.contains(email)) {
			friend.add(email);
			return true;
		}else return false;
	}
	
	public boolean addGroup(String groupId) {
		if(!group.contains(groupId)) {
			group.add(groupId);
			return true;
		}else return false;
	}
	
	
	public List<String> getFriendRequest() {
		return friendRequest;
	}

	public void setFriendRequest(List<String> friendRequest) {
		this.friendRequest = friendRequest;
	}

	public List<String> getAcceptFriendRequest() {
		return acceptFriendRequest;
	}

	public void setAcceptFriendRequest(List<String> acceptFriendRequest) {
		this.acceptFriendRequest = acceptFriendRequest;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public List<String> getFriend() {
		return friend;
	}

	public void setFriend(List<String> friend) {
		this.friend = friend;
	}

	public List<String> getGroup() {
		return group;
	}

	public void setGroup(List<String> group) {
		this.group = group;
	}

}