package cc.fs.sample.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawableView extends View {
    GradientDrawable gradientDrawable = new GradientDrawable();
    Rect rect = new Rect(20, 20, 120, 80);

    public DrawableView(Context context) {
        this(context, null);
    }

    public DrawableView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        gradientDrawable.setBounds(rect.left, rect.top, rect.right, rect.bottom);
        gradientDrawable.setColor(Color.RED);
        gradientDrawable.setCornerRadius(5f);


        gradientDrawable.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                if (rect.left < 400) {
                    rect.left = rect.left + 5;
                    rect.right = rect.right + 5;
                    invalidate();
                    postDelayed(this, 200);
                }
            }
        }, 200);

        return super.onTouchEvent(event);
    }
}
