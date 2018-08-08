package com.smart.cryptolivedata.recview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.smart.cryptolivedata.R;



public class Divider extends RecyclerView.ItemDecoration {
    private static final String TAG = Divider.class.getSimpleName();
    private Drawable mDivider;

    public Divider(Context context) {
        mDivider = context.getResources().getDrawable(R.drawable.list_divider);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        Log.d(TAG, "onDrawOver() called with: left = [" + left + "], right = [" + right + "], childCount = [" + childCount + "]");
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            //Log.d(TAG, "onDrawOver() called with: top = [" + top + "], bottom = [" + bottom + "], child = [" + child + "]");
            Log.d(TAG, "onDrawOver() called with: top = [" + top + "], bottom = [" + bottom + "]");
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}