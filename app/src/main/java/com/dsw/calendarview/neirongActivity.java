package com.dsw.calendarview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class neirongActivity extends Activity{
	MediaPlayer mediaPlayer;
	 String pathToFile;
	 TextView e1,e2;
	 TextView timTextView;
	 ImageView m1,m2,m3;
	 TextView yuyin;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_neirong);
		
		e1=(TextView)findViewById(R.id.editText1);
		e2=(TextView)findViewById(R.id.editText2);
		timTextView=(TextView)findViewById(R.id.timePicker1);
		
		
		    m1=(ImageView)findViewById(R.id.shuru2);
	        m2=(ImageView)findViewById(R.id.shuru3);
	        m3=(ImageView)findViewById(R.id.shuru4);
	        yuyin=(TextView)findViewById(R.id.shuru1);
	        //input1
		
		String s=AirCache.get1(neirongActivity.this, "main", MainActivity.date2);
		
		String[] a=s.split("#");
		
		String[] b=a[1].split("q");
		timTextView.setText(b[0]+":"+b[1]);
		
		e1.setText(a[2]);
		e2.setText(a[3]);
		
	String path1="/sdcard/AArilitixing/"+"AArilitixing"+MainActivity.date1+a[1]+"A"+".png";
		//e1.setText(path1);
	File mFile=new File(path1);
    if (mFile.exists()) {
        Bitmap bitmap=BitmapFactory.decodeFile(path1);
        m1.setImageBitmap(bitmap);
    }
    else {
		m1.setVisibility(View.INVISIBLE);
	}
    
    
    String path2="/sdcard/AArilitixing/"+"AArilitixing"+MainActivity.date1+a[1]+"B"+".png";
	//e1.setText(path1);
File mFile2=new File(path2);
if (mFile2.exists()) {
    Bitmap bitmap=BitmapFactory.decodeFile(path2);
    m2.setImageBitmap(bitmap);
}
else {
	m2.setVisibility(View.INVISIBLE);
}




String path3="/sdcard/AArilitixing/"+"AArilitixing"+MainActivity.date1+a[1]+"C"+".png";
//e1.setText(path1);
File mFile3=new File(path3);
if (mFile3.exists()) {
Bitmap bitmap=BitmapFactory.decodeFile(path3);
m3.setImageBitmap(bitmap);
}
else {
m3.setVisibility(View.INVISIBLE);
}


    
    pathToFile ="/sdcard/AArilitixing/"+"AArilitixing"+MainActivity.date1+a[1]+"D"+".mp3";;
	 
	
    
    
    //create mediaplayer
    yuyin.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			   mediaPlayer = new MediaPlayer();
			  //set audio file path
			  try {
			    mediaPlayer.setDataSource(pathToFile);
			  } catch (IllegalArgumentException e) {
			    e.printStackTrace();
			  } catch (IllegalStateException e) {
			    e.printStackTrace();
			  } catch (IOException e) {
			    e.printStackTrace();
			  }
			  //Prepare mediaplayer
			  try {
			    mediaPlayer.prepare();
			  } catch (IllegalStateException e) {
			    e.printStackTrace();
			  } catch (IOException e) {
			   e.printStackTrace();
			  }
			  //start mediaPlayer
			  mediaPlayer.start();
		}
	});
    

	}
	
	
	

}
