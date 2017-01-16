package cc.fs.sample.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by fostion on 2017/1/16.
 */

public class TimelineView extends RelativeLayout {

    public TimelineView(Context context) {
        this(context,null);

    }

    public TimelineView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TimelineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

    }
}
