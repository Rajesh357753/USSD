package network.networkcodes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class AirtelThreeG  extends Activity{ 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.i("MAKE CALL","");
		Intent i=new Intent();
		
		try
		{
			startActivity(new Intent("android.intent.action.CALL",Uri.parse("tel:*121*11"+Uri.encode("#"))));
			finish();
			Log.i("finished","");
			
		}catch(android.content.ActivityNotFoundException ex)
		{
			
		}
	}
	}
