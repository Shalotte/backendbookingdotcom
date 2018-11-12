package accomodation.booking.com.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import accomodation.booking.com.entities.BookingEntity;
import accomodation.booking.com.entities.PaymentEntity;
import accomodation.booking.com.entities.UserEntity;
import accomodation.booking.com.repositories.BookingRepository;
import accomodation.booking.com.repositories.PaymentRepositories;
import accomodation.booking.com.repositories.UserRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import javax.validation.Valid;

@CrossOrigin(origins= "http://localhost:4200", allowedHeaders="*")
@RestController
@RequestMapping("/api")


public class PaymentController {
	
	@Autowired
	private PaymentRepositories paymentRepository;
	@Autowired
    private UserRepository userRepository;
	@Autowired
	private BookingRepository bookingRepository;
	

//Getting all payments
	@GetMapping("/payments")
	public List<PaymentEntity> getAllPayments(){
		
	List<PaymentEntity> payments = new ArrayList<>();
	paymentRepository.findAll().forEach(payments::add);
	
	return payments;
		
	}
	
//Make payment   
    Map<String, String> errors;

     @PostMapping("/createPayment/{id}/{id1}")
     public ResponseEntity<Object> createBooking(@RequestBody @Valid PaymentEntity paymentEntity, BindingResult bindingResult,
    		 @PathVariable ("id") Long id, @PathVariable ("id1") String id1 ){
    	
    	if(bindingResult.hasErrors()) {
    		
    	errors = new HashMap<>();
    	for(FieldError error:bindingResult.getFieldErrors()) {
    		
    	errors.put(error.getField(), error.getDefaultMessage());
    	}
    	
    	return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
    	}
    	
    	BookingEntity bookingEntity = null;
    	bookingEntity = bookingRepository.getOne(id1);
    	paymentEntity.setBookingEntity(bookingEntity);
    	
    	
    	UserEntity userEntity = null;
     	userEntity= userRepository.getOne(id);
     	paymentEntity.setUser_entity(userEntity);
    	return new ResponseEntity<>(paymentRepository.save(paymentEntity),HttpStatus.OK);
     }
     

     
//Delete payment
    @Transactional
    @DeleteMapping("/delete/paymentById/{id}")
    public ResponseEntity<String> deleteByProperty_name(@PathVariable ("id") Long id) {
     	
 
    paymentRepository.deleteById(id);
    return new ResponseEntity<>("Payment has been deleted", HttpStatus.OK);
     	
    }
     
//Get payment by id 
     @GetMapping("/userPaymentbyId/{id}")
     public List<PaymentEntity> UserPayment(@PathVariable ("id") Long id){
     	
     	List<PaymentEntity> listpayments = new ArrayList<>();
     	paymentRepository.findAll().forEach(listpayments::add);
     	
     	List<PaymentEntity> _listpayments = new ArrayList<>();
     	
     	for(int i=0; i<listpayments.size();i ++) {
     	if(listpayments.get(i).getUser_entity().getId().equals(id)) {
     		_listpayments.add(listpayments.get(i));
     	}	
     }
     	return _listpayments;
     }
     
    
     

}
