package com.haf.hockey.render;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import com.haf.hockey.R;
import com.haf.hockey.object.Mallet;
import com.haf.hockey.object.TextureTable;
import com.haf.hockey.program.ColorShaderProgram;
import com.haf.hockey.program.TextureShaderProgram;
import com.haf.hockey.util.TextureHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.*;

/**
 * Created by Aaron Mt on 2019/4/28.
 */
public class AirHockeyRenderer implements GLSurfaceView.Renderer{
    private static final String TAG = AirHockeyRenderer.class.getSimpleName();
    private Context mContext = null;

    private float[] mProjectionMatrix = new float[16];
    private float[] mModelMatrix = new float[16];

    private TextureTable mTextureTable = null;
    private Mallet mMallet = null;

    private TextureShaderProgram mTextureShaderProgram = null;
    private ColorShaderProgram mColorShaderProgram = null;

    private int mTextureId = 0;

    public AirHockeyRenderer(Context context) {
        this.mContext = context;
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glClearColor(1f, 1f, 1f, 0f);
        mTextureTable = new TextureTable();
        mMallet = new Mallet();

        mTextureShaderProgram = new TextureShaderProgram(mContext);
        mColorShaderProgram = new ColorShaderProgram(mContext);

        mTextureId = TextureHelper.loadTexture(mContext, R.mipmap.table_v);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.d(TAG, "AirHockeyRenderer onSurfaceChanged");
        Log.d(TAG, "w:" + width + " h:" + height);
        glViewport(0, 0, width, height);
        orthoM(height, width);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        glClear(GL_COLOR_BUFFER_BIT);

        mTextureShaderProgram.useProgram();
        mTextureShaderProgram.setUniforms(mProjectionMatrix, mTextureId);
        mTextureTable.bindData(mTextureShaderProgram);
        mTextureTable.draw();

        mColorShaderProgram.useProgram();
        mColorShaderProgram.setUniforms(mProjectionMatrix);
        mMallet.bindData(mColorShaderProgram);
        mMallet.draw();
    }
    public void orthoM(int screenHeight, int screenWidth) {
        final float aspectRatio = screenWidth > screenHeight ? (float) screenWidth / (float) screenHeight : (float) screenHeight / (float) screenWidth;
        if (screenWidth > screenHeight) {
            Matrix.orthoM(mProjectionMatrix, 0, -aspectRatio, aspectRatio, -1f, 1f, -1f, 1f);
        } else {
            Matrix.orthoM(mProjectionMatrix, 0, -1f, 1f, -aspectRatio, aspectRatio, -1f, 1f);
        }
    }

}
