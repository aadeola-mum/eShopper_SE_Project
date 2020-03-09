/**
 * 
 */
package cs425.team4.eshopper.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;


/**
 * @author miu
 *
 */
@Entity(name = "Merchants")
public class Merchant extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -905427482016727473L;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address officeAddress;
	
	@NotBlank(message = "This field is required")
	@Column(name="office_phone_1", nullable = false)
	private String officePhoneNumber1;
	@Column(name="office_phone_2", nullable = true)
	private String officePhoneNumber2;
	@Column(name="identity_proof_img_url", nullable = true)
	private String identityProof;
	@Column(name="can_sell")
	private boolean approved;
	
	@OneToMany(mappedBy = "merchant")
	private List<Product> products;
	
	
	/**
	 * 
	 */
	public Merchant() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the officeAddress
	 */
	public Address getOfficeAddress() {
		return officeAddress;
	}
	/**
	 * @param officeAddress the officeAddress to set
	 */
	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}
	/**
	 * @return the officePhoneNumber1
	 */
	public String getOfficePhoneNumber1() {
		return officePhoneNumber1;
	}
	/**
	 * @param officePhoneNumber1 the officePhoneNumber1 to set
	 */
	public void setOfficePhoneNumber1(String officePhoneNumber1) {
		this.officePhoneNumber1 = officePhoneNumber1;
	}
	/**
	 * @return the officePhoneNumber2
	 */
	public String getOfficePhoneNumber2() {
		return officePhoneNumber2;
	}
	/**
	 * @param officePhoneNumber2 the officePhoneNumber2 to set
	 */
	public void setOfficePhoneNumber2(String officePhoneNumber2) {
		this.officePhoneNumber2 = officePhoneNumber2;
	}
	/**
	 * @return the identityProof
	 */
	public String getIdentityProof() {
		return identityProof;
	}
	/**
	 * @param identityProof the identityProof to set
	 */
	public void setIdentityProof(String identityProof) {
		this.identityProof = identityProof;
	}
	/**
	 * @return the approved
	 */
	public boolean isApproved() {
		return approved;
	}
	/**
	 * @param approved the approved to set
	 */
	public void setApproved(boolean approved) {
		this.approved = approved;
	} 
	
	
}
