package network.networkcodes;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdView;
import com.google.ads.InterstitialAd;

public class NetworkListActivity extends ListActivity implements AdListener {
	private InterstitialAd interstitialAds = null;
	private TextView textView = null;
	ListView list;
	String[] web = { "Aircel", "Airtel", "Bsnl", "Docomo", "Idea", "Reliance",
			"Vodafone" };
	Integer[] imageId = {R.drawable.aircel_network,R.drawable.airtel_network,R.drawable.bsnl_network,R.drawable.docomo_network,R.drawable.idea_network,R.drawable.relaince_network,R.drawable.vodafone_network  };

	@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			
			CustomList adapter=new CustomList(NetworkListActivity.this, web, imageId);
			list=(ListView)findViewById(android.R.id.list);
			list.setAdapter(adapter);
			AdView adview=(AdView)findViewById(R.id.adView);
			AdRequest re=new AdRequest();
			re.setTesting(true);
			adview.loadAd(re);
			/*this.interstitialAds = new InterstitialAd(this, "ca-app-pub-8389260823448885/7075158259");
			this.interstitialAds.setAdListener(this);
			AdRequest adr = new AdRequest();
			interstitialAds.loadAd(adr);*/

			
			list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent,View view,int position,long id)
		{
	
		if(position==0)
		{
			
			Intent i=new Intent(getApplication(),AircelMainActivity.class);
			startActivity(i);
		}
		
		if(position==1)
		{
			Intent i=new Intent(getApplication(),AirtelMainActivity.class);
			startActivity(i);
		}
		 if(position==2)
		{
			Intent i=new Intent(getApplication(),BsnlMainActivity.class);
			startActivity(i);
		}
		 if(position==3)
		{
			Intent i=new Intent(getApplication(),DocomoMainActivity.class);
			startActivity(i);
		}
		 if(position==4)
		{
			Intent i=new Intent(getApplication(),IdeaMainActivity.class);
			startActivity(i);
		}
		 if(position==5)
		{
			Intent i=new Intent(getApplication(),RelianceMainActivity.class);
			startActivity(i);
		}
		 if(position==6)
		{
			Intent i=new Intent(getApplication(),VodaphoneMainAcitivity.class);
			startActivity(i);
		}
		else
		{
			
		}
	
		}
			});
		}

	@Override
	public void onFailedToReceiveAd(Ad ad, ErrorCode error) {
		String message = "Load Ads Failed: (" + error + ")";
		textView.setText(message);
	}

	@Override
	public void onLeaveApplication(Ad arg0) {
	
	}

	/**
	 * Called when an Activity is created in front of the app (e.g. an
	 * interstitial is shown, or an ad is clicked and launches a new Activity).
	 */
	@Override
	public void onPresentScreen(Ad arg0) {
		
	}

	@Override
	public void onReceiveAd(Ad arg0) {
		if (interstitialAds.isReady()) {
			interstitialAds.show();
		} else {
			textView.setText("Interstitial ad was not ready to be shown.");
		}
	}

	@Override
	public void onDismissScreen(Ad arg0) {
		
		
	}
}

/*
 * static final String[] list=new
 * String[]{"Aircel","Airtel","Bsnl","Docomo","Idea","Reliance","Vodafone"};
 * Integer[]
 * image={R.drawable.aircel_network,R.drawable.airtel_network,R.drawable
 * .bsnl_network
 * ,R.drawable.docomologo_network,R.drawable.idea_network,R.drawable
 * .reliance_network,R.drawable.vodafone_network}; public ImageView
 * image1,image2,image3,image4,image5,image6,image7;
 * 
 * public void onCreate(Bundle savedInstance){ super.onCreate(savedInstance);
 * setListAdapter(new
 * ArrayAdapter<String>(this,R.layout.network_option,R.id.label,list ));
 * 
 * image1=(ImageView)findViewById(R.id.logo1);
 * image2=(ImageView)findViewById(R.id.logo2);
 * image3=(ImageView)findViewById(R.id.logo3);
 * image4=(ImageView)findViewById(R.id.logo4);
 * image5=(ImageView)findViewById(R.id.logo5);
 * image6=(ImageView)findViewById(R.id.logo6);
 * image7=(ImageView)findViewById(R.id.logo7);
 * //iv.setImageDrawable(R.drawable.aircel_network); }
 * 
 * @Override protected void onListItemClick(ListView l,View v,int position,long
 * id) {
 */

/* image1.setImageResource(0); */

/*
 * image1.setImageResource(2); image1.setImageResource(3);
 * image1.setImageResource(4); image1.setImageResource(5);
 * image1.setImageResource(6);
 */
