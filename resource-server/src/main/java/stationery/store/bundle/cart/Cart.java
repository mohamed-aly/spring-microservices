package stationery.store.bundle.cart;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stationery.store.bundle.abstractAndInterfaces.BaseEntity;
import stationery.store.bundle.cartItem.CartItem;
import stationery.store.bundle.customer.Customer;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cart")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Cart extends BaseEntity {

    @Column(name="last_update")
    private Date lastUpdated;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Set<CartItem> cartItems;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonBackReference("customer_cart")
    private Customer customer;

}
