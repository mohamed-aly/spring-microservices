package stationery.store.bundle.cartItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemDAO extends JpaRepository<CartItem, Long> {


}
