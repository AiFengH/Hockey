package com.haf.hockey.object;

import com.haf.hockey.util.Constants;
import com.haf.hockey.program.TextureShaderProgram;
import static android.opengl.GLES20.*;

/**
 * Created by Aaron Mt on 2019/4/22.
 */
public class TextureTable {

    private static final int POSITION_COMPONENT_COUNT = 2;//一个顶点有两个分量表示
    private static final int TEXTURE_COMPONENT_COUNT = 2;//一个颜色有三个分量表示
    private static final int STRIDE = (POSITION_COMPONENT_COUNT + TEXTURE_COMPONENT_COUNT) * Constants.BYTE_PER_FLOAT;

    private static final float[] VERTEX_DATA = {
            //x y s t

            //Triangle Fan
            0f, 0f, 0.5f, 0.5f,
            -0.5f, -0.8f, 0f, 0.9f,
            0.5f, -0.8f, 1f, 0.9f,
            0.5f, 0.8f, 1f, 0.1f,
            -0.5f, 0.8f, 0f, 0.1f,
            -0.5f, -0.8f, 0f, 0.9f
    };

    private VertexArray mVertexArray;

    public TextureTable() {
        mVertexArray = new VertexArray(VERTEX_DATA);
    }

    public void bindData(TextureShaderProgram textureShaderProgram) {
        mVertexArray.setVertexAttribPointer(0, textureShaderProgram.getPositionAttributeLocation(), POSITION_COMPONENT_COUNT, STRIDE);

        mVertexArray.setVertexAttribPointer(POSITION_COMPONENT_COUNT, textureShaderProgram.getTextureCoordinatesAttributeLocation(), TEXTURE_COMPONENT_COUNT, STRIDE);
    }

    public void draw() {
        glDrawArrays(GL_TRIANGLE_FAN, 0, 6);
    }
}
