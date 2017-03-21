
		$(document).ready(function() {			
				// Generate a simple captcha
				function randomNumber(min, max) {
					return Math.floor(Math.random() * (max - min + 1) + min);
				};

				$('#captchaOperation').html([randomNumber(1, 100), '+', randomNumber(1, 200), '='].join(' '));

				$("#btnSubmit").bind("click", function() {
//					console.log("Submit");
				});

				$('#defaultForm').bootstrapValidator({
					//live: 'disabled',
					message: 'This value is not valid',
					feedbackIcons: {
						valid: 'glyphicon glyphicon-ok',
						invalid: 'glyphicon', //glyphion-remove
						validating: 'glyphicon glyphicon-refresh'
					},
					fields: {
//						username: {
//							validators: {
//								callback: {
//									message: '请输入手机号码或者邮箱',
//									callback: function(value, validator) {
//										var isvalid = true;
//										var re = /^1[34578]\d{9}$/;
//										var re_email = /\w@\w*\.\w/;
//										if(re.test(value) || re_email.test(value)) {
//											isvalid = true;
//										} else {
//											isvalid = false;
//										}
//										if(isvalid) {
//											validator.updateStatus('username', 'VALID');
//											return true;
//										} else {
//											return false;
//										}
//									}
//								}
//							}
//						},
											
						ddlQuestion: {
							validators: {
								notEmpty: {
									message: '请输入验证码'
								}
							}
						}
					}
				});

				// Validate the form manually
				$('#validateBtn').click(function() {
					$('#defaultForm').bootstrapValidator('validate');
				});

				$('#resetBtn').click(function() {
					$('#defaultForm').data('bootstrapValidator').resetForm(true);
				});
				
				
				
				$(".form-control.shanghu").on("change",function(){
			    	var self = $(this) ;
			  	    if(self.val() == "2"){
			  	    	$(".merchants_top_box").css("display","block");
			  	    }else{
			  	    	$(".merchants_top_box").css("display","none");
			  	    }
		        });
			});

//  $(document).ready(function() {
//  	
//  	
//  	console.log($(".shanghu option:nth-child(3)").html());
//  	$(".shanghu option:nth-child(3)").click(function(){
//  		$(".merchants_top_box").hide();
//  	})
//  	
//  })
 