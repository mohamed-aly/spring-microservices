package stationery.store.bundle.packages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageDAO extends JpaRepository<Package, Long> {


}
