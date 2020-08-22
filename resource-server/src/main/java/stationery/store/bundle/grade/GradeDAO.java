package stationery.store.bundle.grade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeDAO extends JpaRepository<Grade, Long> {

}
