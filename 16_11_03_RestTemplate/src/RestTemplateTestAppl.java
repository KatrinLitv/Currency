import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestTemplateTestAppl {
static RestTemplate restTemplate = new RestTemplate();
static String url = "http://api.fixer.io/latest";
static ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) throws Exception  {
		//String json = restTemplate.getForObject(url, String.class);
		
		//Map<String, Object> json = restTemplate.getForObject(url, Map.class);
		//double currencyValue = getCurrencyValue("RUB", json);
		//System.out.println(currencyValue);
		
		//Для простых случаев
		Currency json = restTemplate.getForObject(url, Currency.class);
		Map<String,Double> rates=json.getRates();
		Date date = json.getDate();
		double currencyValue = rates.get("RUB");
		System.out.println(json);
		System.out.println(currencyValue);
		System.out.println(date);
		
		//----  Для сложных случаев - List конкретных параметризованных классов
		HttpEntity<Map<String,Object>> map = restTemplate.exchange(url, HttpMethod.GET,null, 
				new ParameterizedTypeReference<Map<String,Object>>() {
		});
	}

	private static double getCurrencyValue(String string, Map<String, Object> json) {
		Map<String,Double> resMap = (Map<String, Double>) json.get("rates");
		Double res = resMap.get(string);
		return res==null ? 0 : res;
	}

}
