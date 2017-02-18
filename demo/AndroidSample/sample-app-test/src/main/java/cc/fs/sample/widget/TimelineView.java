package cc.fs.sample.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cc.fs.sample.R;

/**
 * 时间轴view
 */

public class TimelineView extends RelativeLayout {

    private View rootView;
    private BubbleImageView mImageView;
    private TextView tvTitle, tvTip, tvDate, tvNote;
    private ImageView ivCheck;
    private Drawable mDrawableRes;
    private String mTitle, mTip, mDate, mNote;

    public TimelineView(Context context) {
        this(context, null);
    }

    public TimelineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimelineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initRes(attrs);
        initView();
    }

    private void initRes(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.TimelineView);
        int size = ta.getIndexCount();
        for(int i=0;i<size;i++){
            switch (ta.getIndex(i)){
                case R.styleable.TimelineView_drawable:
                    mDrawableRes = ta.getDrawable(R.styleable.TimelineView_drawable);
                    break;
                case R.styleable.TimelineView_titleText:
                    mTitle = ta.getString(R.styleable.TimelineView_titleText);
                    break;
                case R.styleable.TimelineView_tipText:
                    mTip = ta.getString(R.styleable.TimelineView_tipText);
                    break;
            }
        }
        ta.recycle();
    }

    private void initView() {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.view_timeline_item, this, false);
        mImageView = (BubbleImageView) rootView.findViewById(R.id.mImageView);
        tvTitle = (TextView) rootView.findViewById(R.id.tvTitle);
        tvDate = (TextView) rootView.findViewById(R.id.tvDate);
        tvNote = (TextView) rootView.findViewById(R.id.tvNote);
        tvTip = (TextView) rootView.findViewById(R.id.tvTip);
        ivCheck = (ImageView) rootView.findViewById(R.id.ivCheck);

        mImageView.setImageDrawable(mDrawableRes);
        tvTitle.setText(mTitle);
        tvTip.setText(mTip);
        tvTip.setVisibility(View.VISIBLE);
        tvDate.setVisibility(View.GONE);
        tvNote.setVisibility(View.GONE);
        ivCheck.setVisibility(View.GONE);
        addView(rootView);
    }

    private void bindData(){

    }
}
