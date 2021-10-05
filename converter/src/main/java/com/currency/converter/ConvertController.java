package com.currency.converter;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ConvertController {
    @Value("${key.name}")
    private String key_name;

    @PostMapping("/convert")
    public ConvertedValue convert(@RequestBody ConvertData inputData){
        String source = inputData.getSource();
        String target = inputData.getTarget();
        double amount = inputData.getAmount();

        String uri = "http://api.exchangeratesapi.io/v1/latest?access_key="+key_name;
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri,String.class);
		ObjectMapper mapper = new ObjectMapper();
		double[] rates={-1.0,-1.0};
		try{
		 JsonNode rootNode = mapper.readTree(result);
		 rates[0] = rootNode.get("rates").get(source).asDouble();
		 rates[1] = rootNode.get("rates").get(target).asDouble();
		}catch(Exception e){
			System.out.println(e);
		}
        if((rates[0]==-1)||(rates[1]==-1)){
            ConvertedValue convertedCurrency = new ConvertedValue(false,-1.0,"ERROR","Invalid currency code used. Please check your input and try again."); 
            return convertedCurrency;
        }else{
            double sourceRate = 1/rates[0];
            double euAmt = amount * sourceRate;
            double targetAmt = euAmt * rates[1];
            ConvertedValue convertedCurrency = new ConvertedValue(true,targetAmt, target,"Success!");
            return convertedCurrency;
        }
    }
}
