package cat.institutmarianao.shipmentsws.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

/* Lombok */
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(Action.ASSIGNMENT)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Assignment extends Action implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int MIN_PRIORITY = 1;
    public static final int MAX_PRIORITY = 3;

    @ManyToOne
    @JoinColumn(name = "courier_username")
    @JsonProperty("courier")
    private Courier courier;

    @Column(name = "priority")
    @JsonProperty("priority")
    private Integer priority;
}
