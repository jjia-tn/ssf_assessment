package ibf2022.batch2.ssf.ssf_assessment.service;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import ibf2022.batch2.ssf.ssf_assessment.model.Quotation;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class QuotationService {

    public final static String URL = "https://quotation.chuklee.com/quotation";

    public static Quotation getQuotations(List<String> items) throws Exception, NumberFormatException {

        Quotation quotation = new Quotation();

    // public void getQuotations(List<String> items) throws Exception {

        JsonArrayBuilder jsonArrBuilder = Json.createArrayBuilder(items);
        JsonArray jsonArr = jsonArrBuilder.build();
    
        RequestEntity<String> req = RequestEntity
            .post(URL)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(jsonArr.toString(), String.class);
    
        RestTemplate template = new RestTemplate();
    
        ResponseEntity<String> resp = null;
        
        String payload; 
        // int statusCode;

        try {

            resp = template.exchange(req, String.class);
            payload = resp.getBody();
            // statusCode = resp.getStatusCode().value();

        } catch (HttpClientErrorException ex) {

            payload = ex.getResponseBodyAsString();
            // statusCode = resp.getStatusCode().value();
        }
    
        // System.out.printf("Status code: %s\n", statusCode);
        // System.out.printf("Payload: %s\n", payload);

        Map<String, Float> temp = new HashMap<>();

        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject json = reader.readObject();
        String quoteId = json.getString("quoteId");
        JsonArray quotations = json.getJsonArray("quotations");
        for (int i = 0; i < quotations.size(); i++) {

            JsonObject jsonObj = quotations.getJsonObject(i);
            String item = jsonObj.getString("item");
            Float unitPrice = Float.parseFloat(jsonObj.getJsonNumber("unitPrice").toString());

            temp.put(item, unitPrice);
            // quotation.addQuotation(item, unitPrice);

        }

        quotation.setQuotations(temp);
        quotation.setQuoteId(quoteId);

        return quotation;
    }
    
}
