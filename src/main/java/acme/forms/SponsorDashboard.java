
package acme.forms;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SponsorDashboard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	Integer						invoicesWithTaxLessEqual21;
	Integer						sponsorShipsWithLink;
	Double						averageAmountSponsorShips;
	Double						deviationAmountSponsorShips;
	Double						minimunAmountSponsorShips;
	Double						maximumAmountSponsorShips;
	Double						averageQuantityInvoices;
	Double						deviationQuantityInvoices;
	Double						minimunQuantityInvoices;
	Double						maximumQuantityInvoices;

}
