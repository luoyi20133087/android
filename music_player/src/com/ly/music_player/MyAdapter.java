package com.ly.music_player;

import java.util.ArrayList;

import com.ly.music_model.MusicModel;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	ArrayList<MusicModel> alist;
	Context mContext;
	
	
	public MyAdapter(ArrayList<MusicModel> alist,Context mContext)
	{
		this.alist=alist;
		this.mContext=mContext;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return alist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return alist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	//该方法返回的值将作为列表框
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		viewhodler viewhodler = null;
		
		if(convertView==null)
		{
			viewhodler=new viewhodler();
			//加入布局
			convertView=LayoutInflater.from(mContext).inflate(R.layout.layout_list,null);
			
			viewhodler.text_gq=(TextView)convertView.findViewById(R.id.text1);
			viewhodler.text_gs=(TextView)convertView.findViewById(R.id.text2);
			viewhodler.img=(ImageView)convertView.findViewById(R.id.image11);
		}else{
			viewhodler=(viewhodler) convertView.getTag();
		}
		
		
		viewhodler.text_gq.setText(""+alist.get(position).getMusic_name());
		viewhodler.text_gs.setText(""+alist.get(position).getMusic_singer());
		viewhodler.img.setImageResource(R.drawable.dianji_11);
		return  convertView;
	}

	//定义一个显示ListView项的类
	static class viewhodler{
		TextView text_gq,text_gs;
		ImageView img;
	}
}
