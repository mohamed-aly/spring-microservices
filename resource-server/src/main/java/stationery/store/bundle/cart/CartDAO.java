package stationery.store.bundle.cart;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDAO extends JpaRepository<Cart, Long> {


}
