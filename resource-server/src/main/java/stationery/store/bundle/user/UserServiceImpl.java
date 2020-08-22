package stationery.store.bundle.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stationery.store.exceptions.EmailExistsException;

import java.util.HashSet;
import java.util.Set;


@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Set<User> findAll() {
        log.debug("I'm in the service");

        Set<User> userSet = new HashSet<>();
        userDAO.findAll().iterator().forEachRemaining(userSet::add);
        return userSet;
    }


    @Override
    public Set<User> findByUserType(UserType userType) {
        return userDAO.findByTypeRef(userType.getType());
    }


    @Override
    public void deleteById(Long idToDelete) {
        userDAO.deleteById(idToDelete);
    }


    @Override
    public User saveWithEncryption(final User user, UserType userType) throws EmailExistsException {
        if (getUserByEmail(user.getEmail())!=null) {
            throw new EmailExistsException("There is an account with that email address: " + user.getEmail());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserType(userType);
        return userDAO.save(user);
    }

    @Override
    public User save(User object) throws EmailExistsException {
        return userDAO.save(object);
    }

    @Override
    public User update(User oldUser, User newUser) throws EmailExistsException {
        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setLastName(newUser.getLastName());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPhoneNumber1(newUser.getPhoneNumber1());
        oldUser.setPhoneNumber2(newUser.getPhoneNumber2());
        oldUser.setGender(newUser.getGender());
        oldUser.setDateOfBirth(newUser.getDateOfBirth());
        return userDAO.save(oldUser);
    }


    @Override
    public User findById(Long l) {
        return userDAO.findById(l).orElse(null);
    }


    @Override
    public void delete(User object) {

    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }


}





