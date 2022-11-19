package com.example.challenge.controllers;

import com.example.challenge.services.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/challenge")
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;

    @PostMapping
    public void calculateAverage() throws Exception {
        challengeService.calculateAverage();
    }

}
