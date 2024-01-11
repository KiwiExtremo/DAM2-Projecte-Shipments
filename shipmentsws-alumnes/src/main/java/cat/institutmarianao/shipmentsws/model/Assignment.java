package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@DiscriminatorValue("ASSIGNMENT")  
@Table(name = "assignment")
public class Assignment extends Action implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int MIN_PRIORITAT = 1;
    public static final int MAX_PRIORITAT = 3;

    @ManyToOne
    @JoinColumn(name = "courier_username")
    private Courier courier;

    @Column(name = "priority")
    private Integer priority;
}
