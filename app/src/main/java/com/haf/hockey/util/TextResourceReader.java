package com.haf.hockey.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Aaron Mt on 2019/4/9.
 */
public class TextResourceReader {
    /**
     *
     * @param context
     * @param fileName "mprespons.txt"
     * @return
     */
    public static String readTextFromResource(Context context, String fileName) {
        InputStream is = null;
        String text = null;
        try {
            is = context.getResources().getAssets().open(fileName);
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            text = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return text;
    }
}
