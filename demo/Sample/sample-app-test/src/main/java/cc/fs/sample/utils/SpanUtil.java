package cc.fs.sample.utils;

import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

/**
 * SpannableString
 */
public class SpanUtil {

    public static SpannableString createDrawableSpan(Drawable drawable) {
        return createDrawableSpan(drawable, -1);
    }

    public static SpannableString createDrawableSpan(Drawable srcDrawable, int size) {
        SpannableString spanStr = new SpannableString("0");
        float scale;
        if (size != -1) {
            if (srcDrawable.getIntrinsicWidth() > srcDrawable.getIntrinsicHeight())
                scale = size * 1.0f / srcDrawable.getIntrinsicWidth();
            else scale = size * 1.0f / srcDrawable.getIntrinsicHeight();
        } else {
            scale = -1;
        }
        srcDrawable.setBounds(0, 0, (int) (srcDrawable.getIntrinsicWidth() * scale), (int) (srcDrawable.getIntrinsicHeight() * scale));
        ImageSpan imageSpan = new ImageSpan(srcDrawable, ImageSpan.ALIGN_BOTTOM); //注意这里插入图片问题
        spanStr.setSpan(imageSpan, 0, spanStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanStr;
    }

}
