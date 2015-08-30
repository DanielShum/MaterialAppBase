package com.daililol.material.appbase.widget;

import com.daililol.material.appbase.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class MaterialImageButton extends ImageButton{

	public static enum Theme{
		LIGHT,
		DARK
	}
	
	public MaterialImageButton(Context context){
		this(context, null);
	}
	
	public MaterialImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		int theme = 0;
		
		if (attrs != null){
			TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.MaterialButton);
			theme = t.getInt(R.styleable.MaterialButton_button_theme, 0);
			t.recycle();
		}
		
		setupTheme(Theme.values()[theme]);
	}
	
	
	
	public void setupTheme(Theme theme){
		
		if (theme == Theme.LIGHT){
			this.setBackgroundResource(R.drawable.touch_feedback_holo_light_circle);
		}else{
			this.setBackgroundResource(R.drawable.touch_feedback_holo_dark_circle);
		}
	
	}

}
