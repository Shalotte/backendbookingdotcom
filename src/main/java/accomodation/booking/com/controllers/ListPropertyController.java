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
import accomodation.booking.com.entities.ListPropertyEntity;
import accomodation.booking.com.entities.UserEntity;
import accomodation.booking.com.repositories.ListPropertyRepository;
import accomodation.booking.com.repositories.UserRepository;




@CrossOrigin(origins = "http://localhost:4200",allowedHeaders="*")
@RestController
@RequestMapping("/api")



public class ListPropertyController {
	
	@Autowired
	private ListPropertyRepository listpropertyRepository;
	@Autowired
	private UserRepository userRepository;
	
//Getting all the properties
	@GetMapping("/all/properties")
	public List<ListPropertyEntity> getAllProperties(){
	System.out.println("Get all Properties....");
			
	
	List<ListPropertyEntity> properties = new ArrayList<>();
	listpropertyRepository.findAll().forEach(properties::add);

	return properties;
	}

//Getting a list of property belonging to a user
	
@GetMapping("/userProperty/{id}")
public List<ListPropertyEntity> UserProperty(@PathVariable ("id") Long id){
	
	List<ListPropertyEntity> listProperty = new ArrayList<>();
	listpropertyRepository.findAll().forEach(listProperty::add);
	
	List<ListPropertyEntity> userProperty = new ArrayList<>();
	
	for(int i=0; i<listProperty.size();i ++) {
	if(listProperty.get(i).getUser_entity().getId().equals(id)) {
    userProperty.add(listProperty.get(i));
	}	
}
	return userProperty;
}

//Updating property	
@RequestMapping(method=RequestMethod.PUT,value=("/update/property/{id}"))
public ListPropertyEntity updateProperty(@RequestBody ListPropertyEntity listPropertyEntity, @PathVariable ("id") Long id) {
	
	UserEntity userEntity= null;
	userEntity=userRepository.getOne(id);
	listPropertyEntity.setUser_entity(userEntity);
    return listpropertyRepository.save(listPropertyEntity);
}
	
 
//Listing property
@PostMapping("/create/properties/{id}")
public ListPropertyEntity listProperty(@RequestBody ListPropertyEntity listproperty,@PathVariable ("id") Long id) {
	
    UserEntity userEntity= null;
    userEntity=userRepository.getOne(id);

    listproperty.setUser_entity(userEntity);
	return listpropertyRepository.save(listproperty);
}

//Delete property
@Transactional
@DeleteMapping("/delete/property/{id}")
public ResponseEntity<String> deleteByProperty_name(@PathVariable ("id") String id) {
	
	listpropertyRepository.deleteById(id);
	return new ResponseEntity<>("Property has been deleted", HttpStatus.OK);
	
}


//Searching for destination
@GetMapping("/search/property/{destination}/{guests}/{rooms}")
public List<ListPropertyEntity> searchProperty(@PathVariable("destination") String destination,
@PathVariable("guests") Integer guests, @PathVariable("rooms") Integer rooms){
	  
  
List<ListPropertyEntity> listPropertyEntity = new ArrayList<>();
listpropertyRepository.findAll().forEach(listPropertyEntity::add);

List<ListPropertyEntity> _listPropertyEntity = new ArrayList<>();
	
for(int i=0; i<listPropertyEntity.size(); i++) {
if( listPropertyEntity.get(i).getNumber_of_bedrooms()==rooms || listPropertyEntity.get(i).getGuests()==guests &&
listPropertyEntity.get(i).getProperty_city_name().equals(destination) ) {

	_listPropertyEntity.add(listPropertyEntity.get(i));
	
}	
}
	
return _listPropertyEntity ;
}



//Create new property  
Map<String, String> errors;

  @PostMapping("/createProperty/{id}")
  public ResponseEntity<Object> createProperty(@RequestBody @Valid ListPropertyEntity listproperty, BindingResult bindingResult, @PathVariable ("id") Long id ){
	
	if(bindingResult.hasErrors()) {
		
	errors = new HashMap<>();
	for(FieldError error:bindingResult.getFieldErrors()) {
		
	errors.put(error.getField(), error.getDefaultMessage());
	}
	
	return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
	}
	
	ListPropertyEntity list_property = listpropertyRepository.findByEmail(listproperty.getEmail());
	
	if(list_property!=null) {
	return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	UserEntity userEntity= null;
	userEntity=userRepository.getOne(id);
	listproperty.setUser_entity(userEntity);
	return new ResponseEntity<>(listpropertyRepository.save(listproperty),HttpStatus.OK);
	
  }
  }



  





