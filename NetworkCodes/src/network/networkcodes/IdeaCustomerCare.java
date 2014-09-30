package network.networkcodes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class IdeaCustomerCare extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.i("MAKE CALL","");
		Intent i=new Intent(Intent.ACTION_CALL);
		i.setData(Uri.parse("tel:198"));
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
