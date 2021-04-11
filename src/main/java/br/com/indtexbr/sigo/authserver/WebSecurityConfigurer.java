package br.com.indtexbr.sigo.authserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.indtexbr.sigo.authserver.repository.UserRepository;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	UserRepository userRepository;	
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	/*@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		return super.userDetailsService();
	}*/

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Bean
	public UserDetailsService userDetailsServiceImpl() {
	    return new UserDetailsServiceImpl(userRepository);
	}	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl()).passwordEncoder(passwordEncoder());

		/*
		 * auth.inMemoryAuthentication() .passwordEncoder(passwordEncoder())
		 * .withUser("joao") .password(passwordEncoder().encode("joaopassword"))
		 * .roles("USER");
		 */
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		;
	}

}
