package ru.aora.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.aora.microservice.entity.user.User;
import ru.aora.microservice.service.UserService;

import java.util.List;
import java.util.Map;

import static ru.aora.microservice.controller.UserController.UPDATE_OR_CREATE_MAPPING;

@Controller
@RequestMapping(UPDATE_OR_CREATE_MAPPING)
public class UserController {

    public static final String UPDATE_OR_CREATE_MAPPING = "/users/manage";

    private static final String USER_MODEL = "user";
    private static final String USERS_MODEL = "users";
    private static final String ROLES_MODEL = "roles";

    private static final String ADD_USER_TEMPLATE = "addUser";


    private UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUserForm(Map<String, Object> model) {
        model.put(USER_MODEL, userService.emptyUser());
        model.put(USERS_MODEL, userService.userRepository().findAll());
        model.put(ROLES_MODEL, userService.allRoles());
        return ADD_USER_TEMPLATE;
    }

    @PostMapping()
    public String addUser(Map<String, Object> model, @ModelAttribute User user) {

//        userService.updateOrCreate(user);
        model.put(USER_MODEL, userService.emptyUser());
        model.put(USERS_MODEL, userService.userRepository().findAll());
        model.put(ROLES_MODEL, userService.allRoles());
        return ADD_USER_TEMPLATE;
    }

    @RequestMapping(value = "/users/manage/delete",method = RequestMethod.POST)
    public String delete(Map<String, Object> model, @ModelAttribute List<User> users) {
        System.out.println("===================================================================");
        System.out.println(users);
//        model.put(USER_MODEL, userService.emptyUser());
//        model.put(USERS_MODEL, userService.userRepository().findAll());
//        model.put(ROLES_MODEL, userService.allRoles());
        return "redirect:/users/manage";
    }

//    @RequestMapping(value = UPDATE_OR_CREATE_MAPPING + "/doDelete", method = RequestMethod.POST)
//    public String deleteUser(@RequestParam int id) {
////        userService.updateOrCreate(user);
//        System.out.println(id);
//        return "redirect:" + ADD_USER_TEMPLATE;
//    }
}
