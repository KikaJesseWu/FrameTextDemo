package com.example.jessewu.frametextdemo;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.jessewu.frametextdemo.frametext.FTConfiguration;
import com.example.jessewu.frametextdemo.frametext.FrameTextView;

public class MainActivity extends AppCompatActivity {

    FrameTextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 200);

        final FTConfiguration configuration = new FTConfiguration();
        configuration.a = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "a.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "a1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "a2.png"};
        configuration.b = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "b.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "b1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "b2.png"};
        configuration.c = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "c.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "c1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "c2.png"};
        configuration.d = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "d.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "d1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "d2.png"};
        configuration.e = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "e.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "e1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "e2.png"};
        configuration.f = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "f.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "f1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "f2.png"};
        configuration.g = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "g.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "g1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "g2.png"};
        configuration.h = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "h.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "h1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "h2.png"};
        configuration.i = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "i.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "i1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "i2.png"};
        configuration.j = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "j.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "j1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "j2.png"};
        configuration.k = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "k.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "k1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "k2.png"};
        configuration.l = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "l.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "l1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "l2.png"};
        configuration.m = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "m.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "m1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "m2.png"};
        configuration.n = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "n.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "n1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "n2.png"};
        configuration.o = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "o.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "o1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "o2.png"};
        configuration.p = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "p.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "p1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "p2.png"};
        configuration.q = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "q.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "q1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "q2.png"};
        configuration.r = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "r.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "r1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "r2.png"};
        configuration.s = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "s.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "s1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "s2.png"};
        configuration.t = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "t.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "t1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "t2.png"};
        configuration.u = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "u.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "u1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "u2.png"};
        configuration.v = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "v.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "v1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "v2.png"};
        configuration.w = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "w.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "w1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "w2.png"};
        configuration.x = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "x.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "x1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "x2.png"};
        configuration.y = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "y.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "y1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "y2.png"};
        configuration.z = new String[]{FTConfiguration.FOLDER_NAME_UPPER_CASE + "z.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "z1.png", FTConfiguration.FOLDER_NAME_UPPER_CASE + "z2.png"};

        configuration.a_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "a.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "a1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "a2.png"};
        configuration.b_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "b.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "b1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "b2.png"};
        configuration.c_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "c.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "c1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "c2.png"};
        configuration.d_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "d.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "d1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "d2.png"};
        configuration.e_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "e.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "e1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "e2.png"};
        configuration.f_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "f.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "f1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "f2.png"};
        configuration.g_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "g.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "g1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "g2.png"};
        configuration.h_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "h.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "h1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "h2.png"};
        configuration.i_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "i.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "i1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "i2.png"};
        configuration.j_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "j.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "j1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "j2.png"};
        configuration.k_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "k.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "k1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "k2.png"};
        configuration.l_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "l.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "l1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "l2.png"};
        configuration.m_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "m.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "m1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "m2.png"};
        configuration.n_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "n.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "n1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "n2.png"};
        configuration.o_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "o.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "o1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "o2.png"};
        configuration.p_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "p.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "p1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "p2.png"};
        configuration.q_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "q.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "q1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "q2.png"};
        configuration.r_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "r.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "r1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "r2.png"};
        configuration.s_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "s.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "s1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "s2.png"};
        configuration.t_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "t.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "t1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "t2.png"};
        configuration.u_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "u.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "u1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "u2.png"};
        configuration.v_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "v.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "v1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "v2.png"};
        configuration.w_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "w.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "w1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "w2.png"};
        configuration.x_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "x.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "x1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "x2.png"};
        configuration.y_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "y.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "y1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "y2.png"};
        configuration.z_lower_case = new String[]{FTConfiguration.FOLDER_NAME_LOWER_CASE + "z.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "z1.png", FTConfiguration.FOLDER_NAME_LOWER_CASE + "z2.png"};



        configuration.letterType = FTConfiguration.BOTH;
        configuration.backgrounds = new String[]{FTConfiguration.FOLDER_NAME_BACKGROUND + "bg_3.jpg", FTConfiguration.FOLDER_NAME_BACKGROUND + "bg_1.jpg"};
//        configuration.background = "#ff0000";

        ViewGroup container = findViewById(R.id.container);
        view = new FrameTextView(this);
        container.addView(view);
        view.setResources(configuration);
        view.setFrameDuration(configuration.frameDuration);

        EditText editText = findViewById(R.id.edit);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                view.setText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        view.recycle();
    }
}
