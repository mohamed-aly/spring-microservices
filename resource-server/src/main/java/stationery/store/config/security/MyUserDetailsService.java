package stationery.store.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import stationery.store.bundle.user.User;
import stationery.store.bundle.user.UserDAO;

import javax.transaction.Transactional;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private UserDAO userDAO;

    public MyUserDetailsService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final User user = userDAO.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + email);
        }
        return user;
    }



}