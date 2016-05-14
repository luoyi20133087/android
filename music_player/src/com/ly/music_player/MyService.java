package com.ly.music_player;

import java.util.ArrayList;

import com.ly.music_model.*;


import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;

public class MyService extends Service {

	MediaPlayer mediaplay;
	GetMusicFile scan; 
	ArrayList<MusicModel> alist;
	ArrayList<LrcModel> lrclist;
	 MyBroadMusic myBoard;
	
	Handler handler;
	Handler lrchandler;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		alist=new ArrayList<MusicModel>();
		lrclist=new ArrayList<LrcModel>();
		
		mediaplay=new MediaPlayer();
		scan=new GetMusicFile();
		
		handler=new Handler();
		lrchandler=new Handler();
		
		alist=scan.query(this);
			
		//对BroadcastReceiver进行注册
		register();
		
	}

	public class MyBroadMusic extends BroadcastReceiver
	{

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
			
		}
		}
	
	//注册广播
	public void register()
	{
		IntentFilter filter=new IntentFilter();
		
		filter.addAction("");
		filter.addAction("");
		registerReceiver(myBoard,filter);
	}
	
}
