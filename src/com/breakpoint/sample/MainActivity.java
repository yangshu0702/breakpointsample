package com.breakpoint.sample;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.open.young.soul.breakpoint.widgets.floatwindow.BPFloatManager;
import com.open.young.soul.common.framework.ui.BaseFragmentActivity;
import com.open.young.soul.common.net.BPHttpCallback;
import com.open.young.soul.common.net.BPHttpClient;
import com.open.young.soul.common.util.BPLogger;

public class MainActivity extends BaseFragmentActivity {
	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void initLogics() {

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        winParams.flags |= bits;
        win.setAttributes(winParams);
		Class<? extends Window> clazz = getWindow().getClass();
		try {
			int darkModeFlag = 0;
			Class<?> layoutParams = Class
					.forName("android.view.MiuiWindowManager$LayoutParams");
			Field field = layoutParams
					.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
			darkModeFlag = field.getInt(layoutParams);
			Method extraFlagField = clazz.getMethod("setExtraFlags", int.class,
					int.class);
			extraFlagField.invoke(getWindow(), true ? darkModeFlag : 0,
					darkModeFlag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setContentView(R.layout.main_layout);
		final TextView view = (TextView) findViewById(R.id.btn_send2);
		view.post(new Runnable() {

			@Override
			public void run() {
				view.setText(TextUtils.ellipsize("hahhahahhahaahahahha",
						view.getPaint(), view.getMeasuredWidth() - 10,
						TextUtils.TruncateAt.END));
			}
		});
		findViewById(R.id.btn_send).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				BPHttpClient.instance().get("http://www.baidu.com", null,
						new BPHttpCallback() {

							@Override
							public void onResponse(String response) {
								BPLogger.i(TAG, "onResponse response = "
										+ response);
							}

							@Override
							public void onError(int errorCode) {
								BPLogger.i(TAG, "onError errorcode = "
										+ errorCode);
							}
						});
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		BPFloatManager.getInstance().show(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		BPFloatManager.getInstance().hide(this);
	}

}
