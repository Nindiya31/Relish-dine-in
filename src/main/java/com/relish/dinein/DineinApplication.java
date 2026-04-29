package com.relish.dinein;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DineinApplication {

	public static void main(String[] args) {
		SpringApplication.run(DineinApplication.class, args);
	}
	
	 @EventListener(ApplicationReadyEvent.class)
	    public void onStart() {
	        System.out.println("\n:::::: Relish Application started ::::::\n");
	    }

}
