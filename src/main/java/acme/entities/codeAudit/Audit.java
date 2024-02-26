
package acme.entities.codeAudit;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Audit extends AbstractEntity {

	private static final long				serialVersionUID	= 1L;

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "[A-Z]{1,3}-[0-9]{3}")
	private String							code;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	private Date							executionDate;

	@NotNull
	private AuditType						type;

	@OneToMany(mappedBy = "audit")
	private Collection<AuditRecord>			auditRecords;

	@OneToMany(mappedBy = "audit")
	private Collection<CorrectiveActions>	correctiveActions;

	//	@NotBlank
	//	@Length(max = 101)
	//	private String				correctiveActions;

	private String							link;

}
