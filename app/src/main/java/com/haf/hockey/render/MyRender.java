package com.haf.hockey.render;

import static android.opengl.GLES20.*;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.haf.hockey.object.Table;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Aaron Mt on 2019/3/17.
 */
public class MyRender implements GLSurfaceView.Renderer{
    private static final String TAG = MyRender.class.getSimpleName();
    GLSurfaceView mGLSurfaceView = null;
    private Context mContext = null;
    private Table mTable = null;

    public MyRender(Context context) {
        mContext = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.d(TAG, "MyRender onSurfaceCreated");
        glClearColor(1.0f, 0f, 0f, 0f);
        mTable = new Table(mContext);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.d(TAG, "MyRender onSurfaceChanged");
        Log.d(TAG, "w:" + width + " h:" + height);
        if (width > height) {
            glViewport(0, 0, width, height);
        } else {
            glViewport(0, 0, width, height);
        }
        mTable.orthoM(height, width);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.d(TAG, "MyRender onDrawFrame");
        glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
        mTable.draw();
    }

    public void setGLSurfaceView(GLSurfaceView glSurfaceView) {
        this.mGLSurfaceView = glSurfaceView;
    }
}
