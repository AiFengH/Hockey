package com.haf.hockey.util;
import android.util.Log;

import java.nio.Buffer;

import static android.opengl.GLES20.*;
/**
 * Created by Aaron Mt on 2019/4/9.
 */
public class ShaderHelper {
    private static final String TAG = ShaderHelper.class.getSimpleName();

    public static int compileVertexShader(String shaderCode) {
        return compileShader(GL_VERTEX_SHADER, shaderCode);
    }

    public static int compileFragmentShader(String shaderCode) {
        return compileShader(GL_FRAGMENT_SHADER, shaderCode);
    }

    private static int compileShader(int type, String code) {
        final int shaderObjectId = glCreateShader(type);

        if (shaderObjectId == 0) {
            Log.w(TAG, "Could not create new shader " + type);
            return 0;
        }

        glShaderSource(shaderObjectId, code);
        glCompileShader(shaderObjectId);

        final int[] compileStatus = new int[1];
        glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, compileStatus, 0);
        Log.v(TAG, "Results of compiling source:" + "\n" + code + " \n:" + glGetShaderInfoLog(shaderObjectId));
        if (compileStatus[0] == 0) {
            glDeleteShader(shaderObjectId);
            Log.w(TAG, "Compilation of shader failed.");
            return 0;
        }
        return shaderObjectId;
    }

    public static int linkProgram(int vertexShaderId, int fragShaderId) {
        final int programObjectId = glCreateProgram();
        if (programObjectId == 0) {
            Log.w(TAG, "Could not create new program");
            return 0;
        }

        glAttachShader(programObjectId, vertexShaderId);
        glAttachShader(programObjectId, fragShaderId);

        glLinkProgram(programObjectId);

        final int[] linkStatus = new int[1];
        glGetProgramiv(programObjectId, GL_LINK_STATUS, linkStatus, 0);
        Log.v(TAG, "Results of linking program:\n" + glGetProgramInfoLog(programObjectId));

        if (linkStatus[0] == 0) {
            glDeleteProgram(programObjectId);
            Log.w(TAG, "Linking of program failed.");
            return 0;
        }
        return programObjectId;
    }

    public static boolean validateProgram(int programObjectId) {
        glValidateProgram(programObjectId);
        final int[] validateStatus = new int[1];
        glGetProgramiv(programObjectId, GL_VALIDATE_STATUS, validateStatus, 0);
        Log.v(TAG, "Results of validating program: " + validateStatus + "\nLog:" + glGetProgramInfoLog(programObjectId));

        return validateStatus[0] != 0;
    }

    public static void useProgram(int programObjectId) {
        validateProgram(programObjectId);
        glUseProgram(programObjectId);
    }

    public static int getUniformLocation(int program, String uniformName) {
        return glGetUniformLocation(program, uniformName);
    }

    public static int getAttributeLocation(int program, String attributeName) {
        return glGetAttribLocation(program, attributeName);
    }

    public static void writeVertexAttribute(int indx, int size, int type, boolean normalized, int stride, Buffer buffer) {
        glVertexAttribPointer(indx, size, type, normalized, stride, buffer);
        glEnableVertexAttribArray(indx);
    }

}
