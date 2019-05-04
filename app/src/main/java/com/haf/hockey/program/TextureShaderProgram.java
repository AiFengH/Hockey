package com.haf.hockey.program;

import android.content.Context;
import static android.opengl.GLES20.*;

/**
 * Created by Aaron Mt on 2019/4/26.
 */
public class TextureShaderProgram extends ShaderProgram{

    private int mUMatrixLocation = 0;
    private int mUTextureUnitLocation = 0;

    private int mAPositionLocation = 0;
    private int mATextureCoordinates = 0;
    public TextureShaderProgram(Context context) {
        super(context, "texture_vertex_shader.glsl", "texture_fragment_shader.glsl");
        mUMatrixLocation = glGetUniformLocation(mProgram, U_MATRIX);
        mUTextureUnitLocation = glGetUniformLocation(mProgram, U_TEXTURE_UNIT);

        mAPositionLocation = glGetAttribLocation(mProgram, A_POSITION);
        mATextureCoordinates = glGetAttribLocation(mProgram, A_TEXTURE_COORDINATES);
    }

    /**
     * 传递矩阵给uniform（间接通过纹理单元将纹理传递给着色器；一个GPU只能同时绘制数量有限的纹理）
     * @param matrix
     * @param textureId
     */
    public void setUniforms(float[] matrix, int textureId) {
        glUniformMatrix4fv(mUMatrixLocation, 1, false, matrix, 0);//传递矩阵给uniform
        glActiveTexture(GL_TEXTURE0);//激活纹理单元
        glBindTexture(GL_TEXTURE_2D, textureId);//绑定激活的纹理单元
        glUniform1i(mUTextureUnitLocation, 0);//将纹理单元传递给着色器
    }

    public int getPositionAttributeLocation() {
        return mAPositionLocation;
    }

    public int getTextureCoordinatesAttributeLocation() {
        return mATextureCoordinates;
    }
}
