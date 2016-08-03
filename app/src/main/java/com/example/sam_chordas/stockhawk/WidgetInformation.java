package com.example.sam_chordas.stockhawk;

/**
 * Created by Soledad on 8/3/2016.
 */
public class WidgetInformation {
    //Data that we need to call for the widget
    public String getSymbol(){
        return symbol;
    }
    public String getBid(){
        return bid;
    }
    public String getChange(){
        return change;
    }
    private String symbol;
    private String bid;
    private String change;

    public WidgetInformation (String symbol, String bid, String change){
        this.symbol = symbol;
        this.bid = bid;
        this.change = change;
    }
}
