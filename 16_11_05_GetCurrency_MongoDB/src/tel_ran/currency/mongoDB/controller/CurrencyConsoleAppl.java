package tel_ran.currency.mongoDB.controller;

import java.lang.reflect.Method;
import java.util.*;

import org.springframework.web.client.RestTemplate;

import tel_ran.currency.mongoDB.dao.Operations;
import tel_ran.currency.mongoDB.entity.Currency;

public class CurrencyConsoleAppl {
	static RestTemplate restTemplate = new RestTemplate();
	static String url = "http://api.fixer.io/latest";
	static Operations operation;

public static void main(String[] args) {
	//НЕправильно !!! Надо контекст здесь, а в Операшнз только ссылка
		Currency json = restTemplate.getForObject(url, Currency.class);
		Map<String, Double> rates = json.getRates();
		rates.put("EUR", 1D);
		operation = new Operations(rates);

		startAppl();
		while (true)

		{
			System.out.println("Enter one of the operations or 'exit'");
			String action = getAction();
			if ((action == null) || (action.equals("exit")))
				break;
			runOperation(action);
		}
	}

	private static void runOperation(String action) {
		String tmp[] = action.split(" ");
		String methodName = tmp[0];
		try {
			Method method = operation.getClass().getDeclaredMethod(methodName, String.class);
			method.invoke(operation, action);
		} catch (Exception e) {
			System.out.println("Wrong operation name!");
		}

	}

	private static void startAppl() {
		System.out.println("List of operations:");
		System.out.println("currencies");
		System.out.println("currency CUR");
		System.out.println("convert CUR_from CUR_to SUM");
		System.out.println("statisticsM CUR YEAR");
		System.out.println("statisticsY CUR YEAR_from YEAR_to");
	}

	@SuppressWarnings("resource")
	private static String getAction() {
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		return line;
	}
}
