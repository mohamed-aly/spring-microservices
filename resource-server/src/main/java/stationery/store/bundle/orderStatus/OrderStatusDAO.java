package stationery.store.bundle.orderStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusDAO extends JpaRepository<OrderStatus, Long> {

}
