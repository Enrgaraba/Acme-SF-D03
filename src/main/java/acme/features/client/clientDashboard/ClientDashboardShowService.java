
package acme.features.client.clientDashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.accounts.Principal;
import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.ClientDashboard;
import acme.roles.Client;

@Service
public class ClientDashboardShowService extends AbstractService<Client, ClientDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ClientDashboardRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		final ClientDashboard dashboard = new ClientDashboard();

		Principal principal;
		int userAccountId;
		principal = super.getRequest().getPrincipal();
		userAccountId = principal.getAccountId();
		final Client client = this.repository.findOneClientByUserAccountId(userAccountId);

		final double findAverageContractBudget = this.repository.findAverageContractBudget(client).orElse(0.0);
		final double findMaxContractBudget = this.repository.findMaxContractBudget(client).orElse(0.0);
		final double findMinContractBudget = this.repository.findMinContractBudget(client).orElse(0.0);
		final double findLinearDevContractBudget = this.repository.findLinearDevContractBudget(client).orElse(0.0);

		//progressLogsCompleteness

		final Integer progressLogsWithCompletenessUnder25 = this.repository.findNumOfprogressLogsLess25(client).orElse(0);
		final Integer progressLogsWithCompleteness25To50 = this.repository.findNumOfProgressLogsWithRate25To50(client).orElse(0);
		final Integer progressLogsWithCompleteness50To75 = this.repository.findNumOfProgressLogsWithRate50To75(client).orElse(0);
		final Integer progressLogsWithCompletenessOver75 = this.repository.findNumOfProgressLogsWithRateOver75(client).orElse(0);

		dashboard.setAverageBudget(findAverageContractBudget);
		dashboard.setDeviationBudget(findLinearDevContractBudget);
		dashboard.setMaximumBudget(findMaxContractBudget);
		dashboard.setMinimumBudget(findMinContractBudget);
		dashboard.setNumProgressLogsUnder25(progressLogsWithCompletenessUnder25);
		dashboard.setNumProgressLogsBetween25and50(progressLogsWithCompleteness25To50);
		dashboard.setNumProgressLogsBetween50and75(progressLogsWithCompleteness50To75);
		dashboard.setNumProgressLogsAbove75(progressLogsWithCompletenessOver75);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final ClientDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, "numProgressLogsUnder25", "numProgressLogsBetween25and50", "numProgressLogsBetween50and75", "numProgressLogsAbove75", "averageBudget", "deviationBudget", "minimumBudget", "maximumBudget");

		super.getResponse().addData(dataset);
	}

}
