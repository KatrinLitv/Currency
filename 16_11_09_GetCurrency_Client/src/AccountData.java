import java.util.Arrays;

public class AccountData {
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
@Override
public String toString() {
	return "AccountData [username=" + username + ", password=" + password + ", roleS=" + Arrays.toString(roles) + "]";
}


}
