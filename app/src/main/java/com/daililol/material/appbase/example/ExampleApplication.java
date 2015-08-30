package com.daililol.material.appbase.example;

import android.app.Application;
import android.content.Context;

public class ExampleApplication extends Application{
	
	private static Context process;
	
	@Override
	public void onCreate() {
		super.onCreate();
		process = this;
	}
	
	
	synchronized public static Context getProcess(){
		return process;
	}

}
