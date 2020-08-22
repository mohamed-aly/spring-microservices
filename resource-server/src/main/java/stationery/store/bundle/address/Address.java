package stationery.store.bundle.address;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import stationery.store.bundle.abstractAndInterfaces.BaseEntity;
import stationery.store.bundle.user.User;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "address")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Address extends BaseEntity {

    private int isDefault;

    @Column(name = "building")
    private int buildingNumber;

    @Column(name = "apartment")
    private int apartmentNumber;

    @Column(name = "street_details")
    private String streetDetails;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "special_marque")
    private String specialMarque;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value="address-user")
    private User user;

}
