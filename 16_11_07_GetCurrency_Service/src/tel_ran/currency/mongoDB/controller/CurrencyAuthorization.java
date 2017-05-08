package tel_ran.currency.mongoDB.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import static tel_ran.currency.api.UrlConstants.*;

@Configuration
public class CurrencyAuthorization extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/account").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/"+CURRENCIES).permitAll();
		http.authorizeRequests().antMatchers("/"+CURRENCY).hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers("/"+CONVERT, "/"+STATISTICS_M,"/"+STATISTICS_Y).hasRole("BROKER");
//		http.authorizeRequests().antMatchers("/"+STATISTICS_M).hasRole("BROKER");
//		http.authorizeRequests().antMatchers("/"+STATISTICS_Y).hasRole("BROKER");
		http.csrf().disable();
		http.httpBasic();
	}
//{"name":"broker", "password":"123", "roles":["ROLE_BROKER"]}
}
