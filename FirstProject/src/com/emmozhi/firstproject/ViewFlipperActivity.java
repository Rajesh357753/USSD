package com.emmozhi.firstproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class ViewFlipperActivity extends Activity{
	 ViewFlipper view;
	 int Visibility=1;
	 int Invisibility=0;
	 int a=0;
		int b=16;
	 int gallery_grid_images[]={R.drawable.em1,R.drawable.em2,R.drawable.em4,R.drawable.em5,R.drawable.em6,R.drawable.em7,R.drawable.em07,R.drawable.em8,R.drawable.em9,R.drawable.em10,R.drawable.em11,R.drawable.em12,R.drawable.em13,R.drawable.em14,R.drawable.em15,R.drawable.em16};
	//int gallery_grid_images[]={R.drawable.aircel_network,R.drawable.airtel_network,R.drawable.bsnl_network,R.drawable.docomo_network,R.drawable.idea_network,R.drawable.relaince_network,R.drawable.vodafone_network };
	   @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.view_image_onebyone);
	       // Integer[] imageId = {R.drawable.aircel_network,R.drawable.airtel_network,R.drawable.bsnl_network,R.drawable.docomo_network,R.drawable.idea_network,R.drawable.relaince_network,R.drawable.vodafone_network  };
	   
	  
	   view=(ViewFlipper)findViewById(R.id.viewFLipper);
	   for(int i=0;i<gallery_grid_images.length;i++)
	   {
		   setFlipperImage(gallery_grid_images[i]);
	   }
	   
	   Button previous=(Button)findViewById(R.id.previous_button);
	   previous.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(a>0)
			{
			view.showPrevious();
			a--;
			}
		}
	});
	   Button next=(Button)findViewById(R.id.next_button);
	   next.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			if(a<16)
			{
				view.showNext();
				a++;
			}
			else
			{
				//view.showPrevious();
			}
			
		
			
		}
	});
	   Button main_button=(Button)findViewById(R.id.main_button);
	   main_button.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i=new Intent(getApplicationContext(),Emmozhi.class);
			startActivity(i);
		}
	});
	   
	   }
	   private void setFlipperImage(int res)
	   {
		   Log.i("SET Flipper Called",res+"");
		   ImageView image=new ImageView(getApplicationContext());
		   image.setBackgroundResource(res);
		   view.addView(image);
		   
	   }
}
