package stationery.store.bundle.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;


@RestController
@Validated
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public Map<UserType, List<User>> getUsers() {

        Set<User> users = (Set<User>) userService.findAll();

        return users.stream()
                .collect(groupingBy(User::getUserType));
    }

    @GetMapping("/{type}")
    public Set<User> getUsersByType(@PathVariable String type){
        return userService.findByUserType(UserType.valueOf(type.toUpperCase()));
    }

//    @PostMapping(value = {"/signIn", "/login"})
//    public JwtResponse signIn(@RequestBody SignInRequest signInRequest) {
//
//        final Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        User user = userService.getUserByEmail(signInRequest.getUsername());
//        String token = tokenUtil.generateToken(user);
//        JwtResponse response = new JwtResponse(token);
//        return response;
//    }

//    @PostMapping("/{type}/signUp")
//    public User signUp(@RequestBody User user, @PathVariable String type) throws EmailExistsException {
//        User savedUser = userService.saveWithEncryption(user, UserType.valueOf(type.toUpperCase()));
//        savedUser.setToken(tokenUtil.generateToken(savedUser));
//        savedUser.setCreated(LocalDate.now());
//        return savedUser;
//    }

//    @PatchMapping("/update")
//    public User updateUser(Authentication authentication, @RequestBody User newUser) throws EmailExistsException {
//        User oldUser = userService.getUserByEmail(authentication.getName());
//
//        if(oldUser==null){
//            throw new EmailExistsException("this account is not available anymore");
//        }
//
//        User user = userService.update(oldUser, newUser);
//        user.setToken(tokenUtil.generateToken(user));
//        user.setLastUpdated(LocalDate.now());
//        return user;
//    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable Long id){
        userService.deleteById(id);
    }

    @GetMapping("/current")
    public UserDetails getCurrentUser(Authentication authentication) {
        return (UserDetails) authentication.getPrincipal();
    }



}
	

	

















