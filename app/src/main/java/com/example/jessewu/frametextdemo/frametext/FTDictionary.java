package com.example.jessewu.frametextdemo.frametext;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.WorkerThread;
import android.util.Log;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 资源字典
 * 将本地资源文件映射为bitmap，并缓存
 */
public class FTDictionary {

    private FTConfiguration configuration;
    private Map<Character, List<Bitmap>> mTextCache = new HashMap<>();

    public void recycle() {
        for (List<Bitmap> bitmaps : mTextCache.values()) {
            if (bitmaps != null) {
                for (Bitmap bitmap : bitmaps) {
                    if (!bitmap.isRecycled()) {
                        bitmap.recycle();
                    }
                }
            }
        }
        mTextCache.clear();
        mTextCache = null;
        configuration = null;
    }

    public void setResources(FTConfiguration configuration) {
        if (configuration != null) {
            mTextCache.clear();
            this.configuration = configuration;
        }
    }

    private static final String TAG = "FTDictionary";
    private List<Bitmap> getBitmaps(final String[] res, Character c) {
        if (res == null || res.length == 0 || configuration == null) {
            return null;
        }
        final List<Bitmap> bitmaps = new ArrayList<>();
        for (String s : res) {
            if (s != null) {
                Bitmap bitmap = BitmapFactory.decodeFile(configuration.getResourcesPath(s));
                Log.d(TAG, configuration.getResourcesPath(s) + "=" + (bitmap != null));
                if (bitmap != null) {
                    bitmaps.add(bitmap);
                }
            }
        }
        if (c != null && bitmaps.size() > 0) {
            mTextCache.put(c, bitmaps);
        }
        return bitmaps;
    }

    public String getBackgroundColor() {
        return configuration != null ? configuration.background : "#FFFFFF";
    }

    public List<Bitmap> getBackgroundFrames() {
        return configuration != null ? getBitmaps(configuration.backgrounds, null) : null;
    }

    @WorkerThread
    public List<Bitmap> getFrameText(char c) {
        // TODO 大小写逻辑兼容模式
        List<Bitmap> cache = mTextCache.get(c);
        if (cache != null) {
            return cache;
        }
        if (configuration == null) {
            return null;
        }

        if (isNumber(c) && configuration.supportNumbers) {
            return queryNumber(c);
        }
        List<Bitmap> bitmaps = null;
        switch (configuration.letterType) {
            case FTConfiguration.BOTH:
                if (Character.isUpperCase(c)) {
                    bitmaps = queryUpperCase(c);
                } else if (Character.isLowerCase(c)) {
                    bitmaps = queryLowerCase(c);
                }
                break;

            case FTConfiguration.ONLY_LOWER_CASE:
                bitmaps = queryLowerCase(c);
                break;

            case FTConfiguration.ONLY_UPPER_CASE:
                bitmaps = queryUpperCase(c);
                break;
        }
        return bitmaps;
    }

    private boolean isNumber(char c) {
        return c == '0'
                || c == '1'
                || c == '2'
                || c == '3'
                || c == '4'
                || c == '5'
                || c == '6'
                || c == '7'
                || c == '8'
                || c == '9';
    }

    private List<Bitmap> queryNumber(char c) {
        String[] res = null;
        switch (c) {
            case '0':
                res = configuration.zero;
                break;
            case '1':
                res = configuration.one;
                break;
            case '2':
                res = configuration.two;
                break;
            case '3':
                res = configuration.three;
                break;
            case '4':
                res = configuration.four;
                break;
            case '5':
                res = configuration.five;
                break;
            case '6':
                res = configuration.six;
                break;
            case '7':
                res = configuration.seven;
                break;
            case '8':
                res = configuration.eight;
                break;
            case '9':
                res = configuration.nine;
                break;
        }
        return getBitmaps(res, c);
    }

    private List<Bitmap> queryLowerCase(char c) {
        if (!Character.isLowerCase(c)) {
            return null;
        }
        String[] res = null;
        switch (c) {
            case 'a':
                res = configuration.a_lower_case;
                break;
            case 'b':
                res = configuration.b_lower_case;
                break;
            case 'c':
                res = configuration.c_lower_case;
                break;
            case 'd':
                res = configuration.d_lower_case;
                break;
            case 'e':
                res = configuration.e_lower_case;
                break;
            case 'f':
                res = configuration.f_lower_case;
                break;
            case 'g':
                res = configuration.g_lower_case;
                break;
            case 'h':
                res = configuration.h_lower_case;
                break;
            case 'i':
                res = configuration.i_lower_case;
                break;
            case 'j':
                res = configuration.j_lower_case;
                break;
            case 'k':
                res = configuration.k_lower_case;
                break;
            case 'l':
                res = configuration.l_lower_case;
                break;
            case 'm':
                res = configuration.m_lower_case;
                break;
            case 'n':
                res = configuration.n_lower_case;
                break;
            case 'o':
                res = configuration.o_lower_case;
                break;
            case 'p':
                res = configuration.p_lower_case;
                break;
            case 'q':
                res = configuration.q_lower_case;
                break;
            case 'r':
                res = configuration.r_lower_case;
                break;
            case 's':
                res = configuration.s_lower_case;
                break;
            case 't':
                res = configuration.t_lower_case;
                break;
            case 'u':
                res = configuration.u_lower_case;
                break;
            case 'v':
                res = configuration.v_lower_case;
                break;
            case 'w':
                res = configuration.w_lower_case;
                break;
            case 'x':
                res = configuration.x_lower_case;
                break;
            case 'y':
                res = configuration.y_lower_case;
                break;
            case 'z':
                res = configuration.z_lower_case;
                break;
        }
        return getBitmaps(res, c);
    }

    private List<Bitmap> queryUpperCase(char c) {
        if (!Character.isUpperCase(c)) {
            return null;
        }
        String[] res = null;
        switch (c) {
            case 'A':
                res = configuration.a;
                break;
            case 'B':
                res = configuration.b;
                break;
            case 'C':
                res = configuration.c;
                break;
            case 'D':
                res = configuration.d;
                break;
            case 'E':
                res = configuration.e;
                break;
            case 'F':
                res = configuration.f;
                break;
            case 'G':
                res = configuration.g;
                break;
            case 'H':
                res = configuration.h;
                break;
            case 'I':
                res = configuration.i;
                break;
            case 'J':
                res = configuration.j;
                break;
            case 'K':
                res = configuration.k;
                break;
            case 'L':
                res = configuration.l;
                break;
            case 'M':
                res = configuration.m;
                break;
            case 'N':
                res = configuration.n;
                break;
            case 'O':
                res = configuration.o;
                break;
            case 'P':
                res = configuration.p;
                break;
            case 'Q':
                res = configuration.q;
                break;
            case 'R':
                res = configuration.r;
                break;
            case 'S':
                res = configuration.s;
                break;
            case 'T':
                res = configuration.t;
                break;
            case 'U':
                res = configuration.u;
                break;
            case 'V':
                res = configuration.v;
                break;
            case 'W':
                res = configuration.w;
                break;
            case 'X':
                res = configuration.x;
                break;
            case 'Y':
                res = configuration.y;
                break;
            case 'Z':
                res = configuration.z;
                break;
        }
        return getBitmaps(res, c);
    }
}
