package stationery.store.bundle.shipper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import stationery.store.bundle.orderDetails.OrderDetails;
import stationery.store.bundle.user.User;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "shipper")
@PrimaryKeyJoinColumn(name = "user_id")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Shipper extends User {

    @Column(name = "name")
    private String name;

    @Column(name = "shipping_fees")
    private double shippingFees = 15;

    @Column(name = "status")
    private boolean active;

    @OneToMany(mappedBy = "shipper")
    @JsonManagedReference(value="shipper-orderDetails")
    private Set<OrderDetails> orders;

}
