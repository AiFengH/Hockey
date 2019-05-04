package com.haf.hockey.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.util.Log;

import static android.opengl.GLES20.*;

/**
 * Created by Aaron Mt on 2019/4/20.
 */
public class TextureHelper {

    private static final String TAG = TextureHelper.class.getSimpleName();
    public static int loadTexture(Context context, int resourceId) {
        final int[] textureObjectIds = new int[1];
        glGenTextures(1, textureObjectIds, 0);

        if (textureObjectIds[0] == 0) {
            Log.w(TAG, "Could not generate a new Opengl texture Object.");
            return 0;
        }

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);

        if (bitmap == null) {
            Log.w(TAG, "Resource ID " + resourceId + " could not be decoded!");

            glDeleteTextures(1, textureObjectIds, 0);
            return 0;
        }
        glBindTexture(GL_TEXTURE_2D, textureObjectIds[0]);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);//缩小 三线性
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);//放大 双线性

        GLUtils.texImage2D(GL_TEXTURE_2D, 0, bitmap, 0); //告诉opengl读入bitmap数据，并把它复制到当前绑定的纹理对象
        bitmap.recycle();
        glGenerateMipmap(GL_TEXTURE_2D);//生成MIP贴图
        glBindTexture(GL_TEXTURE_2D, 0);//解除纹理绑定

        return textureObjectIds[0];
    }
}
