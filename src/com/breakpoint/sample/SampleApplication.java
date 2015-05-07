package com.breakpoint.sample;
import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.open.young.soul.breakpoint.application.CommonContext;
import com.open.young.soul.common.framework.logic.BPBaseLogicBuilder;
import com.open.young.soul.common.framework.logic.BPILogic;
import com.open.young.soul.common.util.BPLogger;

/**
 * starfish应用类，进行一些初始化操作，并保存少量全局变量
 * 
 * @author Young
 * 
 */
public class SampleApplication extends Application {
	/**
	 * 日志TAG
	 */
	private static final String TAG = SampleApplication.class.getSimpleName();

	/**
	 * 当前类的静态实例
	 */
	private static SampleApplication sInstance;

	/**
	 * 获取application实例，全局获取，通常用于直接得到上下文，并且为了规避多线程调用，进行同步处理
	 * 
	 * @return sInstance实例
	 */
	public synchronized SampleApplication getInstance() {
		if (null == sInstance) {
			throw new NullPointerException(
					"StarfishApplication has not inited yet.");
		}
		return sInstance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = this;
		BPLogger.init(CommonContext.mIsLogToSD, CommonContext.mIsLogToDDMS,
				Environment.getExternalStorageDirectory()
						+ CommonContext.mLogPath, Log.VERBOSE);
		BPBaseLogicBuilder.init(new BPILogic[0]);
		Log.d(TAG, "onCreate");

	}
}
