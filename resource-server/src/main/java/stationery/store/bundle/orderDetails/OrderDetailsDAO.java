package stationery.store.bundle.orderDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsDAO extends JpaRepository<OrderDetails, Long> {

}
