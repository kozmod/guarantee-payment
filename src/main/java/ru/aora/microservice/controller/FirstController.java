package ru.aora.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aora.microservice.repositorie.UserRepository;
//import ru.aora.microservice.repositorie.UserRepository;

import java.util.Map;

@Controller
@RequestMapping("/")
public class FirstController {

//    private UserRepository userRepository;
//
//    @Autowired
//    public FirstController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

//    @GetMapping
//    String home() {
//        System.out.println(userRepository.findAll());
//
//        return "Hello World!";
//    }

    @GetMapping
    public String welcome(Map<String, Object> model) {
        final String message = "Hello World";
        model.put("message", message);
        return "index";
    }

}
