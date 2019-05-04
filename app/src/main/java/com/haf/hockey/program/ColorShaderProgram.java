package com.haf.hockey.program;

import android.content.Context;
import static android.opengl.GLES20.*;

/**
 * Created by Aaron Mt on 2019/4/26.
 */
public class ColorShaderProgram extends ShaderProgram{

    private int mUMatrixLocation = 0;
    private int mAPositionLocation = 0;
    private int mAColorLocation = 0;

    public ColorShaderProgram(Context context) {
        super(context, "simple_vertex_shader.glsl", "simple_fragment_shader.glsl");
        mUMatrixLocation = glGetUniformLocation(mProgram, U_MATRIX);
        mAPositionLocation = glGetAttribLocation(mProgram, A_POSITION);
        mAColorLocation = glGetAttribLocation(mProgram, A_COLOR);
    }

    public void setUniforms(float[] matrix) {
        glUniformMatrix4fv(mUMatrixLocation, 1, false, matrix, 0);
    }

    public int getPositionAttributeLocation() {
        return mAPositionLocation;
    }

    public int getColorAttributeLocation() {
        return mAColorLocation;
    }
}
