package com.currency.converter;

import java.util.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Value;

import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class GetSymbols {
    
    @Value("${key.name}")
    private String key_name;

    @GetMapping("/getSymbols")
    public List<String> getSymbols(){
        String uri="http://api.exchangeratesapi.io/v1/symbols?access_key="+key_name;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri,String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<String> names= new ArrayList<String>();
        try{
            JsonNode rootNode = mapper.readTree(result);
            Iterator<String> fieldNameIterator = rootNode.get("symbols").fieldNames();
            while(fieldNameIterator.hasNext()) {
                String element = fieldNameIterator.next();
                names.add(element);
            }
        }catch(Exception e){
               System.out.println(e);
        }
        return names;
    }
}
