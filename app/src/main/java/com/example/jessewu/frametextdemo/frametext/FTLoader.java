package com.example.jessewu.frametextdemo.frametext;


import android.os.Environment;

import java.io.File;
import java.util.zip.ZipFile;

// 资源加载器
public class FTLoader {

    private static final String TAG = "FTLoader";
    private static final String ROOT_PATH = Environment.getExternalStorageDirectory().getPath() + "/FrameText/";

    private static FTLoader sInstance = new FTLoader();

    public FTLoader getInstance(){
        return sInstance;
    }

    public void unZip(ZipFile file,String Style){

    }

    private FTLoader() {
        createFolderIfNeed(ROOT_PATH);
    }

    private void createFolderIfNeed(String path){
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }


}
