package accomodation.booking.com.entities;


import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLaziInitializer","handler"})
public class UserEntity {

//Details
        @Id 
		@Column(name="user_id")
		@GeneratedValue
		private Long id;
		
        @OneToMany
        @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
		private List<PaymentEntity> paymentEntity;
		
        @OneToMany
        @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
		private List<BookingEntity> booking_entity;
		
        @OneToMany
        @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
		private List<ListPropertyEntity> list_proprty;
        		
		@Email(message="Please Enter a Valid Email")
		@NotBlank(message="Please Enter your Email.")
		@NotBlank(message="Please Enter your Email")
		@Column(name="email")
	    private String email;
		
		@NotBlank(message= "Please Enter your Password")
	    @Size(min=4, message= "Invalid Password, Enter atleast 4 Characters")
	    @Column(name="password")
	    private String password;
	    
		@NotBlank(message = "Please Enter your Phone Number")
		@Size(min=4, message= "Your phone number must be atleast 10 digits.")
	    @Column(name="phone")
	    private String phone;
	    
	    @Column(name="type")
	    private String type;
	    
	    
	    @NotBlank(message="Please Enter your First Name")
	    @Size(min=2, message= "Your First Name must be Wrong")
	    @Column(name="first_name")
	    private String first_name;
	    
	    
	    @NotBlank(message="Please Enter your Last Name")
	    @Size(min=2, message= "Your Last Name must be Wrong")
	    @Column(name="last_name")
	    private String last_name;
	
	    @Column(name="role_description")
	    private String role_description;


		public UserEntity() {

		}


		public UserEntity(Long id, List<PaymentEntity> paymentEntity, List<BookingEntity> booking_entity,
				List<ListPropertyEntity> list_proprty,
				@Email(message = "Please Enter a Valid Email") @NotBlank(message = "Please Enter your Email.") @NotBlank(message = "Please Enter your Email") String email,
				@NotBlank(message = "Please Enter your Password") @Size(min = 4, message = "Invalid Password, Enter atleast 4 Characters") String password,
				@NotBlank(message = "Please Enter your Phone Number") @Size(min = 4, message = "Your phone number must be atleast 10 digits.") String phone,
				String type,
				@NotBlank(message = "Please Enter your First Name") @Size(min = 2, message = "Your First Name must be Wrong") String first_name,
				@NotBlank(message = "Please Enter your Last Name") @Size(min = 2, message = "Your Last Name must be Wrong") String last_name) {
			this.id = id;
			this.paymentEntity = paymentEntity;
			this.booking_entity = booking_entity;
			this.list_proprty = list_proprty;
			this.email = email;
			this.password = password;
			this.phone = phone;
			this.type = type;
			this.first_name = first_name;
			this.last_name = last_name;
		}
		
		public String getRole_description() {
			return role_description;
		}


		public void setRole_description(String role_description) {
			this.role_description = role_description;
		}



		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public List<PaymentEntity> getPaymentEntity() {
			return paymentEntity;
		}


		public void setPaymentEntity(List<PaymentEntity> paymentEntity) {
			this.paymentEntity = paymentEntity;
		}


		public List<BookingEntity> getBooking_entity() {
			return booking_entity;
		}


		public void setBooking_entity(List<BookingEntity> booking_entity) {
			this.booking_entity = booking_entity;
		}


		public List<ListPropertyEntity> getList_proprty() {
			return list_proprty;
		}


		public void setList_proprty(List<ListPropertyEntity> list_proprty) {
			this.list_proprty = list_proprty;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getPhone() {
			return phone;
		}


		public void setPhone(String phone) {
			this.phone = phone;
		}


		public String getType() {
			return type;
		}


		public void setType(String type) {
			this.type = type;
		}


		public String getFirst_name() {
			return first_name;
		}


		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}


		public String getLast_name() {
			return last_name;
		}


		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}


		@Override
		public String toString() {
			return "UserEntity [id=" + id + ", paymentEntity=" + paymentEntity + ", booking_entity=" + booking_entity
					+ ", list_proprty=" + list_proprty + ", email=" + email + ", password=" + password + ", phone="
					+ phone + ", type=" + type + ", first_name=" + first_name + ", last_name=" + last_name + "]";
		}
		
	

	    }
	  
	

