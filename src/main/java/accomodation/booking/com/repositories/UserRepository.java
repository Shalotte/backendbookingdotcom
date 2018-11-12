package accomodation.booking.com.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import accomodation.booking.com.entities.UserEntity;



public interface UserRepository extends JpaRepository <UserEntity, Long> {

	UserEntity findByEmail(String email);
	

}
