package network.networkcodes;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;

public class IdeaGprsSettings extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*Log.i("MAKE CALL","");
		Intent i=new Intent();
		
		try
		{
			startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("sms:ACT3G"+"53333")));
			finish();
			Log.i("finished","");
		}catch(android.content.ActivityNotFoundException ex)
		{
			
		}*/
		sendSms("54671","SET");
	}
	private void sendSms(String phone,String message)
	{
		SmsManager sms=SmsManager.getDefault();
		sms.sendTextMessage(phone, null, message, null, null);
	}

}