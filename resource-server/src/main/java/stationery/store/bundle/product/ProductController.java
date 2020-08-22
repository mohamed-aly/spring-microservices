package stationery.store.bundle.product;


import org.springframework.web.bind.annotation.*;
import stationery.store.exceptions.EmailExistsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> getProducts() {
        return (ArrayList<Product>) productService.findAll();
    }

    @GetMapping(value = "/paged")
    public List<Product> getProducts(@RequestParam(value = "page") int page,
                                     @RequestParam(value = "size") int size) {
        return productService.findAll(page, size);
    }

    @GetMapping("/{id}")
    public Product findByID(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping({"", "/save"})
    public Product save(@RequestBody Product product) throws EmailExistsException {
        return productService.save(product);
    }

    @PatchMapping({"", "/update"})
    public Product update(@RequestBody Product product) throws EmailExistsException {
        return productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/bestSellers")
    public Set<Product> getBestSellers() {
        return productService.findBestSellers();
    }

}
