package stationery.store.bundle.customer;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerDAO extends CrudRepository<Customer, Long> {

    Customer findByEmail(String email);


}
