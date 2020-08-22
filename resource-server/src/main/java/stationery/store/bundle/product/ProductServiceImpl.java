package stationery.store.bundle.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stationery.store.bundle.category.Category;
import stationery.store.exceptions.EmailExistsException;
import stationery.store.utilities.Utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;


    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO=productDAO;
    }

    @Override
    @Transactional
    public List<Product> findAll() {
        log.debug("I'm in the product service");

        List<Product> productDAOAll = productDAO.findAll();
        return productDAOAll;
    }

    @Override
    public List<Product> findAll(int page, int pageSize) {

        Pageable pageable = Utils.pageable(page, pageSize);
        Page<Product> productPage =  productDAO.findAll(pageable);

        List<Product> products = new LinkedList<>();
        productPage.stream().forEach(products::add);

        return products;
    }

    @Override
    @Transactional
    public Product findById(Long aLong) {
        return productDAO.findById(aLong).orElse(null);
    }

    @Override
    @Transactional
    public Product save(Product object) throws EmailExistsException {
        return productDAO.save(object);
    }

    @Override
    @Transactional
    public void delete(Product object) {
        productDAO.delete(object);
    }

    @Override
    @Transactional
    public void deleteById(Long aLong) {
        productDAO.deleteById(aLong);
    }

    @Override
    @Transactional
    public Set<Product> findBestSellers() {
        return productDAO.findBestSellers();
    }

    @Override
    @Transactional
    public Set<Product> getCategoryProducts(long categoryId, int page, int size, String sort) {
        Pageable pageable = Utils.pageable(page, size, sort);
        Category category = new Category();
        category.setId(categoryId);
        Set<Product> products = productDAO.findByCategory(category, pageable).toSet();
        return products;
    }
}





