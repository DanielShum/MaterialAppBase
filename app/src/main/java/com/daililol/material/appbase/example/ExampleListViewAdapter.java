package com.daililol.material.appbase.example;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daililol.material.appbase.utility.Converter;

import java.util.Vector;

/**
 * Created by DennyShum on 8/22/15.
 */
public class ExampleListViewAdapter extends BaseAdapter{

    private Vector<String> dataSet;

    public ExampleListViewAdapter(Vector<String> dataSet){
        this.dataSet = dataSet;
    }


    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View itemView, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if (itemView == null){
            itemView = new LinearLayout(viewGroup.getContext());
            AdapterView.LayoutParams layoutParams =
                    new AdapterView.LayoutParams(AdapterView.LayoutParams.MATCH_PARENT, Converter.dp2px(itemView.getContext(), 56));

            itemView.setPadding(Converter.dp2px(itemView.getContext(), 16), 0,
                    Converter.dp2px(itemView.getContext(), 16), 0);
            ((LinearLayout)itemView).setGravity(Gravity.LEFT | Gravity.START | Gravity.CENTER);
            itemView.setBackgroundResource(R.drawable.touch_feedback_holo_light);
            itemView.setLayoutParams(layoutParams);


            TextView textView = new TextView(viewGroup.getContext());
            textView.setTextSize(18);
            textView.setId(android.R.id.text1);

            ((LinearLayout)itemView).addView(textView);

            viewHolder = new ViewHolder(itemView);

        }else{
            viewHolder = (ViewHolder)itemView.getTag();
        }

        viewHolder.textView.setText(dataSet.get(i));

        return itemView;
    }

    class ViewHolder{
        public TextView textView;
        public ViewHolder(View itemView) {
            textView = (TextView)itemView.findViewById(android.R.id.text1);
            itemView.setTag(this);
        }
    }
}
