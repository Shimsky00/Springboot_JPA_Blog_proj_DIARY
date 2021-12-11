package com.shim.blog.test;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

//사용자가 요청했을 때 HTML파일로 응답해주는 컨트롤러를 만들고싶으면
//@Controller

//사용자가 요청-> 응답(DATA)
@RestController
public class HttpControllerTest {

	private static final String TAG="HttpControllerTest : ";
	
	//localhost:8000/blog/http/lombok
	@GetMapping("/http/lombok")
	public String lombokTest() {
		//Member m= new Member(1,"adsf","1234","email");
		Member m=Member.builder().username("adf").password("1234").email("adf").build();
		System.out.println(TAG + "getter : "+m.getUsername());
		m.setUsername("shim");
		System.out.println(TAG + "setter : "+m.getUsername());
		return "lombok Test 완료";		
	}
	
	// 인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없음
	// http://localhost:8080/http/get	(select)
	@GetMapping("/http/get") //주소
	public String getTest(Member m ) {  //id=1&username=haneul&password=1234&email=a@naver.com
		return "get 요청 :" + m.getId()+","+ m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	// http://localhost:8080/http/post	(insert)
	@PostMapping("/http/post") //text/plain, application/json
	public String postTest(@RequestBody Member m) { //String text;
		return  "post 요청 :" +m.getId()+","+ m.getUsername()+","+m.getPassword()+","+m.getEmail(); //+text;
	}
	
	// http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return  "put 요청:" + m.getId()+","+ m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return  "delete 요청";
	}
}
