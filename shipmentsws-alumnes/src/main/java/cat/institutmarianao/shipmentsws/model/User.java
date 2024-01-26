package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import org.hibernate.annotations.DiscriminatorFormula;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/* Lombok */
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorFormula(discriminatorType = DiscriminatorType.STRING, value = "role")
//JSON
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "role", visible = true)
@JsonSubTypes({ @Type(value = Receptionist.class, name = User.RECEPTIONIST),
		@Type(value = LogisticsManager.class, name = User.LOGISTICS_MANAGER),
		@Type(value = Courier.class, name = User.COURIER) })
public abstract class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Values for role - MUST be constants (can not be enums) */
	public static final String RECEPTIONIST = "RECEPTIONIST";
	public static final String LOGISTICS_MANAGER = "LOGISTICS_MANAGER";
	public static final String COURIER = "COURIER";

	public enum Role {
		RECEPTIONIST, LOGISTICS_MANAGER, COURIER
	}

	public static final int MIN_USERNAME = 2;
	public static final int MAX_USERNAME = 25;
	public static final int MIN_PASSWORD = 4;
	public static final int MIN_FULL_NAME = 3;
	public static final int MAX_FULL_NAME = 100;
	public static final int MAX_EXTENSION = 9999;

	/* Lombok */
	@EqualsAndHashCode.Include
	@Id
	@Column(name = "username", nullable = false, length = MAX_USERNAME)
	@JsonProperty("username")
	protected String username;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false, length = MAX_FULL_NAME)
	// @JsonProperty("role")
	protected Role role;

	@Column(name = "password", nullable = false)
	@JsonProperty("password")
	protected String password;

	@Column(name = "full_name", nullable = false, length = MAX_FULL_NAME)
	@JsonProperty("fullName")
	protected String fullName;

	@Column(name = "extension")
	@JsonProperty("extension")
	protected Integer extension;
}
