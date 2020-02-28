package cs425.team4.eshopper.services;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cs425.team4.eshopper.models.Address;
import cs425.team4.eshopper.models.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4677657640652141814L;
	private String username;
    private String password;
    private String fullnames;
    private Address billingAddress;
    private Address shippingAddress;
    private boolean isActive;
    private List<GrantedAuthority> authorities;

    public UserDetailsImpl(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.isActive = user.isEnabled();
        this.billingAddress = user.getBillingAddress();
        this.shippingAddress = user.getShippingAddress();
        this.fullnames = user.getLastName()+", "+user.getFirstName();
        this.authorities = user.getRoles().stream().map(x -> new SimpleGrantedAuthority(x.getType())).collect(Collectors.toList());
    }

    public UserDetailsImpl() {}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }

	/**
	 * @return the fullnames
	 */
	public String getFullnames() {
		return fullnames;
	}

	/**
	 * @param fullnames the fullnames to set
	 */
	public void setFullnames(String fullnames) {
		this.fullnames = fullnames;
	}

	/**
	 * @return the billingAddress
	 */
	public Address getBillingAddress() {
		return billingAddress;
	}
	/**
	 * @return the shippingAddress
	 */
	public Address getShippingAddress() {
		return shippingAddress;
	}


    
    
}
