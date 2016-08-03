package com.example.sam_chordas.stockhawk;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;

import java.util.ArrayList;

/**
 * Created by Soledad on 8/3/2016.
 */
public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory{

    ArrayList<WidgetInformation> mcollection = new ArrayList<>();
    Cursor cursor;
    Context context;
    Intent intent;

    private void initData(){
        mcollection.clear();
        cursor = context.getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,
                new String[]{QuoteColumns._ID, QuoteColumns.SYMBOL, QuoteColumns.BIDPRICE,
                        QuoteColumns.PERCENT_CHANGE, QuoteColumns.CHANGE, QuoteColumns.ISUP},
                QuoteColumns.ISCURRENT + " = ?",
                new String[]{"1"},
                null);

        while (cursor.moveToNext()){
            mcollection.add(new WidgetInformation(cursor.getString(cursor.getColumnIndex(QuoteColumns.SYMBOL)),cursor.getString(cursor.getColumnIndex(QuoteColumns.BIDPRICE)),cursor.getString(cursor.getColumnIndex(QuoteColumns.CHANGE))));
        }

    }

    public WidgetDataProvider(Context context, Intent intent){
        this.context = context;
        this.intent = intent;
    }

    @Override
    public void onCreate() {
        initData();

    }

    @Override
    public void onDataSetChanged() {
        initData();

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mcollection.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.widget_item); //I created a customized widget_item layout instead of using android simple list layout
        remoteView.setTextViewText(R.id.stock_symbol,mcollection.get(position).getSymbol());
        remoteView.setTextViewText(R.id.bid_price,mcollection.get(position).getBid());
        remoteView.setTextViewText(R.id.stock_change,mcollection.get(position).getChange());

        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
