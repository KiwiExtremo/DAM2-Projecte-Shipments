package cat.institutmarianao.shipmentsws.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

/* Lombok */
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "addresses")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    /* Lombok */
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id")
    @JsonProperty("id")
    protected Long id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "street")
    @JsonProperty("street")
    private String street;

    @Column(name = "number")
    @JsonProperty("number")
    private String number;

    @Column(name = "floor")
    @JsonProperty("floor")
    private String floor;

    @Column(name = "door")
    @JsonProperty("door")
    private String door;

    @Column(name = "city")
    @JsonProperty("city")
    private String city;

    @Column(name = "province")
    @JsonProperty("province")
    private String province;

    @Column(name = "postal_code")
    @JsonProperty("postalCode")
    private String postalCode;

    @Column(name = "country")
    @JsonProperty("country")
    private String country;
}
