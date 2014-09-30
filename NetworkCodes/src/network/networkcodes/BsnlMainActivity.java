package network.networkcodes;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class BsnlMainActivity extends ListActivity{	
	ListView lv_bsnl;
	public void onCreate(Bundle savedInBundle){
	super.onCreate(savedInBundle);
	setContentView(R.layout.activity_main);
	String[] BsnlList=getResources().getStringArray(R.array.Bsnl);
	ArrayAdapter bsnl_array=new ArrayAdapter<String>(this,R.layout.simple_network_row,BsnlList);
	 lv_bsnl=(ListView)findViewById(android.R.id.list);
	/*this.setListAdapter(new ArrayAdapter<String>(this,R.layout.simple_network_row,R.id.label,BsnlList));
	ListView lv=getListView();*/
	 lv_bsnl.setAdapter(bsnl_array);
	 AdView adview=(AdView)findViewById(R.id.adView);
		AdRequest re=new AdRequest();
		re.setTesting(true);
		adview.loadAd(re);
	lv_bsnl.setOnItemClickListener(new OnItemClickListener() {
		
		@Override
		public void onItemClick(AdapterView<?> parent,View view,int position,long id)
		{
		String sel=((TextView) view).getText().toString();
		Toast.makeText(getApplicationContext(),sel,Toast.LENGTH_LONG).show();
		if(sel.equals("Mobile Number"))
		{
			Intent i=new Intent(getApplicationContext(),BsnlMobileNumber.class);
			startActivity(i);
			
		}
		else if(sel.equals("Check balance"))
		{
			Intent i=new Intent(getApplicationContext(),BsnlBalanceCheck.class);
			startActivity(i);
			
		}
		else if(sel.equals("GPRS data balance"))
		{
			Intent i=new Intent(getApplicationContext(),BsnlGprsdatabalance.class);
			startActivity(i);
			
		}
		else if(sel.equals("SMS local"))
		{
			Intent i=new Intent(getApplicationContext(),BsnlSmsLocal.class);
			startActivity(i);
			
		}
		else if(sel.equals("National sms"))
		{
			Intent i=new Intent(getApplicationContext(),BsnlNationalSms.class);
			startActivity(i);
			
		}
		else if(sel.equals("Network call"))
		{
			Intent i=new Intent(getApplicationContext(),BsnlNetworkCall.class);
			startActivity(i);
			
		}
		else if(sel.equals("Local network call"))
		{
			Intent i=new Intent(getApplicationContext(),BsnlLocalNetworkCall.class);
			startActivity(i);
			
		}
		else if(sel.equals("Night GPRS pack"))
		{
			Intent i=new Intent(getApplicationContext(),BsnlNightGprs.class);
			startActivity(i);
			
		}
		else if(sel.equals("Video call bal"))
		{
			Intent i=new Intent(getApplicationContext(),BsnlVideoCallBack.class);
			startActivity(i);
			
		}
		else if(sel.equals("FnF Numbers Enquiry"))
		{
			Intent i=new Intent(getApplicationContext(),BsnlFnfNumbersEnquery.class);
			startActivity(i);
			
		}
		else if(sel.equals("SMS Balance"))
		{
			Intent i=new Intent(getApplicationContext(),BsnlSmsBalance.class);
			startActivity(i);
			
		}
		else if(sel.equals("Voice Packs Info"))
		{
			Intent i=new Intent(getApplicationContext(),BsnlVoicePacksInfo.class);
			startActivity(i);
			
		}
		else if(sel.equals("Last Call Charge"))
		{
			Intent i=new Intent(getApplicationContext(),BsnlLastCallCharge.class);
			startActivity(i);
			
		}
		else
		{
			Intent i=new Intent(getApplicationContext(),BsnlDataBalance.class);
			startActivity(i);
		}
		}
		
	});
	
}

}
