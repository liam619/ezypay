package com.demo.ezypay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ezypay.model.Subplan;
import com.demo.ezypay.model.Subscription;
import com.demo.ezypay.service.AppService;

@RestController
@RequestMapping("/rest")
public class AppController {

    @Autowired
    AppService appService;

    @PostMapping
    public ResponseEntity<Subscription> doSomething(@RequestBody Subplan plan) {

        Subscription sub = appService.performSubscription(plan);

        return ResponseEntity.ok(sub);
    }
}
