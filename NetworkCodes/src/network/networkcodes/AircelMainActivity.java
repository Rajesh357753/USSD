package network.networkcodes;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class AircelMainActivity extends ListActivity {
	
	ListView lv_aircel;
	final Context context=this;
	public void onCreate(Bundle savedInBundle){
		super.onCreate(savedInBundle);
		setContentView(R.layout.activity_main);	
		/*Intent i=new Intent(getApplication(),InterstitialsAdsExampleActivity.class);
		startActivity(i);*/
		String[] AircelList=getResources().getStringArray(R.array.Aircel);
		ArrayAdapter aircel_array=new ArrayAdapter<String>(this,R.layout.simple_network_row,AircelList);
		 lv_aircel=(ListView)findViewById(android.R.id.list);
		
		lv_aircel.setAdapter(aircel_array);
	
		AdView adview=(AdView)findViewById(R.id.adView);
		
		AdRequest re=new AdRequest();
		re.setTesting(true);
		adview.loadAd(re);
		lv_aircel.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent,View view,int position,long id)
			{
			String sel=((TextView) view).getText().toString();
			Toast.makeText(getApplicationContext(),sel,Toast.LENGTH_LONG).show();
			if(sel.equals("Customer Care"))
			{
				Intent i=new Intent(getApplicationContext(),AircelCustomerCare.class);
				startActivity(i);
				
			}
			else if(sel.equals("Your Aircel Number"))
			{
				Intent i=new Intent(getApplicationContext(),AircelNumberActiivty.class);
				startActivity(i);
				
			}
			else if(sel.equals("Balance Check"))
			{
				Intent i=new Intent(getApplicationContext(),AircelBalance.class);
				startActivity(i);
				
			}
			else if(sel.equals("Local Mobile Calls Balance"))
			{
				Intent i=new Intent(getApplicationContext(),AircelLocalMobileCalls.class);
				startActivity(i);
				
			}
			else if(sel.equals("Check GPRS Balance"))
			{
				Intent i=new Intent(getApplicationContext(),AircelCheckGprs.class);
				startActivity(i);
				
			}
			else if(sel.equals("3G Activation"))
			{
				Intent i=new Intent(getApplicationContext(),AircelthreegActivity.class);
				startActivity(i);
				
			}
			else if(sel.equals("Switch Off"))
			{
				Intent i=new Intent(getApplicationContext(),AircelSwitchof.class);
				startActivity(i);
				
			}
			else if(sel.equals("Rate Cutter"))
			{
				Intent i=new Intent(getApplicationContext(),AircelRateCutter.class);
				startActivity(i);
				
			}
			else if(sel.equals("e-Recharge"))
			{
				
				  LayoutInflater li=LayoutInflater.from(context);
					View promptView=li.inflate(R.layout.activity_prompt, null);
					AlertDialog.Builder ab=new AlertDialog.Builder(context);
					ab.setView(promptView);
					final EditText nam=(EditText)promptView.findViewById(R.id.editNumber);
					//final EditText et=(EditText)promptView.findViewById(R.id.editNumber);
					ab.setCancelable(false);
					ab.setNegativeButton("Back", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
						
							dialog.cancel();
						}
					});
					ab.setNeutralButton("Call", new DialogInterface.OnClickListener() {
						
						//@Override
						public void onClick(DialogInterface dialog, int which) {
							
						
							String no=nam.getText().toString();
							if(no.length()==16)
							{
							Log.i("MAKE CALL","");
							Intent i=new Intent();
							
							try
							{
								startActivity(new Intent("android.intent.action.CALL",Uri.parse("tel:*124*"+no+Uri.encode("#"))));
								finish();
								Log.i("finished","");
							}catch(android.content.ActivityNotFoundException ex)
							{
								
							}
						}
							else
							{
								Toast.makeText(getApplicationContext(), "Type 16 digit number from your recharge Voucher", Toast.LENGTH_LONG).show();
								
							}
						}
						
					});
					AlertDialog ad=ab.create();
					ad.show();
				
				
			}
			else if(sel.equals("Value Added Service"))
			{
				Intent i=new Intent(getApplicationContext(),AircelValueAddedService.class);
				startActivity(i);
				
			}
			else if(sel.equals("IMIS no"))
			{
				Intent i=new Intent(getApplicationContext(),AircelImisNoAcitivty.class);
				startActivity(i);
				
			}
			else if(sel.equals("Aircel free 3G Service"))
			{
				Intent i=new Intent(getApplicationContext(),AircelFreethreeService.class);
				startActivity(i);
				
			}
			else
			{
				Intent i=new Intent(getApplicationContext(),AircelBalanceTransfer.class);
				startActivity(i);
			}
			}
			
		});
		
	}

}
