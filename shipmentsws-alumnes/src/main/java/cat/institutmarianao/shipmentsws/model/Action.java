package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;
import java.util.Date;


import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "actions")


public abstract class Action implements Serializable {

    private static final long serialVersionUID = 1L;

    /* Values for type - MUST be constants */
    public static final String RECEPTION = "RECEPTION";
    public static final String ASSIGNMENT = "ASSIGNMENT";
    public static final String DELIVERY = "DELIVERY";

    public enum Type {
        RECEPTION, ASSIGNMENT, DELIVERY
    }

    /* Lombok */
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id")
    protected Long id;

    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false)
    protected Type type;

    @ManyToOne
    @JoinColumn(name = "performer_username")
    protected User performer;

    @Column(name = "date", nullable = false)
    protected Date date = new Date();

    @ManyToOne
    @JoinColumn(name = "shipment_id", nullable = false)
    protected Shipment shipment;
}
