package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/* Lombok */
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "offices")
public class Office implements Serializable {

    private static final long serialVersionUID = 1L;

    /* Lombok */
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
