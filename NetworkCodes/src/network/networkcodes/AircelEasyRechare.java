package network.networkcodes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AircelEasyRechare extends Activity {
	
	final Context context=this;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_prompt);
        
     
				// TODO Auto-generated method stub
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
				Intent i = new Intent(getBaseContext(),AircelMainActivity.class);
				startActivity(i);
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
		
        
    }