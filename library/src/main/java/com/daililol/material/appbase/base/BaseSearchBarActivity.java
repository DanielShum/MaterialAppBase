package com.daililol.material.appbase.base;

import com.daililol.material.appbase.R;
import com.daililol.material.appbase.utility.Converter;
import com.daililol.material.appbase.utility.DrawableUtil;
import com.daililol.material.appbase.widget.MaterialImageButton;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public abstract class BaseSearchBarActivity extends BaseActionbarActivity implements 
																			OnEditorActionListener,
																			TextWatcher{



    /**
     * An activity will be created like this
     * --------------------------------------
     * |       ---------------------------  |
     * |  <-   | Search             |_|  |  |  <<-- The search bar
     * |       ---------------------------  |
     * |------------------------------------|
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                THE                 |
     * |              CONTENT               |
     * |                VIEW                |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * --------------------------------------
     */



    private RelativeLayout masterView;
	private View searchBar;
	private EditText searchKeywordEditText;
	private MaterialImageButton searchButton;
	private LinearLayout searchKeywordView;
	private TextView searchKeywordText;
	private MaterialImageButton cleanKeywordButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		masterView = (RelativeLayout) findViewById(R.id.baseMasterView);

		searchBar = LayoutInflater.from(this).inflate(R.layout.base_view_search_bar, null);
		searchKeywordEditText = (EditText) searchBar.findViewById(R.id.baseSearcehKeywordText);
		searchButton = (MaterialImageButton) searchBar.findViewById(R.id.baseSearchButton);
		searchKeywordView = (LinearLayout) searchBar.findViewById(R.id.baseKeywordView);
		searchKeywordText = (TextView) searchBar.findViewById(R.id.baseKeywordText);
		cleanKeywordButton = (MaterialImageButton) searchBar.findViewById(R.id.baseCleanKeywordButton);

		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		params.leftMargin = getToolBar().getLayoutParams().height - Converter.dp2px(this, 16);
		searchBar.setLayoutParams(params);
		params.alignWithParent = true;

		searchKeywordEditText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
		searchKeywordEditText.addTextChangedListener(this);
		searchKeywordEditText.setOnEditorActionListener(this);
		searchButton.setOnClickListener(this);
		cleanKeywordButton.setOnClickListener(this);
		searchButton.setImageDrawable(DrawableUtil.getDrawable(this, R.drawable.ic_search_white_24dp, Color.parseColor("#DEDEDE")));

		masterView.addView(searchBar);
		setActionbarTitle("", Color.WHITE);

        onSearchBarAttached();

	}

    /**
     * This method will be called after the the search bar is attached to the actionbar
     * (witch is involved after onActivityCreated());
     */
	protected abstract void onSearchBarAttached();

    /**
     * This method will involved when the user click the search key on the soft keyboard
     * @param Keyword
     */
	protected abstract void onSearch(String Keyword);

    /**
     * This method will be involved when the user clean the search keyword.
     */
	protected abstract void onCleanKeyword();

    /**
     * This method is intended to simulate a search action.
     * Same with the user preform a click activity on the soft keyboard
     */
	protected void performSearch(){
		if (onBeforeSearch()) onSearch(searchKeywordText.getText().toString());
	}

    /**
     *
     * @return the keyword input EditText
     */
	protected EditText getKeywordEditText() {
		return searchKeywordEditText;
	}

	protected MaterialImageButton getSearchButton() {
		return searchButton;
	}

	private boolean onBeforeSearch() {
		String Keyword = searchKeywordEditText.getText().toString();
		if (Keyword.length() == 0) {
			shakeView(searchBar);
			return false;
		}

		searchKeywordEditText.setEnabled(false);
		searchKeywordText.setText(searchKeywordEditText.getText());
		searchKeywordView.setVisibility(View.VISIBLE);
		return true;
	}
	
	private boolean onBeforeClean(){
		searchKeywordView.setVisibility(View.GONE);
		searchKeywordText.setText("");
		searchKeywordEditText.setEnabled(true);
		searchKeywordEditText.setText("");
		return true;
	}
	
	@Override
	public void afterTextChanged(Editable s) {
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (s.length() == 0){
			searchButton.setImageDrawable(DrawableUtil.getDrawable(this, R.drawable.ic_search_white_24dp, Color.parseColor("#DEDEDE")));
			searchButton.setEnabled(false);
		}else{
			searchButton.setImageResource(R.drawable.ic_close_grey600_24dp);
			searchButton.setEnabled(true);
		}
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.baseCleanKeywordButton || v.getId() == R.id.baseSearchButton) {
			if (onBeforeClean()) onCleanKeyword();
			return;
		}
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (actionId == EditorInfo.IME_ACTION_SEARCH) {
			// Hide keyboard
			if (onBeforeSearch()){
				onSearch(searchKeywordText.getText().toString());
				((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE))
				.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				
			}

			return true;
		}
		
		return false;

	}


}
