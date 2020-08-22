package stationery.store.bundle.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stationery.store.bundle.user.UserType;
import stationery.store.exceptions.EmailExistsException;

import java.util.HashSet;
import java.util.Set;


@Slf4j
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;
    private PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerDAO customerDAO, PasswordEncoder passwordEncoder) {
        this.customerDAO = customerDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Set<Customer> findAll() {
        log.debug("I'm in the service");

        Set<Customer> customers = new HashSet<>();
        customerDAO.findAll().iterator().forEachRemaining(customers::add);
        return customers;
    }




    @Override
    public void deleteById(Long idToDelete) {
        customerDAO.deleteById(idToDelete);
    }


    private boolean emailExist(final String email) {
        final Customer customer = customerDAO.findByEmail(email);
        return customer != null;
    }

    @Override
    public Customer save(Customer customer) throws EmailExistsException {
        if (emailExist(customer.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: " + customer.getEmail());
        }

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setUserType(UserType.CUSTOMER);
        return customerDAO.save(customer);
    }

    @Override
    public Customer update(Customer oldObject, Customer newObject) throws EmailExistsException {
        return null;
    }

    @Override
    public Customer updateExistingUser(Customer customer) throws EmailExistsException {
        if (!emailExist(customer.getEmail())) {
            throw new EmailExistsException("There is no account with that email address: " + customer.getEmail());
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerDAO.save(customer);
    }


    @Override
    public Customer findById(Long l) {
        return null;
    }


    @Override
    public void delete(Customer object) {

    }

    @Override
    public Customer getUserByEmail(String email) {
        return customerDAO.findByEmail(email);
    }


}





