package im.ligas.remotefornode;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import java.util.concurrent.TimeUnit;

import im.ligas.remotefornode.ui.ConsoleTextView;
import im.ligas.remotefornode.ui.VerticalSeekBar;

public class MainActivity extends Activity {

    public static final String TAG = "MainActivity";
    public static final String EMPTY = "";

    private Integer speed = 0;
    private ConsoleTextView console;
    private EditText ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        console = (ConsoleTextView) findViewById(R.id.console);
        ip = (EditText) findViewById(R.id.ip);
        final VerticalSeekBar speedBar = (VerticalSeekBar) findViewById(R.id.speedBar);

        Button up = (Button) findViewById(R.id.up);
        up.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "UP");
                if (speed < 1000) {
                    speed += 10;
                    sendCommandToNode("/speed/" + speed);
                    speedBar.setProgress(speed/10);
                }
            }
        });

        Button down = (Button) findViewById(R.id.down);
        down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "DOWN");
                if (speed > 0) {
                    speed -= 10;
                    sendCommandToNode("/speed/" + speed);
                    speedBar.setProgress(speed/10);
                }
            }
        });
        Button left = (Button) findViewById(R.id.left);
        left.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "LEFT");
                sendCommandToNode("/left");
            }
        });
        Button right = (Button) findViewById(R.id.right);
        right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "RIGHT");
                sendCommandToNode("/right");
            }
        });
        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "START");
                sendCommandToNode("/start");
            }
        });
        Button stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "STOP");
                sendCommandToNode("/stop");
            }
        });

        speedBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i(TAG, "Speed Bar");
                speed = progress * 10;
                sendCommandToNode("/speed/" + speed);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }



    private void sendCommandToNode(String url) {
        String ipAddress = ip.getText().toString().trim();
        if (!EMPTY.equals(ipAddress)) {

            url = "http://" + ipAddress + url;
             AsyncTask<String, Void, String> execute = new SendCommand().execute(url);
            try {
                console.append(execute.get(1, TimeUnit.SECONDS));
            } catch (Exception e) {
                Log.e(TAG, "No response", e);
            }
        }
    }


}
