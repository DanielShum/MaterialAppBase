package com.daililol.material.appbase.widget;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.View;

public class MaterialDropDownMenu extends PopupMenu{

	
	public MaterialDropDownMenu(Context context, View anchor){
		this(context, anchor, null);
	}
	
	public MaterialDropDownMenu(Context context, View anchor, String[] menuItem) {
		super(context, anchor);
		// TODO Auto-generated constructor stub
		setMenu(menuItem);
		
		
	}
	
	public void setMenu(String[] menuItem){
		
		if (menuItem == null || menuItem.length == 0) return;
		for (int i = 0; i < menuItem.length; i++){
			this.getMenu().add(menuItem[i]);
		}
	}
	
	

}
