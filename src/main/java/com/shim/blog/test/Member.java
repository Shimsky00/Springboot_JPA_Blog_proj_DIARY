package com.shim.blog.test;

//import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
//@AllArgsConstructor 직접 만들어보자
@NoArgsConstructor
public class Member {
	private  int id;
	private  String username;
	private  String  password;
	private  String email;
	
	@Builder  //source -> field
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
