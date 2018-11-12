package accomodation.booking.com.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import accomodation.booking.com.repositories.UserRepository;
import accomodation.booking.com.entities.ListPropertyEntity;
import accomodation.booking.com.entities.UserEntity;


@CrossOrigin(origins = "http://localhost:4200",allowedHeaders="*")
@RestController
@RequestMapping("/api")



public class UserController {

	    @Autowired
	    private UserRepository userRepository;
	
//Getting all the users	
		@GetMapping("/users")
		public List<UserEntity> getAllUsers(){
		System.out.println("Get all Users....");
		
		List<UserEntity> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		
		return users;
		}


//Posting or creating a new user
		@PostMapping(value="/create")
		public UserEntity postUser(@RequestBody UserEntity user) {
			user.setType("customer");
			UserEntity _user = userRepository.save(user);
		    return _user;
		}
		
	
		

//Updating User		
       @RequestMapping(method=RequestMethod.PUT,value=("/update"))
       public UserEntity updateUser(@RequestBody UserEntity users) {
       users.setType("customer");
       return userRepository.save(users);
       }
	
//Deleting one user
	   @DeleteMapping("/users/delete/{id}")
	   public ResponseEntity<String> deleteUser(@PathVariable ("id") Long id){
			   
	  System.out.println("Delete User with ID=" + id+ ".....");
		   
	  userRepository.deleteById(id);
		   
	  return new ResponseEntity<>("User has been deleted!", HttpStatus.OK);
		 
	  }

//Searching
	   
	 @GetMapping("/users/{email}") 
	 public List<UserEntity> searchUser(@PathVariable("email") String email){
	 
	 List<UserEntity> searchuser = new ArrayList<>();
	 
	 List<UserEntity> users = new ArrayList<>();
	 userRepository.findAll().forEach(users::add);
	 
	 searchuser = (List<UserEntity>) users.stream().filter(t->t.getEmail().equals(email) || t.getPhone().equals(email)).collect(Collectors.toList());
	 
	 return searchuser;
	 }

//Delete all the users
	 @DeleteMapping("/users/delete")
	 public ResponseEntity<String> deleteAllUsers(){
	 System.out.println("Delete All User");
			
	 userRepository.deleteAll();
			
	 return new ResponseEntity<>("All user have been deleted!", HttpStatus.OK);
	 }
		  
//Login user
       @GetMapping(value="/users/login/{email}/{password}")
      
       public UserEntity login(@PathVariable String email,@PathVariable String password){
    	
       UserEntity user = null;
       String status = null;
      
       List<UserEntity> users = new ArrayList<>();
       userRepository.findAll().forEach(users::add);
      
       for(int i=0; i<users.size(); i++) {
    	   
       
       if(users.get(i).getEmail().equals(email) && users.get(i).getPassword().equals(password)) {
      
       user = users.get(i);
       status = "Successful";
       return user;
       }
       else { status = "Unsuccessful";}
       }
       return user;
       }

       
    //Get user by email
       @GetMapping(value="/users/forgetpassword/{email}")
       public UserEntity GetByEmai(@PathVariable String email){
    	
       UserEntity user = null;
       String status = null;
      
       List<UserEntity> users = new ArrayList<>();
       userRepository.findAll().forEach(users::add);
      
       for(int i=0; i<users.size(); i++) {
    	   
       
       if(users.get(i).getEmail().equals(email)) {
      
       user = users.get(i);
       status = "Successful";
       return user;
       }
       else { status = "Unsuccessful";}
       }
       return user;
       }
       
//Create new user     
Map<String, String> errors;

    @PostMapping("/createUser")
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserEntity user, BindingResult bindingResult ){
	
	if(bindingResult.hasErrors()) {
		
	errors = new HashMap<>();
	for(FieldError error:bindingResult.getFieldErrors()) {
		
	errors.put(error.getField(), error.getDefaultMessage());
	}
	
	return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
	}
	UserEntity _user = userRepository.findByEmail(user.getEmail());
	
//if the user already exists return status code 400
	if(_user!=null) {
	return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	if(user.getRole_description()==null) {
	user.setType("customer");
	user.setRole_description("2");
	}else
	{
		user.setType("admin");
	}
	return new ResponseEntity<>(userRepository.save(user),HttpStatus.OK);
    }
    
    }
    
    







