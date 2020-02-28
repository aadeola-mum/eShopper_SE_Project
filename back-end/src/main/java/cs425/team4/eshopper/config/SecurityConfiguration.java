package cs425.team4.eshopper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import cs425.team4.eshopper.utils.JWTAuthenticationFilter;
import cs425.team4.eshopper.utils.JWTAuthorizationFilter;
import cs425.team4.eshopper.utils.SecurityConstants;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTAuthorizationFilter jwtAuthorizationFilter;
    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;
    
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //web.debug(true); uncomment to enable more debugging
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.cors()
    	.and()
    	.csrf().disable()
    	.authorizeRequests()
        	.antMatchers(HttpMethod.POST,"/login","/", SecurityConstants.SIGN_UP_URL).permitAll()
        	.anyRequest().authenticated()
        	.and()
        .formLogin()
			.loginPage("/login")
			.permitAll()
			.and()
		.logout()
			.permitAll()
			.and()
        .addFilter(jwtAuthenticationFilter)
        .addFilter(jwtAuthorizationFilter)
        // this disables session creation on Spring Security
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
      final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
      return source;
    }
    
    @Bean
    JWTAuthenticationFilter getJwtAuthenticationFilter() throws Exception {
    	return new JWTAuthenticationFilter(authenticationManager());
    }
    
    @Bean
    JWTAuthorizationFilter getJWTAuthorizationFilter() throws Exception {
    	return new JWTAuthorizationFilter(authenticationManager());
    }
}
