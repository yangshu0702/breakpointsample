package com.breakpoint.sample;
import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.open.young.soul.breakpoint.application.CommonContext;
import com.open.young.soul.common.framework.logic.BPBaseLogicBuilder;
import com.open.young.soul.common.framework.logic.BPILogic;
import com.open.young.soul.common.util.BPLogger;

/**
 * starfishӦ���࣬����һЩ��ʼ������������������ȫ�ֱ���
 * 
 * @author Young
 * 
 */
public class SampleApplication extends Application {
	/**
	 * ��־TAG
	 */
	private static final String TAG = SampleApplication.class.getSimpleName();

	/**
	 * ��ǰ��ľ�̬ʵ��
	 */
	private static SampleApplication sInstance;

	/**
	 * ��ȡapplicationʵ����ȫ�ֻ�ȡ��ͨ������ֱ�ӵõ������ģ�����Ϊ�˹�ܶ��̵߳��ã�����ͬ������
	 * 
	 * @return sInstanceʵ��
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
