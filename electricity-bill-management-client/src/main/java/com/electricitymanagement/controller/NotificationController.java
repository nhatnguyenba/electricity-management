package com.electricitymanagement.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.electricitymanagement.model.Customer;

@Controller
@RequestMapping("/notification")
public class NotificationController {
	private String BODY_HEADER = "CÔNG TY ĐIỆN LỰC VIỆT NAM\n\n\n";
	private String BODY_FOOTER = "\n\n\n" + "==========================\n" + "Mọi thông tin liên hệ tại: xxxx-xxx-xxx";
	private String COMPANY_EMAIL = "electricityvn.company@gmail.com";

	private RestTemplate rest = new RestTemplate();

	@Autowired
	private JavaMailSender mailSender;

	@GetMapping
	public String showNotificationPage(Model model) {
		CustomerController.loadAllCustomerToModel(model);
		return "notification/sendEmail";
	}

	@PostMapping("/send")
	public String sendEmail(@RequestParam("receiverEmail") String receiverEmail,
			@RequestParam("subject") String subject, @RequestParam("body") String body, Model model) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(COMPANY_EMAIL);
		simpleMailMessage.setTo(receiverEmail);
		simpleMailMessage.setText(BODY_HEADER + body + BODY_FOOTER);
		simpleMailMessage.setSubject(subject);

		Customer[] customerArr = rest.getForObject("http://localhost:8069/customer", Customer[].class);
		List<Customer> customers = Arrays.asList(customerArr);

		if (receiverEmail.equals("All")) {
			for (Customer customer : customers) {
				simpleMailMessage.setTo(customer.getEmail());
				mailSender.send(simpleMailMessage);
			}
		} else {
			System.out.println("Vao else");
			try {
				mailSender.send(simpleMailMessage);
			} catch (Exception e) {
				model.addAttribute("message", "Email không hợp lệ.");
				model.addAttribute("receiverEmail", receiverEmail);
				model.addAttribute("subject", subject);
				model.addAttribute("body", body);
				CustomerController.loadAllCustomerToModel(model);
				return "notification/sendEmail";
			}
		}
		return "notification/sendEmailSuccessful";
	}
	
	@GetMapping("/send")
	public String sendEmailByGet(Model model) {
		CustomerController.loadAllCustomerToModel(model);
		return "notification/sendEmail";
	}

}
