package com.example.challenge.controllers;

import com.example.challenge.services.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;

    public void calculateAverage() throws Exception {
        challengeService.calculateAverage();
    }

}
