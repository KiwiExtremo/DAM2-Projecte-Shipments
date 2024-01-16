package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import org.hibernate.annotations.DiscriminatorFormula;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/* Lombok */
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorFormula(discriminatorType = DiscriminatorType.STRING, value = "user_role")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "role", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Receptionist.class, name = User.RECEPTIONIST),
        @JsonSubTypes.Type(value = LogisticsManager.class, name = User.LOGISTICS_MANAGER),
        @JsonSubTypes.Type(value = Courier.class, name = User.COURIER)
})
public abstract class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /* Values for role - MUST be constants (can not be enums) */
    public static final String RECEPTIONIST = "RECEPTIONIST";
    public static final String LOGISTICS_MANAGER = "LOGISTICS_MANAGER";
    public static final String COURIER = "COURIER";

    public enum Role {
        RECEPTIONIST, LOGISTICS_MANAGER, COURIER
    }

    public static final int MIN_USERNAME = 2;
    public static final int MAX_USERNAME = 25;
    public static final int MIN_PASSWORD = 4;
    public static final int MIN_FULL_NAME = 3;
    public static final int MAX_FULL_NAME = 100;
    public static final int MAX_EXTENSION = 9999;

    /* Lombok */
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "username", nullable = false, length = MAX_USERNAME)
    protected String username;

    @Column(name = "role", nullable = false, length = MAX_FULL_NAME, columnDefinition = "varchar(31)")
    protected Role role;

    @Column(name = "password", nullable = false)
    protected String password;

    @Column(name = "full_name", nullable = false, length = MAX_FULL_NAME)
    protected String fullName;

    @Column(name = "extension")
    protected Integer extension;
}
