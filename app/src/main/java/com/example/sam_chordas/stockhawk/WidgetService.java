package com.example.sam_chordas.stockhawk;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Soledad on 8/3/2016.
 */
public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        //return remote view factory here
        return new WidgetDataProvider(this,intent);
    }
}
