package com.daililol.material.appbase.component;

import java.util.ArrayList;

import com.daililol.material.appbase.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BaseNavigationDrawerListAdapter extends BaseAdapter{

	private Context context;
	private ArrayList<MenuItem> menuList;
    private int selectedItem = -1;
	
	
	public void addItem(String text, Drawable icon){
		menuList.add(new MenuItem(text, icon));
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder viewHolder;
		
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.base_item_for_navigation_drawer, null);
			viewHolder  = new ViewHolder(convertView);
		}else{
			viewHolder = (ViewHolder)convertView.getTag();
		}

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
		public String text;
		public Drawable icon;
		
		public MenuItem(String text, Drawable icon){

			this.text = text;
			this.icon = icon;
		}
	}

}
