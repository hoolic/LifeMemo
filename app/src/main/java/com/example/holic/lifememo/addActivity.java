package com.dsw.calendarview;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.BounceInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class addActivity extends Activity{
	
	 private byte[] mContent;
	 Bitmap b1,b2,b3;
	 String timeString;
	
	ImageView[] ms=new ImageView[4];
	String FileName;
	int hour,min;
	TextView yuyinshuru,fanhui;
	ImageView pic1,pic2,pic3;
	TextView t1TextView;
	String dateqian;
	
	
	 private MediaPlayer mPlayer = null;  
	    private MediaRecorder mRecorder = null;  
	    TimePicker timePick1;
	    EditText e1,e2;
	
	
	int[] res={R.id.imageView4,R.id.imageView1,R.id.imageView2,R.id.imageView3};
ArrayList<ImageView> i1=new ArrayList<ImageView>();
boolean flag=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_add);
		BitmapLMTtools.createSDCardDir("AArilitixing");
		
		timePick1=(TimePicker)findViewById(R.id.timePicker1);  
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		timePick1.setIs24HourView(true);  
        TimeListener times=new TimeListener();  
        timePick1.setOnTimeChangedListener(times);  
        yuyinshuru=(TextView)findViewById(R.id.shuru1);
        fanhui=(TextView)findViewById(R.id.TextView01);
        
        dateqian= MainActivity.date1;
        //SimpleDateFormat formatter = new SimpleDateFormat("HHmm");
       // Date curDate = new Date(System.currentTimeMillis());//获取当前时间
       // final String time = formatter.format(curDate);
        hour=timePick1.getCurrentHour();
        min=timePick1.getCurrentMinute();
        
        String time2;
		time2=hour+"q"+min+"q";
		MainActivity.date1=dateqian+time2;
        
      //  timeString=dateqian+time;
        
       // MainActivity.date1=timeString;
		//T.showLong(addActivity.this, MainActivity.date1);
        
        
        pic1=(ImageView)findViewById(R.id.shuru2);
        pic2=(ImageView)findViewById(R.id.shuru3);
        pic3=(ImageView)findViewById(R.id.shuru4);
        t1TextView=(TextView)findViewById(R.id.textView1);
        
        

		FileName = Environment.getExternalStorageDirectory().getAbsolutePath();  
        FileName += "/AArilitixing/"+MainActivity.date1+"D.mp3";
		
		
		
		List<Integer> list = new ArrayList<Integer>();
		//ImageView m4=(ImageView) findViewById(R.id.imageView4);
		
		for (int i = 0; i < res.length; i++) {
        	ms[i]=(ImageView)findViewById(res[i]);
        	
        	i1.add(ms[i]);
        	
        	
			
		}
		ms[0].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (flag) {
					opena();
				}
				else {
					closea();	
				}
			}
		});
		ms[3].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				 mRecorder = new MediaRecorder();  
	             mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);  
	             mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);  
	             mRecorder.setOutputFile("/sdcard/AArilitixing/"+"AArilitixing"+MainActivity.date1+"D.mp3");  
	             mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);  
	             try {  
	                 mRecorder.prepare();  
	             } catch (IOException e) {  
	                 Log.e("sdsds", "prepare() failed");  
	             }  
	             mRecorder.start(); 
	             ms[3].setEnabled(false);
	             yuyinshuru.setText("正在录音中...（点击停止）");
				
				
			}
		});
		
		yuyinshuru.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 ms[3].setEnabled(true);
				  yuyinshuru.setText("已经录音");
				  try {
						 mRecorder.stop();  
			             mRecorder.release();  
			             mRecorder = null; 
			             T.showLong(addActivity.this, "录音停止！");
						
					} catch (Exception e) {
						// TODO: handle exception
					}
				 
				 
				 
			}
		});
	//	ms[1].setOnClickListener(new stopRecordListener()); 
ms[1].setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		ImageUtils.openCameraImage(addActivity.this
                );
	}
}); 
ms[2].setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		ImageUtils.openLocalImage(addActivity.this);
	}
}); 

fanhui.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		finish();
		
		
	}
});

