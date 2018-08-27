package ru.aora.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aora.microservice.entity.user.User;
import ru.aora.microservice.entity.user.UsersDto;
import ru.aora.microservice.service.UserService;

import java.util.Map;

import static ru.aora.microservice.controller.UserController.GET_USERS_MAPPING;

@Controller
@RequestMapping(GET_USERS_MAPPING)
public class UserController {

    //todo: move to ???
    public static final String REDIRECT = "redirect:";
    public static final String GET_USERS_MAPPING = "/users/manage";

    private static final String USER_MODEL = "user";
    private static final String USERS_DTO_MODEL = "usersDto";
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
        model.put(USERS_DTO_MODEL, userService.usersDto());
        model.put(ROLES_MODEL, userService.allRoles());
        return ADD_USER_TEMPLATE;
    }

    @PostMapping(params = "action=Update or Create")
    public String add(@ModelAttribute User user) {
        userService.updateOrCreate(user);
        return REDIRECT.concat(GET_USERS_MAPPING);
    }

    @PostMapping(params = "action=Delete selected users")
    public String deleteSelectedUsers(@ModelAttribute UsersDto dto) {
        userService.deleteAllBySelected(dto);
        return REDIRECT.concat(GET_USERS_MAPPING);
    }
}
