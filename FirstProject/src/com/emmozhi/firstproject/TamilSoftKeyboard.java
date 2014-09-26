package com.emmozhi.firstproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.method.MetaKeyKeyListener;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

@SuppressLint("NewApi") public class TamilSoftKeyboard extends InputMethodService 
        implements KeyboardView.OnKeyboardActionListener, SharedPreferences.OnSharedPreferenceChangeListener {
    static final boolean DEBUG = false;
    
    /**
     * This boolean indicates the optional example code for performing
     * processing of hard keys in addition to regular text generation
     * from on-screen interaction.  It would be used for input methods that
     * perform language translations (such as converting text entered on 
     * a QWERTY keyboard to Chinese), but may not be used for input methods
     * that are primarily intended to be used for on-screen text entry.
     */
    static final boolean PROCESS_HARD_KEYS = true;
    static final List<Integer> UYIR_MAI_LIST = Arrays.asList(new Integer[]{2965, 2969, 2970, 2972, 2974, 2975, 2979, 2980, 2984, 2985,2986, 2990, 2991, 2992, 2993, 2994, 2995, 2996, 2997, 2999, 3000, 3001});

    private boolean showingSoftKeyboard = true;
    private KeyboardView mInputView;
    private TamilPreview mTamilPreviewView;
    private TamilPreview msuggestionTamilPreview;
    private LinearLayout mCandidateAndConfigLayout;
    private LinearLayout mSuggestionConfigView;
    Context context;
    
   // private ConfigButtonView configBtns;
    private Boolean isShifted = false;
    private CompletionInfo[] mCompletions;
    private AlertDialog mOptionsDialog;
    private StringBuilder mComposing = new StringBuilder();
//    private char mPrevChar;
    private int mPrevChar;
    private int m2ndPrevChar;

    private StringBuilder mText = new StringBuilder();
    private boolean mPredictionOn;
    private boolean mCompletionOn;
    private int mLastDisplayWidth;
    private boolean mCapsLock;
    private long mLastShiftTime;
    private long mMetaState;
    public String s1;
    
    private TamilKeyboard mSymbolsKeyboard;
    private TamilKeyboard mSymbolsShiftedKeyboard;
    private TamilKeyboard mQwertyKeyboard;
    private TamilKeyboard mTamilKeyboard;
    private TamilKeyboard mTamil99Keyboard;
    private TamilKeyboard mTamil99ShiftedKeyboard;
    private TamilKeyboard mPhoneticEnglishKeyboard;
    
    private TamilKeyboard mCurKeyboard;
    private TamilKeyboard mSugKeyboard;
    
    private String currentLayout = "tamil99";
    private String mWordSeparators;
    
    private Boolean mAlt = false;
    private boolean mShift =false;
    private Boolean mTamil = false;
    
    /**
     * Main initialization of the input method component.  Be sure to call
     * to super class.
     */
    @Override public void onCreate() {
        super.onCreate();
    	
        mWordSeparators = getResources().getString(R.string.word_separators);
       
    }
    
    /**
     * This is the point where you can do all of your UI initialization.  It
     * is called after creation and any configuration change.
     */
    @Override public void onInitializeInterface() {
    	try{
        if (mQwertyKeyboard != null) {
            // Configuration changes can happen after the keyboard gets recreated,
            // so we need to be able to re-build the keyboards if the available
            // space has changed.
            int displayWidth = getMaxWidth();
            if (displayWidth == mLastDisplayWidth) return;
            mLastDisplayWidth = displayWidth;
        }
        mQwertyKeyboard = new TamilKeyboard(this, R.xml.qwerty);
        mSymbolsKeyboard = new TamilKeyboard(this, R.xml.symbols);
        mSymbolsShiftedKeyboard = new TamilKeyboard(this, R.xml.symbols_shift);
        mTamilKeyboard = new TamilKeyboard(this, R.xml.tamil);
        mPhoneticEnglishKeyboard = new TamilKeyboard(this, R.xml.tamil_phonetic);
        mTamil99Keyboard = new TamilKeyboard(this, R.xml.tamil99);
        mTamil99ShiftedKeyboard = new TamilKeyboard(this, R.xml.tamil99_shift);
        
        
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    /**
     * Called by the framework when your view for creating input needs to
     * be generated.  This will be called the first time your input method
     * is displayed, and every time it needs to be re-created such as due to
     * a configuration change.
     */
    @Override public View onCreateInputView() {
        mInputView = (KeyboardView) getLayoutInflater().inflate(R.layout.input, null);
        mInputView.setOnKeyboardActionListener(this);
        mInputView.setPreviewEnabled(false);
    	  switch (TamilKeyboard.CURRENT_LAYOUT) {
    	  	case TamilKeyboard.KEYBOARD_TAMIL99:
    	        mInputView.setKeyboard(mTamil99Keyboard);  
    	        break;
    	  	case TamilKeyboard.KEYBOARD_PHONETIC:
  	          mInputView.setKeyboard(mTamilKeyboard);
          	  break;
    	  	case TamilKeyboard.KEYBOARD_PHONETIC_ENGLISH:
    	        mInputView.setKeyboard(mPhoneticEnglishKeyboard);
          	  break;
    	  	default:
    	        mInputView.setKeyboard(mQwertyKeyboard);
	            break;
    	  }
    	  
        return mInputView;
    }

    /**
     * Called by the framework when your view for showing candidates needs to
     * be generated, like {@link #onCreateInputView}.
     */
    @Override public View onCreateCandidatesView() {
    
    	mCandidateAndConfigLayout = new LinearLayout(this);
	    	mCandidateAndConfigLayout = new LinearLayout(this);
	    	mCandidateAndConfigLayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	    	mCandidateAndConfigLayout.setOrientation(LinearLayout.VERTICAL);
	      // CandidateView candidateView = new CandidateView(this);
	    	mTamilPreviewView = new TamilPreview(this);
	       // mTamilPreviewView.setService(this);
	        //configBtns = new ConfigButtonView(this);
	        
	    //	mCandidateAndConfigLayout.addView(configBtns.getConfigView());
	
	        setCandidatesViewShown(true);
	        updateCandidateText(0);
//	        View view = getLayoutInflater().inflate(R.layout.previewview, null);
//	        mCandidateAndConfigLayout.addView(view);
	    	mCandidateAndConfigLayout.addView(mTamilPreviewView.getPreviewView());
        return mCandidateAndConfigLayout;
    }
    private View onCreateSuggestionView() {
    	mSuggestionConfigView=new LinearLayout(this);
    	mSuggestionConfigView=new LinearLayout(this);
    	mSuggestionConfigView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    	mSuggestionConfigView.setOrientation(LinearLayout.VERTICAL);
    	msuggestionTamilPreview=new TamilPreview(this);
    	setCandidatesViewShown(true);
    	updateCandidateSuggestionText();
    	
    	mSuggestionConfigView.addView(msuggestionTamilPreview.getPreviewView());
    	
    	
    	return mSuggestionConfigView;
	}

    public View oCreateSuggestionView1()
    {
    	
    	mSuggestionConfigView=new LinearLayout(this);
    	mSuggestionConfigView=new LinearLayout(this);
    	mSuggestionConfigView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    	mSuggestionConfigView.setOrientation(LinearLayout.VERTICAL);
    	msuggestionTamilPreview=new TamilPreview(this);
    	setCandidatesViewShown(true);
    	updateCandidateSuggestionText();
    	
    	mSuggestionConfigView.addView(msuggestionTamilPreview.getPreviewView());
    	
    	
    	return mSuggestionConfigView;
    }
    
    
    
    
    /**
     * This is the main point where we do our initialization of the input method
     * to begin operating on an application.  At this point we have been
     * bound to the client, and are now receiving all of the detailed information
     * about the target of our edits.
     */
    @Override public void onStartInput(EditorInfo attribute, boolean restarting) {
        super.onStartInput(attribute, restarting);
        
        // Reset our state.  We want to do this even if restarting, because
        // the underlying state of the text editor could have changed in any way.

        
        mComposing.setLength(0);
        
        
        updateCandidates();

        updateCandidateText(0);
        
        
        if (!restarting) {
            // Clear shift states.
            mMetaState = 0;
        }

        
        mPredictionOn = false;
        mCompletionOn = false;
        mCompletions = null;
        
        // We are now going to initialize our state based on the type of
        // text being edited.
        switch (attribute.inputType&EditorInfo.TYPE_MASK_CLASS) {
            case EditorInfo.TYPE_CLASS_NUMBER:
            case EditorInfo.TYPE_CLASS_DATETIME:
                // Numbers and dates default to the symbols keyboard, with
                // no extra features.
                mCurKeyboard = mSymbolsKeyboard;
                break;
                
            case EditorInfo.TYPE_CLASS_PHONE:
                // Phones will also default to the symbols keyboard, though
                // often you will want to have a dedicated phone keyboard.
                mCurKeyboard = mSymbolsKeyboard;
                break;
                
//            case EditorInfo.TYPE_CLASS_TEXT:
//                // This is general text editing.  We will default to the
//                // normal alphabetic keyboard, and assume that we should
//                // be doing predictive text (showing candidates as the
//                // user types).
//                mCurKeyboard = mQwertyKeyboard;
//                mPredictionOn = true;
//                
//                // We now look for a few special variations of text that will
//                // modify our behavior.
//                int variation = attribute.inputType &  EditorInfo.TYPE_MASK_VARIATION;
//                if (variation == EditorInfo.TYPE_TEXT_VARIATION_PASSWORD ||
//                        variation == EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
//                    // Do not display predictions / what the user is typing
//                    // when they are entering a password.
//                    mPredictionOn = false;
//                }
//                
//                if (variation == EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS 
//                        || variation == EditorInfo.TYPE_TEXT_VARIATION_URI
//                        || variation == EditorInfo.TYPE_TEXT_VARIATION_FILTER) {
//                    // Our predictions are not useful for e-mail addresses
//                    // or URIs.
//                    mPredictionOn = false;
//                }
//                
//                if ((attribute.inputType&EditorInfo.TYPE_TEXT_FLAG_AUTO_COMPLETE) != 0) {
//                    // If this is an auto-complete text view, then our predictions
//                    // will not be shown and instead we will allow the editor
//                    // to supply their own.  We only show the editor's
//                    // candidates when in fullscreen mode, otherwise relying
//                    // own it displaying its own UI.
//                    mPredictionOn = false;
//                    mCompletionOn = isFullscreenMode();
//                }
//                
//                // We also want to look at the current state of the editor
//                // to decide whether our alphabetic keyboard should start out
//                // shifted.
//                updateShiftKeyState(attribute);
//                break;
                
            default:
                // For all unknown input types, default to the alphabetic
                // keyboard with no special features.
          	  switch (TamilKeyboard.CURRENT_LAYOUT) {
      	  	case TamilKeyboard.KEYBOARD_TAMIL99:
                mCurKeyboard = mTamil99Keyboard;
	            	  break;
      	  	case TamilKeyboard.KEYBOARD_PHONETIC:
                mCurKeyboard = mTamilKeyboard;
            	  break;
      	  	case TamilKeyboard.KEYBOARD_PHONETIC_ENGLISH:
                mCurKeyboard = mPhoneticEnglishKeyboard;
            	  break;
      	  	default:
                mCurKeyboard = mQwertyKeyboard;
                updateShiftKeyState(attribute);
	            	  break;
      	  }
            	
            	
        }
        
        // Update the label on the enter key, depending on what the application
        // says it will do.
        mCurKeyboard.setImeOptions(getResources(), attribute.imeOptions);
    }
    public void  getString(String s)
    {
    	this.s1=s;
    }
    
   public  void updateCandidateText(int n){
        try{
            int variation = getCurrentInputEditorInfo().inputType &  EditorInfo.TYPE_MASK_VARIATION;

            if (variation == EditorInfo.TYPE_TEXT_VARIATION_PASSWORD ||
                    variation == EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {        		
        		mTamilPreviewView.update("", 0);
            }
          
            
            
        	else{
    	        ExtractedText txt = getCurrentInputConnection().getExtractedText(new ExtractedTextRequest(), InputConnection.GET_EXTRACTED_TEXT_MONITOR);
    	        String strbeforeCursor="";
    	        String strafterCursor ="";
    	        	strbeforeCursor = getCurrentInputConnection().getTextBeforeCursor(1000000000, 0).toString();
    	        strafterCursor = getCurrentInputConnection().getTextAfterCursor(1000000000, 0).toString();
    	        String str = strbeforeCursor + "|"+strafterCursor;

    			System.out.println("updatecandideSuggge 1111111111111111111");
    			System.out.println("update candidate sugg value"+n);
    			System.out.println(n);
    	        if(mTamilPreviewView != null)
    	        {
    	        	mTamilPreviewView.update(str,strbeforeCursor.length());
    	        }
    	      /*  if(strbeforeCursor.length()==0)
    	        {
    	          InputConnection ic=getCurrentInputConnection();
    	        	ic.setComposingText("அ", 1);
    	        	ic.setComposingText("அம�?",1);
    	        	 ic.commitText("அம�?மா", 1);
    	        	
    	        	
    	        }*/
    	   /*
    	    * 
    	    
    	        	InputConnection input=getCurrentInputConnection();
    	        	input.setComposingRegion(0, 5);
    	        	input.setComposingText("அ", 1);
    	        	input.setComposingText("அம�?",1);*/
    	        //boolean input=getCurrentInputConnection().commitText("அ", 5);
    	        	/*String s=getCurrentInputConnection().getTextAfterCursor(111, 0).toString();*/
    	        
    	        
    	        
    	   
    	        //objjjjj();
    	     String s= mTamilPreviewView.mPreviewSuggestionText3.getText().toString();
    	     String m=s;
    	     System.out.println("update candidate text "+s);
    	     System.out.println("update candidate textttttttttttt"+s1);
    	   /*  if((s.equals("Åð¼õ"))&(strbeforeCursor.length()==3))
    	     {
    	    	 InputConnection icConnection=getCurrentInputConnection();
    	    	 icConnection.setComposingRegion(5, 9);
    	    	 icConnection.setComposingText("டம�?",strbeforeCursor.length());
    	    	 icConnection.commitText("டம�?", strbeforeCursor.length());
    	    	 icConnection.setSelection(5, 5);
    	    
    	    	 }
    	     else if(s.equals("þÈôÒ")&(strbeforeCursor.length()==2))
    	     {
    	    	 InputConnection icConnection1 =getCurrentInputConnection();
    	    	 icConnection1.commitText("ப�?ப�?",strbeforeCursor.length());
    	     }
    	     else
    	     {
    	    	 
    	     }*/
    	     /*else if((s.equals("Åð¼õ"))&(strbeforeCursor.length()==3))
    	     {
    	    	 InputConnection icConnection=getCurrentInputConnection();
    	    	 icConnection.setComposingRegion(5, 9);
    	    	 icConnection.setComposingText("டம�?",strbeforeCursor.length());
    	    	 icConnection.commitText("டம�?", strbeforeCursor.length());
    	    	 icConnection.finishComposingText();
    	    	 }
    	     else
    	     {
    	    	 
    	     }*/
    
    	      mTamilPreviewView.mPreviewSuggestionText11.getText();
    	        	
    	       mTamilPreviewView.updatesuggestion1(str, strbeforeCursor.length());    	        	   
    	       mTamilPreviewView.updatesuggestion2(str, strbeforeCursor.length());
 	    	        mTamilPreviewView.updatesuggestion3(str,strbeforeCursor.length());
 	    	        
 	      	       mTamilPreviewView.updatesuggestion11(str, strbeforeCursor.length());
	    	        mTamilPreviewView.updatesuggestion21(str,strbeforeCursor.length());
	    	  	       mTamilPreviewView.updatesuggestion31(str, strbeforeCursor.length());
	 	    	  
        	}
        }
        catch (Exception e) {
//        	Log.e("t", "errr", e);
        	
		}

    }
   /*public void objjjjj()
    {
	   try
	   {
		   LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
		   final LinearLayout yourlayout=(LinearLayout)layoutInflater.inflate(R.id.suggestion_preview, null);
		   
    	   TextView t=(TextView)yourlayout.findViewById(R.id.text_view_3);
    	   t.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				InputConnection ic=getCurrentInputConnection();
				ic.setComposingRegion(5, 9);
	        	ic.setComposingText("அ", 5);
	        	ic.setComposingText("அம�?",5);
	        	 ic.commitText("அம�?மா", 5);
			}
		});
	          
	        	
	        	
	   }catch(Exception e)
	   {
		   
	   }
    }*/
  /* public void checking(String hai)
   
   {
	   String h;
	  h=hai;
	  try
	  {
	  InputConnection ic=getCurrentInputConnection();
	  ic.setComposingRegion(0, 5);
  	ic.setComposingText("அ", 5);
  	ic.setComposingText("அம�?",5);
  	 ic.commitText("அம�?மா", 5);
	  }
	  catch(Exception e)
	  
	  {
		  
	  }
   }*/
   /* public TextView findViewById(int textView3) {
		// TODO Auto-generated method stub
    	TextView t=(TextView)findViewById(R.id.text_view_3);
    	t.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				InputConnection ic=getCurrentInputConnection();
	        	ic.setComposingText("அ", 1);
	        	ic.setComposingText("அம�?",1);
	        	 ic.commitText("அம�?மா", 1);
			}
		});
		return null;
	}*/

	private void updateCandidateSuggestionText(){
    	
    	try
    	{
    		int suggestion=getCurrentInputEditorInfo().inputType & EditorInfo.TYPE_MASK_VARIATION;
    		
    		
    		if(suggestion==EditorInfo.TYPE_TEXT_VARIATION_PASSWORD||suggestion==EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
    		{
    			
    				msuggestionTamilPreview.update("", 0);
    			
    			
    		}
    		else
    		{
    			ExtractedText txt=getCurrentInputConnection().getExtractedText(new ExtractedTextRequest(),InputConnection.GET_EXTRACTED_TEXT_MONITOR );
    			String strbeforeCursor="";
    			String strafterCursor="";
    			strbeforeCursor=getCurrentInputConnection().getTextBeforeCursor(100, 0).toString();
    			strafterCursor=getCurrentInputConnection().getTextAfterCursor(1000000000, 0).toString();
    			String str=strbeforeCursor+"|"+strafterCursor;
    			if(mTamilPreviewView!=null)
    			mTamilPreviewView.update(str, strbeforeCursor.length());
    			
    		}
    		
    		
    	}catch(Exception e)
    	{
    		
    	}
    }

    public void moveCursorToLeft(){
    	try{
    		InputConnection ic = getCurrentInputConnection();
    		if(ic != null){
    			String strbeforeCursor = ic.getTextBeforeCursor(1000000000, 0).toString();
    			System.out.println("Movecursor to left 4444444444");
    			if(strbeforeCursor.length()>0)
    				ic.setSelection(strbeforeCursor.length()-1, strbeforeCursor.length()-1);
    			System.out.println("Movecursor to left 33333333333333333");
    		}
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    public void moveCursorToFirst(){
    	try{
    		InputConnection ic = getCurrentInputConnection();
    		System.out.println("Movecursor to first 222222222222222");
    				ic.setSelection(0, 0);
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }

    
    public void moveCursorToRight(){
    	try{
        	String strbeforeCursor = getCurrentInputConnection().getTextBeforeCursor(1000000000, 0).toString();
        	getCurrentInputConnection().setSelection(strbeforeCursor.length()+1, strbeforeCursor.length()+1);
        	System.out.println("Movecursor to right 222222222222222");
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    public void moveCursorToEnd(){
    	try{
    		InputConnection ic = getCurrentInputConnection();
	        ExtractedText txt = ic.getExtractedText(new ExtractedTextRequest(), InputConnection.GET_EXTRACTED_TEXT_MONITOR);
    				ic.setSelection(txt.text.length(), txt.text.length());
    				System.out.println("Movecursor to end 1111111111111");
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }

    
    
    /**
     * This is called when the user is done editing a field.  We can use
     * this to reset our state.
     */
    @Override public void onFinishInput() {
        super.onFinishInput();
        try{
	        // Clear current composing text and candidates.
	        mComposing.setLength(0);
	        updateCandidates();
	        
	        // We only hide the candidates window when finishing input on
	        // a particular editor, to avoid popping the underlying application
	        // up and down if the user is entering text into the bottom of
	        // its window.
	//        setCandidatesViewShown(false);
	        
	        mCurKeyboard = mQwertyKeyboard;
	        if (mInputView != null) {
	            mInputView.closing();
	        }
	        if(getResources().getConfiguration().hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO){
		
		       if( mCandidateAndConfigLayout != null){
		    	   mCandidateAndConfigLayout.setVisibility(View.GONE);
		    	   
		    	   
		       }
	        }
	    }
        catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    @Override public void onStartInputView(EditorInfo attribute, boolean restarting) {
        super.onStartInputView(attribute, restarting);
        // Apply the selected keyboard to the input view.
        try{
	        mInputView.setKeyboard(mCurKeyboard);
	        mInputView.closing();
	    	mInputView.setVisibility(View.VISIBLE);
	        if(getResources().getConfiguration().hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO){
//	    	if(true){
	        	mInputView.setVisibility(View.GONE);
    			if(mCandidateAndConfigLayout == null){
            		setCandidatesView(onCreateCandidatesView());
            		setCandidatesView(onCreateSuggestionView());
    			}
	            	mCandidateAndConfigLayout.setVisibility(View.VISIBLE);
	            	setCandidatesViewShown(true);
	            	mTamilPreviewView.getPreviewView().setVisibility(View.VISIBLE);    		
	            	
	        }
	        
	      /*  if(getResources().getConfiguration().hardKeyboardHidden==Configuration.HARDKEYBOARDHIDDEN_NO){
	        	mInputView.setKeyboard(mSugKeyboard);
	        	mInputView.closing();
	        	mInputView.setVisibility(View.VISIBLE);
	        	if(getResources().getConfiguration().hardKeyboardHidden==Configuration.HARDKEYBOARDHIDDEN_NO)
	        	{
	        		mInputView.setVisibility(View.GONE);
	        		if(mSuggestionConfigView==null)
	        		{
	        			setCandidatesView(onCreateCandidatesView());
	        		}
	        		mSuggestionConfigView.setVisibility(View.VISIBLE);
	        		setCandidatesViewShown(true);
	        		msuggestionTamilPreview.getPreviewView().setVisibility(View.VISIBLE);
	        	}
	        }*/
	        
	        
        }
        catch (Exception e) {
			// TODO: handle exception
		}
    }
    
   

	/**
     * Deal with the editor reporting movement of its cursor.
     */
    @Override public void onUpdateSelection(int oldSelStart, int oldSelEnd,
            int newSelStart, int newSelEnd,
            int candidatesStart, int candidatesEnd) {
        super.onUpdateSelection(oldSelStart, oldSelEnd, newSelStart, newSelEnd,
                candidatesStart, candidatesEnd);
        
        try{
        InputConnection ic = getCurrentInputConnection();
        updateCandidateText(0);
            // If the current selection in the text view changes, we should
        // clear whatever candidate text we have.
        if (mComposing.length() > 0 && (newSelStart != candidatesEnd
                || newSelEnd != candidatesEnd)) {
        	
            mComposing.setLength(0);
            updateCandidates();
            if (ic != null) {
                ic.finishComposingText();
            	
            }
        }
        if (ic != null) {
        	ExtractedText txt = ic.getExtractedText(new ExtractedTextRequest(), InputConnection.GET_EXTRACTED_TEXT_MONITOR);
        	try{
        		mPrevChar = txt.text.charAt(newSelStart-1);  
        	
        		System.out.println("UpdateSelection:"+mPrevChar);
        		System.out.println("updateSelection 1222222222222222222");
        	}catch (Exception e) {
				// TODO: handle exception
			}
        }        
        }catch (Exception e) {
			// TODO: handle exception
		}
    }
    public void onUpdateSuggestion(int oldSelStart,int oldSelEnd,int newSelStart,int newSelEnd,int candidatesStart,int candidatesEnd)
    {
    	super.onUpdateSelection(oldSelStart, oldSelEnd, newSelStart, newSelEnd, candidatesStart, candidatesEnd);
    	try
    	{
    		InputConnection ic=getCurrentInputConnection();
    		updateCandidateSuggestionText();
    		if(mComposing.length()>0&&(newSelStart!=candidatesEnd||newSelEnd!=candidatesEnd))
    		{
    			mComposing.setLength(0);
    			updateSuggestionCandidate();
    			if(ic!=null)
    			{
    				ic.finishComposingText();
    			}
    			
    		}
    		if(ic!=null)
    		{
    			ExtractedText txt=ic.getExtractedText(new ExtractedTextRequest(), InputConnection.GET_EXTRACTED_TEXT_MONITOR);
    			try{
    				mPrevChar=txt.text.charAt(newSelStart-1);
    			}catch(Exception e)
    			{
    				
    			}
    		}
    	}catch(Exception e)
    	{
    		
    	}
    }
    /**
     * This tells us about completions that the editor has determined based
     * on the current text in it.  We want to use this in fullscreen mode
     * to show the completions ourself, since the editor can not be seen
     * in that situation.
     */
    @Override public void onDisplayCompletions(CompletionInfo[] completions) {
    	try{
        if (mCompletionOn) {
            mCompletions = completions;
            if (completions == null) {
                setSuggestions(null, false, false);
                return;
            }
            
            List<String> stringList = new ArrayList<String>();
            for (int i=0; i<(completions != null ? completions.length : 0); i++) {
                CompletionInfo ci = completions[i];
                if (ci != null) stringList.add(ci.getText().toString());
            }
            setSuggestions(stringList, true, true);
        }
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }
    public void onDisplaySuggestionCompletion(CompletionInfo[] completions)
    {
    	try
    	{
    		if(mCompletionOn)
    		{
    			mCompletions=completions;
    			if(completions==null)
    			{
    				setSuggestions(null, false, false);
    				return;
    			}
    			List<String> stringList=new ArrayList<String>();
    			for(int i=0;i<(completions !=null ? completions.length:0);i++)
    			{
    				CompletionInfo ci=completions[i];
    				if(ci!=null)stringList.add(ci.getText().toString());
    			}
    			setSuggestions(stringList, true, true);
    		}
    	}catch(Exception e)
    	{
    		
    	}
    }
    /**
     * This translates incoming hard key events in to edit operations on an
     * InputConnection.  It is only needed when using the
     * PROCESS_HARD_KEYS option.
     */
    public boolean translateKeyDown(int keyCode, KeyEvent event) {
        mMetaState = MetaKeyKeyListener.handleKeyDown(mMetaState,
                keyCode, event);
        System.out.println("keycode:"+keyCode);
        int c = event.getUnicodeChar(MetaKeyKeyListener.getMetaState(mMetaState));
        mMetaState = MetaKeyKeyListener.adjustMetaAfterKeypress(mMetaState);
        InputConnection ic = getCurrentInputConnection();
        if (c == 0 || ic == null) {
            return false;
        }
        boolean dead = false;

        if ((c & KeyCharacterMap.COMBINING_ACCENT) != 0) {
            dead = true;
            c = c & KeyCharacterMap.COMBINING_ACCENT_MASK;
        }
        
        if (mComposing.length() > 0) {
            char accent = mComposing.charAt(mComposing.length() -1 );
            System.out.println(""+accent);
            int composed = KeyEvent.getDeadChar(accent, c);

            if (composed != 0) {
                c = composed;
                mComposing.setLength(mComposing.length()-1);
            }
        }
        
        onKey(c, null);
        
        return true;
    }
    

    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
    	
//        mMetaState = MetaKeyKeyListener.handleKeyDown(mMetaState,
//                keyCode, event);
//        int c = event.getUnicodeChar(MetaKeyKeyListener.getMetaState(mMetaState));
//
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setNegativeButton(android.R.string.cancel, null);
//        builder.setTitle("Options" + keyCode +"  " + c);
//        mOptionsDialog = builder.create();
//        Window window = mOptionsDialog.getWindow();
//        WindowManager.LayoutParams lp = window.getAttributes();    	         
//        lp.token = mCandidateAndConfigLayout.getWindowToken();
//        lp.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;
//        window.setAttributes(lp);
//        window.addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
//        mOptionsDialog.show();    

    	if(mAlt){
    		if(keyCode == 9){
    			if(mCandidateAndConfigLayout != null && mCandidateAndConfigLayout.getVisibility() == View.VISIBLE){
        			try{
        				System.out.println("key event starts1");
        				showKeyboardLayoutDialog();
        			}catch (Exception e) {
        				Log.e("","",e);
    				}  
                }	
    			else{
    	    		try{
    	    			System.out.println("key event starts1");
	            		setCandidatesView(onCreateCandidatesView());
		            	mCandidateAndConfigLayout.setVisibility(View.VISIBLE);
		            	setCandidatesViewShown(true);
		            	mTamilPreviewView.getPreviewView().setVisibility(View.VISIBLE); 
		            	mTamilPreviewView.mPreviewSuggestionText3.setVisibility(View.VISIBLE);
	        		}catch (Exception e) {
	        			// TODO: handle exception
	        		}
    			}
    			mAlt = false;
        		return true;
    		}
    		
    	}
    	if(event.isAltPressed()){
    		mAlt = true;
    	}
    	if(event.isShiftPressed()){
    		mShift = true;
    	}else if(mShift){
    		handleShift();
    	}

//    	if(Constants.KEY_CODE_MAP.containsKey(keyCode)){
//    		try{
//        		setCandidatesView(onCreateCandidatesView());
//        	
//        	mCandidateAndConfigLayout.setVisibility(View.VISIBLE);
//        	setCandidatesViewShown(true);
//        	mTamilPreviewView.getPreviewView().setVisibility(View.VISIBLE);    		
//    		}catch (Exception e) {
//    			// TODO: handle exception
//    		}
//    	}
    	
    	switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
            	break;
                
            case KeyEvent.KEYCODE_DEL:
                // Special handling of the delete key: if we currently are
                // composing text for the user, we want to modify that instead
                // of let the application to the delete itself.
            	 handleBackspace();
            		System.out.println("key event starts1");
            	 mAlt = event.isAltPressed();
            	 mShift = event.isShiftPressed();
            	 return true;
              
            	//                if (mComposing.length() > 0) {
//                    onKey(Keyboard.KEYCODE_DELETE, null);
//                    return true;
//                }
              //  break;
                
            case KeyEvent.KEYCODE_ENTER:
                // Let the underlying text editor always handle these.
           	 	mAlt = event.isAltPressed();
           	 	mShift = event.isShiftPressed();
           		System.out.println("key event starts1");
            	return true;
                
            case KeyEvent.KEYCODE_SHIFT_LEFT:
            case KeyEvent.KEYCODE_SHIFT_RIGHT:
            	handleShift();
           	 	mAlt = event.isAltPressed();
           	 	mShift = event.isShiftPressed();
           		System.out.println("key event starts1");
            	return true;
            case KeyEvent.KEYCODE_ALT_LEFT:
            case KeyEvent.KEYCODE_ALT_RIGHT:
           	 	mAlt = event.isAltPressed();
           	 	mShift = event.isShiftPressed();
           		System.out.println("key event starts1");
            	return true;
            default:
            {
            	switch (TamilKeyboard.CURRENT_LAYOUT) {
					case TamilKeyboard.KEYBOARD_TAMIL99:
						if(mAlt && Constants.T99_ALT_MAP.containsKey(keyCode)){
							handleCharacter(Constants.T99_ALT_MAP.get(keyCode), null);
			           	 	mAlt = event.isAltPressed();
			           	 mShift = event.isShiftPressed();
			         	System.out.println("key event starts1");
	                    	  return true;
						}
						if(mShift && Constants.T99_SHIFT_MAP.containsKey(keyCode)){
	                    	  onKey(Constants.T99_SHIFT_MAP.get(keyCode), null);
	                     	 	mAlt = event.isAltPressed();
	                     	 	mShift = event.isShiftPressed();
	                     		System.out.println("key event starts1");
	                    	  return true;							
						}
						if(Constants.T99_CODE_MAP.containsKey(keyCode)){
	                    	  onKey(Constants.T99_CODE_MAP.get(keyCode), null);
	                     	 	mAlt = event.isAltPressed();
	                     	 	mShift = event.isShiftPressed();
	                     		System.out.println("key event starts1");
	                    	  return true;							
						}
						
					case TamilKeyboard.KEYBOARD_PHONETIC:
					case TamilKeyboard.KEYBOARD_PHONETIC_ENGLISH:
		                if(Constants.PHONETIC_KEY_CODE_MAP.containsKey(keyCode)){
	                    	  onKey(Constants.PHONETIC_KEY_CODE_MAP.get(keyCode), null);
	                     	 	mAlt = event.isAltPressed();
	                     	 	mShift = event.isShiftPressed();
	                     		System.out.println("key event starts1");
	                    	  return true;
		                  }
						break;
					default:
						break;
				}
            }
        }
 	 	mAlt = event.isAltPressed();    	
    	mShift = event.isShiftPressed();
        boolean returnVal = super.onKeyDown(keyCode, event);
        return returnVal;
    }

    /**
     * Use this to monitor key events beingd delivered to the application.
     * We get first crack at them, and can either resume them or let them
     * continue to the app.
     */
    @Override public boolean onKeyUp(int keyCode, KeyEvent event) {
        // If we want to do transformations on text being entered with a hard
        // keyboard, we need to process the up events to update the meta key
        // state we are tracking.
    	try{
        if (PROCESS_HARD_KEYS) {
            if (mPredictionOn) {
                mMetaState = MetaKeyKeyListener.handleKeyUp(mMetaState,
                        keyCode, event);
            }
            updateCandidateText(0);
        }
    	}catch (Exception e) {
			// TODO: handle exception
		}
    
        return super.onKeyUp(keyCode, event);
    }

    /**
     * Helper function to commit any text being composed in to the editor.
     */
    public void commitTyped(InputConnection inputConnection) {
    	try{
        if (mComposing.length() > 0) {
            inputConnection.commitText(mComposing, mComposing.length());
            mComposing.setLength(0);
            updateCandidates();
        }
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }

    /**
     * Helper to update the shift state of our keyboard based on the initial
     * editor state.
     */
    private void updateShiftKeyState(EditorInfo attr) {
    	try{
        if (attr != null 
                && mInputView != null && mQwertyKeyboard == mInputView.getKeyboard()) {
            int caps = 0;
            EditorInfo ei = getCurrentInputEditorInfo();
            if (ei != null && ei.inputType != EditorInfo.TYPE_NULL) {
                caps = getCurrentInputConnection().getCursorCapsMode(attr.inputType);
            }
            mInputView.setShifted(mCapsLock || caps != 0);
        }
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    /**
     * Helper to determine if a given character code is alphabetic.
     */
    private boolean isAlphabet(int code) {
        if (Character.isLetter(code)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Helper to send a key down / key up pair to the current editor.
     */
    private void keyDownUp(int keyEventCode) {
    	try{
        getCurrentInputConnection().sendKeyEvent(
                new KeyEvent(KeyEvent.ACTION_DOWN, keyEventCode));
        getCurrentInputConnection().sendKeyEvent(
                new KeyEvent(KeyEvent.ACTION_UP, keyEventCode));
        updateCandidateText(0);
    	}
    	catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    /**
     * Helper to send a character to the editor as raw key events.
     */
    private void sendKey(int keyCode) {
    	try{
        switch (keyCode) {
            case '\n':
                keyDownUp(KeyEvent.KEYCODE_ENTER);
                break;
            default:
                if (keyCode >= '0' && keyCode <= '9') {
                    keyDownUp(keyCode - '0' + KeyEvent.KEYCODE_0);
                } else {
                    getCurrentInputConnection().commitText(String.valueOf((char) keyCode), 1);
                }
                break;
        }
        System.out.println(keyCode);
        mText.append(String.valueOf((char) keyCode));
        updateCandidateText(0);
    	}
        catch (Exception e) {
			// TODO: handle exception
		}
    }

    // Implementation of KeyboardViewListener

    public void onKey(int primaryCode, int[] keyCodes) {
    	try{
    		System.out.println("checking :"+primaryCode);
    		System.out.println("checking :"+keyCodes);
        if (isWordSeparator(primaryCode)) {
            // Handle separator
            if (mComposing.length() > 0) {
                commitTyped(getCurrentInputConnection());
                System.out.println();
            }
            sendKey(primaryCode);
            updateShiftKeyState(getCurrentInputEditorInfo());
        } else if (primaryCode == Keyboard.KEYCODE_DELETE) {
            handleBackspace();
        } else if (primaryCode == Keyboard.KEYCODE_SHIFT) {
            handleShift();
        } else if (primaryCode == -8) {
            showKeyboardLayoutDialog();
            } else if (primaryCode == Keyboard.KEYCODE_CANCEL) {
        	handleClose();
            return;
        } else if (primaryCode == TamilKeyboardView.KEYCODE_OPTIONS) {
            // Show a menu or somethin'
        	
        } else if (primaryCode == Keyboard.KEYCODE_MODE_CHANGE
                && mInputView != null) {
            Keyboard current = mInputView.getKeyboard();
            if (current == mSymbolsKeyboard || current == mSymbolsShiftedKeyboard) {
				switch (TamilKeyboard.CURRENT_LAYOUT) {
				case TamilKeyboard.KEYBOARD_TAMIL99:
					// configBtns.keyboardLayoutTxt.setText("Tamil 99");
					current = mTamil99Keyboard;
					break;
				case TamilKeyboard.KEYBOARD_PHONETIC:
					// configBtns.keyboardLayoutTxt.setText("Phonetic - Tamil keys");
					current = mTamilKeyboard;
					break;
				case TamilKeyboard.KEYBOARD_PHONETIC_ENGLISH:
					// configBtns.keyboardLayoutTxt.setText("Phonetic - English keys");
					current = mPhoneticEnglishKeyboard;
					break;
				case TamilKeyboard.KEYBOARD_ENGLISH:
					// configBtns.keyboardLayoutTxt.setText("English");
					current = mQwertyKeyboard;
					break;
				}
            } else {
                current = mSymbolsKeyboard;
            }
            mInputView.setKeyboard(current);
            System.out.println("key set keyboard"+current);
            if (current == mSymbolsKeyboard) {
                current.setShifted(false);
            }
        } else {
            handleCharacter(primaryCode, keyCodes);
        }
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }

    public boolean isTamilKeyboard(){
        Keyboard current = mInputView.getKeyboard();
        System.out.println("is Tamil Keyboard:"+current);
        return current == mTamilKeyboard;
    }
    public void onText(CharSequence text) {
    	try{
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;
        ic.beginBatchEdit();
        if (mComposing.length() > 0) {
            commitTyped(ic);
        }
        ic.commitText(text, 0);
        ic.endBatchEdit();
        updateShiftKeyState(getCurrentInputEditorInfo());
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }

    /**
     * Update the list of available candidates from the current composing
     * text.  This will need to be filled in by however you are determining
     * candidates.
     */
    private void updateCandidates() {
    	try{
        if (!mCompletionOn) {
            if (mComposing.length() > 0) {
                ArrayList<String> list = new ArrayList<String>();
                list.add(mComposing.toString());
                setSuggestions(list, true, true);
            } else {
                setSuggestions(null, false, false);
            }
        }
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }
    private void updateSuggestionCandidate()
    {
    	try{
    		if(!mCompletionOn)
    		{
    			if(mComposing.length()>0)
    			{
    				ArrayList<String> list=new ArrayList<String>();
    				list.add(mComposing.toString());
    				setSuggestions(list, true,true);
    			}else{
    				setSuggestions(null, false,false);
    			}
    		}
    	}catch(Exception e)
    	{
    	
    	}
    }
    
    public void setSuggestions(List<String> suggestions, boolean completions,
            boolean typedWordValid) {
    	try{
        if (suggestions != null && suggestions.size() > 0) {
            setCandidatesViewShown(true);
        } else if (isExtractViewShown()) {
            setCandidatesViewShown(true);
        }
        if (mTamilPreviewView != null) {
            mTamilPreviewView.setSuggestions(suggestions, completions, typedWordValid);
        }
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }
   
    private void handleBackspace() {

        try{
        final int length = mComposing.length();
        if (length > 1) {
            mComposing.delete(length - 1, length);
            getCurrentInputConnection().commitText(mComposing, 1);
            updateCandidates();
            updateSuggestionCandidate();
        } else if (length > 0) {
            mComposing.setLength(0);
            getCurrentInputConnection().commitText("", 0);
            updateCandidates();
            updateSuggestionCandidate();
        } else {
            keyDownUp(KeyEvent.KEYCODE_DEL);
        }
       // updateShiftKeyState(getCurrentInputEditorInfo());
        updateCandidateText(0);
        updateCandidateSuggestionText();
        }
        catch (Exception e) {
			// TODO: handle exception
		}
    }

    public void handleShift() {
    	try{
    	isShifted = !isShifted;
    	if (mInputView == null) {
            checkToggleCapsLock();
            return;
            
        }
        
        Keyboard currentKeyboard = mInputView.getKeyboard();
        if (mQwertyKeyboard == currentKeyboard || mTamilKeyboard == currentKeyboard) {
            // Alphabet keyboard or tamil 
            checkToggleCapsLock();
            mInputView.setShifted(mCapsLock || !mInputView.isShifted());
        }
        else if(currentKeyboard == mTamil99Keyboard){
        	mTamil99Keyboard.setShifted(true);
            mInputView.setKeyboard(mTamil99ShiftedKeyboard);
            mTamil99ShiftedKeyboard.setShifted(true);        	
        }
        else if(currentKeyboard == mTamil99ShiftedKeyboard){
        	mTamil99ShiftedKeyboard.setShifted(false);
            mInputView.setKeyboard(mTamil99Keyboard);
            mTamil99Keyboard.setShifted(false);        	
        }
        else if (currentKeyboard == mSymbolsKeyboard) {
            mSymbolsKeyboard.setShifted(true);
            mInputView.setKeyboard(mSymbolsShiftedKeyboard);
            mSymbolsShiftedKeyboard.setShifted(true);
        } else if (currentKeyboard == mSymbolsShiftedKeyboard) {
            mSymbolsShiftedKeyboard.setShifted(false);
            mInputView.setKeyboard(mSymbolsKeyboard);
            mSymbolsKeyboard.setShifted(false);
        }
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }
    
	private void handleCharacter(int primaryCode, int[] keyCodes) {
		try {
			mCandidateAndConfigLayout.setVisibility(View.VISIBLE);
			setCandidatesViewShown(true);
			System.out.println("handleChar 11111111111111111111111111");
			mTamilPreviewView.getPreviewView().setVisibility(View.VISIBLE);
		} catch (Exception e) {
			// TODO: handle exception
		}
		Keyboard currentKeyboard = mInputView.getKeyboard();
//		if (currentKeyboard == mTamil99Keyboard
//				|| currentKeyboard == mTamil99ShiftedKeyboard) {

		if (TamilKeyboard.CURRENT_LAYOUT == TamilKeyboard.KEYBOARD_TAMIL99) {
			handleTamil99Char(primaryCode, keyCodes);
		} else {
			try {

				if (isInputViewShown()) {
					if (mInputView.isShifted()) {
						primaryCode = Character.toUpperCase(primaryCode);
						if (Constants.PHONETIC_SHIFTED_KEYS.containsKey(primaryCode)) {
							primaryCode = Constants.PHONETIC_SHIFTED_KEYS
									.get(primaryCode);
						}

						if (currentKeyboard == mTamilKeyboard) {
							mInputView.setShifted(false);
						}
					}
				}
				if (isShifted || mShift) {
					if (Constants.PHONETIC_SHIFTED_KEYS.containsKey(primaryCode)) {
						primaryCode = Constants.PHONETIC_SHIFTED_KEYS.get(primaryCode);
					}

					if (currentKeyboard == mTamilKeyboard) {
						isShifted = false;
					}

				}
				// Agaram
				if (primaryCode == 2949) {
					if (mPrevChar == 3021) {
						mPrevChar = m2ndPrevChar;
						m2ndPrevChar = 0;
						handleBackspace();
						updateCandidateText(0);
						updateCandidateSuggestionText();
					} else {
						if (mPrevChar == 2949) {
							handleBackspace();
							primaryCode = 2950;
						} else if (UYIR_MAI_LIST.contains(mPrevChar)) {
							primaryCode = 3006;
						}
						getCurrentInputConnection().commitText(
								String.valueOf((char) primaryCode), 1);
						mText.append(String.valueOf((char) primaryCode));
						mPrevChar = primaryCode;
						updateCandidateText(0);
						updateCandidateSuggestionText();
					}
				} else if (Constants.KURIL_EXECPT_AGARAM.contains(primaryCode)) {
					if (mPrevChar == 3021) {
						handleBackspace();
						primaryCode = Constants.KURIL_SYMBOLS_MAP
								.get(primaryCode);
					} else if (Constants.KURIL_SYMBOLS.contains(mPrevChar)
							&& mPrevChar == Constants.KURIL_SYMBOLS_MAP
									.get(primaryCode)) {
						handleBackspace();
						primaryCode = Constants.NEDIL_SYMBOLS_MAP
								.get(primaryCode);
					} else if (primaryCode == 2951 && mPrevChar == 2949) {
						handleBackspace();
						primaryCode = 2960;
					} else if (primaryCode == 2951
							&& UYIR_MAI_LIST.contains(mPrevChar)) {
						primaryCode = 3016;
					}

					else if (primaryCode == mPrevChar) {
						handleBackspace();
						primaryCode = Constants.UYIR_NEDIL_MAP.get(primaryCode);
					}
					getCurrentInputConnection().commitText(
							String.valueOf((char) primaryCode), 1);
					mText.append(String.valueOf((char) primaryCode));
					mPrevChar = primaryCode;

					updateCandidateText(0);
				} else if (UYIR_MAI_LIST.contains(primaryCode)) {
					// nj
					if (primaryCode == 2972 && m2ndPrevChar == 2985
							&& mPrevChar == 3021) {
						handleBackspace();
						handleBackspace();
						primaryCode = 2974;
					}
					// ng
					if (primaryCode == 2965 && m2ndPrevChar == 2985
							&& mPrevChar == 3021) {
						handleBackspace();
						handleBackspace();
						primaryCode = 2969;
					}
					// th
					if (primaryCode == 3001 && m2ndPrevChar == 2975
							&& mPrevChar == 3021) {
						handleBackspace();
						handleBackspace();
						primaryCode = 2980;
					}
					// sh
					if (primaryCode == 3001 && m2ndPrevChar == 2970
							&& mPrevChar == 3021) {
						handleBackspace();
						handleBackspace();
						primaryCode = 2999;
					}

					getCurrentInputConnection().commitText(
							String.valueOf((char) primaryCode), 1);

					mText.append(String.valueOf((char) primaryCode));
					mPrevChar = primaryCode;

					getCurrentInputConnection().commitText(
							String.valueOf((char) 3021), 1);
					mText.append(String.valueOf((char) 3021));
					m2ndPrevChar = primaryCode;
					mPrevChar = 3021;
					updateCandidateText(0);
					 updateSuggestionCandidate();
				} else {
					getCurrentInputConnection().commitText(
							String.valueOf((char) primaryCode), 1);

					mText.append(String.valueOf((char) primaryCode));
					mPrevChar = primaryCode;
					updateCandidateText(0);
					 updateSuggestionCandidate();

				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

    public void handleTamil99Char(int primaryCode, int[] keyCodes){
    	try{
    		if (UYIR_MAI_LIST.contains(mPrevChar)) {
    			switch (primaryCode) {
				case 2950:
					primaryCode = 3006;
					break;
				case 2951:
					primaryCode = 3007;
					break;
				case 2952:
					primaryCode = 3008;
					break;
				case 2953:
					primaryCode = 3009;
					break;
				case 2954:
					primaryCode = 3010;
					break;
				case 2958:
					primaryCode = 3014;
					break;
				case 2959:
					primaryCode = 3015;
					break;
				case 2960:
					primaryCode = 3016;
					break;
				case 2962:
					primaryCode = 3018;
					break;
				case 2963:
					primaryCode = 3019;
					break;
				case 2964:
					primaryCode = 3020;
					break;

				default:
					break;
				}
    		}
			getCurrentInputConnection().commitText(
					String.valueOf((char) primaryCode), 1);
	
			mText.append(String.valueOf((char) primaryCode));
			mPrevChar = primaryCode;
			System.out.println("keycodes....."+mPrevChar);
			updateCandidateText(0);
			 updateSuggestionCandidate();
		} catch (Exception e) {
			// TODO: handle exception
		}

    	
    }
    
    
    private void handleClose() {
    	try{
        commitTyped(getCurrentInputConnection());
        requestHideSelf(0);
        mInputView.closing();
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }

    private void checkToggleCapsLock() {
    	try{
        long now = System.currentTimeMillis();
        if (mLastShiftTime + 800 > now) {
            mCapsLock = !mCapsLock;
            mLastShiftTime = 0;
        } else {
            mLastShiftTime = now;
        }
    	}
    	catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    private String getWordSeparators() {
        return mWordSeparators;
    }
    
    public boolean isWordSeparator(int code) {
        String separators = getWordSeparators();
        return separators.contains(String.valueOf((char)code));
    }

    public void pickDefaultCandidate() {
        pickSuggestionManually(0);
    }
    public void toggleTamilKeyBoard(){
    	try{
    	mTamil = ! mTamil;
    	if(mTamil){
    		mInputView.setKeyboard(mTamilKeyboard);
    	}else{
    		mInputView.setKeyboard(mQwertyKeyboard);
    	}
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }
    public void showSoftKeyboardView(){
    	try{
    	showingSoftKeyboard = true;;
		mInputView.setVisibility(View.VISIBLE);
    	}catch (Exception e) {
			// TODO: handle exception
		}
    	
    }
    public void hideSoftKeyboardView(){
    	try{
    	showingSoftKeyboard = false;
    		mInputView.setVisibility(View.GONE);
    	}catch (Exception e) {
			// TODO: handle exception
		}
    }

    
    public void toggleSoftKeyboardView(){
    	try{
    	showingSoftKeyboard = ! showingSoftKeyboard;
    	if(showingSoftKeyboard){
    		mInputView.setVisibility(View.VISIBLE);
    	}else{
    		mInputView.setVisibility(View.GONE);
    	}
    	}catch (Exception e) {
			// TODO: handle exception
		}
    	
    }
    public void pickSuggestionManually(int index) {
        if (mCompletionOn && mCompletions != null && index >= 0
                && index < mCompletions.length) {
            CompletionInfo ci = mCompletions[index];
            getCurrentInputConnection().commitCompletion(ci);
            if (mTamilPreviewView != null) {
                mTamilPreviewView.clear();
            }
            updateShiftKeyState(getCurrentInputEditorInfo());
        } else if (mComposing.length() > 0) {
            // If we were generating candidate suggestions for the current
            // text, we would commit one of them here.  But for this sample,
            // we will just commit the current text.
            commitTyped(getCurrentInputConnection());
        }
    }
    
    public void swipeRight() {
        if (mCompletionOn) {
            pickDefaultCandidate();
        }
    }
    
    public void swipeLeft() {
        handleBackspace();
    }

    public void swipeDown() {
        handleClose();
    }

    public void swipeUp() {
    }
    
    public void onPress(int primaryCode) {
    }
    
    public void onRelease(int primaryCode) {
    }
    
	public void showKeyboardLayoutDialog() {
		Resources r = getResources();
		AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this,R.style.AlertDialogCustom));
		builder.setCancelable(true);
		builder.setNegativeButton(android.R.string.cancel, null);
		builder.setTitle("Keyboard Options");
		
		builder.setItems(
				new CharSequence[] { "Tamil Keyboard", "Sanskrit Tamil keys",
						"English-Tamil keys", "English" },
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface di, int position) {
						mTamil = !mTamil;
						TamilKeyboard.CURRENT_LAYOUT = position;
						if (mInputView != null) {
							switch (position) {
							case TamilKeyboard.KEYBOARD_TAMIL99:
								// configBtns.keyboardLayoutTxt.setText("Tamil 99");
								mInputView.setKeyboard(mTamil99Keyboard);
								break;
							case TamilKeyboard.KEYBOARD_PHONETIC:
								// configBtns.keyboardLayoutTxt.setText("Phonetic - Tamil keys");
								mInputView.setKeyboard(mTamilKeyboard);
								break;
							case TamilKeyboard.KEYBOARD_PHONETIC_ENGLISH:
								// configBtns.keyboardLayoutTxt.setText("Phonetic - English keys");
								mInputView
										.setKeyboard(mPhoneticEnglishKeyboard);
								break;
							case TamilKeyboard.KEYBOARD_ENGLISH:
								// configBtns.keyboardLayoutTxt.setText("English");
								mInputView.setKeyboard(mQwertyKeyboard);
								break;
							}
						}
						di.dismiss();
					}
				});

		mOptionsDialog = builder.create();
		Window window = mOptionsDialog.getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.token = mCandidateAndConfigLayout.getWindowToken();
		lp.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;
		window.setAttributes(lp);
		window.addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		mOptionsDialog.show();
	}

  public KeyboardView getInputView(){
	  return mInputView;
  }

	@Override
	public void onSharedPreferenceChanged(SharedPreferences arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}
}
