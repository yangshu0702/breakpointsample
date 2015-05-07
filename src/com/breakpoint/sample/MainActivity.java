package com.breakpoint.sample;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

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
		setContentView(R.layout.main_layout);
		findViewById(R.id.btn_send).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				BPHttpClient.instance().get("http://www.baidu.com", null,
						new BPHttpCallback() {

							@Override
							public void onResponse(String response) {
								BPLogger.i(TAG, "onResponse response = " + response);
							}

							@Override
							public void onError(int errorCode) {
								BPLogger.i(TAG, "onError errorcode = " + errorCode);
							}
						});
			}
		});
	}

}
