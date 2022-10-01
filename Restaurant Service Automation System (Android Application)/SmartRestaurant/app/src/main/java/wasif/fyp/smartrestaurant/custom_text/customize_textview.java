package wasif.fyp.smartrestaurant.custom_text;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class customize_textview extends android.support.v7.widget.AppCompatTextView {

    public customize_textview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public customize_textview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public customize_textview(Context context) {
        super(context);
        init();
    }

    private void init() {

        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/SinkinSans-400Regular.otf");
            setTypeface(tf);
        }


    }

}