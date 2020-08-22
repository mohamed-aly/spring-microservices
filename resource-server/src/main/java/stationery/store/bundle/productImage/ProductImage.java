package stationery.store.bundle.productImage;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import stationery.store.bundle.abstractAndInterfaces.BaseEntity;
import stationery.store.bundle.product.Product;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product_images")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductImage extends BaseEntity {

    @Column(name = "image_url")
    private String imageUrl;

    @OneToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    @JsonBackReference(value="2")
    private Product product2;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference(value="productImage-product")
    private Product product;

    @ColumnDefault(value = "0")
    private int isMain;



}
