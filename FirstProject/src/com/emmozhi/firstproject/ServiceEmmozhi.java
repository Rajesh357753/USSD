package com.emmozhi.firstproject;

import android.inputmethodservice.InputMethodService;
import android.view.inputmethod.InputConnection;
interface A extends InputConnection
{
	
}
public class ServiceEmmozhi extends InputMethodService {
	
	
	@Override
	public void onCreate()
	{   try
	{
		InputConnection ic=getCurrentInputConnection();
		super.onCreate();
		ic.commitText("அம�?மா", 5);
	}catch(Exception e)
	{
		
	}
	}

}