t1TextView.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		String eeeString;
		//eeeString=e1.getText()+"##"
		String time2;
		time2=hour+"q"+min+"q";
		 //MainActivity.date1+=time2;
		 
		 MainActivity.date1=dateqian+time2;
		 
		
		
		//AirCache.save1(addActivity.this,  MainActivity.date1, "e1", e1.getText()+"");
		
		
		//AirCache.save1(addActivity.this,  MainActivity.date1, "e2", e2.getText()+"");
		
		 //String s=AirCache.get1(addActivity.this, dateqian, "1");
		 //T.showShort(addActivity.this, ""+s+s.length());
		 
		 
		
		if (AirCache.get1(addActivity.this, "main", dateqian+"X").length()==0) {
			
			
			
			//AirCache.save1(addActivity.this,  dateqian, "1",MainActivity.date1+"");
			
			//AirCache.save1(addActivity.this, dateqian, "1e1", e1.getText()+"");
			AirCache.save1(addActivity.this,
					"main", dateqian+"X","#"+time2+"#"+e1.getText()+"#"+e2.getText());
			
			//T.showShort(addActivity.this, "ok");
			
			
		}
		
		else if (AirCache.get1(addActivity.this, "main", dateqian+"Y").length()==0){
			AirCache.save1(addActivity.this,
					"main", dateqian+"Y","#"+time2+"#"+e1.getText()+"#"+e2.getText());
			
			
		
		}
		
        else if (AirCache.get1(addActivity.this, "main", dateqian+"Z").length()==0){
        	AirCache.save1(addActivity.this,
					"main", dateqian+"Z","#"+time2+"#"+e1.getText()+"#"+e2.getText());
			
			
		
        }
		
		 T.showShort(addActivity.this, "保存成功");
		 finish();
		
	}
});

		
		/*ms[3].setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				if (event.getAction() == MotionEvent.ACTION_DOWN) {
				//	v.setAlpha(0.6f);
					
					T.showLong(addActivity.this, "按住录音 ！松开停止！");
					
					
					ms[3].setImageDrawable(getResources()
							.getDrawable(R.drawable.yuyin5));
					
					
					
					 mRecorder = new MediaRecorder();  
		             mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);  
		             mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);  
		             mRecorder.setOutputFile(FileName);  
		             mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);  
		             try {  
		                 mRecorder.prepare();  
		             } catch (IOException e) {  
		                 Log.e("sdsds", "prepare() failed");  
		             }  
		             mRecorder.start(); 
					
					
					
					
					
					
					

				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					ms[3].setImageDrawable(getResources()
							.getDrawable(R.drawable.yuyin4));
					
					
					
					try {
						 mRecorder.stop();  
			             mRecorder.release();  
			             mRecorder = null; 
			             T.showLong(addActivity.this, "录音停止！");
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					
					
					
				} else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
					ms[3].setImageDrawable(getResources()
							.getDrawable(R.drawable.yuyin4));
					
					
					
					try {
						 mRecorder.stop();  
			             mRecorder.release();  
			             mRecorder = null; 
			             T.showLong(addActivity.this, "录音停止！");
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					
				}
				
				
				
				
				
				
				// 舔不舔
				return false;
			}

		});
		
		
		*/
		
		
		
		//list.add(10);
		//list.add(12);
		//list.add(15);
		//list.add(16);
		
		
	
	
	}
	
	
	class OnChangeListener implements OnClickListener{  
        @Override  
        public void onClick(View v) {  
            // TODO Auto-generated method stub  
            int h=timePick1.getCurrentHour();  
            int m=timePick1.getCurrentMinute();  
            System.out.println("h:"+h+"   m:"+m);  
        }  
    }  
    class TimeListener implements OnTimeChangedListener{  
          
        /** 
         * view 当前选中TimePicker控件 
         *  hourOfDay 当前控件选中TimePicker 的小时 
         * minute 当前选中控件TimePicker  的分钟 
         */  
        @Override  
        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {  
            // TODO Auto-generated method stub  
            //T.showShort(addActivity.this,"h:"+ hourOfDay +" m:"+minute);  
        	hour=hourOfDay;
        	min=minute;
        	
        	String time2;
    		time2=hour+"q"+min+"q";
        	
        	 MainActivity.date1=dateqian+time2;
        	
        	
        }  
          
    }  
	
	
	
	 class startRecordListener implements OnClickListener{  
		  
	        @Override  
	        public void onClick(View v) {  
	            // TODO Auto-generated method stub  
	             mRecorder = new MediaRecorder();  
	             mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);  
	             mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);  
	             mRecorder.setOutputFile("AArilitixing"+MainActivity.date1+"D.mp3");  
	             mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);  
	             try {  
	                 mRecorder.prepare();  
	             } catch (IOException e) {  
	                 Log.e("sdsds", "prepare() failed");  
	             }  
	             mRecorder.start();  
	        }  
	          
	    }  
	 //tingzhi
	 
	 class stopRecordListener implements OnClickListener{  
		  
	        @Override  
	        public void onClick(View v) {  
	            // TODO Auto-generated method stub  
	             mRecorder.stop();  
	             mRecorder.release();  
	             mRecorder = null; 
	             T.showShort(addActivity.this, "luyin停止");
	        }  
	          
	    }  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void opena() {
		// TODO Auto-generated method stub
		for (int i = 0; i < res.length; i++) {
			if (i==0) {
				ObjectAnimator.ofFloat(i1.get(i), "rotation", 0F,360F).setDuration(3000).start();
				ObjectAnimator.ofFloat(i1.get(i), "Alpha", 0.3F,1F).setDuration(1500).start();
			}
			else {
				
			
			ObjectAnimator a1=ObjectAnimator.ofFloat(i1.get(i), "translationY", 0F,-i*250);
			a1.setDuration(1000);
			a1.setInterpolator(new BounceInterpolator());
			a1.setStartDelay(i*400);
			a1.start();
			}
			flag=false;
		}
	}
	
	
	
	
	private void closea() {
		// TODO Auto-generated method stub
		for (int i = 0; i < res.length; i++) {
			if (i==0) {
				ObjectAnimator.ofFloat(i1.get(i), "rotation", 360F,0F).setDuration(3000).start();
				ObjectAnimator.ofFloat(i1.get(i), "Alpha", 0.3F,1F).setDuration(1500).start();
			}
			else {
				
			
			ObjectAnimator a1=ObjectAnimator.ofFloat(i1.get(i), "translationY", -i*250,0F);
			a1.setDuration(1000);
			a1.setInterpolator(new BounceInterpolator());
			a1.setStartDelay(i*400);
			a1.start();
			}
			flag=true;
		}
	}
	
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        switch (requestCode) {
            // 拍照获取图片
            case ImageUtils.GET_IMAGE_BY_CAMERA:
                // uri传入与否影响图片获取方式,以下二选一
                // 方式一,自定义Uri(ImageUtils.imageUriFromCamera),用于保存拍照后图片地址
                if (ImageUtils.imageUriFromCamera != null) {
                    // 可以直接显示图片,或者进行其他处理(如压缩或裁剪等)
//				iv.setImageURI(ImageUtils.imageUriFromCamera);

                    // 对图片进行裁剪
                    ImageUtils.cropImage(this, ImageUtils.imageUriFromCamera);
                    break;
                }

                break;
            // 手机相册获取图片
            case ImageUtils.GET_IMAGE_FROM_PHONE:
                if (data != null && data.getData() != null) {
                    // 可以直接显示图片,或者进行其他处理(如压缩或裁剪等)
                    // iv.setImageURI(data.getData());

                    // 对图片进行裁剪
                    ImageUtils.cropImage(this, data.getData());
                }
                break;
            // 裁剪图片后结果
            case ImageUtils.CROP_IMAGE:
                if (ImageUtils.cropImageUri != null) {
                    // 可以直接显示图片,或者进行其他处理(如压缩等)
                    //iv.setImageURI(ImageUtils.cropImageUri);

                    ContentResolver resolver = getContentResolver();
                    try {
                        mContent = BitmapLMTtools.readStream(resolver.openInputStream(Uri.parse(ImageUtils.cropImageUri.toString())));
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //将字节数组转换为ImageView可调用的Bitmap对象
                    //  b1 = getPicFromBytes(mContent, null);

                    SimpleDateFormat formatter = new SimpleDateFormat("MMdd.HH:mm");
                    Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                    final String time = formatter.format(curDate);
                    
                    if (b1==null) {
                    	b1=BitmapLMTtools.getPicFromBytes(mContent, null);
                    	 Tools.saveMyBitmap("AArilitixing"+MainActivity.date1+"A", b1);
                    	 pic1.setImageBitmap(b1);
					}
                    else if (b2==null) {
                    	b2=BitmapLMTtools.getPicFromBytes(mContent, null);
                    	 Tools.saveMyBitmap("AArilitixing"+MainActivity.date1+"B", b2);
                    	 pic2.setImageBitmap(b2);
					}
                    else if (b3==null) {
                    	b3=BitmapLMTtools.getPicFromBytes(mContent, null);
                    	 Tools.saveMyBitmap("AArilitixing"+MainActivity.date1+"C", b3);
                    	 pic3.setImageBitmap(b3);
					}
                    
                    
                    /*touxiangbitmap = BitmapLMTtools.getPicFromBytes(mContent, null);
                    //  m1.setImageBitmap(b1);
                    Tools.saveMyBitmap("temp1", touxiangbitmap);
                    Fragment4.m1.setImageBitmap(touxiangbitmap);
                    uptouxiang();*/
				/*
				iv.setImageBitmap(b1);
				shangchuan(b1, start1.nowid,time);
				start1.b2=b1;
				start1.touxiang.setImageBitmap(b1);
				Toast.makeText(touxiangscActivity.this,"成功上传头像", Toast.LENGTH_LONG).show();*/
                }
                break;
            default:
                break;
        }
    }
	

}
