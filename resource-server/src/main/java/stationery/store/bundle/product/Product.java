package stationery.store.bundle.product;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import stationery.store.bundle.abstractAndInterfaces.BaseEntity;
import stationery.store.bundle.cartItem.CartItem;
import stationery.store.bundle.category.Category;
import stationery.store.bundle.offer.Offer;
import stationery.store.bundle.orderItem.OrderItem;
import stationery.store.bundle.packages.Package;
import stationery.store.bundle.productImage.ProductImage;
import stationery.store.bundle.productPatch.ProductPatch;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)

@NamedEntityGraphs({
        @NamedEntityGraph(name="simpleView", attributeNodes={
                @NamedAttributeNode("id"),
                @NamedAttributeNode("name"),
                @NamedAttributeNode("price"),
                @NamedAttributeNode("offers"),
                @NamedAttributeNode("mainImage")
        }),
        @NamedEntityGraph(name="complexView", attributeNodes={
                @NamedAttributeNode("name"),
                @NamedAttributeNode("price"),
                @NamedAttributeNode("offers"),
                @NamedAttributeNode("imageUrl")
        })
})

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

public class Product extends BaseEntity {

    @NotEmpty
    private String name;

    private String description;

    private int minStock;

    @Min(1)
    private int price;

    private int inStock;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate created;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate lastUpdated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference(value="category-product")
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference(value = "orderItem-product")
    private Set<OrderItem> orders;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "product2")
    @JsonManagedReference(value="2")
    @Where(clause = "is_main = 1")
    private ProductImage mainImage;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference(value="productImage-product")
    @Where(clause = "is_main = 0")
    private Set<ProductImage> imageUrl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @JsonManagedReference(value="cartItem-product")
    private Set<CartItem> cartItems;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonManagedReference(value="productPatch-product")
    @OrderBy("dateIn")
    private Set<ProductPatch> patches;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    @JsonManagedReference(value="offer-product")
    @OrderBy("endDate desc")
    @Where(clause = "end_date > sysdate()")
    private Set<Offer> offers;

    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL)
    @JsonManagedReference(value="package-product")
    private Set<Package> packages;

    public Product(long id, String name) {
        super(id);
        this.name=name;
    }
}
