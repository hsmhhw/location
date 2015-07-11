package com.dwexample.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MenuImageAdapter extends BaseAdapter {
	   
	private ImageView [] menuImg;  //保存所以要填充的图片组件
	private Context context = null;  //上下文对象
	private int selectedMenuImg ;   //保存选中索引图片
	
	public MenuImageAdapter(Context context,int imgIds[],int width, int height,
			int selectedMenuImg ){
		this.context = context;
		this.selectedMenuImg = selectedMenuImg;
		this.menuImg = new ImageView[imgIds.length]; // 开辟新的数组
		for (int x = 0; x < imgIds.length; x++) {
			this.menuImg[x] = new ImageView(this.context); // 实例化ImageView
			this.menuImg[x].setLayoutParams(new GridView.LayoutParams(width,
					height));	// 设置图片的大小
			this.menuImg[x].setAdjustViewBounds(false); // 不调整边界显示
			this.menuImg[x].setPadding(3, 3, 3, 3);// 设置间距
			this.menuImg[x].setImageResource(imgIds[x]);// 设置显示图片
		}
			
	}

	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return this.menuImg.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO 自动生成的方法存根
		return this.menuImg[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		ImageView imageView = null;
		if(convertView == null){
			imageView = this.menuImg[position];
		}else
			{
			imageView = (ImageView) convertView;
			
				}
		return imageView;
	}
	
	public void setFocus(int selId){ //设置选中
		for(int x = 0; x < this.menuImg.length; x++){
			if(x != selId){  //如果没有选中
				this.menuImg[x].setBackgroundResource(0);  //不设置背景
			}
		}
		this.menuImg[selId].setBackgroundResource(this.selectedMenuImg);
	}

}
