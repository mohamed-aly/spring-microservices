package stationery.store.bundle.gradeLevel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import stationery.store.bundle.abstractAndInterfaces.BaseEntity;
import stationery.store.bundle.grade.Grade;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "grade_level")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GradeLevel extends BaseEntity {

    @Column(name = "level")
    private String level;

    @OneToMany(mappedBy = "gradeLevel", cascade = CascadeType.ALL)
    @JsonManagedReference(value="grade-gradeLevel")
    private Set<Grade> grades;


}
