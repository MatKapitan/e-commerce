package com.matkap.ecommerce;

import com.matkap.ecommerce.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class ECommerceApplication {

	@Autowired
	MailService mailService;

	public static void main(String[] args){
		SpringApplication.run(ECommerceApplication.class, args);
	}


//	@EventListener
//	void run(ApplicationStartedEvent event)  {
//		mailService.sendSimpleMessage("dasenecusjetiti@gmail.com","test","test message");
//	}
}
