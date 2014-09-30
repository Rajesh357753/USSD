package network.networkcodes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class VodafoneGprsBalanceCheck extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.i("MAKE CALL","");
		Intent i=new Intent();
		
		try
		{
			startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("sms:"+"144")));
			finish();
			Log.i("finished","");
			Toast.makeText(getApplicationContext(),"Type GPRS in the message", Toast.LENGTH_LONG).show();
		}catch(android.content.ActivityNotFoundException ex)
		{
			
		}
		//sendSms("144","ACT 3G");
	}
	/*private void sendSms(String phone,String message)
	{
		SmsManager sms=SmsManager.getDefault();
		sms.sendTextMessage(phone, null, message, null, null);
	}*/

}
