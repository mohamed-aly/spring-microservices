package stationery.store.bundle.user;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserDAO extends CrudRepository<User, Long> {

    User findByEmail(String email);

    Set<User> findByTypeRef(int type);
}
