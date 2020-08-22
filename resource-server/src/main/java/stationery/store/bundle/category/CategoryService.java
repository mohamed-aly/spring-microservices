package stationery.store.bundle.category;


import stationery.store.bundle.abstractAndInterfaces.AbstractService;

import java.util.List;
import java.util.Set;

public interface CategoryService extends AbstractService<Category, Long> {
    Set<Category> findMainCategories();

    Set<Category> findSubCategories(long categoryId, int page, int pageSize, String sortBy);

    List<Category> findAll(int page, int pageSize);

}
