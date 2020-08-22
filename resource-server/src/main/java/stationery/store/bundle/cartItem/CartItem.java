package stationery.store.bundle.cartItem;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import stationery.store.bundle.abstractAndInterfaces.BaseEntity;
import stationery.store.bundle.product.Product;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cart_item")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CartItem extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonBackReference(value="cartItem-product")
    private Product product;

    @Column(name = "quantity")
    private int quantity;


}
