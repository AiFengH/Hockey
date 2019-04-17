package com.haf.hockey.object;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;

import com.haf.hockey.util.ShaderHelper;
import com.haf.hockey.util.TextResourceReader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import static android.opengl.GLES20.*;
/**
 * Created by Aaron Mt on 2019/3/17.
 */
public class Table {

    private static final int POSITION_COMPONENT_COUNT = 2;//一个顶点有两个分量表示
    private static final int COLOR_COMPONENT_COUNT = 3;//一个颜色有三个分量表示
    private static final int BYTE_PER_FLOAT = 4;
    private static final int STRIDE = (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT) * BYTE_PER_FLOAT;
    private static final String U_COLOR = "u_Color";
    private static final String A_COLOR = "a_Color";
    private static final String A_POSITION = "a_Position";
    private static final String U_MATRIX = "u_Matrix";
    public float[] mVertices = {
//            //triangle 11
//            -4.5f, -7f,
//            4.5f, -7f,
//            4.5f, 7f,
//
//            //triangle 2
//            -4.5f, -7f,
//            4.5f, 7f,
//            -4.5f, 7f,

            //triangle fan
            0f, 0f, 1f, 1f, 1f,
            -4.5f, -7f, 0.7f, 0.7f, 0.7f,
            4.5f, -7f, 0.7f, 0.7f, 0.7f,
            4.5f, 7f, 0.7f, 0.7f, 0.7f,
            -4.5f, 7f, 0.7f, 0.7f, 0.7f,
            -4.5f, -7f, 0.7f, 0.7f, 0.7f,

            //line 1
            -4.5f, 0f, 1f, 0f, 0f,
            4.5f, 0f, 1f, 0f, 0f,

            //mallets point
            0f, -5f, 0f, 0f ,1f,
            0f, 5f, 1f, 0f, 0f

    };

    private int mScreenWidth = 0;
    private int mScreenHeight = 0;
    private final float[] mProjectionMatrix = new float[16];
    private FloatBuffer mVertexBuffer = null;

    private String mVertexShaderSource = "";
    private String mFragmentShaderSource = "";

    private int mVertexShader = 0;
    private int mFragShader = 0;

    private int mProgram = 0;

    private int uColorLocation = 0;
    private int aPositionLocation = 0;
    private int aColorLocation = 0;
    private int uMatrixLocation = 0;
    private Context mConText = null;

    public void setScreenWidth(int screenWidth) {
        this.mScreenWidth = screenWidth;
    }

    public void setScreenHeight(int screenHeight) {
        this.mScreenHeight = screenHeight;
    }

    public String getFragmentShaderSource() {
        return mFragmentShaderSource;
    }

    public float[] getVertices() {
        return mVertices;
    }

    public FloatBuffer getVertexBuffer() {
        return mVertexBuffer;
    }

    public String getVertexShaderSource() {
        return mVertexShaderSource;
    }

    public void validateProgram() {
    }

    public Table(Context context) {
        mConText = context;
        mVertexBuffer = ByteBuffer.allocateDirect(mVertices.length * BYTE_PER_FLOAT).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mVertexBuffer.put(mVertices);

        mVertexShaderSource = TextResourceReader.readTextFromResource(context, "simple_vertex_shader.glsl");
        mFragmentShaderSource = TextResourceReader.readTextFromResource(context, "simple_fragment_shader.glsl");

        mVertexShader = ShaderHelper.compileVertexShader(mVertexShaderSource);
        mFragShader = ShaderHelper.compileFragmentShader(mFragmentShaderSource);

        mProgram = ShaderHelper.linkProgram(mVertexShader, mFragShader);

        ShaderHelper.useProgram(mProgram);

        uColorLocation = ShaderHelper.getUniformLocation(mProgram, U_COLOR);
        aPositionLocation = ShaderHelper.getAttributeLocation(mProgram, A_POSITION);
        aColorLocation = ShaderHelper.getAttributeLocation(mProgram, A_COLOR);
        uMatrixLocation = ShaderHelper.getAttributeLocation(mProgram, U_MATRIX);

        mVertexBuffer.position(0);
//        ShaderHelper.writeVertexAttribute(aPositionLocation, POSITION_COMPONENT_COUNT, GLES20.GL_FLOAT, false, 0, mVertexBuffer);
        ShaderHelper.writeVertexAttribute(aPositionLocation, POSITION_COMPONENT_COUNT, GLES20.GL_FLOAT, false, STRIDE, mVertexBuffer);

        mVertexBuffer.position(POSITION_COMPONENT_COUNT);
        ShaderHelper.writeVertexAttribute(aColorLocation, COLOR_COMPONENT_COUNT, GLES20.GL_FLOAT, false, STRIDE, mVertexBuffer);

        final float aspectRatio = mScreenWidth > mScreenHeight ? (float) mScreenWidth / (float) mScreenHeight : (float) mScreenHeight / (float) mScreenWidth;
        if (mScreenWidth > mScreenHeight) {
            Matrix.orthoM(mProjectionMatrix, 0, -aspectRatio, aspectRatio, -1f, 1f, -1f, 1f);
        } else {
            Matrix.orthoM(mProjectionMatrix, 0, -1f, 1f, -aspectRatio, aspectRatio, -1f, 1f);
        }
    }

    public void draw() {
//        glUniform4f(uColorLocation, 1f, 1f, 1f, 1f);
//        glDrawArrays(GL_TRIANGLES, 0, 6);
        glUniformMatrix4fv(uMatrixLocation, 1, false, mProjectionMatrix, 0);
        glDrawArrays(GL_TRIANGLE_FAN, 0, 6);

//        glUniform4f(uColorLocation, 1f, 0f, 0f, 1f);
        glDrawArrays(GL_LINES, 6, 2);

//        glUniform4f(uColorLocation, 0f, 0f, 1f, 1f);
        glDrawArrays(GL_POINTS, 8, 1);

//        glUniform4f(uColorLocation, 1f, 0f, 0f, 1f);
        glDrawArrays(GL_POINTS, 9, 1);
    }

}
