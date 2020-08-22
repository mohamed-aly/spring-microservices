package stationery.store.bundle.shipper;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShipperDAO extends CrudRepository<Shipper, Long> {


}
