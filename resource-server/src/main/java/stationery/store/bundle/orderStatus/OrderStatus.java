package stationery.store.bundle.orderStatus;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import stationery.store.bundle.abstractAndInterfaces.BaseEntity;
import stationery.store.bundle.order.Order;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "order_status")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderStatus extends BaseEntity {

    @Transient
    private Status status;

    @JsonIgnore
    private int stageValue;

    @Column(name = "status_date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="order_id")
    @JsonBackReference(value="order-orderStatus")
    private Order order;

    @PostLoad
    void fillTransient() {
        if (stageValue > 0) {
            this.status = Status.of(stageValue);
        }
    }

    @PrePersist
    void fillPersistent() {
        if (status != null) {
            this.stageValue = status.getStage();
        }
    }

}
