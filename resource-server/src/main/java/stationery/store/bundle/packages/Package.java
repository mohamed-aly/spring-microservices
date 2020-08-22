package stationery.store.bundle.packages;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import stationery.store.bundle.abstractAndInterfaces.BaseEntity;
import stationery.store.bundle.grade.Grade;
import stationery.store.bundle.product.Product;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "package")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Package extends BaseEntity {


    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "grade_id")
    @JsonBackReference(value="package-grade")
    private Grade grade;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "product_id")
    @JsonBackReference(value="package-product")
    private Product product;
}
