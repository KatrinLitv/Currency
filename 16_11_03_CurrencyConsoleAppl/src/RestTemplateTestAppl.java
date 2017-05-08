import java.lang.reflect.*;
import java.util.*;
import org.springframework.web.client.RestTemplate;

public class RestTemplateTestAppl {
static RestTemplate restTemplate = new RestTemplate();
static String url = "http://api.fixer.io/latest";
static Operations operation;

	public static void main(String[] args) throws Exception  {
		Currency json = restTemplate.getForObject(url, Currency.class);
		Map<String,Double> rates=json.getRates();
		rates.put("EUR", 1D);
		operation = new Operations(rates);
		
		startAppl();		
		while(true){
			System.out.println("Enter one of the operations or 'exit'");
			String action = getAction();
			if ((action==null) || (action.equals("exit")))
				break;			
			runOperation(action);			
		}	
	}

	private static void runOperation(String action) {
		String tmp [] = action.split(" ");
		String methodName = tmp[0];		
		try {
			Method method = operation.getClass().getDeclaredMethod(methodName,String.class);
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
	}

	private static String getAction() {
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		return line;
	}

}
