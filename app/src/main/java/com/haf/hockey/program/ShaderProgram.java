package com.haf.hockey.program;

import android.content.Context;

import com.haf.hockey.util.ShaderHelper;
import com.haf.hockey.util.TextResourceReader;

import static android.opengl.GLES20.*;

/**
 * Created by Aaron Mt on 2019/4/26.
 */
public class ShaderProgram {
    //Uniform constants
    protected static final String U_MATRIX = "u_Matrix";
    protected static final String U_TEXTURE_UNIT = "u_TextureUnit";

    //Attribute constants
    protected static final String A_POSITION = "a_Position";
    protected static final String A_COLOR = "a_Color";
    protected static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";

    protected int mProgram = 0;

    public ShaderProgram(Context context, String vertexShaderResourceId, String fragmentShaderResourceId) {
        mProgram = ShaderHelper.buildProgram(TextResourceReader.readTextFromResource(context, vertexShaderResourceId)
                , TextResourceReader.readTextFromResource(context, fragmentShaderResourceId));
    }

    public void useProgram() {
        glUseProgram(mProgram);
    }
}
