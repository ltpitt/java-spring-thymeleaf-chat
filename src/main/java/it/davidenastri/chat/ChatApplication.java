package it.davidenastri.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}

//  // no longer needed
//	@Bean
//	public String message() {
//		System.out.println("Creating message bean");
//		return "Hello, Spring!";
//	}
//
//	@Bean
//	public String uppercaseMessage(MessageService messageService) {
//		System.out.println("Creating uppercaseMessage bean");
//		return messageService.uppercase();
//	}
//
//	@Bean
//	public String lowercaseMessage(MessageService messageService) {
//		System.out.println("Creating lowercaseMessage bean");
//		return messageService.lowercase();
//	}

}
