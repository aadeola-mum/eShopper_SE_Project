package cs425.team4.eshopper.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    
    public Role() {}
    
    
    

    /**
	 * @param id
	 * @param type
	 */
	public Role(long id, String type) {
		super();
		this.id = id;
		this.type = type;
	}


	/**
	 * @param type
	 */
	public Role(String type) {
		super();
		this.type = type;
	}




	public String getType() {
        return type;
    }
    
    

   
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	private void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(type, role.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
    public void removeuser(User user) {
        //this.user.remove(user);
    }

}

