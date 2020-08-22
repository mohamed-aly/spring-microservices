package stationery.store.bundle.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import stationery.store.bundle.abstractAndInterfaces.BaseEntity;
import stationery.store.bundle.address.Address;
import stationery.store.validation.ValidPassword;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;


@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(value = {"isEnabled", "type", "created", "authorities", "username",
        "enabled", "accountNonLocked", "credentialsNonExpired", "accountNonExpired", "genderType", "userType"},
        allowSetters = true)
@NamedEntityGraph(
        name = "user.addresses",
        attributeNodes = @NamedAttributeNode("addresses")
)
public class User extends BaseEntity implements UserDetails {

    @NotEmpty(message = "first name is required.")
    private String firstName;

    @NotEmpty(message = "Last name is required.")
    private String lastName;

    @NotEmpty(message = "Phone number is required.")
    private String phoneNumber1;

    private String phoneNumber2;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @Column(name = "email", unique = true)
    @NotEmpty(message = "Email is required.")
    @Email
    private String email;


    @NotEmpty(message = "Password is required.")
    @ValidPassword
    private String password;

    @Transient
    private String passwordConfirmation;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate created;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate lastUpdated;

    private int isEnabled = 1;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "address-user")
    private Set<Address> addresses;

    private int typeRef;

    @Transient
    private UserType userType;

    private int genderRef;

    @Transient
    private String gender = "male";

    @Transient
    private String token;

    public User(long id, String email, String password, int typeRef, int isEnabled) {
        this.id=id;
        this.email = email;
        this.password = password;
        this.typeRef=typeRef;
    }

    @PostLoad
    void fillTransient() {
        if (typeRef > 0) {
            this.userType = UserType.of(typeRef);
        }

        if (genderRef == 1) {
            gender = "male";
        } else if (genderRef == 2) {
            gender = "female";
        }
    }

    @PrePersist
    void fillPersistent() {
        if (userType != null) {
            this.typeRef = userType.getType();
        }

        if (gender.toLowerCase().equals("male")) {
            this.genderRef = 1;
        } else if (gender.toLowerCase().equals("female")) {
            this.genderRef = 2;
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(this.getUserType().toString().toUpperCase()));
    }


    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override

    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if (this.isEnabled == 1) {
            return true;
        }
        return false;
    }

    public void setEnabled(boolean isEnabled) {
        if (isEnabled)
            this.isEnabled = 1;
        else this.isEnabled = 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [email=" + email + "]";
    }

}
