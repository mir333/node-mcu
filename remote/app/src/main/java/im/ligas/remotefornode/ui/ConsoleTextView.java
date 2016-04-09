package im.ligas.remotefornode.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by ligasm on 09/04/16.
 */
public class ConsoleTextView extends TextView {

    private Queue<String> queue = new ArrayDeque<>(5);

    public ConsoleTextView(Context context) {
        super(context);
    }

    public ConsoleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ConsoleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ConsoleTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void append(String newMsg) {
        super.beginBatchEdit();
        super.setText("");
        queue.offer(newMsg);

        for (String msg : queue) {
            if (msg != null) {
                super.append(msg);
                super.append("\n");
            }
        }
        super.endBatchEdit();
        if(queue.size() > 4) {
            queue.poll();
        }
    }
}
