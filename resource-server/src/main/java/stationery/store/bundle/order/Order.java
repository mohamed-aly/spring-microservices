package stationery.store.bundle.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import stationery.store.bundle.abstractAndInterfaces.BaseEntity;
import stationery.store.bundle.orderItem.OrderItem;
import stationery.store.bundle.orderStatus.OrderStatus;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Order extends BaseEntity {

    @OneToMany(mappedBy = "order")
    @JsonManagedReference(value="order-orderStatus")
    private Set<OrderStatus> orderStatuses;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference(value="orderItem-order")
    private Set<OrderItem> orderItems;
}
