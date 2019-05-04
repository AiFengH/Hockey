package com.haf.hockey;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.haf.hockey.render.AirHockeyRenderer;
import com.haf.hockey.render.MyRender;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();
    GLSurfaceView mGLSurfaceView = null;
    MyRender mRender = null;
    AirHockeyRenderer mAirHockeyRenderer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        setContentView(mGLSurfaceView);
    }

    private void initData() {
        boolean isSupportGL2 = isSupportGL2();
        if (isSupportGL2) {
            Log.d(TAG, "SupportGL2");
            mGLSurfaceView.setEGLContextClientVersion(2);
//            mRender = new MyRender(this);
            mAirHockeyRenderer = new AirHockeyRenderer(this);
//            mRender.setGLSurfaceView(mGLSurfaceView);
//            mGLSurfaceView.setRenderer(mRender);
            mGLSurfaceView.setRenderer(mAirHockeyRenderer);
        } else {
            Toast.makeText(getApplicationContext(), "This device is not support GL2!!!", Toast.LENGTH_LONG).show();
            return;
        }
    }

    private void initView() {
        mGLSurfaceView = new GLSurfaceView(this);
    }

    public boolean isSupportGL2() {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        Log.d(TAG, "GlEsVersion:" + info.reqGlEsVersion);
        return info.reqGlEsVersion >= 0x20000;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }
}
