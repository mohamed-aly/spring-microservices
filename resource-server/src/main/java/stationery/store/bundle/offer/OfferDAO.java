package stationery.store.bundle.offer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferDAO extends JpaRepository<Offer, Long> {

    @EntityGraph(value = "offer.product")
    Page<Offer> findAll(Pageable pageable);

    @EntityGraph(value = "offer.product")
    List<Offer> findAll();


}
