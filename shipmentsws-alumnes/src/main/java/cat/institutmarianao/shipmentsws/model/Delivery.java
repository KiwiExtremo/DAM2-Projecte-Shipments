package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

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
@DiscriminatorValue(Action.DELIVERY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Delivery extends Action implements Serializable {

    private static final long serialVersionUID = 1L;
}
