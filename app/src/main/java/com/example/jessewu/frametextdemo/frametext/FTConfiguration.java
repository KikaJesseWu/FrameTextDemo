package com.example.jessewu.frametextdemo.frametext;

import android.os.Environment;

/**
 * FrameText 配置文件，包含资源路径，配置信息
 */
public class FTConfiguration {

    private static final String ROOT_PATH = Environment.getExternalStorageDirectory().getPath() + "/FrameText/";

    public static final String FOLDER_NAME_UPPER_CASE = "upper_case/";
    public static final String FOLDER_NAME_LOWER_CASE = "lower_case/";
    public static final String FOLDER_NAME_BACKGROUND = "background/";
    public static final String FOLDER_NAME_NUMBER = "number/";

    public static final int BOTH = 0;
    public static final int ONLY_LOWER_CASE = 1;
    public static final int ONLY_UPPER_CASE = 2;


    public int frameDuration = 200;
    public int letterType = ONLY_UPPER_CASE;
    public boolean supportNumbers = false;
    public String background;

    public String[] backgrounds;
    public String[] a;
    public String[] b;
    public String[] c;
    public String[] d;
    public String[] e;
    public String[] f;
    public String[] g;
    public String[] h;
    public String[] i;
    public String[] j;
    public String[] k;
    public String[] l;
    public String[] m;
    public String[] n;
    public String[] o;
    public String[] p;
    public String[] q;
    public String[] r;
    public String[] s;
    public String[] t;
    public String[] u;
    public String[] v;
    public String[] w;
    public String[] x;
    public String[] y;
    public String[] z;

    public String[] a_lower_case;
    public String[] b_lower_case;
    public String[] c_lower_case;
    public String[] d_lower_case;
    public String[] e_lower_case;
    public String[] f_lower_case;
    public String[] g_lower_case;
    public String[] h_lower_case;
    public String[] i_lower_case;
    public String[] j_lower_case;
    public String[] k_lower_case;
    public String[] l_lower_case;
    public String[] m_lower_case;
    public String[] n_lower_case;
    public String[] o_lower_case;
    public String[] p_lower_case;
    public String[] q_lower_case;
    public String[] r_lower_case;
    public String[] s_lower_case;
    public String[] t_lower_case;
    public String[] u_lower_case;
    public String[] v_lower_case;
    public String[] w_lower_case;
    public String[] x_lower_case;
    public String[] y_lower_case;
    public String[] z_lower_case;

    public String[] zero;
    public String[] one;
    public String[] two;
    public String[] three;
    public String[] four;
    public String[] five;
    public String[] six;
    public String[] seven;
    public String[] eight;
    public String[] nine;

    public final String getResourcesPath(String resName) {
        return ROOT_PATH + resName;
    }
}
