package stationery.store.bootstrap;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import stationery.store.bundle.user.User;
import stationery.store.bundle.user.UserDAO;
import stationery.store.bundle.user.UserService;
import stationery.store.bundle.user.UserType;
import stationery.store.bundle.admin.Admin;


@Slf4j
@Component
public class BootStrapMySQL implements ApplicationListener<ContextRefreshedEvent> {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public BootStrapMySQL(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @SneakyThrows
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        if (userService.findAll().isEmpty()) {

            User user = new Admin();
            user.setFirstName("Test");
            user.setLastName("Test");
            user.setPassword("E!12@password");
            user.setPasswordConfirmation("E!12@password");
            user.setPhoneNumber1("01285030106");
            user.setEmail("test@test.com");
            user.setEnabled(true);
            userService.save(user, UserType.ADMIN);
        }
    }

}