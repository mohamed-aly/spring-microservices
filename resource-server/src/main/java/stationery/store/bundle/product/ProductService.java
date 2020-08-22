package stationery.store.bundle.product;


import stationery.store.bundle.abstractAndInterfaces.AbstractService;

import java.util.List;
import java.util.Set;

public interface ProductService extends AbstractService<Product, Long> {

    List<Product> findAll(int page, int pageSize);

    Set<Product> getCategoryProducts(long categoryId, int page, int size, String sort);

    Set<Product> findBestSellers();

}
