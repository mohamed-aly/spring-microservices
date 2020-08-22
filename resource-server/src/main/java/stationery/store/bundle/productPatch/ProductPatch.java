package stationery.store.bundle.productPatch;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import stationery.store.bundle.abstractAndInterfaces.BaseEntity;
import stationery.store.bundle.admin.Admin;
import stationery.store.bundle.product.Product;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "product_patch")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductPatch extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    @JsonBackReference("admin-patch")
    private Admin admin;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonBackReference(value="productPatch-product")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "purchasing_price")
    private double purchasePrice;

    @Column(name = "date_in")
    private Date dateIn;

}
