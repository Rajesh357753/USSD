package network.networkcodes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class AirtelMobileProtablillity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.i("SEND Sms","");
		Intent i=new Intent();
		
		try
		{
			startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("sms:"+"1909")));
			finish();
			Log.i("finished","");
			Toast.makeText(getApplicationContext(),"Type PORT in the message", Toast.LENGTH_LONG).show();
		}catch(android.content.ActivityNotFoundException ex)
		{
			
		}
		//sendSms("1909","PORT");
	}
	/*private void sendSms(String phone,String message)
	{
		SmsManager sms=SmsManager.getDefault();
		sms.sendTextMessage("1909", null, message, null, null);
	}
*/
	}


