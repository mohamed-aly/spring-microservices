package stationery.store.bundle.offer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import stationery.store.bundle.abstractAndInterfaces.BaseEntity;
import stationery.store.bundle.product.Product;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "offers")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NamedEntityGraph(name = "offer.product",
        attributeNodes = {@NamedAttributeNode("product")}
)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Offer extends BaseEntity {

    @Min(0)
    @Max(1)
    private double discount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @FutureOrPresent
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Future
    private LocalDate endDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate created;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate lastUpdated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
//    @JsonManagedReference(value="offer-product")
    private Product product;

}
