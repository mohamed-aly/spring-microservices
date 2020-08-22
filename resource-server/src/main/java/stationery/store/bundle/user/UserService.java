package stationery.store.bundle.user;


import stationery.store.bundle.abstractAndInterfaces.AbstractService;
import stationery.store.exceptions.EmailExistsException;

import java.util.Set;

public interface UserService extends AbstractService<User, Long> {

    User saveWithEncryption(final User user, UserType userType) throws EmailExistsException;

    User update(User oldUser, User newUser) throws EmailExistsException;

    User getUserByEmail(String email);

    Set<User> findByUserType(UserType userType);

}
