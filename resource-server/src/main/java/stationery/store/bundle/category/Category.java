package stationery.store.bundle.category;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stationery.store.bundle.abstractAndInterfaces.BaseEntity;
import stationery.store.bundle.product.Product;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "category")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Category extends BaseEntity {

    @NotEmpty
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl = "default category image url";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="main_category")
    @JsonBackReference("category_mainCategory")
    private Category mainCategory;

    @OneToMany(mappedBy = "mainCategory")
    @JsonManagedReference("category_mainCategory")
    private Set<Category> subCategories;

    @OneToMany(mappedBy = "category",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JsonManagedReference(value="category-product")
    private List<Product> products;

    public Category(long id) {
        super(id);
    }
}
