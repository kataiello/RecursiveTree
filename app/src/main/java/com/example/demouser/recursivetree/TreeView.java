package com.example.demouser.recursivetree;

/**
 * Created by demouser on 1/13/17.
 */


import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class TreeView extends View {

    private static final double X_MAX = 1;
    private static final double X_MIN = -2.5;
    private static final double Y_MAX = 1.75;
    private static final double Y_MIN = -1.75;
    public double GOLDEN_RATIO = 1.618;

//    private double mXMin = X_MIN;
//    private double mXMax = X_MAX;
//    private double mXRange = X_MAX - X_MIN;
//    private double mYMin = Y_MIN;
//    private double mYMax = Y_MAX;
//    private double mYRange = Y_MAX - Y_MIN;

    private Paint mBackgroundPaint;
    private Paint mPaint;
    private int mMaxDepth;
    private int mNumPoints = 10;

    private int[] mColors;

    public TreeView(Context context) {
        super(context);
        init();
    }

    public TreeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TreeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TreeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mBackgroundPaint = new Paint();
        //mBackgroundPaint.setColor(getResources().getColor(R.color.background_color));
        mBackgroundPaint.setColor(Color.BLACK);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        if (widthMeasureSpec == 0 || heightMeasureSpec == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
        int size = Math.max(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(size, size);
    }

    public void reset(int depth) {
        mMaxDepth = depth;
//        mXMin = X_MIN;
//        mXMax = X_MAX;
//        mXRange = mXMax - mXMin;
//        mYMin = Y_MIN;
//        mYMax = Y_MAX;
//        mYRange = mYMax - mYMin;
        //initializeColors();
        postInvalidate();
    }

//    /**
//     * Define the colors to use, based on the depth.
//     * This will mean less calculation during onDraw!
//     */
//    private void initializeColors() {
//        mColors = new int[mMaxDepth + 1];
//        for (int i = 0; i < mMaxDepth; i++) {
//            int r = (int) (255 * (i * 1.0 / mMaxDepth));
//            int g = (int) (255 * (1 - i * 1.0 / mMaxDepth));
//            mColors[i] = Color.rgb(r, g, 255);
//        }
//        mColors[mMaxDepth] = Color.BLACK;
//    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.drawRect(0, 0, getWidth(), getHeight(), mBackgroundPaint);

        drawBranch(canvas, getWidth()/2, 0, 90, mMaxDepth);

//        int dotSize = getWidth() / mNumPoints;
//
//        for(int i = 0; i < mNumPoints; i++)
//        {
//            double mx = ((i * 1.0) / mNumPoints) * mXRange + mXMin;
//            float px = ((i * 1.0f) / mNumPoints) * getWidth();
//
//            for(int j = 0; j < mNumPoints; j++)
//            {
//                double my = ((j * 1.0) / mNumPoints) * mYRange + mYMin;
//                float py = ((j * 1.0f) / mNumPoints) * getHeight();
//
//                int color = getMandelbrotColor(mx, my);
//                mPaint.setColor(color);
//                canvas.drawCircle(px, py, dotSize, mPaint);
//            }
//        }
    }

    public void drawBranch(Canvas canvas, int x, int y, double angle, double length)
    {

        /*
         * BASE CASE:
         * x < 0, x > width
         * y < 0, y > height
         * length < k, k = 3, 5?
         */
        if(x > 0 && x < getWidth() && y > 0 && y < getHeight() && length > mMaxDepth)
        {
            /*
             * WORK:
             * drawline(x, y, new_x, new_y)
             * new_x = ?
             * new_y = ?
             */

            //calculate the endpoint
            int newX = (int) (x + length * Math.cos(angle));
            int newY = (int) (y + length * Math.sin(angle));

            /*
             * PAINT:
             * stroke width
             * stroke cap
             * color
             */

            //draw line
            mPaint.setColor(Color.CYAN);
            canvas.drawLine(x, y, newX, newY, mPaint);

            /*
             * RECURSE:
             * drawBranch(new_x, new_y, angle?, length)
             */

            //calculate a random angle between [theta - delta, theta] for the left
            // and [theta, theta + delta] for the right
            //where theta is the angle of this branch and delta is MAX_ANGLE
            double newAngle1 = (Math.random()/(-2)) * Math.PI + angle;
            double newAngle2 = (Math.random()/2) * Math.PI + angle;

            //calculate the new length using the golden ratio
            double newLength = length / GOLDEN_RATIO;

            //call recursion
            drawBranch(canvas, newX, newY, newAngle1, newLength);
            drawBranch(canvas, newX, newY, newAngle2, newLength);
        }
    }

//    /**
//     * Z_0 = (mX, mY)
//     * Z_n+1 = Z_n * Z_n + Z_0
//     * @param mX
//     * @param mY
//     * @return
//     */
//    private int getMandelbrotColor(double mX, double mY)
//    {
//        double zx = mX;
//        double zy = mY;
//        int iter = 0;
//
//        while(  iter < mMaxDepth &&
//                //check divergence
//                (zx*zx) + (zy * zy) < 4
//                )
//        {
//            double tx = (zx * zx) - (zy * zy) + mX;
//            zy = 2 * zx * zy + mY;
//            zx = tx;
//            iter++;
//        }
//
//        return mColors[iter];
//    }
}
