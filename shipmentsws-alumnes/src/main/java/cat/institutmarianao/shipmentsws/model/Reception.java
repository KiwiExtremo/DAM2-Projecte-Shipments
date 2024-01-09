package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import jakarta.persistence.Column;
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
public class Reception extends Action implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "tracking_number")
    private Integer trackingNumber;
}
