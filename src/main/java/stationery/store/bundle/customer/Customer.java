package stationery.store.bundle.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Transient
    private CustomerType customerType;

    @JsonIgnore
    private int type;

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference(value = "orderDetails-customer")
    private Set<OrderDetails> ordersDetails;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "customer")
    @JsonManagedReference("customer_cart")
    private Cart cart;

    @PostLoad
    void fillTransient() {
        if (this.type > 0) {
            this.customerType = CustomerType.of(type);
        }
    }

    @PrePersist
    void fillPersistent() {
        if (customerType != null) {
            this.type = customerType.getType();
        }
    }
}
