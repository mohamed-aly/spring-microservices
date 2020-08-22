package stationery.store.bundle.orderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemDAO extends JpaRepository<OrderItem, Long> {

}
