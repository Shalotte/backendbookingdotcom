package accomodation.booking.com.controllers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import accomodation.booking.com.entities.BookingEntity;
import accomodation.booking.com.entities.ListPropertyEntity;
import accomodation.booking.com.entities.UserEntity;
import accomodation.booking.com.repositories.BookingRepository;
import accomodation.booking.com.repositories.ListPropertyRepository;
import accomodation.booking.com.repositories.UserRepository;

@CrossOrigin(origins = "http://localhost:4200",allowedHeaders="*")
@RestController
@RequestMapping("/api")

public class BookingController {
	
       @Autowired
       private BookingRepository bookingRepository;
       @Autowired
       private UserRepository userRepository;
       @Autowired
   	   private ListPropertyRepository listpropertyRepository;
  
       
       
   	
//Getting all the bookings
     	@GetMapping("/all/bookings")
     	public List<BookingEntity> getAllBooking(){
     	System.out.println("Get all Booking....");
     			
     	List<BookingEntity> bookings = new ArrayList<>();
     	bookingRepository.findAll().forEach(bookings::add);
     			
     	return bookings;
     	}
     	
     	
 //get booking by id
      @GetMapping("/booking/{id}")
      public List<BookingEntity> getUser(@PathVariable ("id") Long id ) {
    	  
       	List<BookingEntity> bookingEntity = new ArrayList<>();
       	bookingRepository.findAll().forEach(bookingEntity::add);
      	
      	List<BookingEntity> _bookingEntity = new ArrayList<>();
      	
      	for(int i=0; i<bookingEntity.size();i ++) {
      	if(bookingEntity.get(i).getUser_entity().getId().equals(id)) {
      		
      		_bookingEntity.add(bookingEntity.get(i));
      	}	
      		
      	}
      	
      	return _bookingEntity; 
      }
      
//Updating User		
      @RequestMapping(method=RequestMethod.PUT,value=("/update/booking/{id}/{id1}"))
      public BookingEntity updateBooking(@RequestBody BookingEntity bookingEntity, @PathVariable ("id") Long id,
    		  @PathVariable ("id1") String id1) {
      	
      	UserEntity userEntity= null;
      	userEntity=userRepository.getOne(id);
      	bookingEntity.setUser_entity(userEntity);
      	
    	ListPropertyEntity listPropertyEntity = null;
       	listPropertyEntity= listpropertyRepository.getOne(id1);
       	bookingEntity.setListPropertyEntity(listPropertyEntity);
      	
          return bookingRepository.save(bookingEntity);
      }
            
//Create new booking    
       Map<String, String> errors;


        @PostMapping("/createBooking/{id}/{id1}")
        public ResponseEntity<Object> createBooking(@RequestBody @Valid BookingEntity bookingEntity, BindingResult bindingResult,
        		@PathVariable ("id") Long id, @PathVariable ("id1") String id1){

       	
       	if(bindingResult.hasErrors()) {
       		
       	errors = new HashMap<>();
       	for(FieldError error:bindingResult.getFieldErrors()) {
       		
       	errors.put(error.getField(), error.getDefaultMessage());
       	}
       	
       	return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
       	}
       	

       	ListPropertyEntity listPropertyEntity = null;
       	listPropertyEntity= listpropertyRepository.getOne(id1);
       	bookingEntity.setListPropertyEntity(listPropertyEntity);
       	
       	UserEntity userEntity = null;
       	userEntity= userRepository.getOne(id);
       	bookingEntity.setUser_entity(userEntity);
       	
       	return new ResponseEntity<>(bookingRepository.save(bookingEntity),HttpStatus.OK);
        }
        
        
   
 


//Delete booking
@Transactional
@DeleteMapping("/delete/booking/{id}")
public ResponseEntity<String> deleteByProperty_name(@PathVariable ("id") String id){
	
	bookingRepository.deleteById(id);
	return new ResponseEntity<>("Booking has been deleted", HttpStatus.OK);
	
}

}

