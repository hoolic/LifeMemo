package com.dsw.calendarview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BitmapLMTtools {
	public static void saveMyBitmap(String bitName, Bitmap mBitmap, String name) {
		createSDCardDir(bitName);
		File f = new File("/sdcard/"+bitName+"/"+name+ ".png");

		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void createSDCardDir(String s) {
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			//create a folder object = the directory of the external storage
			File sdcardDir = Environment.getExternalStorageDirectory();
			// got the path of the folder
			String path = sdcardDir.getPath() + "/" + s;
			File path1 = new File(path);
			//if not exist, create it when the app first start
			if (!path1.exists()) {
				path1.mkdirs();
				// setTitle("paht ok,path:"+path);
			}
		} else {
			// setTitle("false");
			return;

		}

	}
	
	 public static byte[] readStream(InputStream inStream) throws Exception { 
	        byte[] buffer = new byte[1024]; 
	        int len = -1; 
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream(); 
	        while ((len = inStream.read(buffer)) != -1) { 
	                 outStream.write(buffer, 0, len); 
	        } 
	        byte[] data = outStream.toByteArray(); 
	        outStream.close(); 
	        inStream.close(); 
	        return data; 

	   }  


	    public static Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts) { 
	        if (bytes != null) 
	            if (opts != null) 
	                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,opts); 
	            else 
	                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length); 
	        return null; 
	    } 

}
