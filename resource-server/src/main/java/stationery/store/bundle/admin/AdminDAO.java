package stationery.store.bundle.admin;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDAO extends CrudRepository<Admin, Long> {


}
