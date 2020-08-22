package stationery.store.bundle.orderDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import stationery.store.bundle.abstractAndInterfaces.BaseEntity;
import stationery.store.bundle.address.Address;
import stationery.store.bundle.customer.Customer;
import stationery.store.bundle.order.Order;
import stationery.store.bundle.shipper.Shipper;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "order_details")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderDetails extends BaseEntity {


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonBackReference(value="orderDetails-customer")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipper_id")
    @JsonBackReference(value="shipper-orderDetails")
    private Shipper shipper;

}
