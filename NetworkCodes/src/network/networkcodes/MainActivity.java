package network.networkcodes;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goo_ads);
		AdView adview=(AdView)findViewById(R.id.adView);
		AdRequest re=new AdRequest();
		re.setTesting(true);
		adview.loadAd(re);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
