package stationery.store.bundle.admin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import stationery.store.bundle.productPatch.ProductPatch;
import stationery.store.bundle.user.User;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "admin")
@PrimaryKeyJoinColumn(name = "user_id")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Admin extends User {

    @OneToMany(mappedBy = "admin")
    @JsonManagedReference("admin-patch")
    private Set<ProductPatch> patches;

}
