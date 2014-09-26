package com.emmozhi.firstproject;

import android.inputmethodservice.InputMethodService;

public class Suggestion extends InputMethodService  {


public void suggestiontext1(String txt) {
	// TODO Auto-generated method stub
	txt=UnicodeUtil.unicode2tsc(txt);
	
}
}
