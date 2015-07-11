package com.dwexample.location;
import com.dwexample.Adapter.CoverFlow;
import com.dwexample.Adapter.ImageAdapter;
import com.dwexample.location.R;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class HelloAndroid extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		CoverFlow cf = new CoverFlow(this);
		// cf.setBackgroundResource(R.drawable.shape);
		cf.setBackgroundColor(Color.BLACK);
		final Integer[] mImageIds = { 
				R.drawable.a1,R.drawable.a2, R.drawable.a3,R.drawable.a4, R.drawable.a5,R.drawable.dw,
				R.drawable.fjd,R.drawable.lov,R.drawable.mz,R.drawable.shida,R.drawable.xh,R.drawable.zxh
				};
		final Class<?>[] target={CamActivity.class,
				com.dwexample.location.LocationDemo.class,
				MyActivity.class,
				com.dwexample.location.LocationDemo.class,
				com.dwexample.Guide.ViewPagerActivity.class,com.dwexample.location.LocationDemo.class,
				MyActivity.class,com.dwexample.Guide.ViewPagerActivity.class,
				MyActivity.class,com.dwexample.location.LocationDemo.class,com.dwexample.Guide.ViewPagerActivity.class};
		cf.setAdapter(new ImageAdapter(this));
		ImageAdapter imageAdapter = new ImageAdapter(this);
		cf.setAdapter(imageAdapter);
		// cf.setAlphaMode(false);
		// cf.setCircleMode(false);
		
		cf.setSelection(2, true);
		cf.setAnimationDuration(1000);
		cf.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				final Intent intent=new Intent();
				System.out.println("onItemClick:"+arg2);
				intent.setClass(HelloAndroid.this,target[arg2]);
				startActivity(intent);
			}
		});
		setContentView(cf);
	}

}