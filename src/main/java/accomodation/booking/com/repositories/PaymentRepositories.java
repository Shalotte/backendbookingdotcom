package accomodation.booking.com.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import accomodation.booking.com.entities.PaymentEntity;

public interface PaymentRepositories extends JpaRepository <PaymentEntity, Long>{

}
