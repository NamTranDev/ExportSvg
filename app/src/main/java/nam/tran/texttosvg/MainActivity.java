package nam.tran.texttosvg;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    SvgExportView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.svg);
    }

    @Override
    protected void onStop() {
        super.onStop();

        try {
            String svg = ImageTracerAndroid.imageToSVG(view.getBitmap(), null, null);
            Log.d("SVGGGGGGGGGGGGG", svg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
