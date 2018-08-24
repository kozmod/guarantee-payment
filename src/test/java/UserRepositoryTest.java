import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.aora.microservice.GuaranteePaymentApp;
import ru.aora.microservice.entity.user.User;
import ru.aora.microservice.entity.user.UserRole;
import ru.aora.microservice.repositorie.UserRepository;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GuaranteePaymentApp.class)
public class UserRepositoryTest {

    private static final String blueTemplate = "\033[34m%s\033[0m";

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAllTest() {
        System.out.println(
                String.format(blueTemplate, userRepository.findAll())
        );
    }

    @Test
    public void findByNameTest() {
        Optional<User> user = userRepository.findByName("User");
        System.out.println(
                String.format(blueTemplate, user.orElse(null))
        );
    }

    @Test
//    @Ignore
    public void saveAndDeleteTest() {
        //prepare
        User user = User.builder()
                .withAuthorities(
                        Collections.singletonList(UserRole.USER)
                )
                .withUsername("MAX")
                .withPassword("1")
                .withMail("zzz@bk.ru")
                .withAccountNonExpired(true)
                .withAccountNonLocked(true)
                .withCredentialsNonExpired(true)
                .withEnabled(true)
                .build();
        //act
        User savedUser = userRepository.save(user);
        System.out.println(
                String.format(blueTemplate, savedUser)
        );
//        userRepository.delete(savedUser);
    }

    @Test
    @Ignore
    public void deleteByIdTest() {
        final int USER_ID_TO_DELETE = 9;
        //act
        userRepository.deleteById(USER_ID_TO_DELETE);
        Optional<User> user = userRepository.findById(USER_ID_TO_DELETE);
        //assert
        assertFalse(user.isPresent());
    }
}
