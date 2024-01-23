package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/* Lombok */
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(User.RECEPTIONIST)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Receptionist extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JsonProperty("office_id")
    @JoinColumn(name = "office_id")
    private Office office;
    @JsonProperty("place")
    @Column(name = "place")
    private String place;
}
