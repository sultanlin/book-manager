package com.sultanlinjawi.library;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HelloWorld {
    @GetMapping("/")
    public ResponseEntity<String> getHelloWorld() {
        // public String getHelloWorld() {
        var ret = "Hello World";
        System.out.println(ret);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("{\"message\":\"Hello, World!\"}");
        // return new ResponseEntity<String>("Hello World", HttpStatus.CREATED);
        // return "Hello, world";
    }
}
