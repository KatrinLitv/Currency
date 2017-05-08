
import static tel_ran.currency.api.UrlConstants.*;

import java.util.Base64;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import tel_ran.currency.api.CurrencyRequest;

public class CurrencyTestAppl {
	static final String URL= "http://localhost:8080/";
	static RestTemplate restTemplate=new RestTemplate();
	
	public static void main(String[] args) {
		CurrencyRequest requestBody = new CurrencyRequest();
		HttpHeaders headers= new HttpHeaders();
		//Authentication
		String token="admin:admin";
		String authToken = Base64.getEncoder().encodeToString(token.getBytes());
		headers.add("Authorization", "Basic "+authToken);
		
//		HttpEntity<AccountData> requestPut = 
//				new HttpEntity<>(new AccountData("Vasya", "1234", new String[]{"BROKER"}),headers);
//		 restTemplate.put(URL+"/account", requestPut);
		
		HttpHeaders headersBroker= new HttpHeaders();
		String tokenBroker="Vasya:1234";
		String authTokenBroker = Base64.getEncoder().encodeToString(tokenBroker.getBytes());
		headersBroker.add("Authorization", "Basic "+authTokenBroker);
		
		HttpEntity<String> requestGet=new HttpEntity<>(headersBroker);
		ResponseEntity<String> responseCURRENCY=restTemplate.exchange(URL+"/"+CURRENCY,
				HttpMethod.GET,requestGet,String.class);
		System.out.println(responseCURRENCY.getBody());
	}

}
