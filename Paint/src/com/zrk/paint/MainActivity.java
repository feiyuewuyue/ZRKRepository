package com.zrk.paint;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Bitmap bitmap=null;
	private ImageView iv=null;
	private Canvas canvas=null;  //画布
	private Paint paint=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv=(ImageView) findViewById(R.id.iv);
		paint=new Paint();
		paint.setStrokeWidth(5);
		paint.setColor(Color.GREEN);
		bitmap=Bitmap.createBitmap(720,1038,Bitmap.Config.ARGB_8888);
		canvas=new Canvas(bitmap);
		System.out.println(iv.getWidth());
		System.out.println(iv.getHeight());
		canvas.drawColor(Color.WHITE);
				
		//知道用户手指
		iv.setOnTouchListener(new OnTouchListener() {
			int startX;
			int startY;
			//int entX;
			//int endY;
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					

					startX=(int) event.getX();
					startY=(int) event.getY();
					System.out.println("手指按下");
					break;

				case MotionEvent.ACTION_MOVE:
					int newX=(int) event.getX();
					int newY=(int) event.getY();
					canvas.drawLine(startX, startY, newX, newY, paint);
					startX=(int) event.getX();
					startY=(int) event.getY();
					System.out.println("手指一动");
					iv.setImageBitmap(bitmap);
					break;
					
				case MotionEvent.ACTION_POINTER_UP:
					
					
					
					break;
				}
				
				return true;
			}
		});
		
	}

	public void click(View view){
		try{
		File file=new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis()+".jpg");
		
		FileOutputStream stream=null;
		
			stream = new FileOutputStream(file);
			bitmap.compress(CompressFormat.JPEG, 100, stream);
			stream.close();
			Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
			Intent intent=new Intent();
			intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
			intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
			sendBroadcast(intent);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
		}
		
		
		
		
	}

}
