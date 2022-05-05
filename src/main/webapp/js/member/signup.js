$(function() {
	// 프로필사진 선택한 사진으로 변경
	const fileInput = document.getElementById('profile');
	const handleFiles = () => {
		const selectedFile = [...fileInput.files];
		const fileReader = new FileReader();
		
		fileReader.readAsDataURL(selectedFile[0]);
		fileReader.onload = function() {
			document.getElementById("profileImg").src = fileReader.result;
		};
	};
	fileInput.addEventListener("change", handleFiles);
	// 이미지제거 클릭시 기본 이미지로 프로필 사진 변경
	$('#defaultProfile').click(function() {
		$('#profileImg').attr('src', '/img/member/defaultProfile.png');
	});
	
	// 선택시 테두리 굵은 검은색 변경
	$(".form-use-btn>input:first-child").on('focus', function() {
		$(this).parent().css('border', '2px solid black').css('border-radius', '4px');
	});
	$(".form-use-btn>input:first-child").on('blur', function() {
		$(this).parent().css('border', '1px solid #ccc').css('border-radius', '0px');
	});
	
	// 인증박스 활성화
	const activeCheckBox = (element) => {
		element.each(function () {
			$(this).removeAttr('disabled').parent().css('background-color', '#fff');
		});
	}
	// 인증박스 비활성화
	const disabledCheckBox = (element) => {
		element.each(function () {
			$(this).attr('disabled','disabled').parent().css('background-color', '#FAFAFA');
		});
	}
	
	// input 숫자만 입력 가능
	const onlyNum = (element) => {
		var regOnlyNum = /[^0-9]/g;
		$(element).val($(element).val().replace(regOnlyNum, ''));
	}
	
	// 글자수 한정
	const inputMaxLength = (element, max) => {
		if($(element).val().length>max){
			$(element).val($(element).val().substring(0, max));
		}
	}
	
	var emailCheck = false;
	var pwdCheck = false;
	var nicknameCheck = false;
	var telCheck = false;
	// 이메일 인증
	var mailCkNum = "";
	let regEmail = /^\w{6,}[@][a-zA-Z]{2,}[.][a-zA-Z]{2,3}([.][a-zA-Z]{2,3})?$/;
	$('#emailCheckNumSend').click(function() {
		if(regEmail.test($('#email').val())){
			$('#emailCk').val('');
			$(".ck-msg:eq(0)").html('');
			activeCheckBox($('#emailCheckBox>input'));
			$.ajax({
				url: '/member/checkMail',
				data: "email="+$('#email').val(),
				type: 'post',
				success: function(result) {
					mailCkNum=result;
				}
			});
			str = '<span style="color: green;">메일이 발송되었습니다.</span>';
		}else {
			str = '<span style="color: red;">이메일을 확인해주세요.</span>';
		}
		$(".ck-msg:eq(0)").html(str);
	});
	$('#emailCkBtn').on('click', function() {
		var str='';
		if(mailCkNum==$('#emailCk').val() && $('#emailCk').val()!=''){
			str = '<span style="color: blue;">인증 되었습니다.</span>';
			emailCheck = true;
		}else {
			str = '<span style="color: red;">인증번호를 확인해주세요</span>';
		}
		$(".ck-msg:eq(0)").html(str);
	});
	$('#email').on('input', function(){
		emailCheck = false;
		$('#emailCk').val('');
		$(".ck-msg:eq(0)").html('');
		disabledCheckBox($('#emailCheckBox>input'));
	});
	
	// 비밀번호 확인
	let userpwd = $('#pwd');
    let userpwdCk = $('#pwdCk');
    let regPw = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,20}$/;
    const pwCk = function() {
		inputMaxLength(this, 20);
        let str = "";
        pwdCheck = false;
        if(userpwd.val().length===0 || userpwdCk.val().length===0){
            str = '<span style="color: gray;">영문+숫자+특수문자 사용(8~20자리)</span>';
        } else if(!regPw.test(userpwd.val())){
            str = '<span style="color: red;">비밀번호 형식이 잘못되었습니다.</span>';
        } else if(userpwd.val() != userpwdCk.val()){
            str = '<span style="color: red;">비밀번호가 동일하지 않습니다.</span>';
        } else {
            str = '<span style="color: blue;">비밀번호가 동일합니다.</span>';
            pwdCheck = true;
        }
        $(".ck-msg:eq(1)").html(str);
    };
    userpwd.on('input', pwCk);
    userpwdCk.on('input', pwCk);
    
    // 닉네임 확인
    let nicknameMaxLength =10;
    let regNickname = new RegExp("^[\\w가-힣]{2,"+nicknameMaxLength+"}$");
    let nickname = $('#nickname');
    nickname.on('input', function() {
		let str = "";
		nicknameCheck = false;
		inputMaxLength(this, 10);
		if(nickname.val().length<2) {
			str = '<span style="color: gray;">문자+숫자 사용(2~10글자)</span>';
		}else if(regNickname.test(nickname.val())) {
			$.ajax({
				url: "/member/searchNickname",
				data: "nickname="+nickname.val(),
				type: "get",
				async: false,
				success: function(result) {
					if(result==0){
						str = '<span style="color: blue;">사용이 가능합니다.</span>';
						nicknameCheck = true;
					}else {
						str = '<span style="color: red;">중복된 닉네임이 존재합니다.</span>';
					}
				}
			});
		}else {
			str = '<span style="color: red;">사용 불가능한 닉네임입니다.</span>';
		}
		$(".ck-msg:eq(2)").html(str);
	});
	
	
	// 전화번호 인증
	var tel = $('#tel');
	tel.on('input', function() {
		onlyNum(this);
		inputMaxLength(this, 11);
	});
	var telCkNum = "";
	var regTel = /^010|011|016|017|018|019/
	$('#telCheckNumSend').click(function() {
		var str = '';
		if(tel.val().length<phonNumLength-1 || !regTel.test(tel.val())){
			str = '<span style="color: red;">전화번호를 정확히 입력해주세요.</span>';
		} else {
			$('#telCk').val('');
			$(".ck-msg:eq(3)").html('');
			activeCheckBox($('#telCheckBox>input'));
			$.ajax({
				url: "/member/sendSMS",
				data: "tel="+$('#tel').val(),
				type: "post",
				success: function(result) {
					telCkNum=result;
				}
			});
			str = '<span style="color: green;">인증번호가 발송되었습니다.</span>';
		}
		$(".ck-msg:eq(3)").html(str);
	});
	$('#telCkBtn').on('click', function() {
		var str='';
		if(telCkNum==$('#telCk').val() && $('#telCk').val()!=''){
			str = '<span style="color: blue;">인증 되었습니다.</span>'
			telCheck = true;
		}else {
			str = '<span style="color: red;">인증번호를 확인해주세요</span>'
		}
		$(".ck-msg:eq(3)").html(str);
	});
	$('#tel').on('input', function(){
		$('#telCk').val('');
		$(".ck-msg:eq(3)").html('');
		disabledCheckBox($('#telCheckBox>input'));
		telCheck = false;
	});
	
	// 나이
	var today = new Date();
	var age = 0;
	$('#birthdate').on('input', function() {
		var birthdateArr = $(this).val().split('-');
		var birthdate = new Date(birthdateArr[0], birthdateArr[1]-1, birthdateArr[2]);
		
		if(new Date(today.getFullYear(), today.getMonth(), today.getDate()) < birthdate){
			$(this).val(today.toISOString().split('T')[0]);
			age = 0;
		}else {
			age = today.getFullYear() - birthdate.getFullYear();
			var subMonth = today.getMonth()-birthdate.getMonth();
			if(age!=0 && (subMonth<0 || (subMonth === 0 && today.getDate()<birthdate.getDate()))){
				age--;
			}
		}
	});
	
	// 키
	var height = 0;
	$('#height').on('input', function() {
		onlyNum(this);
		inputMaxLength(this, 3);
		
		height = $(this).val();
	});
	
	// 체중
	var weight = 0;
	$('#weight').on('input', function() {
		onlyNum(this);
		inputMaxLength(this, 4);
		
		weight = $(this).val();
	});
	
	// 활동량
	var activeScore = 0;
	$('input[name="active"]').on('click', function() {
		activeScore = $(this).val();
	});
	
	// 기초 대사량, 활동대사량
	$('#checkMetabolic').click(function() {
		if(age * height * weight * activeScore == 0){
			alert("입력란을 모두 채워주세요.");
		}else {
			var BMR = 0;
			var AMR = 0;
			if($('input[name="gender"]:checked').val()=='m'){
				// 남자 측정
			}else if($('input[name="gender"]:checked').val()=='w'){
				// 여자 측정
			}
		}
	});
});