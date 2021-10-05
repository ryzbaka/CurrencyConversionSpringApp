package com.currency.converter;

public class ConvertData {
    public final double amount;
    public final String source;
    public final String target;

    public ConvertData(){
        this.amount = 0;
        this.source = "NOT_DEFINED";
        this.target = "NOT_DEFINED";
    }
    public ConvertData(long amt, String src, String tgt){
        this.amount = amt;
        this.source = src;
        this.target = tgt;
    }

    public double getAmount(){
        return this.amount;
    }

    public String getSource(){
        return this.source;
    }

    public String getTarget(){
        return this.target;
    }
}
