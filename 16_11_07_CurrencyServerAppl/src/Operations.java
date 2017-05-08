import java.util.Map;
import java.util.Map.Entry;

public class Operations {
	Map<String,Double> rates;	
	
	public Operations(Map<String, Double> rates) {
		super();
		this.rates = rates;
	}

	public void convert(String args) {
		String params[]= args.split(" ");
		String oldCurrName= params[1];
		String newCurrName=params[2];
		int sum=0;
		try {
			sum = Integer.parseInt(params[3]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		double curOld = rates.get(oldCurrName);
		double curNew = rates.get(newCurrName);
		System.out.println(curNew * sum / curOld);		
	}

	public void currency(String args) {
		String tmp [] = args.split(" ");
		String currecyName = tmp[1];
		System.out.println("Result of your request:");
		System.out.println(currecyName+ " : "+rates.get(currecyName));		
	}

	public void currencies(String args) {
		for (Entry<String, Double> entry : rates.entrySet()){
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}		
	}
}
