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

public class RelianceMainActivity extends ListActivity{	
	ListView lv_reliance;
	public void onCreate(Bundle savedInBundle){
	super.onCreate(savedInBundle);
	setContentView(R.layout.activity_main);
	String[] RelianceList=getResources().getStringArray(R.array.Reliance);
	ArrayAdapter reliance_array=new ArrayAdapter<String>(this,R.layout.simple_network_row,RelianceList);
	 lv_reliance=(ListView)findViewById(android.R.id.list);
	/*this.setListAdapter(new ArrayAdapter<String>(this,R.layout.simple_network_row,R.id.label,RelianceList));
	ListView lv=getListView();*/
	 lv_reliance.setAdapter(reliance_array);
	 AdView adview=(AdView)findViewById(R.id.adView);
		AdRequest re=new AdRequest();
		re.setTesting(true);
		adview.loadAd(re);
	 lv_reliance.setOnItemClickListener(new OnItemClickListener() {
		
		@Override
		public void onItemClick(AdapterView<?> parent,View view,int position,long id)
		{
		String sel=((TextView) view).getText().toString();
		Toast.makeText(getApplicationContext(),sel,Toast.LENGTH_LONG).show();
		if(sel.equals("Mobile Number"))
		{
			Intent i=new Intent(getApplicationContext(),RelianceMobileNumber.class);
			startActivity(i);
			
		}
		else if(sel.equals("Check balance"))
		{
			Intent i=new Intent(getApplicationContext(),RelianceCheckBalance.class);
			startActivity(i);
			
		}
		else if(sel.equals("Special Packs"))
		{
			Intent i=new Intent(getApplicationContext(),RelianceSpecialPacks.class);
			startActivity(i);
			
		}
		else if(sel.equals("Caller Tunes"))
		{
			Intent i=new Intent(getApplicationContext(),RelianceCallerTunes.class);
			startActivity(i);
			
		}
		else if(sel.equals("Packs"))
		{
			Intent i=new Intent(getApplicationContext(),ReliancePacks.class);
			startActivity(i);
			
		}
		else if(sel.equals("Latest Balance information"))
		{
			Intent i=new Intent(getApplicationContext(),RelianceLatestInformation.class);
			startActivity(i);
			
		}
		else if(sel.equals("Activate Caller Tune"))
		{
			Intent i=new Intent(getApplicationContext(),RelianceActivateCallerTune.class);
			startActivity(i);
			
		}
		else if(sel.equals("Deactivate caller tune"))
		{
			Intent i=new Intent(getApplicationContext(),RelianceDeactiveCallerTune.class);
			startActivity(i);
			
		}
		else if(sel.equals("Local Call balance"))
		{
			Intent i=new Intent(getApplicationContext(),RelianceLocalCallBalance.class);
			startActivity(i);
			
		}
		else if(sel.equals("GPRS data balance"))
		{
			Intent i=new Intent(getApplicationContext(),RelianceGprsDataBalance.class);
			startActivity(i);
			
		}
		else if(sel.equals("customer care"))
		{
			Intent i=new Intent(getApplicationContext(),RelianceCustomerCare.class);
			startActivity(i);
			
		}
		else if(sel.equals("GPRS setting"))
		{
			Intent i=new Intent(getApplicationContext(),RelianceGprsSetting.class);
			startActivity(i);
			
		}
		else if(sel.equals("Reliance Voucher"))
		{
			Intent i=new Intent(getApplicationContext(),RelianceVoucher.class);
			startActivity(i);
			
		}
		else
		{
			Intent i=new Intent(getApplicationContext(),RelianceFreeInternet.class);
			startActivity(i);
		}
		}
		
	});
	
}

}
