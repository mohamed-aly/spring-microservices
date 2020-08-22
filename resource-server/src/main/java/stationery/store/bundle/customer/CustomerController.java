package stationery.store.bundle.customer;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stationery.store.bundle.user.UserService;


@RestController
@Validated
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

    private final UserService userService;

    public CustomerController(UserService userService) {
        this.userService = userService;
    }


}
	

	

















