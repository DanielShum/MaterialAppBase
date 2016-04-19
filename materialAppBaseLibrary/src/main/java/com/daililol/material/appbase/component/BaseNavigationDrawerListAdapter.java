package com.daililol.material.appbase.component;

import java.util.ArrayList;

import com.daililol.material.appbase.R;
import com.daililol.material.appbase.utility.Converter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BaseNavigationDrawerListAdapter extends BaseAdapter{

	private Context context;
	private ArrayList<MenuItem> menuList;
    private int selectedItem = -1;
	
	
	public void addItem(String text, Drawable icon, MenuItem.Type type){
		menuList.add(new MenuItem(text, icon, type));
		this.notifyDataSetChanged();
	}

    /**
     * Set -1 to dishighlight selected item
     * @param position
     */
	public void setSelectedPosition(int position){
        selectedItem = position;
        notifyDataSetChanged();
    }

	public BaseNavigationDrawerListAdapter(Context context){
		this.context = context;
		this.menuList = new ArrayList<MenuItem>();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return menuList.size();
	}

	@Override
	public Object getItem(int position) {
		
		return null;
	}

	@Override
	public long getItemId(int position) {
		
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		MenuItem.Type type = menuList.get(position).type;

		if (type == MenuItem.Type.DIVIDER){
            AbsListView.LayoutParams convertViewParams = new AbsListView.LayoutParams(
                    AbsListView.LayoutParams.MATCH_PARENT, Converter.dp2px(context, 17));
            convertView = new LinearLayout(context);
            convertView.setLayoutParams(convertViewParams);
            convertView.setBackgroundColor(Color.WHITE);
            ((LinearLayout)convertView).setOrientation(LinearLayout.VERTICAL);
            ((LinearLayout)convertView).setGravity(Gravity.CENTER);

            LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, Converter.dp2px(context, 1));
            View divider = new View(context);
            divider.setLayoutParams(dividerParams);
            divider.setBackgroundColor(ContextCompat.getColor(context, R.color.base_divider_grey));

            ((LinearLayout)convertView).addView(divider);

            return convertView;
		}

        if (type == MenuItem.Type.BLANK_DIVEDER){
            AbsListView.LayoutParams convertViewParams = new AbsListView.LayoutParams(
                    AbsListView.LayoutParams.MATCH_PARENT, Converter.dp2px(context, 8));
            convertView = new View(context);
            convertView.setLayoutParams(convertViewParams);
            convertView.setBackgroundColor(Color.WHITE);
            return convertView;
        }

        convertView = LayoutInflater.from(context).inflate(R.layout.base_item_for_navigation_drawer, null);
        ViewHolder viewHolder  = new ViewHolder(convertView);

        if (selectedItem == position){
            viewHolder.backgroundView.setSelected(true);
            viewHolder.backgroundView.setBackgroundResource(R.color.base_ui_selected_holo_light);
        }else{
            viewHolder.backgroundView.setSelected(false);
            viewHolder.backgroundView.setBackgroundColor(Color.TRANSPARENT);
        }

		MenuItem menuItem = menuList.get(position);
		viewHolder.textView.setText(menuItem.text);
		if (menuItem.icon != null){
			viewHolder.imageView.setVisibility(View.VISIBLE);
			viewHolder.imageView.setImageDrawable(menuItem.icon);
		}else{
			viewHolder.imageView.setVisibility(View.GONE);
		}
		
		return convertView;
	}
	
	private class ViewHolder{
        public RelativeLayout backgroundView;
		public TextView textView;
		public ImageView imageView;
		
		public ViewHolder(View convertView){
            this.backgroundView = (RelativeLayout)convertView.findViewById(R.id.backgroundView);
			this.textView = (TextView)convertView.findViewById(R.id.text);
			this.imageView = (ImageView)convertView.findViewById(R.id.icon);
			convertView.setTag(this);
		}
	}
	
	public static class MenuItem{

		public static enum Type{
			DIVIDER,
            BLANK_DIVEDER,
			MENU_ITEM
		}

		public Type type;
		public String text;
		public Drawable icon;
		
		public MenuItem(String text, Drawable icon, Type type){
			this.type = type;
			this.text = text;
			this.icon = icon;
		}
	}

}
