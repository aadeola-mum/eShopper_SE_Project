package cs425.team4.eshopper.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Role {
    @Id
    private String type;
    @ManyToMany(mappedBy = "roles")
    private List<User> user = new ArrayList<>();
    public Role() {}
    public Role(String type) { this.type = type; }

    public String getType() {
        return type;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public List<User> getUser() {
        return user;
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
        this.user.remove(user);
    }

}

