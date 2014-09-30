package network.networkcodes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class IdeaGprsFresh extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.i("MAKE CALL","");
		Intent i=new Intent();
		
		try
		{
			startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("sms:FRESH"+"4666")));
			finish();
			Log.i("finished","");
			Toast.makeText(getApplicationContext(),"Type FRESH in the message", Toast.LENGTH_LONG).show();
		}catch(android.content.ActivityNotFoundException ex)
		{
			
		}
		//sendSms("4666","FRESH");
	}
	/*private void sendSms(String phone,String message)
	{
		SmsManager sms=SmsManager.getDefault();
		sms.sendTextMessage(phone, null, message, null, null);
	}*/

}