package stationery.store.bundle.productPatch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPatchDAO extends JpaRepository<ProductPatch, Long> {


}
