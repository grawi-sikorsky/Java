package pl.printo3d.waluty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

  @Bean
  public UserDetailsService userDetailsService()
  {
    UserDetails userDetails = User.withDefaultPasswordEncoder()
    .username("kloc")
    .password("kloc")
    .roles("KIEP")
    .build();

    return new InMemoryUserDetailsManager(userDetails);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests()
    .antMatchers("/login","/img/**","/css/**").permitAll()
    .antMatchers("/").authenticated()
    .anyRequest().hasRole("KIEP")


    .and()
      .formLogin().permitAll()
      .loginPage("/login").permitAll()
      .loginProcessingUrl("/perform_login")
      .defaultSuccessUrl("/home", true)
      .failureUrl("/login.html?error=true")
      .permitAll()
      .and()
      .logout().permitAll()
      .logoutUrl("/perform_logout")
      .deleteCookies("JSESSIONID");


    http.csrf().disable();

  }

  
  
}
