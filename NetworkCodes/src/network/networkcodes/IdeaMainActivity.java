package network.networkcodes;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class IdeaMainActivity extends ListActivity {
	ListView lv_idea;
	public void onCreate(Bundle savedInBundle){
		super.onCreate(savedInBundle);
		setContentView(R.layout.activity_main);
		String[] IdeaList=getResources().getStringArray(R.array.Idea);
		ArrayAdapter idea_array=new ArrayAdapter<String>(this,R.layout.simple_network_row,IdeaList);
		 lv_idea=(ListView)findViewById(android.R.id.list);
		/*this.setListAdapter(new ArrayAdapter<String>(this,R.layout.simple_network_row,R.id.label,IdeaList));
		ListView lv=getListView();*/
		 lv_idea.setAdapter(idea_array);
		 AdView adview=(AdView)findViewById(R.id.adView);
			AdRequest re=new AdRequest();
			re.setTesting(true);
			adview.loadAd(re);
		 lv_idea.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent,View view,int position,long id)
			{
			String sel=((TextView) view).getText().toString();
			Toast.makeText(getApplicationContext(),sel,Toast.LENGTH_LONG).show();
			if(sel.equals("Customer Care"))
			{
				Intent i=new Intent(getApplicationContext(),IdeaCustomerCare.class);
				startActivity(i);
				
			}
			else if(sel.equals("Balance Checker"))
			{
				Intent i=new Intent(getApplicationContext(),IdeaBalanceChecker.class);
				startActivity(i);
				
			}
			else if(sel.equals("3G Activation"))
			{
				Intent i=new Intent(getApplicationContext(),Idea3GActivation.class);
				startActivity(i);
				
			}
			else if(sel.equals("Idea Live Tv"))
			{
				Intent i=new Intent(getApplicationContext(),IdeaLiveTv.class);
				startActivity(i);
				
			}
			else if(sel.equals("GPRS"))
			{
				Intent i=new Intent(getApplicationContext(),IdeaGprsFresh.class);
				startActivity(i);
				
			}
			else if(sel.equals("Service Number"))
			{
				Intent i=new Intent(getApplicationContext(),IdeaServiceNumber.class);
				startActivity(i);
				
			}
			else if(sel.equals("GPRS Setting"))
			{
				Intent i=new Intent(getApplicationContext(),IdeaGprsSettings.class);
				startActivity(i);
				
			}
			else if(sel.equals("Your Idea Number"))
			{
				Intent i=new Intent(getApplicationContext(),IdeaYourNum.class);
				startActivity(i);
				
			}
			else if(sel.equals("SMS Balance"))
			{
				Intent i=new Intent(getApplicationContext(),IdeaSmsBalance.class);
				startActivity(i);
				
			}
			else
			{
				Intent i=new Intent(getApplicationContext(),IdeaDndService.class);
				startActivity(i);
			}
			}
			
		});
		
	}

}
