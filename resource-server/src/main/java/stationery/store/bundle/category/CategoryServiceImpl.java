package stationery.store.bundle.category;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import stationery.store.exceptions.EmailExistsException;
import stationery.store.utilities.Utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryDAO categoryDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public Set<Category> findAll() {
        Set<Category> categories = new HashSet<>();
        categoryDAO.findAll().iterator().forEachRemaining(categories::add);
        return categories;
    }


    @Override
    public List<Category> findAll(int page, int pageSize) {

        Pageable pageable = Utils.pageable(page, pageSize);
        Page<Category> categories =  categoryDAO.findAll(pageable);

        List<Category> categoryList = new LinkedList<>();
        categories.iterator().forEachRemaining(categoryList::add);

        return categoryList;
    }

    @Override
    public Set<Category> findMainCategories() {
        Set<Category> categories = categoryDAO.findByMainCategory(null);
        return categories;
    }

    @Override
    public Set<Category> findSubCategories(long categoryID, int page, int pageSize, String sortBy) {
        Category category= new Category(categoryID);
        Pageable pageable = Utils.pageable(page, pageSize, sortBy);
        return categoryDAO.findByMainCategory(category, pageable).toSet();
    }

    @Override
    public Category findById(Long aLong) {
        return categoryDAO.findById(aLong).orElse(null);
    }

    @Override
    public Category save(Category object) throws EmailExistsException {
        return categoryDAO.save(object);
    }


    @Override
    public void delete(Category object) {
        categoryDAO.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        categoryDAO.deleteById(aLong);

    }
}





