package com.daililol.material.appbase.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class MaterialEditText extends TextInputLayout{

	public MaterialEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		int[] attrSet = new int[]{android.R.attr.hint};
		TypedArray a = context.obtainStyledAttributes(attrs, attrSet, 0, 0);
		String hint = a.getString(0);
		a.recycle();
		if (hint != null) this.setHint(hint);
		
		AppCompatEditText editor = new AppCompatEditText(context, attrs);
		this.addView(editor);
		editor.setHint("");
		editor.setId(android.R.id.text1);
	}

}
