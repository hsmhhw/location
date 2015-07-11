package com.dwexample.location;

import com.dwexample.Adapter.MenuImageAdapter;


import android.app.Activity;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;


public class mainActivity extends ActivityGroup {
	private GridView gridviewToolbar;  //工具栏菜单
	private MenuImageAdapter menu = null;
	private LinearLayout content = null;
	private int menu_img[] = new int[] { R.drawable.menu_main,
			R.drawable.menu_qiandao, R.drawable.menu_talk, R.drawable.menu_more,
			R.drawable.menu_exit };
	private int width = 0 ;
	private int height = 0 ;  //求出高度 宽度 定位显示
	private Intent intent;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
		super.setContentView(R.layout.main);
		this.gridviewToolbar = (GridView) super.findViewById(R.id.gridviewbar);
		this.content = (LinearLayout) super.findViewById(R.id.content);

		this.gridviewToolbar.setNumColumns(this.menu_img.length); // 求出可以保存的个数
		this.gridviewToolbar.setSelector(new ColorDrawable(Color.TRANSPARENT));
		this.gridviewToolbar.setGravity(Gravity.CENTER);
		this.gridviewToolbar.setVerticalSpacing(0);

		this.width = super.getWindowManager().getDefaultDisplay().getWidth()
				/ this.menu_img.length;
		this.height = super.getWindowManager().getDefaultDisplay().getHeight() / 8;
		this.menu = new MenuImageAdapter(this, this.menu_img, this.width,
				this.height, R.drawable.menu_selected);
		this.gridviewToolbar.setAdapter(this.menu);
		this.switchActivity(0); // 第一个被选中
		this.gridviewToolbar.setOnItemClickListener(new OnItemClickListenerImpl());

		
	}
	private class OnItemClickListenerImpl implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			mainActivity.this.switchActivity(position);
		}
	}
	private void switchActivity(int id) {
		// TODO 自动生成的方法存根
		this.menu.setFocus(id); // 设置选中图片的背景
		this.content.removeAllViews(); // 删除所有的内容
		switch (id) {
		case 0:
			this.intent = new Intent(mainActivity.this, CamActivity.class);
			break;
		case 1:
			this.intent = new Intent(mainActivity.this, LocationDemo.class);
			break;
		case 2:
			this.intent = new Intent(mainActivity.this, com.dwexample.Guide.ViewPagerActivity.class);
			break;
		case 3:
			this.intent = new Intent(mainActivity.this, MyActivity.class);
			break;
		case 4:
			this.exitDialog() ;
			return;
		}
		this.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		Window subActivity = super.getLocalActivityManager().startActivity(
				"subActivity", this.intent);
		this.content.addView(subActivity.getDecorView(),
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
	}
	private void exitDialog() {
		Dialog dialog = new AlertDialog.Builder(this).setIcon(R.drawable.tb)
				.setTitle("程序退出？ ").setMessage("您确定要退出本程序吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						mainActivity.this.finish() ;
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						mainActivity.this.switchActivity(0);
					}
				}).create();

		dialog.show();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			this.exitDialog() ;
		}
		return false ;
	}
}
	