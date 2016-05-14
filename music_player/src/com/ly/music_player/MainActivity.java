package com.ly.music_player;

import java.util.ArrayList;

import com.ly.music_model.MusicModel;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends Activity implements OnClickListener{

	ListView list;
	MyAdapter adapter;
	
	ArrayList<MusicModel> alist;
	SeekBar bar;
	
	TextView nec;
	TextView bec,lyric;
	TextView play_now,lc2;
	ImageView play;
	ImageView forward;
	ImageView next;
	ImageView rotate;
	
	MediaPlayer media;
	GetMusicFile scan;
	Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		list=(ListView)findViewById(R.id.listView1);
		scan=new GetMusicFile();
		
		alist=new ArrayList<MusicModel>();
		alist=scan.query(this);
		
		adapter=new MyAdapter(alist, this);
		list.setAdapter(adapter);
		
		bar=(SeekBar)findViewById(R.id.seekBar1);
		
		nec=(TextView)findViewById(R.id.nec);
		bec=(TextView)findViewById(R.id.bec);
		
		play=(ImageView)findViewById(R.id.play);
		forward=(ImageView)findViewById(R.id.forward);
		rotate=(ImageView)findViewById(R.id.rotate);
		next=(ImageView)findViewById(R.id.next);
		
		play_now=(TextView)findViewById(R.id.play_now);
		lc2=(TextView)findViewById(R.id.lc2);
		lyric=(TextView)findViewById(R.id.lyric);
		
		media=new MediaPlayer();
		
		//事件
		play.setOnClickListener(this);
		forward.setOnClickListener(this);
		next.setOnClickListener(this);
		rotate.setOnClickListener(this);
		lyric.setOnClickListener(this);
		
		lc2.setVisibility(View.GONE);
		
		//启动Service服务器
		Intent intent=new Intent();
		intent.setClass(MainActivity.this,MyService.class);
		startService(intent);
		
		//注册广播标签
	}



	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

	class MyBoard extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
