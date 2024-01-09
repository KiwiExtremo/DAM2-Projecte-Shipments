package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

import org.hibernate.annotations.Formula;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/* Lombok */
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "shipments")
public class Shipment implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int MAX_DESCRIPTION = 500;

    public enum Category {
        PARTICULAR, COMPANY, GOVERNMENT
    }

    public static final String PENDING = "PENDING";
    public static final String IN_PROCESS = "IN_PROCESS";
    public static final String DELIVERED = "DELIVERED";

    public enum Status {
        PENDING, IN_PROCESS, DELIVERED
    }

    /* Lombok */
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Address sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Address recipient;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "height")
    private Float height;

    @Column(name = "width")
    private Float width;

    @Column(name = "length")
    private Float length;

    @Column(name = "express")
    private Boolean express;

    @Column(name = "fragile")
    private Boolean fragile;

    @Column(name = "note", length = MAX_DESCRIPTION)
    private String note;

    @OneToMany(mappedBy = "shipment")
    private List<Action> tracking;

    /* JPA */
    @Enumerated(EnumType.STRING) // Stored as string
    /* Hibernate */
    @Formula("(SELECT CASE a.type WHEN '" + Action.RECEPTION + "' THEN '" + PENDING + "' " + " WHEN '"
            + Action.ASSIGNMENT + "' THEN '" + IN_PROCESS + "' " + " WHEN '" + Action.DELIVERY + "' THEN '" + DELIVERED
            + "' ELSE NULL END FROM actions a "
            + " WHERE a.date=( SELECT MAX(last_action.date) FROM actions last_action "
            + " WHERE last_action.shipment_id=a.shipment_id AND last_action.shipment_id=id ))")
    // Lombok
    @Setter(AccessLevel.NONE)
    private Status status;
}
