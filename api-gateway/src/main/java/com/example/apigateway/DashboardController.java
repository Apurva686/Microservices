package com.example.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class DashboardController {

    @RequestMapping("/")
    public String dashboard(){
        return "Welcome to Roost";
    }
}
