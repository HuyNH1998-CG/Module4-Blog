package home.security;

import home.service.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    BlogUserService blogUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(blogUserService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/blog").permitAll()
                .and()
                .authorizeRequests().antMatchers("/create/blog").hasAnyRole("ADMIN","USER")
                .and()
                .authorizeRequests().antMatchers("/create/category").hasRole("ADMIN")
                .and()
                .formLogin().successHandler(new SecSuccessHandle())
                .and()
                .logout().logoutSuccessUrl("/blog");
    }
}
