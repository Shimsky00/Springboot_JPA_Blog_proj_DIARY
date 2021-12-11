let index={
		init: function(){
			$("#btn-save").on("click", ()=>{ //funtion(){} , ()=>{} this를 바인딩하기 위해서 사용
				this.save();
			});
		},

	save: function(){
		//alert('user의 save함수 호출됨');
		let data ={
				username: $("#username").val(),
				password: $("#password").val(),
				email: $("#email").val()
		};
		
		//ajax통신을 위해 3개의 데이터를 json 으로 변경하여 insert요청 
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType:"json"
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.");
			//console.log(resp);
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));			
		});
	},
}

index.init();