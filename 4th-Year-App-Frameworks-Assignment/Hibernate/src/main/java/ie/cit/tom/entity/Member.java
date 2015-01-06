package ie.cit.tom.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Table(name="member")
public class Member {	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;	
	@NotEmpty
	@Size(min=4, max=50)
	private String name;
	@NotEmpty
	@Size(min=2, max=50)
	private String address1;
	@Size(min=0, max=50)
	private String address2;
	@NotEmpty
	@Size(min=2, max=50)
	private String town;
	@NotEmpty
	@Size(min=4, max=50)
	private String contact_number;
	@NotNull
	@Min(0)
	@Max(5)
	private int book_allowance;
	@NotNull
	@DecimalMin(value="0.00", message="Must be at least 0.00")
	@DecimalMax("50.00")
	private BigDecimal balance;
	@NotNull
	private boolean active;

	public boolean isLate() {
		return balance.compareTo(new BigDecimal("10.00")) > 0 ? true : false;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public int getBook_allowance() {
		return book_allowance;
	}
	public void setBook_allowance(int book_allowance) {
		this.book_allowance = book_allowance;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
}
