package com.gogo.demo1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by liuchen_ on 2018/6/13.
 */

public class MainAdapter extends MyBaseAdapter<String> {
    public MainAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_text, null, false);
        }
        ((TextView) convertView.findViewById(R.id.text)).setText(getItem(position));
        return convertView;
    }
}
