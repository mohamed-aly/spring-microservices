package stationery.store.bundle.abstractAndInterfaces;

import stationery.store.exceptions.EmailExistsException;

import java.util.Collection;

public interface AbstractService<T, ID> {

    Collection<T> findAll();

    T findById(ID id);

    T save(T object) throws EmailExistsException;

    void delete(T object);

    void deleteById(ID id);
}
