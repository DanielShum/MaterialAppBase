MaterialAppBase
=====================

MaterialAppBase is a open source project which help android developers to:

* Save much of time to learn Google Material Design (to developers who new to Material Design).
* Help android developers to start a Material Designed android app project very easily.
* Reduce much of codes to do the activity layouts.

**Index**
* [Background](#background)
* [Features](#features)
* [Getting Started](#getting-started)
* [Creating activities](#creating-activities)

# Background
I am a full stack engineer work on mobile apps. I have done many android projects. What I am saying is if you are an android developer you already know that: 
* We have to write so much repeated codes to create different look-alike activities. 
* We sometimes copy a project just for creating another look-alike project. 

But if you do above things, it is very hard to keep the code synchronized between class and even between projects. 

This is why I create this app base as a library which shares between projects so that I can prevent for writing repeated codes between projects. More importantly I can keep the shared code synchronized.

I think I should share this projects to anyone who's new to android or JAVA to help them on learning how to cut down the unnecessary codes.

# Features
**Activities**
* [Navigation drawer activity] (https://github.com/DanielShum/MaterialAppBase/blob/master/library/src/main/java/com/daililol/material/appbase/base/BaseNavigationDrawerActivity.java)
* [Tab bar activity] (https://github.com/DanielShum/MaterialAppBase/blob/master/library/src/main/java/com/daililol/material/appbase/base/BaseTabbableActionbarActivity.java)
* [Action bar activity] (https://github.com/DanielShum/MaterialAppBase/blob/master/library/src/main/java/com/daililol/material/appbase/base/BaseActionbarActivity.java)
* [Search bar activity] (https://github.com/DanielShum/MaterialAppBase/blob/master/library/src/main/java/com/daililol/material/appbase/base/BaseSearchBarActivity.java)
* [Collapsing view activity] (https://github.com/DanielShum/MaterialAppBase/blob/master/library/src/main/java/com/daililol/material/appbase/base/BaseCollapsingActionbarActivity.java)

**Fragments**
* [ListView fragment] (https://github.com/DanielShum/MaterialAppBase/blob/master/library/src/main/java/com/daililol/material/appbase/base/BaseListViewFragment.java)
* [GridView fragment]  (https://github.com/DanielShum/MaterialAppBase/blob/master/library/src/main/java/com/daililol/material/appbase/base/BaseGridViewFragment.java)
* [RecyclerView fragment]  (https://github.com/DanielShum/MaterialAppBase/blob/master/library/src/main/java/com/daililol/material/appbase/base/BaseRecyclerViewFragment.java)

**Widgets**
* [MaterialRaisedButton]
* [MaterialFlatButton]
* [MaterialImageButton]
* [MaterialEditText]

**Components**
* FragmentAdapter
* RecyclerViewOnItemClickListener
* GridedRecyclerViewDivider

# Getting started
**Requirement**
* Min android SDK: 9
* Target SDK: 21
* Android support library (Support-V7, Support-Design and RecyclerView)

**Adding this library to your projects**
* Download this project and unzip to you Android studio workpace directory.
* Include the library to your project by editting settings.gradle file. it may look like below
```gradle
include ':app', '..:MaterialAppBase:library'
```

**Adding dependence**
```gradle
dependencies {
   compile 'com.android.support:appcompat-v7:23.0.0'
   compile 'com.android.support:recyclerview-v7:23.0.0'
   compile 'com.android.support:design:23.0.0'
   compile project(':..:MaterialAppBase:library')   //this is the compile code to add this library
}
```

**Setting your app theme**
```xml
  <application
	...
    android:icon="@drawable/ic_launcher"
    android:label="@string/app_name"
    android:theme="@style/Base.AppTheme">
	...
  </application>
```

## Creating activities

**Create an actionbar activity**
```java
	public class MyActionbarActivity extends BaseActionbarActivity{
		......
		@Override
		protected void onActivityCreated(Bundle savedInstanceState) {
			// TODO this method will be involved when all start-up tasks are done.
			// Do your own work here
		}
		
		@Override
		protected int setupContentVew() {
			// TODO return your main content view layout
			return R.layout.my_actionbar_activity;
		}
		
		@Override
		protected Drawable setupThemeColor() {
			// TODO return your actionbar background color (or image) here
			return new ColorDrawable(Color.RED);
		}

		@Override
		protected boolean onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
			// TODO inflate your actionbar menu item here 
			inflater.inflate(R.menu.setting, menu);
			
			//return false to allow normal menu processing to proceed, true to consume it here.
			//simplely say that must return true if you want onMenuItemSelected(MenuItem menu)
			//be involved when an action bar item is click
			return true; 
		}
		
		@Override
		protected void onMenuItemSelected(MenuItem menu) {
			// TODO this method will be involved when an menu item is selected or clicked
			switch (menu.getItemId()){
			case android.R.id.home:
				finish();
				break;
			default:
				break;
			}
		}
		......
	}
```
![](https://github.com/DanielShum/MaterialAppBase/blob/master/images/actionbarActivity.png)

**Create a navigation drawer activity**
```java
  public class DrawerActivity extends BaseNavigationDrawerActivity{
    
	......
  	@Override
  	protected void setupNavigationDrawerItem(ExtendableListView listView,
  			BaseNavigationDrawerListAdapter navigationListAdapter) {
  		// TODO add the navigation menu item here
  		navigationListAdapter.addItem("item 1", 
  		  DrawableUtil.getDrawable(this, R.drawable.ic_chat_grey600_24dp));
  		navigationListAdapter.addItem("item 2", 
  		  DrawableUtil.getDrawable(this, R.drawable.ic_chat_grey600_24dp));
  	}
    ......
  }
```
![](https://github.com/DanielShum/MaterialAppBase/blob/master/images/navigationDrawerActivity.png)

**Create a tabbed swipe view activity**
```java
  public class MainActivity extends BaseTabbableActionbarActivity{
    ......
  	@Override
  	protected void setupViewPager(ViewPager viewPager) {
  		// TODO setup contents for viewPage (tab number depands on how many fragment added to the viewPager).
  		BaseFragmentPagerAdapter adapter = new BaseFragmentPagerAdapter(getSupportFragmentManager());
  		adapter.addFragment(new TestFragment(), "Section 1");
  		adapter.addFragment(new TestFragment(), "Section 2");
  		viewPager.setAdapter(adapter);
  	}
  	
  	@Override
  	protected Drawable setupTabIcon(int pisiton) {
  		// TODO return an icon for each tab (if necessary)
  		return null;
  	}
    ......
  }
```
![](https://github.com/DanielShum/MaterialAppBase/blob/master/images/tabbarActivity.png)

**Create a search bar activity**
```java
	public class TestSearchbarActivity extends BaseSearchBarActivity{
		......
		@Override
		protected void onSearch(String Keyword) {
			// TODO this method will be involved when the Search button is clicked.
			doSearch(Keyword);
		}

		@Override
		protected void onCleanKeyword() {
			// TODO this method will be involved when the user clean the search keywords.
		}
		......
	}
```
![](https://github.com/DanielShum/MaterialAppBase/blob/master/images/searchBarActivity.png)

**Create a collapsing scroll view activity**
```java
	public class TestCollapsingActictionbarActivity extends BaseCollapsingActionbarActivity{

		......
		@Override
		public View setupCollapsingView() {
			// TODO return the header view here (which is under the action bar)
			return null;
		}

		@Override
		public View setupContentView() {
			// TODO return the main content view here 
			//(the view must contains a NestedSrollView or RecyclerView.)
			return null;
		}
		......
	}
```
![](https://github.com/DanielShum/MaterialAppBase/blob/master/images/collapsingViewActivity.gif)
