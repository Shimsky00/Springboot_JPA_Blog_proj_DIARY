package com.shim.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shim.blog.model.RoleType;
import com.shim.blog.model.User;
import com.shim.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired //의존성 주입(DI)
	private UserRepository userRepository;
	
	//emil, passoword
	
	//delete테스트
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
		return "삭제에 실패하였습니다. 해당 id는DB에 없습니다.";
	}
		
		return "삭제되었습니다. id:" + id; 
	}
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id:"+id);
		System.out.println("password:"+requestUser.getPassword());
		System.out.println("email:"+requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user);
		return user;
		
	}
	
	//http://localhost:8000/blogEX/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//한 페이지당 2건의 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User>pagingUser=userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return pagingUser;
	}
	
	//{id}주소로 파라메터를 전달 받을 수 있음
	//http://localhost:8000/blogEX/dummy/user/5
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		//optional로 user객체를 감싸서 가져올테니 null인지 아닌지 return 해라
		
		User user= userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
				@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다.");
			}
		});
			
		//요청: 웹브라우저
		//user 객체= 자바 오브젝트
		//변환(웹브라우저가 이해할 수 있는 데이터 ->json)
		return user;
	}
	
	//http://localhost:8000/blogEX/dummy/join(요청)
	//http 바디에 username email, password 데이터를 가지고 요청

		@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id:"+user.getId());		
		System.out.println("username:"+user.getUsername());
		System.out.println("password:"+user.getPassword());
		System.out.println("email:"+user.getEmail());
		System.out.println("role:"+user.getRole());
		System.out.println("createDate:"+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
