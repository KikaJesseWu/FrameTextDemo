package com.example.jessewu.frametextdemo.frametext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class FrameTextView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder mHolder;
    private Worker mWorker;
    private FTRenderer mRenderer;
    private boolean drawable;
    private boolean runnable;
    private int frameDuration = 200;

    public FrameTextView(Context context) {
        this(context, null);
    }

    public FrameTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FrameTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSurfaceView();
    }

    private void initSurfaceView() {
        mHolder = this.getHolder();
        mHolder.addCallback(this);
        mWorker = new Worker();
        mRenderer = new FTRenderer();
    }

    public final void setText(final String text) {
        if (text != null && mRenderer != null) {
            mRenderer.updateText(text);
        }
    }

    public final void setResources(FTConfiguration resources) {
        if (resources != null && mRenderer != null) {
            mRenderer.setResources(resources);
        }
    }

    public final void setFrameDuration(int duration) {
        this.frameDuration = duration;
    }

    public void recycle() {
        runnable = false;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        runnable = true;
        drawable = true;
        if (mRenderer != null) {
            mRenderer.setRootSize(getWidth(), getHeight());
        }
        try {
            mWorker.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        drawable = false;
    }

    private class Worker extends Thread {

        @Override
        public void run() {
            super.run();
            while (runnable) {
                try {
                    sleep(frameDuration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (drawable) {
                    Canvas canvas = null;
                    try {
                        canvas = mHolder.lockCanvas();
                        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                        if (mRenderer != null) {
                            mRenderer.drawFrame(canvas);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (canvas != null) {
                            mHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }
            if (mRenderer != null) {
                mRenderer.recycle();
            }
        }
    }

}
