package com.emmozhi.firstproject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdView;
import com.google.ads.InterstitialAd;

public class Emmozhi extends Activity implements AdListener {
	TextView emmozhi_welcometext;
	TextView emmozhi_helptext1;
	TextView emmozhi_helptext2;
	TextView emmozhi_helptext3;
	TextView emmozhi_helptext4;
	TextView emmozhi_helptext5;
	TextView emmozhi_link;
	ImageView image_fliper_button;
	ImageView video_fliper_button;
	ImageView Fbbutton;
	private InterstitialAd interstitialAds = null;
	protected void onCreate(Bundle savedInstance)
	{
		
		super.onCreate(savedInstance);
		setContentView(R.layout.main_screen);
		AdView adview=(AdView)findViewById(R.id.adView);
		AdRequest re=new AdRequest();
		re.setTesting(true);
		adview.loadAd(re);
		this.interstitialAds = new InterstitialAd(this, "ca-app-pub-8389260823448885/8752087451");
		this.interstitialAds.setAdListener(this);
		AdRequest adr = new AdRequest();
		interstitialAds.loadAd(adr);
		
		
		image_fliper_button=(ImageView)findViewById(R.id.image_button);
		video_fliper_button=(ImageView)findViewById(R.id.video_button);
		Fbbutton=(ImageView)findViewById(R.id.facee);
		emmozhi_link=(TextView)findViewById(R.id.text_link);
		/*
		 * Typeface fontface = Typeface.createFromAsset(getAssets(), "fonts/mylai.ttf");
		emmozhi_welcometext=(TextView)findViewById(R.id.emmozhi_Welcometext_1);
		emmozhi_welcometext.setTypeface(fontface,Typeface.BOLD);
		emmozhi_welcometext.setText(UnicodeUtil.unicode2tsc(getResources().getString(R.string.emmozhi_welcome_text)));
		emmozhi_welcometext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "hai", Toast.LENGTH_LONG).show();
				
			}
		});
		
		emmozhi_helptext1=(TextView)findViewById(R.id.Help_emmozhi_text1);
		emmozhi_helptext1.setTypeface(fontface,Typeface.BOLD);
		emmozhi_helptext1.setText(UnicodeUtil.unicode2tsc(getResources().getString(R.string.emmozhi_help_text1)));
		
		emmozhi_helptext2=(TextView)findViewById(R.id.Help_emmozhi_text2);
		emmozhi_helptext2.setTypeface(fontface,Typeface.BOLD);
		emmozhi_helptext2.setText(UnicodeUtil.unicode2tsc(getResources().getString(R.string.emmozhi_help_text2)));
		
		emmozhi_helptext3=(TextView)findViewById(R.id.Help_emmozhi_text3);
		emmozhi_helptext3.setTypeface(fontface,Typeface.BOLD);
		emmozhi_helptext3.setText(UnicodeUtil.unicode2tsc(getResources().getString(R.string.emmozhi_help_text3)));
		
		emmozhi_helptext4=(TextView)findViewById(R.id.Help_emmozhi_text4);
		emmozhi_helptext4.setTypeface(fontface,Typeface.BOLD);
		emmozhi_helptext4.setText(UnicodeUtil.unicode2tsc(getResources().getString(R.string.emmozhi_help_text4)));
		
		emmozhi_helptext5=(TextView)findViewById(R.id.Help_emmozhi_text5);
		emmozhi_helptext5.setTypeface(fontface,Typeface.BOLD);
		emmozhi_helptext5.setText(UnicodeUtil.unicode2tsc(getResources().getString(R.string.emmozhi_help_text5)));
		
		*/
		ImageView emmozhi_main_button=(ImageView)findViewById(R.id.emmozhi_button_1);
		emmozhi_main_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Intent.ACTION_MAIN);
				i.setClassName("com.android.settings", "com.android.settings.LanguageSettings");
				startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));
				
			}
		});
		image_fliper_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),ViewFlipperActivity.class);
				startActivity(i);
			}
		});
		video_fliper_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			/*	AdRequest adr = new AdRequest();
				interstitialAds.loadAd(adr);*/
				Intent i=new Intent();
				startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://youtu.be/Pk20p8-Gnjk")));
			}
		});
emmozhi_link.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			/*	AdRequest adr = new AdRequest();
				interstitialAds.loadAd(adr);*/
				Intent i=new Intent();
				startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.emmozhi.org")));
			}
		});
		
Fbbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			/*	AdRequest adr = new AdRequest();
				interstitialAds.loadAd(adr);*/
				Intent shareIntent = new Intent();
				shareIntent.setAction(Intent.ACTION_SEND);
				shareIntent.setType("text/plain");
				shareIntent.putExtra(Intent.EXTRA_TEXT,	"தகவல்களை தமிழில் பரிமாற்றம் செய்ய எம்மொழி என்னும் தமிழ் விசைபலகையை பயன்படுத்துகிறேன், அதை பதிவிறக்கம் செய்ய https://play.google.com/store/apps/details?id=com.emmozhi.firstproject பயன்படுத்தவும்"+"\n"+"I send messages in my Tamil language using Emmozhi Tamil Android Keyboard to download this use https://play.google.com/store/apps/details?id=com.emmozhi.firstproject");
				startActivity(Intent.createChooser(shareIntent,
						"Download Emmozhi From Google play"));
			}
		});
		
	}
	@Override
	public void onDismissScreen(Ad arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onFailedToReceiveAd(Ad arg0, ErrorCode arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onLeaveApplication(Ad arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPresentScreen(Ad arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onReceiveAd(Ad arg0) {
		// TODO Auto-generated method stub
		interstitialAds.show();
		
	}

}
