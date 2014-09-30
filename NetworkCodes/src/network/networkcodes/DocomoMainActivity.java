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

public class DocomoMainActivity extends ListActivity {
	final Context context=this;
	ListView lv_docomo;
	public void onCreate(Bundle savedInBundle){
		super.onCreate(savedInBundle);
		setContentView(R.layout.activity_main);
		String[] DocomoList=getResources().getStringArray(R.array.Docomo);
		ArrayAdapter docomo_array=new ArrayAdapter<String>(this,R.layout.simple_network_row,DocomoList);
		 lv_docomo=(ListView)findViewById(android.R.id.list);
		/*this.setListAdapter(new ArrayAdapter<String>(this,R.layout.simple_network_row,R.id.label,DocomoList));
		ListView lv=getListView();*/
		 lv_docomo.setAdapter(docomo_array);
		 AdView adview=(AdView)findViewById(R.id.adView);
			AdRequest re=new AdRequest();
			re.setTesting(true);
			adview.loadAd(re);
		lv_docomo.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent,View view,int position,long id)
			{
			String sel=((TextView) view).getText().toString();
			Toast.makeText(getApplicationContext(),sel,Toast.LENGTH_LONG).show();
			if(sel.equals("Mobile Number"))
			{
				Intent i=new Intent(getApplicationContext(),DocomoMobileNumber.class);
				startActivity(i);
				
			}
			else if(sel.equals("Balance"))
			{
				Intent i=new Intent(getApplicationContext(),DocomoBalance.class);
				startActivity(i);
				
			}
			else if(sel.equals("Internet Balance"))
			{
				Intent i=new Intent(getApplicationContext(),DocomoInternetBalance.class);
				startActivity(i);
				
			}
			else if(sel.equals("Customer Care"))
			{
				Intent i=new Intent(getApplicationContext(),DocomoCustomerCare.class);
				startActivity(i);
				
			}
			else if(sel.equals("Recharge"))
			{
				/*Intent i=new Intent(getApplicationContext(),DocomoRecharge.class);
				startActivity(i);*/
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
							// TODO Auto-generated method stub
							/*Intent i = new Intent(getBaseContext(),AircelMainActivity.class);
							startActivity(i);*/
							dialog.cancel();
						}
					});
					ab.setNeutralButton("Call", new DialogInterface.OnClickListener() {
						
						//@Override
						public void onClick(DialogInterface dialog, int which) {
							
							// TODO Auto-generated method stub
						//	t2=(EditText) et.getText();
							//n2=(EditText) nam.getText();
							String no=nam.getText().toString();
							if(no.length()==16)
							{
							Log.i("MAKE CALL","");
							Intent i=new Intent();
							
							try
							{
								startActivity(new Intent("android.intent.action.CALL",Uri.parse("tel:*135*2*"+no+Uri.encode("#"))));
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
			else if(sel.equals("Internet Packs"))
			{
				Intent i=new Intent(getApplicationContext(),DocomoInternetPacks.class);
				startActivity(i);
				
			}
			else if(sel.equals("Activate 3G"))
			{
				Intent i=new Intent(getApplicationContext(),DocomoActivate3G.class);
				startActivity(i);
				
				
			}
			else
			{
				Intent i=new Intent(getApplicationContext(),DocomoMoreInfo.class);
				startActivity(i);
			}
			}
			
		});
		
	}

}
