package speedhome.interview.boot.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("librarian").password("{noop}password").roles("LIBRARIAN").build());
        manager.createUser(User.withUsername("member").password("{noop}password").roles("MEMBER").build());
        return manager;
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/books/**").hasAnyRole("LIBRARIAN", "MEMBER")
//                .antMatchers("/api/members/self").authenticated()
//                .antMatchers("/api/members/**").hasRole("LIBRARIAN")
//                .antMatchers("/web/**", "/error").permitAll() // Allow access to web pages
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .and()
//                .httpBasic();
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/web/**", "/error").permitAll() // Allow access to web pages
//                .antMatchers("/api/**").hasAnyRole("LIBRARIAN", "MEMBER") // Ensure REST endpoints are secured
                .antMatchers("/api/books/**").hasAnyRole("LIBRARIAN", "MEMBER")
                .antMatchers("/api/members/self").authenticated()
                .antMatchers("/api/members/**").hasRole("LIBRARIAN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf()
                .ignoringAntMatchers("/api/**")
                .and().httpBasic(); // Disable CSRF protection for API endpoints
    }
}
