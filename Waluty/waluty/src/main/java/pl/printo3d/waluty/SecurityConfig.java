package pl.printo3d.waluty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

  @Bean
  public UserDetailsService userDetailsService()
  {
    UserDetails userDetails = User.withDefaultPasswordEncoder()
    .username("user")
    .password("user")
    .roles("KIEP")
    .build();

    return new InMemoryUserDetailsManager(userDetails);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests()
    .antMatchers("/login").permitAll()
    .antMatchers("/").permitAll()
    .anyRequest().permitAll() //.hasRole("ADMIN");

    .and()
      .formLogin()
      .loginPage("/login.html")
      .loginProcessingUrl("/perform_login")
      .defaultSuccessUrl("/homepage.html", true)
      .failureUrl("/login.html?error=true")
      .and()
      .logout()
      .logoutUrl("/perform_logout")
      .deleteCookies("JSESSIONID");


    http.csrf().disable();
    
  }

  
  
}
