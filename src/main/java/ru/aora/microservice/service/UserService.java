package ru.aora.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aora.microservice.entity.ActionResult;
import ru.aora.microservice.entity.user.User;
import ru.aora.microservice.entity.user.UserRole;
import ru.aora.microservice.repositorie.UserRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException(name));
    }

    public ActionResult updateOrCreate(final User user) {
        return userRepository.findByName(user.getUsername())
                .map(target -> {
                    user.setId(target.getId());
                    if(user.getPassword().isEmpty()) user.setPassword(target.getPassword());
                    else encodeUserPassword(user);
                    userRepository.save(user);
                    return new ActionResult(
                            ActionResult.Result.SUCCESS,
                            "User updated"
                    );
                }).orElseGet(() -> {
                    encodeUserPassword(user);
                    userRepository.save(user);
                    return new ActionResult(
                            ActionResult.Result.SUCCESS,
                            "User created"
                    );
                });

    }

    public User emptyUser(){
        System.out.println(UserRole.valueOf("USER"));
                return User.builder()
                .withAccountNonExpired(true)
                .withAccountNonLocked(true)
                .withCredentialsNonExpired(true)
                .withEnabled(true)
                .build();
    }


    public List<UserRole> allRoles(){
        return Arrays.asList(UserRole.values())  ;
    }


    public UserRepository userRepository() {
        return userRepository;
    }

    private void encodeUserPassword(User user) {
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
    }

}
