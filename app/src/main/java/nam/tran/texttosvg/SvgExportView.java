package nam.tran.texttosvg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SvgExportView extends View {

    public Bitmap mBitmap = null;
    private Canvas mBitmapCanvas = null;
    private Paint mPaint;
    private TextPaint mPaintText;
    private Paint mPaintBorder;
    private Path mPathText;

    public SvgExportView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //Fixed parameters
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

        mPaintText = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        mPaintText.setColor(Color.BLACK);
        mPaintText.setTextSize(20);

        mPathText = new Path();

        mPaintBorder = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        resetDraw();
        String text = "写真の黒板を編集";
        mPaintText.getTextPath(text, 0, text.length(), w / 2, h / 2, mPathText);
        mBitmapCanvas.drawPath(mPathText, mPaintText);
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

    public File savebitmap() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        mBitmap.compress(Bitmap.CompressFormat.PNG, 60, bytes);
        File f = new File(Environment.getExternalStorageDirectory()
                + File.separator + "imageText.png");
        f.createNewFile();
        FileOutputStream fo = new FileOutputStream(f);
        fo.write(bytes.toByteArray());
        fo.close();
        return f;
    }

    private void resetDraw() {
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(),
                Bitmap.Config.ARGB_8888);
        mBitmapCanvas = new Canvas(mBitmap);
    }
}
