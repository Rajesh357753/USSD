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

public class AirtelMainActivity extends ListActivity{ 	
	//final Context context=this;
	ListView lv_airtel;
	final Context context=this;
public void onCreate(Bundle savedInBundle){
	super.onCreate(savedInBundle);
	setContentView(R.layout.activity_main);
	String[] AirtelList=getResources().getStringArray(R.array.Airtel);
	ArrayAdapter airtel_array=new ArrayAdapter<String>(this,R.layout.simple_network_row,AirtelList);
	 lv_airtel=(ListView)findViewById(android.R.id.list);
	
	 lv_airtel.setAdapter(airtel_array);
	 AdView adview=(AdView)findViewById(R.id.adView);
		AdRequest re=new AdRequest();
		re.setTesting(true);
		adview.loadAd(re);
	lv_airtel.setOnItemClickListener(new OnItemClickListener() {
		
		@Override
		public void onItemClick(AdapterView<?> parent,View view,int position,long id)
		{
		String sel=((TextView) view).getText().toString();
		Toast.makeText(getApplicationContext(),sel,Toast.LENGTH_LONG).show();
		if(sel.equals("Customer Care"))
		{
			Intent i=new Intent(getApplicationContext(),AirtelCustomerCare.class);
			startActivity(i);
			
		}
		else if(sel.equals("Voucher"))
		{
			
			 LayoutInflater li=LayoutInflater.from(context);
				View promptView=li.inflate(R.layout.activity_prompt, null);
				AlertDialog.Builder ab=new AlertDialog.Builder(context);
				ab.setView(promptView);
				final EditText nam=(EditText)promptView.findViewById(R.id.editNumber);
				
				ab.setCancelable(false);
				ab.setNegativeButton("Back", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.cancel();
					}
				});
				ab.setNeutralButton("Call", new DialogInterface.OnClickListener() {
					
					
					public void onClick(DialogInterface dialog, int which) {
						
						
						String no=nam.getText().toString();
						if(no.length()==16)
						{
						Log.i("MAKE CALL","");
						Intent i=new Intent();
						
						try
						{
							startActivity(new Intent("android.intent.action.CALL",Uri.parse("tel:*101*"+no+Uri.encode("#"))));
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
		else if(sel.equals("3G"))
		{
			Intent i=new Intent(getApplicationContext(),AirtelThreeG.class);
			startActivity(i);
			
		}
		else if(sel.equals("Complaint no"))
		{
			Intent i=new Intent(getApplicationContext(),AirtelComplaintno.class);
			startActivity(i);
			
		}
		else if(sel.equals("Balance Check"))
		{
			Intent i=new Intent(getApplicationContext(),AirtelBalanceCheck.class);
			startActivity(i);
			
		}
		else if(sel.equals("Gift Service"))
		{
			Intent i=new Intent(getApplicationContext(),AirtelGiftService.class);
			startActivity(i);
			
		}
		else if(sel.equals("Airtel 2G data balance"))
		{
			Intent i=new Intent(getApplicationContext(),Airtel2gdatabalance.class);
			startActivity(i);
			
		}
		else if(sel.equals("Airtel 3G data balance"))
		{
			Intent i=new Intent(getApplicationContext(),Airtel3gdatabalance.class);
			startActivity(i);
			
		}
		else if(sel.equals("2G internet pack"))
		{
			Intent i=new Intent(getApplicationContext(),Airtel2ginternetpack.class);
			startActivity(i);
			
		}
		else if(sel.equals("Recharge"))
		{
			Intent i=new Intent(getApplicationContext(),AirtelRecharge123.class);
			startActivity(i);
			
		}
		else if(sel.equals("Mobile Portability"))
		{
			Intent i=new Intent(getApplicationContext(),AirtelMobileProtablillity.class);
			startActivity(i);
			
		}
		else if(sel.equals("3G Activation"))
		{
			Intent i=new Intent(getApplicationContext(),Airtel3gActivation.class);
			startActivity(i);
			
		}
		else if(sel.equals("Callertune Trick"))
		{
			Intent i=new Intent(getApplicationContext(),AirtelCallerTuneTrick.class);
			startActivity(i);
			
		}
		else
		{
			Intent i=new Intent(getApplicationContext(),AirtelbalanceTransfer.class);
			startActivity(i);
		}
		}
		
	});
	
}

}