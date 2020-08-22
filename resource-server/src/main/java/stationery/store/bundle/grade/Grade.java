package stationery.store.bundle.grade;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import stationery.store.bundle.abstractAndInterfaces.BaseEntity;
import stationery.store.bundle.gradeLevel.GradeLevel;
import stationery.store.bundle.packages.Package;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "grade")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Grade extends BaseEntity {

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL)
    @JsonManagedReference(value="package-grade")
    private Set<Package> packages;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "grade_level")
    @JsonBackReference(value="grade-gradeLevel")
    private GradeLevel gradeLevel;

    @Column(name = "grade_number")
    private int gradeNumber;

}
