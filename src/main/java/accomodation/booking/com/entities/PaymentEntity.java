package accomodation.booking.com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="payments")


@JsonIgnoreProperties({"hibernateLaziInitializer","handler"})
public class PaymentEntity {
	
    @Id 
    @Column(name="payment_id")
    @GeneratedValue
     private Long id;
    
    
    @ManyToOne
    private UserEntity user_entity;
     
    @OneToOne(fetch = FetchType.LAZY)
    private BookingEntity bookingEntity;

	@Column(name="card_holder_name")
    @NotBlank(message = "Please enter card holder name")
    private String card_holder_name;
    
    @Column(name="card_type")
    @NotBlank(message = "Please enter card type")
    private String card_type;
    
    @Column(name="card_number")
    @NotBlank(message = "Please enter card number")
    private String card_number;
    
    @Column(name="expiry_date")
    @NotBlank(message = "Please enter expiry date")
    private String expiry_date;
    
    @Column(name="cvc")
    @NotBlank(message = "Please enter cvc")
    private String cvc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
    @JsonIgnore
    public BookingEntity getBookingEntity() {
		return bookingEntity;
	}

	public void setBookingEntity(BookingEntity bookingEntity) {
		this.bookingEntity = bookingEntity;
	}

	@JsonIgnore
	public UserEntity getUser_entity() {
		return user_entity;
	}

	public void setUser_entity(UserEntity user_entity) {
		this.user_entity = user_entity;
	}

	public String getCard_holder_name() {
		return card_holder_name;
	}

	public void setCard_holder_name(String card_holder_name) {
		this.card_holder_name = card_holder_name;
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public String getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public PaymentEntity() {
	}

	public PaymentEntity(UserEntity user_entity, String card_holder_name, String card_type, String card_number,
			String expiry_date, String cvc) {
		super();
		this.user_entity = user_entity;
		this.card_holder_name = card_holder_name;
		this.card_type = card_type;
		this.card_number = card_number;
		this.expiry_date = expiry_date;
		this.cvc = cvc;
	}

	@Override
	public String toString() {
		return "PaymentEntity [id=" + id + ", user_entity=" + user_entity + ", card_holder_name=" + card_holder_name
				+ ", card_type=" + card_type + ", card_number=" + card_number + ", expiry_date=" + expiry_date
				+ ", cvc=" + cvc + "]";
	}
	
	

	
}
