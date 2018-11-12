package accomodation.booking.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;



import accomodation.booking.com.entities.ListPropertyEntity;

public interface ListPropertyRepository extends JpaRepository <ListPropertyEntity, String> {

	ListPropertyEntity findByEmail(String email);
	void deleteByEmail(String email);
	
}
