package hu.me.fdsz.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class HelloEndpoint {

    @GetMapping(value = "/say-hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public String sayHello(){
        return "Hello World!";
    }

}
