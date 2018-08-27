package ru.aora.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.aora.microservice.entity.user.UsersDto;
import ru.aora.microservice.repositorie.UserRepository;

import java.util.Map;

@Controller
@RequestMapping("/")
public class TestController {
    @Autowired
    private UserRepository userRepository;

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
//        UsersDto dto = new UsersDto(userRepository.findAll());
//        System.out.println("GET:"+dto.getUsers());
//        model.put("dto", dto);
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveBooks(@ModelAttribute UsersDto dto, Map<String, Object> model) {
//        System.out.println("POST:"+dto.getUsers());
//        model.put("dto", dto);
        return "index";
    }

}
