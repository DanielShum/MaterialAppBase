package com.daililol.material.appbase.widget;

import com.daililol.material.appbase.R;
import com.daililol.material.appbase.utility.Converter;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

public class MaterialFlatButton extends TextView{

	public static enum Theme{
		LIGHT,
		DARK
	}
	
	public MaterialFlatButton(Context context){
		this(context, null);
	}
	
	public MaterialFlatButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
		int theme = 0;
		int padding = -1;
		int paddingLeft = -1;
		int paddingRight = -1;
		int paddingTop = -1;
		int paddingBottom = -1;
		

		
		if (attrs != null){
			TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.MaterialButton);
			theme = t.getInt(R.styleable.MaterialButton_button_theme, 0);
			padding = (int)t.getDimension(R.styleable.MaterialButton_android_padding, padding);
			paddingLeft = (int)t.getDimension(R.styleable.MaterialButton_android_paddingLeft, paddingLeft);
			paddingRight = (int)t.getDimension(R.styleable.MaterialButton_android_paddingRight, paddingRight);
			paddingTop = (int)t.getDimension(R.styleable.MaterialButton_android_paddingTop, paddingTop);
			paddingBottom = (int)t.getDimension(R.styleable.MaterialButton_android_paddingBottom, paddingBottom);
			t.recycle();
			
		}

		this.setClickable(true);
		
		int defaultPaddingLeft = Converter.dp2px(context, 16);
		int defaultPaddingRight = Converter.dp2px(context, 16);
		int defaultPaddingTop = Converter.dp2px(context, 8);
		int defaultPaddingBottom = Converter.dp2px(context, 8);
		
		if (padding > -1){
			this.setPadding(padding, padding, padding, padding);
		}else{
			this.setPadding((paddingLeft > -1) ? paddingLeft : defaultPaddingLeft, 
							(paddingTop > -1) ? paddingTop : defaultPaddingTop, 
							(paddingRight > -1) ? paddingRight : defaultPaddingRight, 
							(paddingBottom > -1) ? paddingBottom : defaultPaddingBottom);
		}
		
		
		
		setupTheme(Theme.values()[theme]);
	}
	
	
	
	public void setupTheme(Theme theme){

		if (theme == Theme.LIGHT){
			this.setBackgroundResource(R.drawable.touch_feedback_holo_light_rounded_angle);
		}else{
			this.setBackgroundResource( R.drawable.touch_feedback_holo_dark_rounded_angle);
		}

	}

}
