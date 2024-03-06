
package acme.entities.invoice;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.client.data.datatypes.Money;
import acme.entities.sponsorship.Sponsorship;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Invoice extends AbstractEntity {

	// Serialisation identifier ----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ------------------------------------------------------------

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "^IN-[0-9]{4}-[0-9]{4}$")
	private String				code;

	@Temporal(TemporalType.DATE)
	@Past
	@NotNull
	private Date				registrationTime;

	@NotNull
	private Date				dueDate;

	@NotNull
	private Money				quantity;

	@DecimalMin(value = "0.0")
	@DecimalMax(value = "1.0")
	private double				tax;

	@URL
	@Length(max = 255)
	private String				link;

	// Derived attributes -----------------------------------------------------


	@Transient
	private Double totalAmount() {
		return this.quantity.getAmount() + this.tax * this.quantity.getAmount();
	}

	// Relationships ----------------------------------------------------------


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Sponsorship sponsorship;

}
