package com.dxbwf.wasif.wasif_dx_ball;

        import android.app.Activity;
        import android.content.pm.ActivityInfo;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.KeyEvent;
        import android.view.View;
        import android.view.View.OnKeyListener;
        import android.view.Window;
        import android.view.WindowManager;

 public class MainActivity extends Activity implements OnKeyListener {
    //StringBuilder builder = new StringBuilder();
    public static MainActivity con;
    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        switch(event.getAction())
        {
            case KeyEvent.ACTION_DOWN:
                //builder.append("down, ");
                break;
            case KeyEvent.ACTION_UP:
                //builder.append("up, ");
                break;
        }
    	/*builder.append(event.getKeyCode());
    	builder.append(", ");
    	builder.append((char) event.getUnicodeChar());
    	String text = builder.toString();*/
        Log.d("KeyTest", keyCode+"");
        //return event.getKeyCode() != KeyEvent.KEYCODE_BACK;
        return true;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        GameCanvas g= new GameCanvas(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        g.setOnKeyListener(this);
        g.setFocusableInTouchMode(true);
        g.requestFocus();
        setContentView(g);

        Helper helper=new Helper();
        con=MainActivity.this;
    }
}

