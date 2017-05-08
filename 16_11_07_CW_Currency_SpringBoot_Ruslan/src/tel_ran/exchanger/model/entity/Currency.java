package tel_ran.exchanger.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import tel_ran.exchanger.model.annotation.Id;
import tel_ran.exchanger.model.annotation.Index;

import java.util.Map;

@Document(collection="currency161105")
public class Currency {

    @Id
    private String id;

    @Index(unique = false)
    private String date;
    private Map<String,Double> rates;

    public Currency(){}

    public void setId(String id) {
        this.id = id;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", date=" + date +
                ", rates=" + rates +
                '}';
    }
}
