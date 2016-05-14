package com.ly.music_player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.text.TextUtils;


import com.ly.music_model.LrcModel;
import com.ly.music_model.MusicModel;

public class GetMusicFile {

	//读取音乐文件
	public ArrayList <MusicModel> query(Context mcontext)
	{
		ArrayList <MusicModel> al=null;
		
		/**
		 * 每一个应用程序程序都拥有一个contentprovider实例进行存储，
		 * 而contentresolver则是用于管理所有程序的contentprovider实例，
		 * 通过contentrescolver可以获得数据，插入数据等
		 * getcontentrescolver()就是获取实例
		 */
		//MediaStore.Audio.Media读取SD卡中所有的音乐文件
		Cursor c=mcontext.getContentResolver().query
				(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
		if(c!=null)
		{
			al=new ArrayList <MusicModel>();
			
			//将歌曲文件中的歌曲名和作曲家和路径读出
			while(c.moveToNext())
			{
				MusicModel model=new MusicModel();
				
				//歌曲的名称 ：MediaStore.Audio.Media.TITL
				String music_name=c.getString(c.getColumnIndex(MediaStore.Audio.Media.TITLE));
				//歌曲的歌手名： MediaStore.Audio.Media.ARTIST
				String singer=c.getString(c.getColumnIndex(MediaStore.Audio.Media.ARTIST));
				//歌曲文件的全路径 ：MediaStore.Audio.Media.DATA
				String path=c.getString(c.getColumnIndex(MediaStore.Audio.Media.DATA));
				
				model.setMusic_name(music_name);
				model.setMusic_singer(singer);
				model.setPath(path);
				al.add(model);
			}
		}
		
		return  al;
	}
	
	//读取音乐的歌词
	public  ArrayList<LrcModel> redlrc(String path)
	{
		ArrayList <LrcModel> lc=new ArrayList <LrcModel>();
		
		//转换音乐的文件格式
		File f=new File(path.replace(".mp3", ".lrc"));
		
		//将歌词按行读入
		try{
			
		FileInputStream fs=new FileInputStream(f);
		InputStreamReader inputstreamreader=new InputStreamReader(fs,"utf-8");
		BufferedReader br=new BufferedReader(inputstreamreader);
		String s="";
		while(null!=(s=br.readLine()))
		{
			//利用Android提供的TextUtils类对于字符串进行处理
			if(!TextUtils.isEmpty(s))
			{
				LrcModel lrc=new LrcModel();
				//读取歌词和时间
				String lylrc=s.replace("[", "");
				String Data_lrc[]=lylrc.split("]");
				
				if(Data_lrc.length>1)
				{
				
					//?
					String time=Data_lrc[0];
					lrc.setTime(lrcdata(time));
					
					String lrc1=Data_lrc[1];
					lrc.setLrc(lrc1);
					
					lc.add(lrc);
					
				}
			}
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return lc;
		}
	
	public int lrcdata(String time)
	{
		time=time.replace(":", "#");
		time=time.replace(".", "#");
		
		String mTime[]=time.split("#");
		
		int mtime=Integer.parseInt(mTime[0]);
		int stime=Integer.parseInt(mTime[1]);
		int mitime=Integer.parseInt(mTime[2]);
		
		int ctime=(mtime*60+stime)*1000+mitime*10;
		return ctime;
		
	}
}
