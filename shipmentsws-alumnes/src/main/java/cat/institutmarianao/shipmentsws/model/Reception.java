package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
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
@DiscriminatorValue("RECEPTION")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reception extends Action implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty("trackingNumber")
    @Column(name = "tracking_number")
    private Integer trackingNumber;
}
