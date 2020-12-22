package nam.tran.texttosvg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SvgExportView extends View {

    public Bitmap mBitmap = null;
    private Canvas mBitmapCanvas = null;
    private Paint mPaint;
    private Paint mPaintText;
    private Paint mPaintBorder;

    public SvgExportView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //Fixed parameters
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

        mPaintText = new Paint();
        mPaintText.setColor(Color.BLACK);
        mPaintText.setAntiAlias(true);
        mPaintText.setStrokeWidth(5);
        mPaintText.setStyle(Paint.Style.FILL);
        mPaintText.setStrokeJoin(Paint.Join.ROUND);
        mPaintText.setStrokeCap(Paint.Cap.ROUND);
        mPaintText.setTextSize(200);

        mPaintBorder = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        resetDraw();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
    }

    public Bitmap getBitmap() {
        Bitmap whiteBgBitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(whiteBgBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        return whiteBgBitmap;
    }

    private void resetDraw(){
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(),
            Bitmap.Config.ARGB_8888);
        mBitmapCanvas = new Canvas(mBitmap);
    }
}
