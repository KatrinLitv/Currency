package tel_ran.security.accounts;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document(collection="CurrencyFixer_account")
public class AccountData {
@Id
String username;
String password;
String[] roles;

public AccountData(){};
public AccountData(String username, String password, String[] roles) {
	super();
	this.username = username;
	this.password = password;
	this.roles = roles;
}
public String getUsername() {
	return username;
}
public String getPassword() {
	return password;
}
public String[] getRoles() {
	return roles;
}


}
