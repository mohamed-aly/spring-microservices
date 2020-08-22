package stationery.store.bundle.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Long> {

    Set<Category> findByMainCategory(Category category);

    Page<Category> findByMainCategory(Category category, Pageable pageable);

}
