package stationery.store.bundle.customer;



import stationery.store.bundle.abstractAndInterfaces.AbstractService;
import stationery.store.exceptions.EmailExistsException;

public interface CustomerService extends AbstractService<Customer, Long> {
    Customer update(Customer oldObject, Customer newObject) throws EmailExistsException;
    Customer getUserByEmail(String email);
    Customer updateExistingUser(Customer customer) throws EmailExistsException;

}
