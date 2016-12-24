package cc.fs.sample.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author AjayNiu
 * @since 2016-12-22
 */

public class FlashImageView extends ImageView {

    private Paint mPaint;
    private LinearGradient gradient;
    private float mPosX;
    private float mPosY;
    private int[] mColors;
    private float[] mPositions;
    private int mFlashWidth;
    private int mRotateAngle = -45;
    private int mSpeexDelta = 5;
    // 对角线长度
    private float diagonalLength;

    public FlashImageView(Context context) {
        super(context);
        init();
    }

    public FlashImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        //Color.parseColor("#99ffffff")
        mColors = new int[]{Color.TRANSPARENT, Color.RED , Color.TRANSPARENT};
        mPositions = new float[]{0, 0.5f, 1};



        postDelayed(runnable,5000);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            anim();
            postDelayed(this,5000);
        }
    };

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        diagonalLength = (float) Math.sqrt(Math.pow(getMeasuredWidth(), 2) + Math.pow(getMeasuredHeight(), 2));
        mFlashWidth = getMeasuredWidth();
        gradient = new LinearGradient(0, 0, 0, mFlashWidth, mColors, mPositions, Shader.TileMode.CLAMP);
        mPaint.setShader(gradient);
    }

    public void anim() {
        needInvalidate = true;
        mPosY = - mFlashWidth;
        invalidate();
    }

    public void setSpeexDelta(int mSpeexDelta) {
        this.mSpeexDelta = mSpeexDelta;
    }

    public void setRotateAngle(int mRotateAngle) {
        this.mRotateAngle = mRotateAngle;
    }

    public boolean needInvalidate;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (needInvalidate) {
            mPosY += mSpeexDelta;
            if (mPosY > getHeight()) {
                needInvalidate = false;
            }
            canvas.save();
            canvas.rotate(mRotateAngle, getWidth() / 2, getHeight() / 2);
            canvas.translate(mPosX - ((diagonalLength - getWidth()) / 2), mPosY);

            canvas.drawRect(0, 0, diagonalLength, mFlashWidth, mPaint);
            canvas.restore();
            postInvalidate();
        }
    }
}
