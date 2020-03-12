package cs425.team4.eshopper.models;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Indexed;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import cs425.team4.eshopper.View;

@Entity(name = "products")
@SecondaryTable(name = "product_details", pkJoinColumns = @PrimaryKeyJoinColumn(name = "product_id"))
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"merchant_user_id","title"})})
public class Product {
	@JsonView(View.Summary.class)
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonView(View.Summary.class)
	@NotBlank(message = "Title field is required")
	private String title;
	
	@JsonView(View.Summary.class)
	@NotBlank(message = "Summary field is required")
	private String summary;
	
	@JsonView(View.Summary.class)
	@NotBlank(message = "Description field is required")
	private String description;
	
	@JsonView(View.Summary.class)
	@NotNull(message = "Discount field is required")
	private double discount;
	
	@JsonView(View.Summary.class)
	@NotNull(message = "Price field is required")
	private double price;
	
	@JsonView(View.Summary.class)
	@NotNull(message = "Quantity Available field is required")
	private long qtyAvail;
	
	private boolean isAvailable = true;
	 
	@JsonView(View.Summary.class)
	@ManyToOne(cascade = CascadeType.ALL)
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
	
	@JsonBackReference
	@OneToOne(cascade = CascadeType.ALL)
	@NotNull(message = "Category field is required")
	private ProductCategory category;

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
	 * @return the category
	 */
	public ProductCategory getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(ProductCategory category) {
		this.category = category;
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
	
	

	/**
	 * @return the image_1
	 */
	public byte[] getImage_1() {
		return image_1;
	}

	/**
	 * @param image_1 the image_1 to set
	 */
	public void setImage_1(byte[] image_1) {
		this.image_1 = image_1;
	}

	/**
	 * @return the image_2
	 */
	public byte[] getImage_2() {
		return image_2;
	}

	/**
	 * @param image_2 the image_2 to set
	 */
	public void setImage_2(byte[] image_2) {
		this.image_2 = image_2;
	}

	/**
	 * @return the image_3
	 */
	public byte[] getImage_3() {
		return image_3;
	}

	/**
	 * @param image_3 the image_3 to set
	 */
	public void setImage_3(byte[] image_3) {
		this.image_3 = image_3;
	}
	
	
	/**
	 * @return the isAvailable
	 */
	public boolean isAvailable() {
		return isAvailable;
	}

	/**
	 * @param isAvailable the isAvailable to set
	 */
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return String.format(
				"Product [id=%s, title=%s, summary=%s, description=%s, discount=%s, price=%s, qtyAvail=%s, merchant=%s, image_1=%s, image_2=%s, image_3=%s, categories=%s]",
				id, title, summary, description, discount, price, qtyAvail, merchant, Arrays.toString(image_1),
				Arrays.toString(image_2), Arrays.toString(image_3), category);
	}

	
	
	
	
	
	
	
	
}
