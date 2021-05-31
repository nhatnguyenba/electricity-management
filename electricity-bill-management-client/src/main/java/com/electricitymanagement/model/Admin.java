package com.electricitymanagement.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
	
	private Long id;
	
	@NotBlank(message = "Tên là bắt buộc")
	private String name;
	
	@NotBlank(message = "Địa chỉ là bắt buộc")
	private String address;
	
	@Pattern(regexp = "[0-9]{9}|[0-9]{12}", message = "Số chứng minh thư gồm 9 hoặc 12 chữ "
			+ "số.")
	private String idNumber;

	@Pattern(regexp = "[0-9]{10,11}", message = "Số điện thoại gồm 10 hoặc 11 chữ số.")
	private String phone;
	
	@Email(message = "Email không hợp lệ")
	private String email;
	
	@NotBlank(message = "Username là bắt buộc")
	private String username;
	
	@NotBlank(message = "Username là bắt buộc")
	private String password;
	
}
