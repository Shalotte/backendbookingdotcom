package accomodation.booking.com.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import accomodation.booking.com.entities.BookingEntity;

public interface BookingRepository extends JpaRepository <BookingEntity, String> {

}
