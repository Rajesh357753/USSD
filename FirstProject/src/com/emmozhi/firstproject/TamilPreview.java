package com.emmozhi.firstproject;

import java.util.List;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TamilPreview extends InputMethodService {
	private ImageView mKeyboardLayoutSelectionImageView;
	LayoutInflater mInflater;
	LayoutInflater mSuggInflater1;
	TamilKeyboard mtamilkeyboard;
	private static final int PREVIEW_MIN = 1;
	private static final int PREVIEW_MAX = 2;

	private static final int SUGGESSTION_MIN = 1;
	private static final int SUGGESSTION_MAX = 2;

	private int previewMaxMinState = PREVIEW_MIN;
	private int suggestionMaxMinState = SUGGESSTION_MIN;

	private LinearLayout mPreviewContainer;
	public LinearLayout mSuggestionPreviewContainer;
	private Resources mEesources;
	private TextView mPreviewTextView;
	private TamilSoftKeyboard mTamilSoftKeyboard;
	
	public MyInputConnection minputconnection;

	public LinearLayout mSuggestionTextPosition1;
	public LinearLayout mSuggestionTextPosition2;
	public LinearLayout mSuggestionTextPosition3;
	public LinearLayout mSuggestionTextPosition11;
	public LinearLayout mSuggestionTextPosition21;
	public LinearLayout mSuggestionTextPosition31;

	private LinearLayout mLogoContainer;
	private LinearLayout mhelpContainer;
	private LinearLayout mLeftArrowContainer;
	private LinearLayout mRightArrowContainer;
	private LinearLayout mMaxMinContainer;
	private LinearLayout emmozhiIcon;

	private ImageView mMoveToEndArrow;
	private ImageView mLefttArrow;
	private ImageView mMaxMin;
	private ImageView emmozhiSettingsIcon;
	private ImageView emmozhihelpCenter;
    private KeyboardView minputView;
	private FrameLayout mPreviewFrame;
	private FrameLayout mPreviewSuggFrame1;

	private TextView dictionaryPreview1;
	private TextView dictionarypreview2;
	private TextView dictionarypreview3;

	public TextView mPreviewSuggestionText1;
	public TextView mPreviewSuggestionText2;
	public TextView mPreviewSuggestionText3;
	public TextView mPreviewSuggestionText11;
	public TextView mPreviewSuggestionText21;
	public TextView mPreviewSuggestionText31;
	private static TamilPreview mTamilPreview;

	public TamilPreview(TamilSoftKeyboard tamilSoftKeyboard) {
		this.mTamilSoftKeyboard = tamilSoftKeyboard;
		this.mInflater = mTamilSoftKeyboard.getLayoutInflater();
		this.mEesources = tamilSoftKeyboard.getResources();

		initPreview();
	}

	private void initPreview() {
		mPreviewContainer = (LinearLayout) mInflater.inflate(
				R.layout.previewview, null);
		mPreviewFrame = (FrameLayout) mPreviewContainer
				.findViewById(R.id.previewFrame);
		initPreviewTextView();
		/*initPreviewSuggestionText1();
		initPreviewSuggestionText2();
		initPreviewSuggestionText3();
		initPreviewSuggestionText11();
		initPreviewSuggestionText21();
		initPreviewSuggestionText31();*/
		initKeyboardLayoutSelectionImageView();
		initArrowKeys();
	}

	public void initSugesstionPreview1() {
		mSuggestionPreviewContainer = (LinearLayout) mSuggInflater1.inflate(
				R.layout.previewview, null);
		mPreviewSuggFrame1 = (FrameLayout) mSuggestionPreviewContainer
				.findViewById(R.id.previewFrame);
		/*mSuggestionTextPosition1 = (LinearLayout) mPreviewSuggFrame1
				.findViewById(R.id.text_view_1);*/
		initPreviewSuggestionText1();

	}

	public void initSugesstionPreview2() {
		mSuggestionPreviewContainer = (LinearLayout) mSuggInflater1.inflate(
				R.layout.previewview, null);
		mPreviewSuggFrame1 = (FrameLayout) mSuggestionPreviewContainer
				.findViewById(R.id.previewFrame);
	/*	mSuggestionTextPosition2 = (LinearLayout) mPreviewSuggFrame1
				.findViewById(R.id.text_view_2);*/
		initPreviewSuggestionText2();

	}

	public void initSugesstionPreview3() {
		mSuggestionPreviewContainer = (LinearLayout) mSuggInflater1.inflate(
				R.layout.previewview, null);
		mPreviewSuggFrame1 = (FrameLayout) mSuggestionPreviewContainer
				.findViewById(R.id.previewFrame);
	/*	mSuggestionTextPosition3 = (LinearLayout) mPreviewSuggFrame1
				.findViewById(R.id.text_view_3);*/
		initPreviewSuggestionText1();

	}

	public void initSugesstionPreview11() {
		mSuggestionPreviewContainer = (LinearLayout) mSuggInflater1.inflate(
				R.layout.previewview, null);
		mPreviewSuggFrame1 = (FrameLayout) mSuggestionPreviewContainer
				.findViewById(R.id.previewFrame);
	/*	mSuggestionTextPosition3 = (LinearLayout) mPreviewSuggFrame1
				.findViewById(R.id.text_view_11);*/
		initPreviewSuggestionText11();

	}

	public void initSugesstionPreview21() {
		mSuggestionPreviewContainer = (LinearLayout) mSuggInflater1.inflate(
				R.layout.previewview, null);
		mPreviewSuggFrame1 = (FrameLayout) mSuggestionPreviewContainer
				.findViewById(R.id.previewFrame);
		/*mSuggestionTextPosition3 = (LinearLayout) mPreviewSuggFrame1
				.findViewById(R.id.text_view_21);*/
		initPreviewSuggestionText21();

	}

	public void initSugesstionPreview31() {
		mSuggestionPreviewContainer = (LinearLayout) mSuggInflater1.inflate(
				R.layout.previewview, null);
		mPreviewSuggFrame1 = (FrameLayout) mSuggestionPreviewContainer
				.findViewById(R.id.previewFrame);
		/*mSuggestionTextPosition3 = (LinearLayout) mPreviewSuggFrame1
				.findViewById(R.id.text_view_31);*/
		initPreviewSuggestionText31();

	}

	private void dictionaryPreview() {

	}

	private void initPreviewTextView() {
		mPreviewTextView = (TextView) mPreviewContainer
				.findViewById(R.id.previewText);
		mPreviewTextView.setBackgroundColor(mEesources
				.getColor(R.color.candidate_background));

		mPreviewTextView.setTextColor(Color.BLACK);
		mPreviewTextView.setTextSize(16);
		mPreviewTextView.setTypeface(Typeface.createFromAsset(
				mTamilSoftKeyboard.getAssets(), "fonts/mylai.ttf"),
				Typeface.BOLD);
		mPreviewTextView.setHorizontalFadingEdgeEnabled(true);
		mPreviewTextView.setWillNotDraw(false);
		mPreviewTextView.setHorizontalScrollBarEnabled(false);
		mPreviewTextView.setVerticalScrollBarEnabled(false);
		mPreviewTextView.setMovementMethod(new ScrollingMovementMethod());

	}

	private void initPreviewSuggestionText1() {
		/*mPreviewSuggestionText1 = (TextView) mPreviewContainer
				.findViewById(R.id.text_view_1);*/
		mPreviewSuggestionText1.setBackgroundColor(mEesources
				.getColor(R.color.candidate_background));
		mPreviewSuggestionText1.setTextColor(Color.BLACK);
		mPreviewSuggestionText1.setTextSize(16);
		mPreviewSuggestionText1.setTypeface(Typeface.createFromAsset(
				mTamilSoftKeyboard.getAssets(), "fonts/mylai.ttf"),
				Typeface.BOLD);
		mPreviewSuggestionText1.setHorizontalFadingEdgeEnabled(true);
		mPreviewSuggestionText1.setWillNotDraw(false);
		mPreviewSuggestionText1.setHorizontalScrollBarEnabled(false);
		mPreviewSuggestionText1.setVerticalScrollBarEnabled(false);
		mPreviewSuggestionText1
				.setMovementMethod(new ScrollingMovementMethod());
	}

	private void initPreviewSuggestionText2() {
		/*mPreviewSuggestionText2 = (TextView) mPreviewContainer
				.findViewById(R.id.text_view_2);*/
		mPreviewSuggestionText2.setBackgroundColor(mEesources
				.getColor(R.color.candidate_background));
		mPreviewSuggestionText2.setTextColor(Color.BLACK);
		mPreviewSuggestionText2.setTextSize(16);
		mPreviewSuggestionText2.setTypeface(Typeface.createFromAsset(
				mTamilSoftKeyboard.getAssets(), "fonts/mylai.ttf"),
				Typeface.BOLD);
		mPreviewSuggestionText2.setHorizontalFadingEdgeEnabled(true);
		mPreviewSuggestionText2.setWillNotDraw(false);
		mPreviewSuggestionText2.setHorizontalScrollBarEnabled(false);
		mPreviewSuggestionText2.setVerticalScrollBarEnabled(false);
		mPreviewSuggestionText2
				.setMovementMethod(new ScrollingMovementMethod());

	}

	private void initPreviewSuggestionText3() {
		/*mPreviewSuggestionText3 = (TextView) mPreviewContainer
				.findViewById(R.id.text_view_3);*/
		mPreviewSuggestionText3.setBackgroundColor(mEesources
				.getColor(R.color.candidate_background));
		mPreviewSuggestionText3.setTextColor(Color.BLACK);
		mPreviewSuggestionText3.setTextSize(16);
		mPreviewSuggestionText3.setTypeface(Typeface.createFromAsset(
				mTamilSoftKeyboard.getAssets(), "fonts/mylai.ttf"),
				Typeface.BOLD);
		mPreviewSuggestionText3.setHorizontalFadingEdgeEnabled(true);
		mPreviewSuggestionText3.setWillNotDraw(false);
		mPreviewSuggestionText3.setHorizontalScrollBarEnabled(false);
		mPreviewSuggestionText3.setVerticalScrollBarEnabled(false);
		mPreviewSuggestionText3
				.setMovementMethod(new ScrollingMovementMethod());
	
	}

	private void initPreviewSuggestionText11() {
		/*mPreviewSuggestionText11 = (TextView) mPreviewContainer
				.findViewById(R.id.text_view_11);*/
		mPreviewSuggestionText11.setBackgroundColor(mEesources
				.getColor(R.color.candidate_background));
		mPreviewSuggestionText11.setTextColor(Color.BLACK);
		mPreviewSuggestionText11.setTextSize(16);
		mPreviewSuggestionText11.setTypeface(Typeface.createFromAsset(
				mTamilSoftKeyboard.getAssets(), "fonts/mylai.ttf"),
				Typeface.BOLD);
		mPreviewSuggestionText11.setHorizontalFadingEdgeEnabled(true);
		mPreviewSuggestionText11.setWillNotDraw(false);
		mPreviewSuggestionText11.setHorizontalScrollBarEnabled(false);
		mPreviewSuggestionText11.setVerticalScrollBarEnabled(false);
		mPreviewSuggestionText11
				.setMovementMethod(new ScrollingMovementMethod());
	}

	private void initPreviewSuggestionText21() {
		/*mPreviewSuggestionText21 = (TextView) mPreviewContainer
				.findViewById(R.id.text_view_21);*/
		mPreviewSuggestionText21.setBackgroundColor(mEesources
				.getColor(R.color.candidate_background));
		mPreviewSuggestionText21.setTextColor(Color.BLACK);
		mPreviewSuggestionText21.setTextSize(16);
		mPreviewSuggestionText21.setTypeface(Typeface.createFromAsset(
				mTamilSoftKeyboard.getAssets(), "fonts/mylai.ttf"),
				Typeface.BOLD);
		mPreviewSuggestionText21.setHorizontalFadingEdgeEnabled(true);
		mPreviewSuggestionText21.setWillNotDraw(false);
		mPreviewSuggestionText21.setHorizontalScrollBarEnabled(false);
		mPreviewSuggestionText21.setVerticalScrollBarEnabled(false);
		mPreviewSuggestionText21
				.setMovementMethod(new ScrollingMovementMethod());
	}

	private void initPreviewSuggestionText31() {
		/*mPreviewSuggestionText31 = (TextView) mPreviewContainer
				.findViewById(R.id.text_view_31);*/
		mPreviewSuggestionText31.setBackgroundColor(mEesources
				.getColor(R.color.candidate_background));
		mPreviewSuggestionText31.setTextColor(Color.BLACK);
		mPreviewSuggestionText31.setTextSize(16);
		mPreviewSuggestionText31.setTypeface(Typeface.createFromAsset(
				mTamilSoftKeyboard.getAssets(), "fonts/mylai.ttf"),
				Typeface.BOLD);
		mPreviewSuggestionText31.setHorizontalFadingEdgeEnabled(true);
		mPreviewSuggestionText31.setWillNotDraw(false);
		mPreviewSuggestionText31.setHorizontalScrollBarEnabled(false);
		mPreviewSuggestionText31.setVerticalScrollBarEnabled(false);
		mPreviewSuggestionText31
				.setMovementMethod(new ScrollingMovementMethod());
	}

	private void initKeyboardLayoutSelectionImageView() {
		// mKeyboardLayoutSelectionImageView =
		// (ImageView)mPreviewContainer.findViewById(R.id.keyboardSelectionImageView);
		// mKeyboardLayoutSelectionImageView.setImageResource(R.drawable.icons_anjal);
		// mKeyboardLayoutSelectionImageView.setOnClickListener(new
		// View.OnClickListener() {
		// public void onClick(View v) {
		// mTamilSoftKeyboard.showKeyboardLayoutDialog();
		// }
		// });
	}

	private void initArrowKeys() {
		mMaxMin = (ImageView) mPreviewContainer.findViewById(R.id.max_min);
		mLogoContainer = (LinearLayout) mPreviewContainer
				.findViewById(R.id.logoContainer);
		mLogoContainer.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mTamilSoftKeyboard.showKeyboardLayoutDialog();
			}
		});
		/*mhelpContainer=(LinearLayout)mPreviewContainer.findViewById(R.id.help_1_view);
		mhelpContainer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				new HelpActionclass();
				
			}
		});*/
		mLeftArrowContainer = (LinearLayout) mPreviewContainer
				.findViewById(R.id.leftArrowContainer);
		mLeftArrowContainer.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mTamilSoftKeyboard.moveCursorToLeft();
			}
		});

		mRightArrowContainer = (LinearLayout) mPreviewContainer
				.findViewById(R.id.rightArrowContainer);
		mRightArrowContainer.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mTamilSoftKeyboard.moveCursorToRight();
			}
		});

		mMaxMinContainer = (LinearLayout) mPreviewContainer
				.findViewById(R.id.maxMinContainer);
		mMaxMinContainer.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Resources r = mTamilSoftKeyboard.getResources();

				switch (previewMaxMinState) {
				case PREVIEW_MIN:
					mRightArrowContainer.getLayoutParams().height = r
							.getDimensionPixelSize(R.dimen.arrow_exp_hight);
					mLeftArrowContainer.getLayoutParams().height = r
							.getDimensionPixelSize(R.dimen.arrow_exp_hight);
					mPreviewTextView.getLayoutParams().height = r
							.getDimensionPixelSize(R.dimen.preview_exp_hight);
					// mPreviewSuggestionText1.getLayoutParams().height=r.getDimensionPixelSize((R.dimen.sugges_preview_height);
					mPreviewFrame.requestLayout();
					mMaxMin.setImageResource(R.drawable.arrow_down);
					previewMaxMinState = PREVIEW_MAX;
					break;

				default:
					mRightArrowContainer.getLayoutParams().height = r
							.getDimensionPixelSize(R.dimen.arrow_normal_hight);
					mLeftArrowContainer.getLayoutParams().height = r
							.getDimensionPixelSize(R.dimen.arrow_normal_hight);
					mPreviewTextView.getLayoutParams().height = r
							.getDimensionPixelSize(R.dimen.preview_normal_hight);
					mPreviewFrame.requestLayout();
					mMaxMin.setImageResource(R.drawable.arrow_up);
					previewMaxMinState = PREVIEW_MIN;

					break;
				}

			}
		});

	}

	public void update(String txt,int cursorPos) {
		txt = UnicodeUtil.unicode2tsc(txt);
		//txt=txt.concat("«ôÀ¡");
		
		mPreviewTextView.setText(txt);
		/*
		 * mPreviewSuggestionText1.setText(txt);
		 * mPreviewSuggestionText2.setText(txt);
		 * mPreviewSuggestionText3.setText(txt);
		 */
		Spannable text = (Spannable) mPreviewTextView.getText();
		System.out.println(text);
		/*
		 * Spannable textsug1=(Spannable)mPreviewSuggestionText1.getText();
		 * Spannable textsug2=(Spannable)mPreviewSuggestionText2.getText();
		 * Spannable textsug3=(Spannable)mPreviewSuggestionText3.getText();
		 */
		if (cursorPos > 2) {
			cursorPos = cursorPos - 2;
			if (cursorPos > text.length()) {
				cursorPos = text.length();
			}
		}
		/*
		 * else if((cursorPos>2) &(cursorPos<8) ) { cursorPos =cursorPos - 2;
		 * if(cursorPos>textsug1.length()) { cursorPos=textsug1.length(); } }
		 * else if((cursorPos>2)&(cursorPos<12)) { cursorPos =cursorPos - 2;
		 * if(cursorPos>textsug2.length()) {அம�?மா cursorPos=textsug2.length(); }
		 * } else if(cursorPos>2) { cursorPos =cursorPos - 2;
		 * if(cursorPos>textsug3.length()) { cursorPos=textsug3.length(); } }
		 */
		try {
			InputConnection ic=getCurrentInputConnection();
			ic.setComposingText(text, cursorPos);
			//Selection.setSelection(text, cursorPos);
			/*
			 * Selection.setSelection(textsug1, cursorPos);
			 * Selection.setSelection(textsug2, cursorPos);
			 * Selection.setSelection(textsug3, cursorPos);
			 */
		} catch (Exception e) {
		}
	}

	/*
	 * public void updateHead() { updatesuggestion(""); }
	 */

	public void updatesuggestion1(String txt, int curpos) {
		char modify = txt.charAt(0);
        final int curpos1=curpos;
		txt = UnicodeUtil.unicode2tsc(txt);
		System.out.println(txt);
		//System.out.println(txt.substring(1, 2));
		/*
		 * String test=txt.substring(1,2); System.out.println(test);
		 */
		// System.out.println(txt);
		System.out.println("testing1111111111111111111111111111");
		String s1[] = new String[150];
		s1[0] = "அ";
		s1[1] = "ஆ";
		s1[2] = "இ";
		s1[3] = "ஈ";
		s1[4] = "உ";
		s1[5] = "ஊ";
		s1[6] = "எ";
		s1[7] = "�?";
		s1[8] = "�?";
		s1[9] = "ஒ";
		s1[10] = "ஓ";
		s1[11] = "ஔ";
		s1[12] = "க";
		s1[13] = "ங";
		s1[14] = "ச";
		s1[15] = "ஞ";
		s1[16] = "ட";
		s1[17] = "ண";
		s1[18] = "த";
		s1[19] = "ந";
		s1[20] = "ப";
		s1[21] = "ம";
		s1[22] = "ய";
		s1[23] = "ர";
		s1[24] = "ல";
		s1[25] = "வ";
		s1[26] = "ழ";
		s1[27] = "ள";
		s1[28] = "ற";
		s1[29] = "ன";
		s1[33] = "மீ";
		s1[34]="பீ";
		s1[36]="பே";
		s1[38]="த�?";
		s1[40]="ப�?";
		
		s1[30]="பச�? ";
		s1[31]="ஆள�?";
		s1[32]="மீன�?";
		s1[35]="பீர�?";
		s1[37]="பேரி";
		s1[39]="த�?ணி";
		s1[41]="ப�?வி ";
		
		
		
		
		s1[0] = UnicodeUtil.unicode2tsc(s1[0]);
		s1[1] = UnicodeUtil.unicode2tsc(s1[1]);
		s1[2] = UnicodeUtil.unicode2tsc(s1[2]);
		s1[3] = UnicodeUtil.unicode2tsc(s1[3]);
		s1[4] = UnicodeUtil.unicode2tsc(s1[4]);
		s1[5] = UnicodeUtil.unicode2tsc(s1[5]);
		s1[6] = UnicodeUtil.unicode2tsc(s1[6]);
		s1[7] = UnicodeUtil.unicode2tsc(s1[7]);
		s1[8] = UnicodeUtil.unicode2tsc(s1[8]);
		s1[9] = UnicodeUtil.unicode2tsc(s1[9]);
		s1[10] = UnicodeUtil.unicode2tsc(s1[10]);
		s1[11] = UnicodeUtil.unicode2tsc(s1[11]);
		s1[12] = UnicodeUtil.unicode2tsc(s1[12]);
		s1[13] = UnicodeUtil.unicode2tsc(s1[13]);
		s1[14] = UnicodeUtil.unicode2tsc(s1[14]);
		s1[15] = UnicodeUtil.unicode2tsc(s1[15]);
		s1[16] = UnicodeUtil.unicode2tsc(s1[16]);
		s1[17] = UnicodeUtil.unicode2tsc(s1[17]);
		s1[18] = UnicodeUtil.unicode2tsc(s1[18]);
		s1[19] = UnicodeUtil.unicode2tsc(s1[19]);
		s1[20] = UnicodeUtil.unicode2tsc(s1[20]);
		s1[21] = UnicodeUtil.unicode2tsc(s1[21]);
		s1[22] = UnicodeUtil.unicode2tsc(s1[22]);
		s1[23] = UnicodeUtil.unicode2tsc(s1[23]);
		s1[24] = UnicodeUtil.unicode2tsc(s1[24]);
		s1[25] = UnicodeUtil.unicode2tsc(s1[25]);
		s1[26] = UnicodeUtil.unicode2tsc(s1[26]);
		s1[27] = UnicodeUtil.unicode2tsc(s1[27]);
		s1[28] = UnicodeUtil.unicode2tsc(s1[28]);
		s1[30] = UnicodeUtil.unicode2tsc(s1[30]);
		s1[31] = UnicodeUtil.unicode2tsc(s1[31]);
		s1[32] = UnicodeUtil.unicode2tsc(s1[32]);
		s1[33] = UnicodeUtil.unicode2tsc(s1[33]);
		s1[34] = UnicodeUtil.unicode2tsc(s1[34]);
		s1[35] = UnicodeUtil.unicode2tsc(s1[35]);
		s1[36] = UnicodeUtil.unicode2tsc(s1[36]);
		s1[37] = UnicodeUtil.unicode2tsc(s1[37]);
		s1[38] = UnicodeUtil.unicode2tsc(s1[38]);
		s1[39] = UnicodeUtil.unicode2tsc(s1[39]);
		s1[40] = UnicodeUtil.unicode2tsc(s1[40]);
		s1[41] = UnicodeUtil.unicode2tsc(s1[41]);
		
		
		if ((txt != null)) {

			if ((curpos > 0) && (txt.substring(0, 1).equals(s1[0]))) {
				mPreviewSuggestionText1.setText(s1[0]);
				mPreviewSuggestionText1.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("«");
								Spannable textsug1 = (Spannable) mPreviewSuggestionText1
										.getText();
								int curpos2=curpos1;
								try {
									if (curpos1 > 2) {
										
										curpos2 = curpos2 - 2;
										if (curpos2 > textsug1.length()) {
											curpos2 = textsug1.length();
										}
									}
									Selection.setSelection(textsug1,curpos2);
								} catch (Exception e) {

								}

							}
						});
				
				
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[1]))) {
				mPreviewSuggestionText1.setText(s1[31]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¬û");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[2]))) {
				mPreviewSuggestionText1.setText(s1[2]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("þ");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[3]))) {
				mPreviewSuggestionText1.setText(s1[3]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("®");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[4]))) {
				mPreviewSuggestionText1.setText(s1[4]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¯");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[5]))) {
				mPreviewSuggestionText1.setText(s1[5]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("°");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[1]))) {
				mPreviewSuggestionText1.setText(s1[6]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("±");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[7]))) {
				mPreviewSuggestionText1.setText(s1[7]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("²");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[8]))) {
				mPreviewSuggestionText1.setText(s1[8]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("³");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[9]))) {
				mPreviewSuggestionText1.setText(s1[9]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("´");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[10]))) {
				mPreviewSuggestionText1.setText(s1[10]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("µ");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[11]))) {
				mPreviewSuggestionText1.setText(s1[11]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¶");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[12]))) {
				mPreviewSuggestionText1.setText(s1[12]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¸");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[13]))) {
				mPreviewSuggestionText1.setText(s1[13]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¹");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[14]))) {
				mPreviewSuggestionText1.setText(s1[14]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("º");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[15]))) {
				mPreviewSuggestionText1.setText(s1[15]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("»");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[16]))) {
				mPreviewSuggestionText1.setText(s1[16]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¼");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[17]))) {
				mPreviewSuggestionText1.setText(s1[17]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("½");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[18]))) {
				mPreviewSuggestionText1.setText(s1[18]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¾");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[19]))) {
				mPreviewSuggestionText1.setText(s1[19]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¿");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[20]))) {
				mPreviewSuggestionText1.setText(s1[30]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("À�?");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[21]))) {
				mPreviewSuggestionText1.setText(s1[21]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("�?");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[22]))) {
				mPreviewSuggestionText1.setText(s1[22]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("Â");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[23]))) {
				mPreviewSuggestionText1.setText(s1[23]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("Ã");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[24]))) {
				mPreviewSuggestionText1.setText(s1[24]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("Ä");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[25]))) {
				mPreviewSuggestionText1.setText(s1[25]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("Å");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[26]))) {
				mPreviewSuggestionText1.setText(s1[26]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("Æ");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[27]))) {
				mPreviewSuggestionText1.setText(s1[27]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("Ç");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[28]))) {
				mPreviewSuggestionText1.setText(s1[28]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("È");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			} else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[29]))) {
				mPreviewSuggestionText1.setText(s1[29]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("É");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			}
			else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[33]))) {
				mPreviewSuggestionText1.setText(s1[32]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("�?£");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			}
			else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[34]))) {
				mPreviewSuggestionText1.setText(s1[35]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("À£");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			}
			else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[36]))) {
				mPreviewSuggestionText1.setText(s1[37]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("§À");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			}
			else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[38]))) {
				mPreviewSuggestionText1.setText(s1[39]);
				mPreviewSuggestionText1
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("�?½");

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			}
			else if ((curpos > 0) && (txt.substring(0, 1).equals(s1[40]))) {
				mPreviewSuggestionText1.setText(s1[41]);
				mPreviewSuggestionText1.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("ÒÅ");
							    Spannable textsug2=(Spannable)mPreviewSuggestionText1.getText();
								Spannable textsug1 = (Spannable) mPreviewTextView.getText();
								int curpos2=curpos1;
								int curpos3=curpos1;
								try {
									if (curpos1 > 2) {
										
										curpos2 = curpos2 - 2;
										curpos3=curpos3-2;
										
										if (curpos2 > textsug1.length()) {
											curpos2 = textsug1.length();
											curpos3=textsug2.length();
										}
									}
									Selection.setSelection(textsug1,curpos2);
									Selection.setSelection(textsug2, curpos3);
								} catch (Exception e) {

								}


							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText1
						.getText();
			}



			else {
				mPreviewSuggestionText1.setText(null);
			}
			/*
			 * mPreviewSuggestionText1.setText(s1[1]);
			 * System.out.println("testing 2"); Spannable
			 * textsug2=(Spannable)mPreviewSuggestionText1.getText();
			 */

		}
		Spannable textsug1 = (Spannable) mPreviewSuggestionText1.getText();
		if (curpos > 2) {
			curpos = curpos - 2;
			if (curpos > textsug1.length()) {
				curpos = textsug1.length();
			}
		}
		try {
			Selection.setSelection(textsug1, curpos);
		} catch (Exception e) {

		}
	}

	public void updatesuggestion2(String txt, int curpos) {
		final int curpos1=curpos;
		txt = UnicodeUtil.unicode2tsc(txt);
		System.out.println("testing1111111111111111111111111111");
		System.out.println(txt.substring(0,2));
		String s1[] = new String[50];
		String s2[]=new String[50];
		s1[0] = "அம�?";
		s2[0] = "அம�?மா";
		s1[1]="அப�?";
		s2[1]="அப�?பா";
		s1[2]="கர";
		s2[2]="கரடி";
		s1[0] = UnicodeUtil.unicode2tsc(s1[0]);
		s1[1] = UnicodeUtil.unicode2tsc(s1[1]);
		s2[0] = UnicodeUtil.unicode2tsc(s2[0]);
		s2[1] = UnicodeUtil.unicode2tsc(s2[1]);
		s1[2] = UnicodeUtil.unicode2tsc(s1[2]);
		s2[2] = UnicodeUtil.unicode2tsc(s2[2]);
		/*
		 * String s2=""; s2=UnicodeUtil.unicode2tsc(s2);
		 */
		if (txt != null) {
			if ((curpos > 0) && (txt.substring(0, 2).equals(s1[0]))) {
				mPreviewSuggestionText2.setText(s2[0]);
				mPreviewSuggestionText2
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("«õ�?¡");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(0, 2).equals(s1[1]))) {
				mPreviewSuggestionText2.setText(s2[1]);
				mPreviewSuggestionText2
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								//
								 InputConnection ic=getCurrentInputConnection();
								
								System.out.println("testing appaaaa1");
								
									Spannable textsug1 = (Spannable) mPreviewTextView.getText();
									System.out.println(textsug1);
									int curpos2=curpos1;
									int curpos3=curpos1;
									try {
										// InputConnection ic=getCurrentInputConnection();
										 //(mPreviewTextView)ic.commitText("«ôÀ¡", curpos1);
											
											System.out.println("testing appaaaa2");
											//ic.commitText("«ôÀ¡", curpos1);
											
											System.out.println("testing appaaaa3");
										//textsug1=textsug1+textsug1;
										Selection.setSelection(textsug1,curpos2);
									
									} catch (Exception e) {

									}
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2.getText();

			}
			else if ((curpos > 0) && (txt.substring(0, 2).equals(s1[2]))) {
				mPreviewSuggestionText2.setText(s2[2]);
				mPreviewSuggestionText2
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¸ÃÊ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else {
				mPreviewSuggestionText2.setText(null);
			}

		}
		Spannable textsug2 = (Spannable) mPreviewSuggestionText2.getText();
		if (curpos > 2) {
			curpos = curpos - 2;
			if (curpos > textsug2.length()) {
				curpos = textsug2.length();
			}
		}
		try {
			Selection.setSelection(textsug2, curpos);
		} catch (Exception e) {

		}

	}

	public void updatesuggestion3(String txt, int curpos) {
		txt = UnicodeUtil.unicode2tsc(txt);
		//System.out.println(":"+txt.substring(curpos-3, curpos));
		String s1[] = new String[50];
		String s2[]=new String[50];
		 s1[0]="இற";
		s2[0]="இறப�?ப�?";
		s1[1]="வட";
		s2[1]="வட�?டம�?";
		s1[2]="சாள";
		s2[2]="சாளரம�?";
		s1[3]="ம";
		s2[3]="ம�?கவரி";
		s1[4]="சத";
		s2[4]="சத�?ரம�?	";
		s1[5]="சன";
		s2[5]="சனவரி";
		s1[6]="திங";
		s2[6]="திங�?கள�?";
		s1[7]="நட";
		s2[7]="நடத�?தை";
		s1[8]="ம�?டி";
		s2[8]="ம�?டிச�?ச�?";
		s1[9]="அறி";
		s2[9]="அறிக�?கை";
		s1[10]="இணை";
		s2[10]="இணையம�?";
		s1[11]="வியா";
		s2[11]="வியாழன�?";
		s1[12]="செவ";
		s2[12]="செவ�?வாய�?";
		s1[13]="இறை";
		s2[13]="இறைச�?சி";
		s1[14]="சான";
		s2[14]="சான�?விச�?";
		s1[15]="�?ப";
		s2[15]="�?ப�?ரல�?";	
		s1[16]="ஆக";
		s2[16]="ஆகஸ�?ட�?";
		s1[17]="நூல";
		s2[17]="நூலகம�?";
		s1[18]="மின";
		s2[18]="மின�?னல�?";
		s1[19]="இயற";
		s2[19]="இயற�?கை";
		s1[20]="தூக�?";
		s2[20]="தூக�?கம�?";
		s1[21]="தான";
		s2[21]="தான�?ந�?த�?";
		s1[22]="பேர";
		s2[22]="பேர�?ந�?த�?";
		s1[23]="கிழ";
		s2[23]="கிழக�?க�?";
		s1[24]="வட";
		s2[24]="வடக�?க�?";
		s1[25]="நக";
		s2[25]="நகரம�?";
		s1[26]="கப";
		s2[26]="கப�?பல�?";
		s1[27]="நண";
		s2[27]="நண�?பன�?";
		s1[28]="கட";
		s2[28]="கட�?டில�?";
		s1[29]="க�?ழ";
		s2[29]="க�?ழந�?தை	";
		s1[30]="பழ";
		s2[30]="பழ�?ப�?ப";
		s1[31]="எற";
		s2[31]="எற�?ம�?ப�?";
		s1[32]="வெண";
		s2[32]="வெண�?ணெய�?";
		s1[33]="சிங";
		s2[33]="சிங�?கம�?";
		s1[34]="கொ";
		s2[34]="கொட�?டை	";
		s1[35]="தக";
		s2[35]="தக�?காளி";
		s1[36]="தண";
		s2[36]="தண�?ணீர�?";
		s1[37]="மனி";
		s2[37]="மனிதர�?";
		s1[38]="சூரி";
		s2[38]="சூரியன�?";
		s1[39]="தனி";
		s2[39]="தனிமம�?";
		s1[40]="இர";
		s2[40]="இர�?ம�?ப�?";
		s1[41]="கண";
		s2[41]="கண�?ணாடி";
		s1[42]="உலோ";
		s2[42]="உலோகம�?";
		s1[43]="செல";
		s2[43]="செல�?பேசி	";
		s1[44]="உயர";
		s2[44]="உயர�?த�?தி";
		s1[45]="கூட�?";
		s2[45]="கூட�?டல�?";
		s1[46]="விண";
		s2[46]="விண�?மீன�?";
		s1[47]="வானி";
		s2[47]="வானிலை";
		s1[48]="மஞ";
		s2[48]="மஞ�?சள�? ";
	
		
		
		
		
		s1[0] = UnicodeUtil.unicode2tsc(s1[0]);
		s2[0] = UnicodeUtil.unicode2tsc(s2[0]);
		
		s1[1]=UnicodeUtil.unicode2tsc(s1[1]);
		s2[1]=UnicodeUtil.unicode2tsc(s2[1]);
		
		s1[2]=UnicodeUtil.unicode2tsc(s1[2]);
		s2[2]=UnicodeUtil.unicode2tsc(s2[2]);
		
		s1[3]=UnicodeUtil.unicode2tsc(s1[3]);
		s2[3]=UnicodeUtil.unicode2tsc(s2[3]);
		
		s1[4]=UnicodeUtil.unicode2tsc(s1[4]);
		s2[4]=UnicodeUtil.unicode2tsc(s2[4]);
		
		s1[5]=UnicodeUtil.unicode2tsc(s1[5]);
		s2[5]=UnicodeUtil.unicode2tsc(s2[5]);
		
		s1[6]=UnicodeUtil.unicode2tsc(s1[6]);
		s2[6]=UnicodeUtil.unicode2tsc(s2[6]);
		
		s1[7]=UnicodeUtil.unicode2tsc(s1[7]);
		s2[7]=UnicodeUtil.unicode2tsc(s2[7]);
		
		s1[8]=UnicodeUtil.unicode2tsc(s1[8]);
		s2[8]=UnicodeUtil.unicode2tsc(s2[8]);
		
		s1[9]=UnicodeUtil.unicode2tsc(s1[9]);
		s2[9]=UnicodeUtil.unicode2tsc(s2[9]);
		
		s1[10]=UnicodeUtil.unicode2tsc(s1[10]);
		s2[10]=UnicodeUtil.unicode2tsc(s2[10]);
		
		s1[11]=UnicodeUtil.unicode2tsc(s1[11]);
		s2[11]=UnicodeUtil.unicode2tsc(s2[11]);
		
		s1[12]=UnicodeUtil.unicode2tsc(s1[12]);
		s2[12]=UnicodeUtil.unicode2tsc(s2[12]);
		
		s1[13]=UnicodeUtil.unicode2tsc(s1[13]);
		s2[13]=UnicodeUtil.unicode2tsc(s2[13]);
		
		s1[14]=UnicodeUtil.unicode2tsc(s1[14]);
		s2[14]=UnicodeUtil.unicode2tsc(s2[14]);
		
		s1[15]=UnicodeUtil.unicode2tsc(s1[15]);
		s2[15]=UnicodeUtil.unicode2tsc(s2[15]);
		
		s1[16]=UnicodeUtil.unicode2tsc(s1[16]);
		s2[16]=UnicodeUtil.unicode2tsc(s2[16]);
		
		s1[17]=UnicodeUtil.unicode2tsc(s1[17]);
		s2[17]=UnicodeUtil.unicode2tsc(s2[17]);
		
		s1[18]=UnicodeUtil.unicode2tsc(s1[18]);
		s2[18]=UnicodeUtil.unicode2tsc(s2[18]);
		
		s1[19]=UnicodeUtil.unicode2tsc(s1[19]);
		s2[19]=UnicodeUtil.unicode2tsc(s2[19]);
		
		s1[20]=UnicodeUtil.unicode2tsc(s1[20]);
		s2[20]=UnicodeUtil.unicode2tsc(s2[20]);
		
		s1[21]=UnicodeUtil.unicode2tsc(s1[21]);
		s2[21]=UnicodeUtil.unicode2tsc(s2[21]);
		
		s1[22]=UnicodeUtil.unicode2tsc(s1[22]);
		s2[22]=UnicodeUtil.unicode2tsc(s2[22]);
		
		s1[23]=UnicodeUtil.unicode2tsc(s1[23]);
		s2[23]=UnicodeUtil.unicode2tsc(s2[23]);
		
		s1[24]=UnicodeUtil.unicode2tsc(s1[24]);
		s2[24]=UnicodeUtil.unicode2tsc(s2[24]);
		
		s1[25]=UnicodeUtil.unicode2tsc(s1[25]);
		s2[25]=UnicodeUtil.unicode2tsc(s2[25]);
		
		s1[26]=UnicodeUtil.unicode2tsc(s1[26]);
		s2[26]=UnicodeUtil.unicode2tsc(s2[26]);
		
		s1[27]=UnicodeUtil.unicode2tsc(s1[27]);
		s2[27]=UnicodeUtil.unicode2tsc(s2[27]);
		
		s1[28]=UnicodeUtil.unicode2tsc(s1[28]);
		s2[28]=UnicodeUtil.unicode2tsc(s2[28]);
		
		s1[29]=UnicodeUtil.unicode2tsc(s1[29]);
		s2[29]=UnicodeUtil.unicode2tsc(s2[29]);
		
		s1[30]=UnicodeUtil.unicode2tsc(s1[30]);
		s2[30]=UnicodeUtil.unicode2tsc(s2[30]);
		
		s1[31]=UnicodeUtil.unicode2tsc(s1[31]);
		s2[31]=UnicodeUtil.unicode2tsc(s2[31]);
		
		s1[32]=UnicodeUtil.unicode2tsc(s1[32]);
		s2[32]=UnicodeUtil.unicode2tsc(s2[32]);
		
		s1[33]=UnicodeUtil.unicode2tsc(s1[33]);
		s2[33]=UnicodeUtil.unicode2tsc(s2[33]);
		
		s1[34]=UnicodeUtil.unicode2tsc(s1[34]);
		s2[34]=UnicodeUtil.unicode2tsc(s2[34]);
		
		s1[35]=UnicodeUtil.unicode2tsc(s1[35]);
		s2[35]=UnicodeUtil.unicode2tsc(s2[35]);
		
		s1[36]=UnicodeUtil.unicode2tsc(s1[36]);
		s2[36]=UnicodeUtil.unicode2tsc(s2[36]);
		
		s1[37]=UnicodeUtil.unicode2tsc(s1[37]);
		s2[37]=UnicodeUtil.unicode2tsc(s2[37]);
		
		s1[38]=UnicodeUtil.unicode2tsc(s1[38]);
		s2[38]=UnicodeUtil.unicode2tsc(s2[38]);
		
		s1[39]=UnicodeUtil.unicode2tsc(s1[39]);
		s2[39]=UnicodeUtil.unicode2tsc(s2[39]);
		
		s1[40]=UnicodeUtil.unicode2tsc(s1[40]);
		s2[40]=UnicodeUtil.unicode2tsc(s2[40]);
		
		s1[41]=UnicodeUtil.unicode2tsc(s1[41]);
		s2[41]=UnicodeUtil.unicode2tsc(s2[41]);
		
		s1[42]=UnicodeUtil.unicode2tsc(s1[42]);
		s2[42]=UnicodeUtil.unicode2tsc(s2[42]);
		
		s1[43]=UnicodeUtil.unicode2tsc(s1[43]);
		s2[43]=UnicodeUtil.unicode2tsc(s2[43]);
		
		s1[44]=UnicodeUtil.unicode2tsc(s1[44]);
		s2[44]=UnicodeUtil.unicode2tsc(s2[44]);
		
		s1[45]=UnicodeUtil.unicode2tsc(s1[45]);
		s2[45]=UnicodeUtil.unicode2tsc(s2[45]);
		
		s1[46]=UnicodeUtil.unicode2tsc(s1[46]);
		s2[46]=UnicodeUtil.unicode2tsc(s2[46]);
		
		s1[47]=UnicodeUtil.unicode2tsc(s1[47]);
		s2[47]=UnicodeUtil.unicode2tsc(s2[47]);
		
		s1[48]=UnicodeUtil.unicode2tsc(s1[48]);
		s2[48]=UnicodeUtil.unicode2tsc(s2[48]);
		
		
		
		if (txt != null) {
			//txt=txt.trim();
			if ((curpos > 0) && (txt.substring(curpos-2,curpos).equals(s1[0]))) {
				mPreviewSuggestionText3.setText(s2[0]);
				System.out.println("update suggestion 333333333:"+txt.substring(curpos-1, curpos));
				
				mPreviewSuggestionText3.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						new TamilSoftKeyboard().updateCandidateText(1);
						// TODO Auto-generated method stub
						//new TamilSoftKeyboard().checking("அம�?மா");
						
						/*	TamilSoftKeyboard tsk=new TamilSoftKeyboard();
						String s="அம�?மா";
						InputConnection ic=getCurrentInputConnection();
						ic.commitText(s, s.length());
						tsk.commitTyped(ic);*/
					}
				});
	
		/*	mPreviewSuggestionText3.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					minputView=(KeyboardView)getLayoutInflater().inflate(R.layout.input, null);
					Keyboard current=minputView.getKeyboard();
					minputView.setKeyboard(current);
					int[] keycode={2951};
					new TamilSoftKeyboard().handleTamil99Char(2951, keycode);
					
				}
			});*/
				/*mPreviewSuggestionText3.setOnKeyListener(new View.OnKeyListener() {
					
					@Override
					public boolean onKey(View v, int keyCode, KeyEvent event) {
						// TODO Auto-generated method stub
						minputView=(KeyboardView)getLayoutInflater().inflate(R.layout.input, null);
						Keyboard current=minputView.getKeyboard();
						minputView.setKeyboard(current);
						int[] keycode={2951};
						new TamilSoftKeyboard().handleTamil99Char(2951, keycode);
						
						return true;
					}
				});*/
				/*mPreviewSuggestionText3.setOnKeyListener(new View.OnKeyListener() {
					String s="þÈôÒ";
					StringBuilder sb=new StringBuilder("þÈôÒ");
					@Override
					public boolean onkey(View v) {
						// TODO Auto-generated method stub
						KeyEvent event = null;
						int KeyCode=KeyEvent.KEYCODE_I;
						long mMetastate=0;
						
						try
						{
							
							new TamilSoftKeyboard().updateCandidateText();
							
							new TamilSoftKeyboard().objjjjj();
							
							
							
							System.out.println("inside input connection");
						
							
							  ExtractedText txt = getCurrentInputConnection().getExtractedText(new ExtractedTextRequest(), InputConnection.GET_EXTRACTED_TEXT_MONITOR);
				    	        String strbeforeCursor="";
				    	        String strafterCursor ="";
				    	        	strbeforeCursor = getCurrentInputConnection().getTextBeforeCursor(1000000000, 0).toString();
				    	        strafterCursor = getCurrentInputConnection().getTextAfterCursor(1000000000, 0).toString();
				    	        String str = strbeforeCursor + "|"+strafterCursor;
				    	        String s="þÈôÒ";
				    	        str=str.concat(s);

				    			
				    	      update(str,strbeforeCursor.length());
				    	        
							
							
							
								int keychar=2951;
							InputConnection inputContext=getCurrentInputConnection();
							CharSequence prefix="";
							prefix=inputContext.getTextBeforeCursor(2, 0);
							String result=String.valueOf((char)keychar);
							inputContext.commitText(result, result.length());
							
							
						if(sb.length()>0)
						{
							mMetastate=MetaKeyKeyListener.handleKeyDown(mMetastate,KeyCode,event);
							int c=event.getUnicodeChar(MetaKeyKeyListener.getMetaState(mMetastate));
							char accent=sb.charAt(sb.length()-1);
							int composed=KeyEvent.getDeadChar(accent, c);
						}
							event = null;
							event.getUnicodeChar();
							new TamilSoftKeyboard().translateKeyDown(2951, event);
							int[] key={KeyEvent.KEYCODE_I};
							new TamilSoftKeyboard().onKey(2951, key);
					
						}catch(Exception e)
						{
							System.out.println(e);
						}
					}
				});
				
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								
								mPreviewTextView.setText("þÈôÒ");
								String s="þÈôÒ";
								try
								{
								
								}catch(Exception e)
								{
									
								}
								
								

							}
						});*/
			
				

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			
			else if ((curpos > 0) && (txt.substring(curpos-2,curpos).equals("Å¼"))) {
				
				
				//if(txt.substring(curpos).equals("�?"))
				
				mPreviewSuggestionText3.setText(s2[1]);
				
				mPreviewSuggestionText2.setText("Å¼ìÌ");
				
				mPreviewSuggestionText3.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								
								
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			
			else if ((curpos > 0) && (txt.substring(curpos-1,curpos).equals(s1[3]))) {
				
				
				
				//if(txt.substring(curpos).equals("�?"))
				
				mPreviewSuggestionText3.setText(s2[1]);
			
					mPreviewSuggestionText2.setText("�?¸ý");
					mPreviewSuggestionText2.setText("�?¨Ç");
				mPreviewSuggestionText3.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								
								
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			
			else if ((curpos > 0) && (txt.substring(curpos-3,curpos).equals(s1[2]))) {
				mPreviewSuggestionText3.setText(s2[2]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("º¡ÇÃõ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[3]))) {
				mPreviewSuggestionText3.setText(s2[3]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("Ó¸ÅÃ¢");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[4]))) {
				mPreviewSuggestionText3.setText(s2[4]);
				mPreviewSuggestionText2.setText("º¾õ");
				mPreviewSuggestionText11.setText("º¾Å£¾õ");
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("º�?Ãõ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[5]))) {
				mPreviewSuggestionText3.setText(s2[5]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("ºÉÅÃ¢");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[6]))) {
				mPreviewSuggestionText3.setText(s2[6]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[7]))) {
				mPreviewSuggestionText3.setText(s2[7]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¿¼ò¨¾");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(0,2).equals(s1[8]))) {
				mPreviewSuggestionText3.setText(s2[8]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("ÓÊî�?");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[9]))) {
				mPreviewSuggestionText3.setText(s2[9]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("«È¢ì¨¸");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[10]))) {
				mPreviewSuggestionText3.setText(s2[10]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("þ¨½Âõ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[11]))) {
				mPreviewSuggestionText3.setText(s2[11]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("Å¢Â¡Æý");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[12]))) {
				mPreviewSuggestionText3.setText(s2[12]);
				mPreviewSuggestionText11.setText("¦ºùÅ¡Éõ");
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
							
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[13]))) {
				mPreviewSuggestionText3.setText(s2[13]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("þ¨Èîº¢");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[14]))) {
				mPreviewSuggestionText3.setText(s2[14]);
				
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2,curpos).equals(s1[15]))) {
				mPreviewSuggestionText3.setText(s2[15]);
				mPreviewSuggestionText11.setText("²ô¦À¡");
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[16]))) {
				mPreviewSuggestionText3.setText(s2[16]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¬¸ŠÎ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(0,2).equals(s1[17]))) {
				mPreviewSuggestionText3.setText(s2[17]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("áÄ¸õ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[18]))) {
				mPreviewSuggestionText3.setText(s2[18]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("�?¢ýÉø");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[19]))) {
				mPreviewSuggestionText3.setText(s2[19]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("þÂü¨¸");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[20]))) {
				mPreviewSuggestionText3.setText(s2[20]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("àì¸õ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[21]))) {
				mPreviewSuggestionText3.setText(s2[21]);
				mPreviewSuggestionText11.setText("¾¡Éõ");
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
							
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[22]))) {
				mPreviewSuggestionText3.setText(s2[22]);
				mPreviewSuggestionText11.setText("§ÀÃý");
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("§ÀÕó�?");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[23]))) {
				mPreviewSuggestionText3.setText(s2[23]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¸¢ÆìÌ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[24]))) {
				mPreviewSuggestionText3.setText(s2[24]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("Å¼ìÌ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[25]))) {
				mPreviewSuggestionText3.setText(s2[25]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¿¸Ãõ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[26]))) {
				mPreviewSuggestionText3.setText(s2[26]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¸ôÀø");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[27]))) {
				mPreviewSuggestionText3.setText(s2[27]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¿ñÀý");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[28]))) {
				mPreviewSuggestionText3.setText(s2[28]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¸ðÊø");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(0,2).equals(s1[29]))) {
				mPreviewSuggestionText3.setText(s2[29]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("ÌÆó¨¾");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[30]))) {
				mPreviewSuggestionText3.setText(s2[30]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("ÀØôÒ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[31]))) {
				mPreviewSuggestionText3.setText(s2[31]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("±ÚõÒ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[32]))) {
				mPreviewSuggestionText3.setText(s2[32]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¦Åñ¦½ö");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[33]))) {
				mPreviewSuggestionText3.setText(s2[33]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("º¢í¸õ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[34]))) {
				mPreviewSuggestionText3.setText(s2[34]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¦¸¡ð¨¼");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[35]))) {
				mPreviewSuggestionText3.setText(s2[35]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¾ì¸¡Ç¢");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[36]))) {
				mPreviewSuggestionText3.setText(s2[36]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¾ñ½£÷");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[37]))) {
				mPreviewSuggestionText3.setText(s2[37]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("�?É¢¾÷");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(0,2).equals(s1[38]))) {
				mPreviewSuggestionText3.setText(s2[38]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("�?Ã¢Âý");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[39]))) {
				mPreviewSuggestionText3.setText(s2[39]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¾É¢�?õ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[40]))) {
				mPreviewSuggestionText3.setText(s2[40]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("þÕõÒ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[41]))) {
				mPreviewSuggestionText3.setText(s2[41]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¸ñ½¡Ê");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[42]))) {
				mPreviewSuggestionText3.setText(s2[42]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¯§Ä¡¸õ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[43]))) {
				mPreviewSuggestionText3.setText(s2[43]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¦ºø§Àº¢");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[44]))) {
				mPreviewSuggestionText3.setText(s2[44]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¯Â÷ò¾¢");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(0,2).equals(s1[45]))) {
				mPreviewSuggestionText3.setText(s2[45]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("Üð¼ø");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[46]))) {
				mPreviewSuggestionText3.setText(s2[46]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("Å¢ñ�?£ý");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[47]))) {
				mPreviewSuggestionText3.setText(s2[47]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("Å¡É¢¨Ä");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[48]))) {
				mPreviewSuggestionText3.setText(s2[48]);
				mPreviewSuggestionText3
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("�?ïºû");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			 
			else if((curpos>0)&&(txt.substring(curpos).equals(" ")))
			{
				System.out.println("last name");
				mPreviewSuggestionText3.setText(null);
			}
			else 
			{
				mPreviewSuggestionText3.setText(null);
			}
		}
		
	
		Spannable textsug3 = (Spannable) mPreviewSuggestionText3.getText();
		if (curpos > 2) {
			curpos = curpos - 2;
			if (curpos > textsug3.length()) {
				curpos = textsug3.length();
			}
		}
		try {
			Selection.setSelection(textsug3, curpos);
		} catch (Exception e) {

		}

	}

	public void updatesuggestion11(String txt, int curpos) {
		txt = UnicodeUtil.unicode2tsc(txt);
		String s1[] = new String[50];
		String s2[]=new String[50];
		s1[0]="செங�?";
		s2[0]="செங�?கல�?";
		s1[1]="ம�?ள�?";
		s2[1]="ம�?ள�?ளங�?கி";
		s1[2]="இளம�?";
		s2[2]="இளம�?பெண�?";
		s1[3]="சமைய";
		s2[3]="சமையலறை";
		s1[4]="சகோ";
		s2[4]="சகோதரன";
		s1[5]="மாந";
		s2[5]="மாநகரம�?";
		s1[6]="கட�?டி";
		s2[6]="கட�?டிடம�?	";
		s1[7]="அமை";
		s2[7]="அமைச�?சர�?";
		s1[8]="கம�?";
		s2[8]="கம�?பளம�?	";
		s1[9]="மில�?";
		s2[9]="மில�?லியன�?";
		s1[10]="திர�?";
		s2[10]="திர�?மணம�?";
		s1[11]="க�?ட�?ம�?";
		s2[11]="க�?ட�?ம�?பம�?";
		s1[12]="";
		s2[12]="கடற�?கரை";
		s1[13]="நவம�?";
		s2[13]="நவம�?பர�?";
		s1[14]="அக�?டோ";
		s2[14]="அக�?டோபர�?";
		s1[15]="தொட";
		s2[15]="தொடக�?கம�?";
		s1[16]="செயலா";
		s2[16]="செயலாக�?க�?";
		s1[17]="செயல�?";
		s2[17]="செயல�?பாட�?";
		s1[18]="விழ�?";
		s2[18]="விழ�?க�?காட�?";
		s1[19]="கடிகா";
		s2[19]="கடிகாரம�?	";
		s1[20]="நில";
		s2[20]="நிலக�?கரி	";
		s1[21]="மணி";
		s2[21]="மணித�?த�?ளி";
		s1[22]="பெப�?";
		s2[22]="பெப�?ரவரி";
		s1[23]="டிச";
		s2[23]="டிசம�?பர�?";
		s1[24]="நூற�?";
		s2[24]="நூற�?றாண�?ட�?";
		s1[25]="வெங�?";
		s2[25]="வெங�?காயம�?";
		s1[26]="சாக�?க";
		s2[26]="சாக�?கலேட�?";
		s1[27]="கர�?மை";
		s2[27]="கர�?மையான";
		s1[28]="நிலப�?";
		s2[28]="நிலப�?படம�?";
		s1[29]="பரப�?";
		s2[29]="பரப�?பளவ�?	";
		s1[30]="இயந�?";
		s2[30]="இயந�?திரம�?";
		s1[31]="செப�?ட";
		s2[31]="செப�?டம�?பர�?";
		s1[32]="மாரி";
		s2[32]="மாரிகாலம�?";
		
		
		
		s1[0] = UnicodeUtil.unicode2tsc(s1[0]);
		s2[0] = UnicodeUtil.unicode2tsc(s2[0]);
		
		s1[1]=UnicodeUtil.unicode2tsc(s1[1]);
		s2[1]=UnicodeUtil.unicode2tsc(s2[1]);
		
		s1[2]=UnicodeUtil.unicode2tsc(s1[2]);
		s2[2]=UnicodeUtil.unicode2tsc(s2[2]);
		
		s1[3]=UnicodeUtil.unicode2tsc(s1[3]);
		s2[3]=UnicodeUtil.unicode2tsc(s2[3]);
		
		s1[4]=UnicodeUtil.unicode2tsc(s1[4]);
		s2[4]=UnicodeUtil.unicode2tsc(s2[4]);
		
		s1[5]=UnicodeUtil.unicode2tsc(s1[5]);
		s2[5]=UnicodeUtil.unicode2tsc(s2[5]);
		
		s1[6]=UnicodeUtil.unicode2tsc(s1[6]);
		s2[6]=UnicodeUtil.unicode2tsc(s2[6]);
		
		s1[7]=UnicodeUtil.unicode2tsc(s1[7]);
		s2[7]=UnicodeUtil.unicode2tsc(s2[7]);
		
		s1[8]=UnicodeUtil.unicode2tsc(s1[8]);
		s2[8]=UnicodeUtil.unicode2tsc(s2[8]);
		
		s1[9]=UnicodeUtil.unicode2tsc(s1[9]);
		s2[9]=UnicodeUtil.unicode2tsc(s2[9]);
		
		s1[10]=UnicodeUtil.unicode2tsc(s1[10]);
		s2[10]=UnicodeUtil.unicode2tsc(s2[10]);
		
		s1[11]=UnicodeUtil.unicode2tsc(s1[11]);
		s2[11]=UnicodeUtil.unicode2tsc(s2[11]);
		
		s1[12]=UnicodeUtil.unicode2tsc(s1[12]);
		s2[12]=UnicodeUtil.unicode2tsc(s2[12]);
		
		s1[13]=UnicodeUtil.unicode2tsc(s1[13]);
		s2[13]=UnicodeUtil.unicode2tsc(s2[13]);
		
		s1[14]=UnicodeUtil.unicode2tsc(s1[14]);
		s2[14]=UnicodeUtil.unicode2tsc(s2[14]);
		
		s1[15]=UnicodeUtil.unicode2tsc(s1[15]);
		s2[15]=UnicodeUtil.unicode2tsc(s2[15]);
		
		s1[16]=UnicodeUtil.unicode2tsc(s1[16]);
		s2[16]=UnicodeUtil.unicode2tsc(s2[16]);
		
		s1[17]=UnicodeUtil.unicode2tsc(s1[17]);
		s2[17]=UnicodeUtil.unicode2tsc(s2[17]);
		
		s1[18]=UnicodeUtil.unicode2tsc(s1[18]);
		s2[18]=UnicodeUtil.unicode2tsc(s2[18]);
		
		s1[19]=UnicodeUtil.unicode2tsc(s1[19]);
		s2[19]=UnicodeUtil.unicode2tsc(s2[19]);
		
		s1[20]=UnicodeUtil.unicode2tsc(s1[20]);
		s2[20]=UnicodeUtil.unicode2tsc(s2[20]);
		
		s1[21]=UnicodeUtil.unicode2tsc(s1[21]);
		s2[21]=UnicodeUtil.unicode2tsc(s2[21]);
		
		s1[22]=UnicodeUtil.unicode2tsc(s1[22]);
		s2[22]=UnicodeUtil.unicode2tsc(s2[22]);
		
		s1[23]=UnicodeUtil.unicode2tsc(s1[23]);
		s2[23]=UnicodeUtil.unicode2tsc(s2[23]);
		
		s1[24]=UnicodeUtil.unicode2tsc(s1[24]);
		s2[24]=UnicodeUtil.unicode2tsc(s2[24]);
		
		s1[25]=UnicodeUtil.unicode2tsc(s1[25]);
		s2[25]=UnicodeUtil.unicode2tsc(s2[25]);
		
		s1[26]=UnicodeUtil.unicode2tsc(s1[26]);
		s2[26]=UnicodeUtil.unicode2tsc(s2[26]);
		
		s1[27]=UnicodeUtil.unicode2tsc(s1[27]);
		s2[27]=UnicodeUtil.unicode2tsc(s2[27]);
		
		s1[28]=UnicodeUtil.unicode2tsc(s1[28]);
		s2[28]=UnicodeUtil.unicode2tsc(s2[28]);
		
		s1[29]=UnicodeUtil.unicode2tsc(s1[29]);
		s2[29]=UnicodeUtil.unicode2tsc(s2[29]);
		
		s1[30]=UnicodeUtil.unicode2tsc(s1[30]);
		s2[30]=UnicodeUtil.unicode2tsc(s2[30]);
		
		s1[31]=UnicodeUtil.unicode2tsc(s1[31]);
		s2[31]=UnicodeUtil.unicode2tsc(s2[31]);
		
		s1[32]=UnicodeUtil.unicode2tsc(s1[32]);
		s2[32]=UnicodeUtil.unicode2tsc(s2[32]);
		
		
		
		if (txt != null) {
			if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[0]))) {
				mPreviewSuggestionText11.setText(s2[0]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¦ºí¸ø");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[1]))) {
				mPreviewSuggestionText11.setText(s2[1]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("ÓûÇí¸¢");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[2]))) {
				mPreviewSuggestionText11.setText(s2[2]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("þÇõ¦Àñ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[3]))) {
				mPreviewSuggestionText11.setText(s2[3]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("º¨�?ÂÄ¨È");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[4]))) {
				mPreviewSuggestionText11.setText(s2[4]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("º§¸¡¾Ãý");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[5]))) {
				mPreviewSuggestionText11.setText(s2[5]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("�?¡¿¸Ãõ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[6]))) {
				mPreviewSuggestionText11.setText(s2[6]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¸ðÊ¼õ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[7]))) {
				mPreviewSuggestionText11.setText(s2[7]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("«¨�?îº÷");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-2, curpos).equals(s1[8]))) {
				mPreviewSuggestionText11.setText(s2[8]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¸õÀÇõ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[9]))) {
				mPreviewSuggestionText11.setText(s2[9]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("�?¢øÄ¢Âý");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[10]))) {
				mPreviewSuggestionText11.setText(s2[10]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¾¢Õ�?½õ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[11]))) {
				mPreviewSuggestionText11.setText(s2[11]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("ÌÎõÀõ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[12]))) {
				mPreviewSuggestionText11.setText(s2[12]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¸¼ü¸¨Ã");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[13]))) {
				mPreviewSuggestionText11.setText(s2[13]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¿ÅõÀ÷");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			
			else if ((curpos > 0) && (txt.substring(curpos-3, curpos).equals(s1[14]))) {
				mPreviewSuggestionText11.setText(s2[14]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("«ì§¼¡À÷");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[15]))) {
				mPreviewSuggestionText11.setText(s2[15]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¦¾¡¼ì¸õ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[16]))) {
				mPreviewSuggestionText11.setText(s2[16]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¦ºÂÄ¡ìÌ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[17]))) {
				mPreviewSuggestionText11.setText(s2[17]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¦ºÂøÀ¡Î");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[18]))) {
				mPreviewSuggestionText11.setText(s2[18]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("Å¢Øì¸¡Î");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[19]))) {
				mPreviewSuggestionText11.setText(s2[19]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¸Ê¸¡Ãõ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[20]))) {
				mPreviewSuggestionText11.setText(s2[20]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¿¢Äì¸Ã¢");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[21]))) {
				mPreviewSuggestionText11.setText(s2[21]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("�?½¢ò�?Ç¢");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[22]))) {
				mPreviewSuggestionText11.setText(s2[22]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¦ÀôÃÅÃ¢");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[23]))) {
				mPreviewSuggestionText11.setText(s2[23]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("ÊºõÀ÷");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[24]))) {
				mPreviewSuggestionText11.setText(s2[24]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("áüÈ¡ñÎ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[25]))) {
				mPreviewSuggestionText11.setText(s2[25]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¦Åí¸¡Âõ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[26]))) {
				mPreviewSuggestionText11.setText(s2[26]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("º¡ì¸§Äð");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[27]))) {
				mPreviewSuggestionText11.setText(s2[27]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¸Õ¨�?Â¡É");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[28]))) {
				mPreviewSuggestionText11.setText(s2[28]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¿¢ÄôÀ¼õ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[29]))) {
				mPreviewSuggestionText11.setText(s2[29]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("ÀÃôÀÇ×");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[30]))) {
				mPreviewSuggestionText11.setText(s2[30]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("þÂó¾¢Ãõ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[31]))) {
				mPreviewSuggestionText11.setText(s2[31]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¦ºô¼õÀ÷");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[32]))) {
				mPreviewSuggestionText11.setText(s2[32]);
				mPreviewSuggestionText11
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("�?¡Ã¢¸¡Äõ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if((curpos>0)&&(txt.substring(curpos).equals(" ")))
			{
				System.out.println("last name");
				mPreviewSuggestionText11.setText(null);
			}
			else
			{
				mPreviewSuggestionText11.setText(null);
			}
		}
		
		Spannable textsug11 = (Spannable) mPreviewSuggestionText11.getText();
		if (curpos > 2) {
			curpos = curpos - 2;
			if (curpos > textsug11.length()) {
				curpos = textsug11.length();
			}
		}
		try {
			Selection.setSelection(textsug11, curpos);
		} catch (Exception e) {

		}

	}

	public void updatesuggestion21(String txt, int curpos) {
		txt = UnicodeUtil.unicode2tsc(txt);
		String s1[] = new String[50];
		String s2[]=new String[50];
		s1[0]="தொடர�?";
		s2[0]="தொடர�?வண�?டி";
		s1[1]="தலைந";
		s2[1]="தலைநகரம�?";
		s1[2]="ம�?ட�?டை";
		s2[2]="ம�?ட�?டைக�?கோச�?";
		s1[3]="படம�?பி";
		s2[3]="படம�?பிடிகர�?வி";
		s1[4]="சவர�?க�?";
		s2[4]="சவர�?க�?காரம�?	";
		s1[5]="பெட�?ரோ";
		s2[5]="பெட�?ரோலியம�?";
		s1[6]="தோல�?ச";
		s2[6]="தோல�?சரக�?க�? ந�?ட�?பியல�?";
		s1[7]="வேதிப�?";
		s2[7]="வேதிப�?பொர�?ள�?";
		s1[8]="படிவள";
		s2[8]="படிவளர�?ச�?சிக�?";
		s1[9]="பள�?ளிக�?";
		s2[9]="பள�?ளிக�?கூடம�?";
		s1[10]="விமான";
		s2[10]="விமானநிலையம�?";
		s1[11]="கோடை";
		s2[11]="கோடைகாலம�?	";
		s1[12]="அரசிய";
		s2[12]="அரசியல�?வாதி";
		s1[13]="கூட�?த";
		s2[13]="கூட�?தல�? திறனேற�?றி";
		s1[14]="செயல�?";
		s2[14]="செயல�?திறன�?	";
		s1[15]="வெள�?ள";
		s2[15]="வெள�?ளரிக�?காய�?";
		s1[16]="நரந�?த";
		s2[16]="நரந�?தம�?பழம�?";
		s1[17]="மணித�?தி";
		s2[17]="மணித�?தியாலம�?";
		s1[18]="இலைய�?";
		s2[18]="இலைய�?திர�? காலம�?";
		
		s1[0] = UnicodeUtil.unicode2tsc(s1[0]);
		s2[0] = UnicodeUtil.unicode2tsc(s2[0]);
		
		s1[1]=UnicodeUtil.unicode2tsc(s1[1]);
		s2[1]=UnicodeUtil.unicode2tsc(s2[1]);
		
		s1[2]=UnicodeUtil.unicode2tsc(s1[2]);
		s2[2]=UnicodeUtil.unicode2tsc(s2[2]);
		
		s1[3]=UnicodeUtil.unicode2tsc(s1[3]);
		s2[3]=UnicodeUtil.unicode2tsc(s2[3]);
		
		s1[4]=UnicodeUtil.unicode2tsc(s1[4]);
		s2[4]=UnicodeUtil.unicode2tsc(s2[4]);
		
		s1[5]=UnicodeUtil.unicode2tsc(s1[5]);
		s2[5]=UnicodeUtil.unicode2tsc(s2[5]);
		
		s1[6]=UnicodeUtil.unicode2tsc(s1[6]);
		s2[6]=UnicodeUtil.unicode2tsc(s2[6]);
		
		s1[7]=UnicodeUtil.unicode2tsc(s1[7]);
		s2[7]=UnicodeUtil.unicode2tsc(s2[7]);
		
		s1[8]=UnicodeUtil.unicode2tsc(s1[8]);
		s2[8]=UnicodeUtil.unicode2tsc(s2[8]);
		
		s1[9]=UnicodeUtil.unicode2tsc(s1[9]);
		s2[9]=UnicodeUtil.unicode2tsc(s2[9]);
		
		s1[10]=UnicodeUtil.unicode2tsc(s1[10]);
		s2[10]=UnicodeUtil.unicode2tsc(s2[10]);
		
		s1[11]=UnicodeUtil.unicode2tsc(s1[11]);
		s2[11]=UnicodeUtil.unicode2tsc(s2[11]);
		
		s1[12]=UnicodeUtil.unicode2tsc(s1[12]);
		s2[12]=UnicodeUtil.unicode2tsc(s2[12]);
		
		s1[13]=UnicodeUtil.unicode2tsc(s1[13]);
		s2[13]=UnicodeUtil.unicode2tsc(s2[13]);
		
		s1[14]=UnicodeUtil.unicode2tsc(s1[14]);
		s2[14]=UnicodeUtil.unicode2tsc(s2[14]);
		
		s1[15]=UnicodeUtil.unicode2tsc(s1[15]);
		s2[15]=UnicodeUtil.unicode2tsc(s2[15]);
		
		s1[16]=UnicodeUtil.unicode2tsc(s1[16]);
		s2[16]=UnicodeUtil.unicode2tsc(s2[16]);
		
		s1[17]=UnicodeUtil.unicode2tsc(s1[17]);
		s2[17]=UnicodeUtil.unicode2tsc(s2[17]);
		
		s1[18]=UnicodeUtil.unicode2tsc(s1[18]);
		s2[18]=UnicodeUtil.unicode2tsc(s2[18]);
		
		if (txt != null) {
			if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[0]))) {
				mPreviewSuggestionText21.setText(s2[0]);
				mPreviewSuggestionText21
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¦¾¡¼÷ÅñÊ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[1]))) {
				mPreviewSuggestionText21.setText(s2[1]);
				mPreviewSuggestionText21
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¾¨Ä¿¸Ãõ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[2]))) {
				mPreviewSuggestionText21.setText(s2[2]);
				mPreviewSuggestionText21
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("Óð¨¼ì§¸¡�?");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[3]))) {
				mPreviewSuggestionText21.setText(s2[3]);
				mPreviewSuggestionText21
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("À¼õÀ¢Ê¸ÕÅ¢");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[4]))) {
				mPreviewSuggestionText21.setText(s2[4]);
				mPreviewSuggestionText21
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("ºÅ÷ì¸¡Ãõ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[5]))) {
				mPreviewSuggestionText21.setText(s2[5]);
				mPreviewSuggestionText21
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¦Àð§Ã¡Ä¢Âõ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[6]))) {
				mPreviewSuggestionText21.setText(s2[6]);
				mPreviewSuggestionText21
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("§¾¡øºÃìÌ ÑðÀ¢Âø");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[7]))) {
				mPreviewSuggestionText21.setText(s2[7]);
				mPreviewSuggestionText21
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("§Å¾¢ô¦À¡Õû");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[8]))) {
				mPreviewSuggestionText21.setText(s2[8]);
				mPreviewSuggestionText21
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("ÀÊÅÇ÷îº¢ì");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[9]))) {
				mPreviewSuggestionText21.setText(s2[9]);
				mPreviewSuggestionText21
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("ÀûÇ¢ìÜ¼õ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[10]))) {
				mPreviewSuggestionText21.setText(s2[10]);
				mPreviewSuggestionText21
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("Å¢�?¡É¿¢¨ÄÂõ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[11]))) {
				mPreviewSuggestionText21.setText(s2[11]);
				mPreviewSuggestionText21
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("§¸¡¨¼¸¡Äõ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[12]))) {
				mPreviewSuggestionText21.setText(s2[12]);
				mPreviewSuggestionText21
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("«Ãº¢ÂøÅ¡¾¢");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[13]))) {
				mPreviewSuggestionText21.setText(s2[13]);
				mPreviewSuggestionText21
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("ÜÎ¾ø ¾¢È§ÉüÈ¢");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[14]))) {
				mPreviewSuggestionText21.setText(s2[14]);
				mPreviewSuggestionText21
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¦ºÂø¾¢Èý");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[15]))) {
				mPreviewSuggestionText21.setText(s2[15]);
				mPreviewSuggestionText21
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¦ÅûÇÃ¢ì¸¡ö");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[16]))) {
				mPreviewSuggestionText21.setText(s2[16]);
				mPreviewSuggestionText21
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("¿Ãó¾õÀÆõ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[17]))) {
				mPreviewSuggestionText21.setText(s2[17]);
				mPreviewSuggestionText21
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("�?½¢ò¾¢Â¡Äõ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if ((curpos > 0) && (txt.substring(curpos-4, curpos).equals(s1[18]))) {
				mPreviewSuggestionText21.setText(s2[18]);
				mPreviewSuggestionText21
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								mPreviewTextView.setText("þ¨ÄÔ¾¢÷ ¸¡Äõ");
								

							}
						});

				Spannable textsug1 = (Spannable) mPreviewSuggestionText2
						.getText();

			}
			else if((curpos>0)&&(txt.substring(curpos).equals(" ")))
			{
				System.out.println("last name");
				mPreviewSuggestionText21.setText(null);
			}
			else
			{
				mPreviewSuggestionText21.setText(null);
			}
		}
		Spannable textsug21 = (Spannable) mPreviewSuggestionText21.getText();
		if (curpos > 2) {
			curpos = curpos - 2;
			if (curpos > textsug21.length()) {
				curpos = textsug21.length();
			}
		}
		try {
			Selection.setSelection(textsug21, curpos);
		} catch (Exception e) {

		}

	}

	public void updatesuggestion31(String txt, int curpos) {
		txt = UnicodeUtil.unicode2tsc(txt);
		mPreviewSuggestionText31.setText(txt);
		Spannable textsug31 = (Spannable) mPreviewSuggestionText31.getText();
		if (curpos > 2) {
			curpos = curpos - 2;
			if (curpos > textsug31.length()) {
				curpos = textsug31.length();
			}
		}
		try {
			Selection.setSelection(textsug31, curpos);
		} catch (Exception e) {

		}

	}
	

	public void setSuggestions(List<String> suggestions, boolean completions,
			boolean typedWordValid) {

	}

	public View getPreviewView() {
		return mPreviewContainer;
	}

	public void clear() {
	}
}
