package stationery.store.bundle.productImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageDAO extends JpaRepository<ProductImage, Long> {


}
