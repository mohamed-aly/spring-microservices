package stationery.store.bundle.gradeLevel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeLevelDAO extends JpaRepository<GradeLevel, Long> {

}
