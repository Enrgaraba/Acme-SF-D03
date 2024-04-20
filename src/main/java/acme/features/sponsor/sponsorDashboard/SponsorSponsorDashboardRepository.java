
package acme.features.sponsor.sponsorDashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.invoice.Invoice;
import acme.entities.sponsorship.Sponsorship;

@Repository
public interface SponsorSponsorDashboardRepository extends AbstractRepository {

	@Query("SELECT COUNT(s) FROM Sponsorship s where (s.sponsor.id = :sponsorId and s.published = true)")
	int findNumberPublishedSponsorships(int sponsorId);

	@Query("select i from Invoice i where i.sponsorship.id = :sponsorshipId")
	Collection<Invoice> findInvoicesBySponsorshipId(int sponsorshipId);

	@Query("SELECT s FROM Sponsorship s where (s.sponsor.id = :sponsorId and s.published = true)")
	Collection<Sponsorship> findPublishedSponsorshipsBySponsorId(int sponsorId);

	@Query("SELECT COUNT(s) FROM Sponsorship s where (s.link IS NOT NULL and s.sponsor.id = :sponsorId and s.published = true)")
	int findNumberPublishedSponsorshipsWithLink(int sponsorId);

	@Query("select avg(s.amount.amount) from Sponsorship s where (s.sponsor.id = :sponsorId and s.published = true)")
	Double findAverageAmountPublishedSponsorships(int sponsorId);

	@Query("select stddev(s.amount.amount) from Sponsorship s where (s.sponsor.id = :sponsorId and s.published = true)")
	Double findDeviationAmountPublishedSponsorships(int sponsorId);

	@Query("select max(s.amount.amount) from Sponsorship s where (s.sponsor.id = :sponsorId and s.published = true)")
	Double findMaxAmountPublishedSponsorships(int sponsorId);

	@Query("select min(s.amount.amount) from Sponsorship s where (s.sponsor.id = :sponsorId and s.published = true)")
	Double findMinAmountPublishedSponsorships(int sponsorId);

}
