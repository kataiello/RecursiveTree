package com.example.demouser.recursivetree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public double GOLDEN_RATIO = 1.618;
    public double minLength = 3;
    public double MAX_ANGLE = 0.5 * Math.PI;
    public int width;
    public int height;
    private int mDepth = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TreeView treeView = (TreeView) findViewById(R.id.tree_view);
        final TextView depthText = (TextView) findViewById(R.id.depth);
        depthText.setText(String.valueOf(minLength));

        Button resetBtn = (Button) findViewById(R.id.reset);

        resetBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                resetFromDepth(treeView, depthText);
            }
        });

        Button decreaseBtn = (Button) findViewById(R.id.decrease_depth);
        decreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (minLength > 0) {
                    minLength--;
                }
                depthText.setText(String.valueOf(minLength));
            }
        });

        Button increaseBtn = (Button) findViewById(R.id.increase_depth);
        increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                depthText.setText(String.valueOf(++minLength));
            }
        });

        resetFromDepth(treeView, depthText);
    }

    public void drawBranch(int x, int y, double angle, double length)
    {

        /*
         * BASE CASE:
         * x < 0, x > width
         * y < 0, y > height
         * length < k, k = 3, 5?
         */
        if(x > 0 && x < width && y > 0 && y < height && length > minLength)
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

            //calculate a random angle between [theta - delta, theta] for the left
            // and [theta, theta + delta] for the right
            //where theta is the angle of this branch and delta is MAX_ANGLE
            double newAngle1 = (Math.random()/(-2)) * Math.PI + angle;
            double newAngle2 = (Math.random()/2) * Math.PI + angle;

            //calculate the new length using the golden ratio
            double newLength = length / GOLDEN_RATIO;

            //call recursion
            drawBranch(newX, newY, newAngle1, newLength);
            drawBranch(newX, newY, newAngle2, newLength);

        }


    }




}
