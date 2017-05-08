package tel_ran.currency.mongoDB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.*;

import tel_ran.security.accounts.AccountData;
import tel_ran.security.accounts.repo.AccountRepository;

@Configuration
public class CurrencyAuthentication implements UserDetailsService {
@Autowired
AccountRepository accounts;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountData account = accounts.findOne(username);
		if (account==null)
			throw new UsernameNotFoundException("wrong username");
		
		return new User(username, account.getPassword(), AuthorityUtils.createAuthorityList(account.getRoles()));
	}

}
