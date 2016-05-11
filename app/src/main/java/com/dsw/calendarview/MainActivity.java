package com.dsw.calendarview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsw.datepicker.MonthDateView;
import com.dsw.datepicker.MonthDateView.DateClick;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity {
	private ImageView iv_left;
	private ImageView iv_right;
	private TextView tv_date;
	private TextView tv_week;
	private TextView tv_today;
	public static String date1;
	public static String date2;
	TextView shit1,shit2,shit3;
	private MonthDateView monthDateView;
	TextView textView1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		List<Integer> list = new ArrayList<Integer>();

		setContentView(R.layout.activity_date);
		
		shit1=(TextView)findViewById(R.id.textView2);
		shit2=(TextView)findViewById(R.id.textView4);
		shit3=(TextView)findViewById(R.id.textView5);
		
		
		
		shit1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if((shit1.getText()+"").length()>0){
					date2=date1+"X";
					Intent intent = new Intent(MainActivity.this,
							neirongActivity.class);
					startActivity(intent);
					
				}
				
				
				
				
			}
		});
shit2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if((shit2.getText()+"").length()>0){
					date2=date1+"Y";
					Intent intent = new Intent(MainActivity.this,
							neirongActivity.class);
					startActivity(intent);
					
				}
				
				
				
				
			}
		});
shit3.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if((shit3.getText()+"").length()>0){
			date2=date1+"Z";
			Intent intent = new Intent(MainActivity.this,
					neirongActivity.class);
			startActivity(intent);
			
		}
		
		
		
		
	}
});
		
		
		
		
		textView1=(TextView)findViewById(R.id.textView1);
		textView1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						addActivity.class);
				startActivity(intent);
				
			}
		});
		
		
		iv_left = (ImageView) findViewById(R.id.iv_left);
		iv_right = (ImageView) findViewById(R.id.iv_right);
		monthDateView = (MonthDateView) findViewById(R.id.monthDateView);
		tv_date = (TextView) findViewById(R.id.date_text);
		tv_week  =(TextView) findViewById(R.id.week_text);
		tv_today = (TextView) findViewById(R.id.tv_today);
		monthDateView.setTextView(tv_date,tv_week);
		monthDateView.setDaysHasThingList(list);
		monthDateView.setDateClick(new DateClick() {
			
			@Override
			public void onClickOnDate() {
				//textView1.setText(""+monthDateView.getmSelDay()+"yue"+monthDateView.getmSelMonth()+"nian"+monthDateView.getmSelYear());
				tv_today.setVisibility(View.GONE);
				date1=monthDateView.getmSelYear()+"q"+monthDateView.getmSelMonth()+"q"+monthDateView.getmSelDay()+"q";
				
				shit1.setText(gettime(AirCache.get1(MainActivity.this, "main", date1+"X")));
				
				
				
				shit2.setText(gettime(AirCache.get1(MainActivity.this, "main", date1+"Y")));
				shit3.setText(gettime(AirCache.get1(MainActivity.this, "main", date1+"Z")));
				
				
				//Toast.makeText(getApplication(), "click:" + monthDateView.getmSelDay(), Toast.LENGTH_SHORT).show();
			}
		});
		setOnlistener();
		date1=monthDateView.getmSelYear()+"q"+monthDateView.getmSelMonth()+"q"+monthDateView.getmSelDay();
		
		shit1.setText(gettime(AirCache.get1(MainActivity.this, "main", date1+"X")));
		shit2.setText(gettime(AirCache.get1(MainActivity.this, "main", date1+"Y")));
		shit3.setText(gettime(AirCache.get1(MainActivity.this, "main", date1+"Z")));
		
		
	}
	
	public static String gettime(String s) {
		
		
		try {
			
			String[] a=s.split("#");
			/*for (int i = 0; i < a.length; i++) {
				T.showShort(MainActivity.this, message)
				
			};*/
			
			String[] b=a[1].split("q");
			
			String string=""+b[0]+":"+b[1]+"\nReminder："+a[2];
			
			
			return string;
			
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
		
		
	}
	
	
	private void setOnlistener(){
		iv_left.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				monthDateView.onLeftClick();
			}
		});
		
		iv_right.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				monthDateView.onRightClick();
			}
		});
		
		tv_today.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				monthDateView.setTodayToView();
			}
		});
	}
}
