package com.example.challenge;

import com.example.challenge.controllers.ChallengeController;
import com.example.challenge.services.ChallengeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);

		ChallengeController challengeController = new ChallengeController();
		try {
			challengeController.calculateAverage();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
