package com.currency.converter;

public class ConvertedValue {
   private final boolean success;
   private final double value;
   private final String currency;
   private final String message;
   public ConvertedValue(boolean success,double value, String currency,String message){
       this.success = success;
       this.value = value;
       this.currency = currency;
       this.message = message;
   }

   public boolean getSuccess(){
       return this.success;
   }
   public double getValue(){
       return this.value;
   }
   public String getCurrency(){
       return this.currency;
   }
   public String getMessage(){
       return this.message;
   }
}
