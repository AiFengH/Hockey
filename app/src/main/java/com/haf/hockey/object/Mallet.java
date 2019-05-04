package com.haf.hockey.object;

import com.haf.hockey.program.ColorShaderProgram;
import com.haf.hockey.util.Constants;

import static android.opengl.GLES20.*;

/**
 * Created by Aaron Mt on 2019/4/26.
 */
public class Mallet {
    private static final int POSITION_COMPONENT_COUNT = 2;//一个顶点有两个分量表示
    private static final int COLOR_COMPONENT_COUNT = 3;//一个颜色有三个分量表示
    private static final int STRIDE = (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT) * Constants.BYTE_PER_FLOAT;

    private static final float[] VERTEX_DATA = {
            0f, -0.4f, 0f, 0f, 1f,
            0f, 0.4f, 1f, 0f, 0f
    };

    private VertexArray mVertexArray;

    public Mallet() {
        mVertexArray = new VertexArray(VERTEX_DATA);
    }

    public void bindData(ColorShaderProgram colorShaderProgram) {
        mVertexArray.setVertexAttribPointer(0, colorShaderProgram.getPositionAttributeLocation(), POSITION_COMPONENT_COUNT, STRIDE);

        mVertexArray.setVertexAttribPointer(POSITION_COMPONENT_COUNT, colorShaderProgram.getColorAttributeLocation(), COLOR_COMPONENT_COUNT, STRIDE);
    }

    public void draw() {
        glDrawArrays(GL_POINTS, 0, 2);
    }
}
