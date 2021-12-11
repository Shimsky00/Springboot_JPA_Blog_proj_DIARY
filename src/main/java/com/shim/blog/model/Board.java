package com.shim.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY) //auto_increment
	private int id;
	
	@Column(nullable=false, length=100)
	private String title;
	
	@Lob //대용량 데이터 쓸 때 사용
	private String content; // 섬머노트 라이브러리 <html>태그가 섞여서 디자인됨
	
	private int count; //조회수
	
	//보드를 누가 적는지 알아야함
	@ManyToOne(fetch=FetchType.EAGER) //many=many, user=one
	@JoinColumn(name="userId")
	private User user; //db 는 오브젝트를 저장할 수 없다. 자바는 오브젝트 저장가능
	
	@OneToMany(mappedBy="board",fetch=FetchType.EAGER) //foreignkey가 아님 그러므로 db에컬럼 만들지 않을 것
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
