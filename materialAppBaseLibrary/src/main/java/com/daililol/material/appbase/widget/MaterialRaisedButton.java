package com.daililol.material.appbase.widget;


import com.daililol.material.appbase.R;
import com.daililol.material.appbase.utility.DrawableUtil;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class MaterialRaisedButton extends AppCompatButton{

	public MaterialRaisedButton(Context context){
		this(context, null);
	}
	
	public MaterialRaisedButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	 
		int theme = 0;
		int normalStateColor;
		int pressedStateColor;
		int textColor;

		if (attrs != null){
			TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.MaterialButton);
			theme = t.getInt(R.styleable.MaterialButton_button_theme, 0);
			t.recycle();
		}
		
		
		if (theme == 0){
			normalStateColor = getContext().getResources().getColor(R.color.base_ui_white);
			pressedStateColor = getContext().getResources().getColor(R.color.base_ui_light_grey);
			textColor = getContext().getResources().getColor(R.color.base_text_color_dark);
		}else{
			normalStateColor = getContext().getResources().getColor(R.color.base_ui_blue);
			pressedStateColor = getContext().getResources().getColor(R.color.base_ui_blue_pressed);
			textColor = getContext().getResources().getColor(R.color.base_ui_white);
		}
		
		if (attrs != null){
			TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.MaterialButton);
			normalStateColor = t.getColor(R.styleable.MaterialButton_normal_state_color, normalStateColor);
			pressedStateColor = t.getColor(R.styleable.MaterialButton_pressed_state_color, pressedStateColor);
			textColor = t.getColor(R.styleable.MaterialButton_android_textColor, textColor);
			t.recycle();
		}

		ColorStateList colorList;
		
		if (Build.VERSION.SDK_INT >= 21){
			colorList = DrawableUtil.createColorStateListAPI21(normalStateColor);
		}else{
			colorList = DrawableUtil.createColorStateList(normalStateColor, pressedStateColor, pressedStateColor, pressedStateColor, normalStateColor);
		}
		
		this.setTextColor(textColor);
		this.setSupportBackgroundTintList(colorList);

	
	}
	
	
	

	

}
