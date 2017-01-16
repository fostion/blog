package cc.fs.sample.textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import cc.fs.sample.R;

/**
 * 可以控制icon的TextView
 */
public class IconTextView extends TextView {
    private int drawableLeftWidth;
    private int drawableLeftHeight;
    private int drawableRightWidth;
    private int drawableRightHeight;
    private int drawableTopWidth;
    private int drawableTopHeight;
    private int drawableBottomWidth;
    private int drawableBottomHeight;

    public IconTextView(Context context) {
        this(context,null);
    }

    public IconTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IconTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    protected void init(AttributeSet attrs) {

        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.IconTextView);
        drawableLeftWidth = ta.getDimensionPixelOffset(R.styleable.IconTextView_drawableLeftWidth, 0);
        drawableLeftHeight = ta.getDimensionPixelOffset(R.styleable.IconTextView_drawableLeftHeight, 0);

        drawableRightWidth = ta.getDimensionPixelOffset(R.styleable.IconTextView_drawableRightWidth, 0);
        drawableRightHeight = ta.getDimensionPixelOffset(R.styleable.IconTextView_drawableRightHeight, 0);

        drawableTopWidth = ta.getDimensionPixelOffset(R.styleable.IconTextView_drawableTopWidth, 0);
        drawableTopHeight = ta.getDimensionPixelOffset(R.styleable.IconTextView_drawableTopHeight, 0);

        drawableBottomWidth = ta.getDimensionPixelOffset(R.styleable.IconTextView_drawableBottomWidth, 0);
        drawableBottomHeight = ta.getDimensionPixelOffset(R.styleable.IconTextView_drawableBottomHeight, 0);
        ta.recycle();

        Drawable[] drawables = getCompoundDrawables();
        if (drawables[0] != null)
            drawables[0].setBounds(0, 0, drawableLeftWidth, drawableLeftHeight);
        if (drawables[1] != null)
            drawables[1].setBounds(0, 0, drawableTopWidth, drawableTopHeight);
        if (drawables[2] != null)
            drawables[2].setBounds(0, 0, drawableRightWidth, drawableRightHeight);
        if (drawables[3] != null)
            drawables[3].setBounds(0, 0, drawableBottomWidth, drawableBottomHeight);
        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    public void setCompoundDrawableLeft(Drawable drawableLeft){
        Drawable[] drawables = getCompoundDrawables();
        if (drawableLeft != null)
            drawableLeft.setBounds(0, 0, drawableLeftWidth, drawableLeftHeight);
        if (drawables[1] != null)
            drawables[1].setBounds(0, 0, drawableTopWidth, drawableTopHeight);
        if (drawables[2] != null)
            drawables[2].setBounds(0, 0, drawableRightWidth, drawableRightHeight);
        if (drawables[3] != null)
            drawables[3].setBounds(0, 0, drawableBottomWidth, drawableBottomHeight);
        setCompoundDrawables(drawableLeft, drawables[1], drawables[2], drawables[3]);
    }

    public void setCompoundDrawableRight(Drawable drawableRight){
        Drawable[] drawables = getCompoundDrawables();
        if (drawables[0] != null)
            drawables[0].setBounds(0, 0, drawableLeftWidth, drawableLeftHeight);
        if (drawables[1] != null)
            drawables[1].setBounds(0, 0, drawableTopWidth, drawableTopHeight);
        if (drawableRight != null)
            drawableRight.setBounds(0, 0, drawableRightWidth, drawableRightHeight);
        if (drawables[3] != null)
            drawables[3].setBounds(0, 0, drawableBottomWidth, drawableBottomHeight);
        setCompoundDrawables(drawables[0], drawables[1], drawableRight, drawables[3]);
    }

    public void setCompoundDrawableTop(Drawable drawableTop){
        Drawable[] drawables = getCompoundDrawables();
        if (drawables[0] != null)
            drawables[0].setBounds(0, 0, drawableLeftWidth, drawableLeftHeight);
        if (drawableTop != null)
            drawableTop.setBounds(0, 0, drawableTopWidth, drawableTopHeight);
        if (drawables[2] != null)
            drawables[2].setBounds(0, 0, drawableRightWidth, drawableRightHeight);
        if (drawables[3] != null)
            drawables[3].setBounds(0, 0, drawableBottomWidth, drawableBottomHeight);
        setCompoundDrawables(drawables[0], drawableTop, drawables[2], drawables[3]);
    }

    public void setCompoundDrawableBottom(Drawable drawableBottom){
        Drawable[] drawables = getCompoundDrawables();
        if (drawables[0] != null)
            drawables[0].setBounds(0, 0, drawableLeftWidth, drawableLeftHeight);
        if (drawables[1] != null)
            drawables[1].setBounds(0, 0, drawableTopWidth, drawableTopHeight);
        if (drawables[2] != null)
            drawables[2].setBounds(0, 0, drawableRightWidth, drawableRightHeight);
        if (drawableBottom != null)
            drawableBottom.setBounds(0, 0, drawableBottomWidth, drawableBottomHeight);
        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawableBottom);
    }

    public void setDrawableLeftWidth(int drawableLeftWidth) {
        this.drawableLeftWidth = drawableLeftWidth;
    }

    public void setDrawableLeftHeight(int drawableLeftHeight) {
        this.drawableLeftHeight = drawableLeftHeight;
    }

    public void setDrawableRightWidth(int drawableRightWidth) {
        this.drawableRightWidth = drawableRightWidth;
    }

    public void setDrawableRightHeight(int drawableRightHeight) {
        this.drawableRightHeight = drawableRightHeight;
    }

    public void setDrawableTopWidth(int drawableTopWidth) {
        this.drawableTopWidth = drawableTopWidth;
    }

    public void setDrawableTopHeight(int drawableTopHeight) {
        this.drawableTopHeight = drawableTopHeight;
    }

    public void setDrawableBottomWidth(int drawableBottomWidth) {
        this.drawableBottomWidth = drawableBottomWidth;
    }

    public void setDrawableBottomHeight(int drawableBottomHeight) {
        this.drawableBottomHeight = drawableBottomHeight;
    }
}
