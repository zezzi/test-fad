package com.zezzi.fadintroduccion;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Modified by zezzi on 7/8/13.
 * based on GTUG Code by Ykro
 */
public class MyAdapter extends SimpleAdapter {
    private List<Integer> priorityList;
    private int[] colors;

    public MyAdapter(Context context, List<? extends Map<String, ?>> data,
                     int resource, String[] from, int[] to,
                     LinkedList<Integer> priorityList, int[] colors) {
        super(context, data, resource, from, to);
        this.priorityList = priorityList;
        this.colors = colors;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        int color = colors[priorityList.get(position).intValue()];
        view.setBackgroundColor(color);
        return view;
    }
}
