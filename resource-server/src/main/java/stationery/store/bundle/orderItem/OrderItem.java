package stationery.store.bundle.orderItem;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import stationery.store.bundle.abstractAndInterfaces.BaseEntity;
import stationery.store.bundle.order.Order;
import stationery.store.bundle.product.Product;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "order_item")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderItem extends BaseEntity {


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonBackReference(value = "orderItem-product")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_per_unit")
    private double pricePerUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonBackReference(value="orderItem-order")
    private Order order;


}
