package pl.printo3d.test4;

import java.util.Collections;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // TODO Auto-generated method stub
    //super.configure(auth);
    auth.inMemoryAuthentication().withUser(new User("Luj", "Luj", Collections.singleton(new SimpleGrantedAuthority("user"))));
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // TODO Auto-generated method stub
    //super.configure(http);
    http.authorizeRequests()
        .antMatchers("/test1").authenticated()
        .and().formLogin()
        .permitAll();
  }
  
}