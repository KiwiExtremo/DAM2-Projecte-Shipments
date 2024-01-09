package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "addresses")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    /* Lombok */
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id")
    protected Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private String number;

    @Column(name = "floor")
    private String floor;

    @Column(name = "door")
    private String door;

    @Column(name = "city")
    private String city;

    @Column(name = "province")
    private String province;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;
}
