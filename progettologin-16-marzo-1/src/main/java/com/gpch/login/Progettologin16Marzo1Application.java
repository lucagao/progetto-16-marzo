package com.gpch.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.gpch.login.model.Mail;
import com.gpch.login.service.MailService;

@SpringBootApplication
@ComponentScan(basePackages = {
	    "com.gpch.login"
	})
public class Progettologin16Marzo1Application {

	public static void main(String[] args) {
		//SpringApplication.run(Progettologin16Marzo1Application.class, args);
		
        Mail mail = new Mail();
        mail.setMailFrom("Lucagao11@gmail.com");
        mail.setMailTo("Lucagao11@gmail.com");
        mail.setMailSubject("Spring Boot - Email Example");
        mail.setMailContent("Learn How to send Email using Spring Boot!!!");
 
        ApplicationContext ctx = SpringApplication.run(Progettologin16Marzo1Application.class, args);
        MailService mailService = (MailService) ctx.getBean("mailService");
        mailService.sendEmail(mail);
		
	}

}
