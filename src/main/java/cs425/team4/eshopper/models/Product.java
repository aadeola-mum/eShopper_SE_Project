package cs425.team4.eshopper.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "products")
@SecondaryTable(name = "product_details", pkJoinColumns = @PrimaryKeyJoinColumn(name = "product_id"))
public class Product {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "This field is required")
	private String category;
	@NotBlank(message = "This field is required")
	private String title;
	@NotBlank(message = "This field is required")
	private String summary;
	@NotBlank(message = "This field is required")
	private String description;
	@NotBlank(message = "This field is required")
	private double discount;
	@NotBlank(message = "This field is required")
	private double price;
	
	@NotBlank(message = "This field is required")
	private long qtyAvail;
	
	@NotNull @ManyToOne 
	private Merchant merchant;
	
	@Column(table = "product_details", nullable = true, columnDefinition = "LONGBLOB")
	@Lob
	private byte[] image_1;
	
	@Column(table = "product_details", nullable = true, columnDefinition = "LONGBLOB")
	@Lob
	private byte[] image_2;
	
	@Column(table = "product_details", nullable = true, columnDefinition = "LONGBLOB")
	@Lob
	private byte[] image_3;
	
	@ManyToMany
	private List<ProductCategory> categories;

	/**
	 * 
	 */
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the qtyAvail
	 */
	public long getQtyAvail() {
		return qtyAvail;
	}

	/**
	 * @param qtyAvail the qtyAvail to set
	 */
	public void setQtyAvail(long qtyAvail) {
		this.qtyAvail = qtyAvail;
	}

	/**
	 * @return the merchant
	 */
	public Merchant getMerchant() {
		return merchant;
	}

	/**
	 * @param merchant the merchant to set
	 */
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@Override
	public String toString() {
		return String.format(
				"Product [id=%s, category=%s, title=%s, summary=%s, description=%s, discount=%s, price=%s, qtyAvail=%s, merchant=%s]",
				id, category, title, summary, description, discount, price, qtyAvail, merchant);
	}
	
	
	
	
	
	
	
}
