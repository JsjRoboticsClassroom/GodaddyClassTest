package nyc.c4q.unittest.views;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import nyc.c4q.unittest.R;
import nyc.c4q.unittest.backend.MultiSquareResponse;

public class MultiSquareView extends View {
    private static final String TAG = MultiSquareView.class.getName();
    private static final int DEFAULT_COLOR = 0xFFFFFFFF;
    private int mFirstColor = DEFAULT_COLOR;
    private int mSecondColor = DEFAULT_COLOR;
    private int mThirdColor = DEFAULT_COLOR;
    private int mFourthColor = DEFAULT_COLOR;
    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;
    private Paint mPaint4;
    private float mDrawWidth;
    private float mDrawHeight;
    private float mHalfWidth;
    private float mHalfHeight;

    public MultiSquareView(Context context) {
        super(context);
        init();
    }

    public MultiSquareView(Context context, AttributeSet attributes) {
        super(context, attributes);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attributes,
                R.styleable.MultiSquareView,
                0, 0);
        mFirstColor = typedArray.getInt(R.styleable.MultiSquareView_squareOneColor, DEFAULT_COLOR);
        mSecondColor = typedArray.getInt(R.styleable.MultiSquareView_squareTwoColor, DEFAULT_COLOR);
        mThirdColor = typedArray.getInt(R.styleable.MultiSquareView_squareThirdColor, DEFAULT_COLOR);
        mFourthColor = typedArray.getInt(R.styleable.MultiSquareView_squareFourthColor, DEFAULT_COLOR);
        typedArray.recycle();
        init();
    }

    private void init() {
        mPaint1 = buildPaint(mFirstColor);
        mPaint2 = buildPaint(mSecondColor);
        mPaint3 = buildPaint(mThirdColor);
        mPaint4 = buildPaint(mFourthColor);
    }

    private Paint buildPaint(int color) {
        Paint result = new Paint(Paint.ANTI_ALIAS_FLAG);
        result.setColor(color);
        result.setStyle(Paint.Style.FILL);
        return result;
    }

    @Override
    public void onSizeChanged(int width, int height, int oldWidth, int oldHeight){
        float xPadding = (float)(getPaddingLeft() + getPaddingRight());
        float yPadding = (float)(getPaddingTop() + getPaddingBottom());
        mDrawWidth = width - xPadding;
        mDrawHeight = height - yPadding;
        mHalfWidth = mDrawWidth/2;
        mHalfHeight = mDrawHeight/2;
    }

    public void setBoxColors(int firstColor, int secondColor, int thirdColor, int fourthColor){
        mFirstColor = firstColor;
        mSecondColor = secondColor;
        mThirdColor = thirdColor;
        mFourthColor = fourthColor;
        init();
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawFirstBox(canvas);
        drawSecondBox(canvas);
        drawThirdBox(canvas);
        drawFourthBlade(canvas);

    }

    private void drawFourthBlade(Canvas canvas) {
        canvas.drawRect(mHalfWidth, mHalfHeight, mDrawWidth, mDrawHeight, mPaint4);
    }

    private void drawThirdBox(Canvas canvas) {
        canvas.drawRect(0, mHalfHeight, mHalfWidth, mDrawHeight, mPaint3);    }

    private void drawSecondBox(Canvas canvas) {
        canvas.drawRect(mHalfWidth, 0, mDrawWidth, mHalfHeight, mPaint2);    }

    private void drawFirstBox(Canvas canvas) {
        canvas.drawRect(0, 0, mHalfWidth, mHalfHeight, mPaint1);
    }

    public void setBoxColors(MultiSquareResponse result) {
        setBoxColors(result.getFirstColor(), result.getSecondColor(), result.getThirdColor(), result.getFourthColor());
    }
}
