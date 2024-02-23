
package acme.entities.userStories;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import acme.client.data.AbstractEntity;
import acme.entities.projects.Project;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserStory extends AbstractEntity {

	// Serialisation identifier ----------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes ------------------------------------

	@NotBlank
	@Length(max = 76)
	private String				title;

	@NotBlank
	@Length(max = 101)
	private String				description;

	@NotNull
	@Positive
	private Double				estimatedCost;

	@NotBlank
	@Length(max = 101)
	private String				acceptanceCriteria;

	@NotNull
	private Priority			priority;

	private String				link;

	// Relationships ---------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Project				project;

}
