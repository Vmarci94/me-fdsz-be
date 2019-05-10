package hu.me.fdsz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/resort")
public class ResortPageEndpoint {

    @Autowired
    public ResortPageEndpoint() {
    }


}
