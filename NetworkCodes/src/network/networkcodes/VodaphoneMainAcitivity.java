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

public class VodaphoneMainAcitivity extends ListActivity {
	ListView lv_vodaphone;
	public void onCreate(Bundle savedInBundle){
		super.onCreate(savedInBundle);
		setContentView(R.layout.activity_main);
		String[] VodaphoneList=getResources().getStringArray(R.array.Vodafone);
		ArrayAdapter vodaphone_array=new ArrayAdapter<String>(this,R.layout.simple_network_row,VodaphoneList);
		 lv_vodaphone=(ListView)findViewById(android.R.id.list);
		/*this.setListAdapter(new ArrayAdapter<String>(this,R.layout.simple_network_row,R.id.label,VodaphoneList));
		ListView lv=getListView();*/
		 lv_vodaphone.setAdapter(vodaphone_array);
		 AdView adview=(AdView)findViewById(R.id.adView);
			AdRequest re=new AdRequest();
			re.setTesting(true);
			adview.loadAd(re);
lv_vodaphone.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent,View view,int position,long id)
			{
			String sel=((TextView) view).getText().toString();
			Toast.makeText(getApplicationContext(),sel,Toast.LENGTH_LONG).show();
			if(sel.equals("Customer Care- 111"))
			{
				Intent i=new Intent(getApplicationContext(),VodafoneCustomerCare.class);
				startActivity(i);
				
			}
			else if(sel.equals("Main Balance Check"))
			{
				Intent i=new Intent(getApplicationContext(),VodafoneMainBalanceCheck.class);
				startActivity(i);
				
			}
			else if(sel.equals("Gprs Balance Check"))
			{
				Intent i=new Intent(getApplicationContext(),VodafoneGprsBalanceCheck.class);
				startActivity(i);
				
			}
			else if(sel.equals("3G data card balance"))
			{
				Intent i=new Intent(getApplicationContext(),Vodafone3gDatacard.class);
				startActivity(i);
				
			}
			else if(sel.equals("Your Vodafone Number"))
			{
				Intent i=new Intent(getApplicationContext(),VodafoneYourMoileNumber.class);
				startActivity(i);
				
			}
			else if(sel.equals("SMS Balance"))
			{
				Intent i=new Intent(getApplicationContext(),VodafoneSmsBalance.class);
				startActivity(i);
				
			}
			else if(sel.equals("Call Balance Check"))
			{
				Intent i=new Intent(getApplicationContext(),VodafoneCallBalanceCheck.class);
				startActivity(i);
				
			}
			else if(sel.equals("3G Activation"))
			{
				Intent i=new Intent(getApplicationContext(),Vodafone3gActivationAct.class);
				startActivity(i);
				
			}
			else if(sel.equals("Gprs balance data"))
			{
				Intent i=new Intent(getApplicationContext(),VodafoneGprsbalnce.class);
				startActivity(i);
				
			}
			else if(sel.equals("Night GPRS balance"))
			{
				Intent i=new Intent(getApplicationContext(),VodafoneNightGprsdata.class);
				startActivity(i);
				
			}
			else if(sel.equals("20 MB 3G plan"))
			{
				Intent i=new Intent(getApplicationContext(),Vodafone20mb3gplan.class);
				startActivity(i);
				
			}
			else if(sel.equals("1 day GPRS pack"))
			{
				Intent i=new Intent(getApplicationContext(),Vodafone1dayGprsPack.class);
				startActivity(i);
				
			}
			else if(sel.equals("3 days GPRS pack"))
			{
				Intent i=new Intent(getApplicationContext(),Vodafone3daysGprsPack.class);
				startActivity(i);
				
			}
			else if(sel.equals("7 days GPRS pack"))
			{
				Intent i=new Intent(getApplicationContext(),Vodafone7daysGprspack.class);
				startActivity(i);
				
			}
			else if(sel.equals("15 days GPRS pack"))
			{
				Intent i=new Intent(getApplicationContext(),Vodafone15daysGprsPack.class);
				startActivity(i);
				
			}
			else if(sel.equals("1 month GPRS pack"))
			{
				Intent i=new Intent(getApplicationContext(),Vodafone1monthgprsPack.class);
				startActivity(i);
				
			}
			else if(sel.equals("5GB 1 month 3G"))
			{
				Intent i=new Intent(getApplicationContext(),Vodafone5Gb3G.class);
				startActivity(i);
				
			}
			else if(sel.equals("8GB 1 month 3G"))
			{
				Intent i=new Intent(getApplicationContext(),Vodafone8Gb3g.class);
				startActivity(i);
				
			}
			
			else
			{
				Intent i=new Intent(getApplicationContext(),VodafoneRateCutter.class);
				startActivity(i);
			}
			}
			
		});
		
	}

}
