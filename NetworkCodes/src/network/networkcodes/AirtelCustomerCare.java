package network.networkcodes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class AirtelCustomerCare extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TelephonyManager telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String getSimSerialNumber = telemamanger.getSimSerialNumber();
		String getSimNumber = telemamanger.getLine1Number();
		Log.i("MAKE CALL","");
		Intent i=new Intent(Intent.ACTION_CALL);
		i.setData(Uri.parse("tel:121"));
		try
		{
			startActivity(i);
			finish();
			Log.i("finished","");
			
		}catch(android.content.ActivityNotFoundException ex)
		{
			
		}
	}


}
