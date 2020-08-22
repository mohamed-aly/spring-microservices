package stationery.store.bundle.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import stationery.store.bundle.cart.Cart;
import stationery.store.bundle.orderDetails.OrderDetails;
import stationery.store.bundle.user.User;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "customer")
@PrimaryKeyJoinColumn(name = "user_id")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Customer extends User {

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference(value = "orderDetails-customer")
    private Set<OrderDetails> orders;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer")
    @JsonManagedReference("customer_cart")
    private Cart cart;
}
