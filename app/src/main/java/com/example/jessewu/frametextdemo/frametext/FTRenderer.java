package com.example.jessewu.frametextdemo.frametext;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;

/**
 * FrameText渲染器
 */
public class FTRenderer {

    private static final int PADDING_VERTICAL = 50;
    private static final int PADDING_HORIZONTAL = 50;

    private Paint mPaint;
    private Scheduler mScheduler;
    private FTDictionary mDictionary;

    private Set<Character> mCharacters;
    private String text;

    /**
     * draw origin text use top and left of letterRect
     */
    private Rect letterRect;
    private Rect backgroundRect;

    private int rootWidth;
    private int rootHeight;

    private int availableWidth;
    private int availableHeight;

    private int letterHeight;
    private int OriginalTextOffsetY;

    public FTRenderer() {
        mPaint = new Paint();
        mPaint.setTextAlign(Paint.Align.LEFT);
        // TODO 字体颜色如何确定
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);

        mCharacters = new HashSet<>();
        mScheduler = new Scheduler();
        mDictionary = new FTDictionary();
    }

    public void setRootSize(int width, int height) {
        rootWidth = width;
        rootHeight = height;

        availableWidth = width - PADDING_HORIZONTAL;
        availableHeight = height - PADDING_VERTICAL;

        // frame text 的高度是画布的1/3
        letterHeight = rootHeight / 3;
        // 字体大小是 frame text 高度的2/3
        mPaint.setTextSize(letterHeight / 3 * 2);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        int textHeight = (int) (fontMetrics.descent - fontMetrics.ascent);
        OriginalTextOffsetY = (int) (0.5f * textHeight - fontMetrics.bottom);
        backgroundRect = new Rect(0, 0, rootWidth, rootHeight);
        letterRect = new Rect();
    }

    /**
     * call this method when worker thread was shutdown.
     */
    public final void recycle() {
        mDictionary.recycle();
        mScheduler.recycle();
        mDictionary = null;
        mScheduler = null;
        mPaint = null;
        mCharacters.clear();
        mCharacters = null;
    }

    public void setResources(FTConfiguration resources) {
        if (resources != null) {
            mDictionary.setResources(resources);
            Executors.newCachedThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    List<Bitmap> bitmaps = mDictionary.getBackgroundFrames();
                    if (bitmaps != null) {
                        mScheduler.addBackgroundFrames(bitmaps);
                    } else {
                        mScheduler.addBackgroundColor(mDictionary.getBackgroundColor());
                    }
                }
            });
        }
    }

    public final void updateText(final String text) {
        final Set<Character> characters = new HashSet<>();
        for (char c : text.toCharArray()) {
            if (!mCharacters.contains(c)) {
                mCharacters.add(c);
                characters.add(c);
            }
        }
        Executors.newCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                for (Character character : characters) {
                    if (mScheduler.getFrameByChar(character) == null) {
                        List<Bitmap> bitmaps = mDictionary.getFrameText(character);
                        if (bitmaps != null && bitmaps.size() > 0) {
                            mScheduler.addFramesByChar(character, bitmaps);
                        }
                    }
                }
                FTRenderer.this.text = text;
            }
        });
    }

    public final void drawFrame(final Canvas canvas) {
        final Scheduler scheduler = mScheduler;
        if (scheduler == null) {
            return;
        }
        drawBackground(canvas, scheduler);
        final String text = this.text;
        if (text == null || text.length() == 0 || !validateText(text)) {
            return;
        }
        drawText(canvas, scheduler, text);
    }

    private void drawBackground(Canvas canvas, Scheduler scheduler) {
        Bitmap bitmap = scheduler.getBackground();
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, null, backgroundRect, mPaint);
        } else {
            canvas.drawColor(scheduler.getBackgroundColor());
        }
    }

    private boolean validateText(String text) {
        return text.length() <= 24;
    }

    private int adjustBaseY(int lineNumber) {
        switch (lineNumber) {
            case 1:
                return letterHeight;
            case 2:
                return (rootHeight - letterHeight * 2) / 2;
            default:
                return 0;
        }
    }

    private int adjustBaseX(int lineWidth) {
        return (rootWidth - lineWidth) / 2;
    }

    private List<String> spiltText(String chars) {
        // TODO emoji and space
        List<String> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        for (char c : chars.toCharArray()) {
            if (c == ' ') {
                list.add(builder.toString());
                builder.delete(0, builder.length());
                list.add(String.valueOf(c));
            } else {
                builder.append(c);
            }
        }
        list.add(builder.toString());
        return list;
    }

    private List<Word> makeWords(char[] chars, Scheduler scheduler){
        List<Word> words = new ArrayList<>();
        int lineWidth = 0;
        List<Character> temp = new ArrayList<>();
        List<Bitmap> bitmaps = new ArrayList<>();
        List<Integer> widths = new ArrayList<>();
        for (char c : chars) {
            Bitmap bitmap = scheduler.getFrameByChar(c);
            int width = bitmap == null ? getOriginalTextWidth(c) :getFrameTextWidth(bitmap);
            // 如果一个word的长度超出root宽度，切割成多个word
            if (lineWidth + width > availableWidth){
                words.add(makeWord(temp,bitmaps,widths));
                lineWidth = 0;
                temp.clear();
                bitmaps.clear();
                widths.clear();
            }
            lineWidth += width;
            temp.add(c);
            bitmaps.add(bitmap);
            widths.add(width);
        }
        if (temp.size() > 0){
            words.add(makeWord(temp,bitmaps,widths));
        }
        return words;
    }

    private Word makeWord(List<Character> chars,List<Bitmap> bitmaps,List<Integer> widths) {
        if (chars.size() != bitmaps.size() || chars.size() != widths.size()){
            return null;
        }

        Word word = new Word();
        word.letters = new char[chars.size()];
        for (int i = 0; i < chars.size(); i++) {
            word.letters[i] = chars.get(i);
        }

        word.letterWidth = new int[widths.size()];
        word.width = 0;
        for (int i = 0; i < widths.size(); i++) {
            word.letterWidth[i] = widths.get(i);
            word.width += widths.get(i);
        }

        word.frames = new Bitmap[bitmaps.size()];
        for (int i = 0; i < bitmaps.size(); i++) {
            word.frames[i] = bitmaps.get(i);
        }
        return word;
    }

    private void drawText(Canvas canvas, Scheduler scheduler, String text) {

        // 1，将文本拆分成word列表
        List<Word> words = new ArrayList<>();
        for (String s : spiltText(text)) {
            words.addAll(makeWords(s.toCharArray(), scheduler));
        }

        // 2，根据word的长度来确定每个word的行标
        List<List<Word>> lineWord = new ArrayList<>();
        int tempWidth = 0;
        int index = 0;
        while (true){
            List<Word> temp = new ArrayList<>();
            for (; index < words.size(); index++) {
                Word word = words.get(index);
                if (word.width + tempWidth > availableWidth) {
                    tempWidth = 0;
                    break;
                } else {
                    temp.add(word);
                    tempWidth += word.width;
                }
            }
            lineWord.add(temp);
            if (index == words.size()){
                break;
            }
        }

        // 3，以行的形式画出所有文字
        int baseY = adjustBaseY(lineWord.size());
        for (int i = 0; i < lineWord.size(); i++) {
            List<Word> wordList = lineWord.get(i);
            // draw single line text for word
            for (int j = 0; j < wordList.size(); j++) {

                int lineWidth = 0;
                for (Word word : wordList) {
                    lineWidth += word.width;
                }

                int left = 0;
                for (int k = 0; k < j; k++) {
                    left += wordList.get(k).width;
                }
                int baseX = adjustBaseX(lineWidth);

                Word word = wordList.get(j);
                // draw text for letter
                for (int k = 0; k < word.frames.length; k++) {
                    int leftForWord = 0;
                    Bitmap frame = word.frames[k];
                    for (int l = 0; l < k; l++) {
                        leftForWord += word.letterWidth[l];
                    }

                    if (adjustRect(left+leftForWord, frame, baseX, baseY + letterHeight * i)) {
                        canvas.drawText(String.valueOf(word.letters[k]), letterRect.left, letterRect.top, mPaint);
                    } else {
                        canvas.drawBitmap(frame, null, letterRect, mPaint);
                    }
                }
            }

        }
    }

    private boolean adjustRect(int left, Bitmap frame, int baseX, int baseY) {
        boolean isOriginText = frame == null;
        letterRect.left = left + baseX;
        letterRect.top = isOriginText ? baseY + letterHeight / 2 + OriginalTextOffsetY : baseY;
        if (!isOriginText) {
            letterRect.right = letterRect.left + getFrameTextWidth(frame);
            letterRect.bottom = baseY + letterHeight;
        }
        return isOriginText;
    }

    private int getFrameTextWidth(Bitmap bitmap){
        double width = ((double) letterHeight / (double) bitmap.getHeight()) * (double) bitmap.getWidth();
        return (int) width;
    }

    private int getOriginalTextWidth(char str) {
        float[] width = new float[1];
        mPaint.getTextWidths(String.valueOf(str), width);
        return (int) Math.ceil(width[0]);
    }

    private class Word {
        int width;
        char[] letters;
        Bitmap[] frames;
        int[] letterWidth;
    }

    /**
     * 资源调度器，每帧展示的资源
     */
    private class Scheduler {

        private final int BACKGROUND_COLOR = Color.parseColor("#FFFFFF");

        private Map<Character, Integer> mIndex = new HashMap<>();
        private Map<Character, List<Bitmap>> mFrames = new HashMap<>();

        private List<Bitmap> mBackgrounds = new ArrayList<>();
        private int mBackgroundsIndex = 0;

        private int mBackgroundColor = 0;

        private void recycle() {
            mIndex.clear();
            mFrames.clear();
            for (Bitmap bitmap : mBackgrounds) {
                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                }
            }
            mBackgrounds.clear();
            mIndex = null;
            mFrames = null;
            mBackgrounds = null;
        }

        private void addBackgroundFrames(List<Bitmap> frames) {
            mBackgrounds.clear();
            mBackgrounds.addAll(frames);
        }

        private void addBackgroundColor(String color) {
            if (color != null) {
                mBackgroundColor = Color.parseColor(color);
            }
        }

        private Bitmap getBackground() {
            if (mBackgrounds.size() == 0) {
                return null;
            }
            if (mBackgroundsIndex >= mBackgrounds.size()) {
                mBackgroundsIndex = 0;
            }
            return mBackgrounds.get(mBackgroundsIndex++);
        }

        private int getBackgroundColor() {
            return mBackgroundColor != 0 ? mBackgroundColor : BACKGROUND_COLOR;
        }

        private void addFramesByChar(char c, List<Bitmap> frames) {
            if (frames == null || frames.size() == 0) {
                return;
            }
            mFrames.put(c, frames);
            mIndex.put(c, 0);
        }

        private Bitmap getFrameByChar(char c) {
            // TODO 一段文字中多次出现同一个字母？
            List<Bitmap> frames = mFrames.get(c);
            if (frames == null || frames.size() == 0) {
                return null;
            }
            Integer index = mIndex.get(c);
            if (index == null || index == frames.size()) {
                index = 0;
            }
            mIndex.put(c, index + 1);
            return frames.get(index);
        }
    }

}
