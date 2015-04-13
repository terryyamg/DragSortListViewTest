package tw.android;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;

public class CheckableLinearLayout extends LinearLayout implements Checkable {

    private static final int CHECKABLE_CHILD_INDEX = 1;
    private Checkable child;

    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        child = (Checkable) getChildAt(CHECKABLE_CHILD_INDEX);
    }

    public boolean isChecked() {
        return child.isChecked(); 
    }

    public void setChecked(boolean checked) {
        child.setChecked(checked);
    }

    public void toggle() {
        child.toggle();
    }
    
}

