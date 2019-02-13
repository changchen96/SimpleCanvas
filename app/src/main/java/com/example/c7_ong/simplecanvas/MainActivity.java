package com.example.c7_ong.simplecanvas;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{
    //Canvas member variable mCanvas is created
    //Stores information on what to draw on its associated bitmap
private Canvas mCanvas;
    //Paint member variable mPaint is created
    //Paint objects store how to draw
private Paint mPaint = new Paint();
private Paint mPaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    //Bitmap represents the pixels that are shown on the display
private Bitmap mBitmap;
    //the view in this example is the container for the bitmap
private ImageView mImageView;
    //both Rect variables are initialized as rectangles
private Rect mRect = new Rect();
private Rect mBounds = new Rect();
    //OFFSET constant here is the distance of a rectangle drawn from the edge of the canvas
private static final int OFFSET = 120;
private int mOffset = OFFSET;
private static final int MULTIPLIER = 100;
private int mColorBackground;
private int mColorRectangle;
private int mColorAccent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.colorBackground, null);
        mColorRectangle = ResourcesCompat.getColor(getResources(), R.color.colorRectangle, null);
        mColorAccent = ResourcesCompat.getColor(getResources(), R.color.colorAccent, null);
        mPaint.setColor(mColorBackground);
        mPaintText.setColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null));
        mPaintText.setTextSize(70);
        mImageView = (ImageView) findViewById(R.id.myimageview);
    }

    public void drawSomething(View view)
    {
        int vWidth = view.getWidth();
        int vHeight = view.getHeight();
        int halfwidth = vWidth/2;
        int halfheight = vHeight/2;
        if (mOffset == OFFSET)
        {
            mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
            mImageView.setImageBitmap(mBitmap);
            mCanvas = new Canvas(mBitmap);
            mCanvas.drawColor(mColorBackground);
            mCanvas.drawText(getString(R.string.keep_tapping), 100, 100, mPaintText);
            mOffset += OFFSET;

        }
        else
        {
            if (mOffset < halfwidth && mOffset < halfheight)
            {
                mPaint.setColor(mColorRectangle - MULTIPLIER*mOffset);
                mRect.set(mOffset, mOffset, vWidth-mOffset, vHeight-mOffset);
                mCanvas.drawRect(mRect, mPaint);
                mOffset += OFFSET;
            }
            else
            {
                mPaint.setColor(mColorAccent);
                mCanvas.drawCircle(halfwidth, halfheight, halfwidth/3, mPaint);
                String text = getString(R.string.done);
                mPaintText.getTextBounds(text, 0, text.length(), mBounds);
                int x = halfwidth - mBounds.centerX();
                int y = halfheight - mBounds.centerY();
                mCanvas.drawText(text,x,y,mPaintText);
            }
        }
        view.invalidate();
    }
}
