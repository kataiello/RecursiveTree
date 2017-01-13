package com.example.demouser.recursivetree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public double GOLDEN_RATIO = 1.618;
    public double MIN_LENGTH = 3;
    public double MAX_ANGLE = 0.5 * Math.PI;
    public int width;
    public int height;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void drawBranch(int x, int y, double angle, double length)
    {

        /*
         * BASE CASE:
         * x < 0, x > width
         * y < 0, y > height
         * length < k, k = 3, 5?
         */
        if(x > 0 && x < width && y > 0 && y < height && length > MIN_LENGTH)
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

            //TODO: draw line here

            /*
             * RECURSE:
             * drawBranch(new_x, new_y, angle?, length)
             */

            //calculate a random angle between [theta - delta, theta + delta]
            //where theta is the angle of this branch and delta is MAX_ANGLE
            double newAngle = (Math.random() - 0.5) * Math.PI + angle;

            //calculate the new length using the golden ratio
            double newLength = length / GOLDEN_RATIO;

            //call recursion
            drawBranch(newX, newY, newAngle, newLength);

        }


    }




}
