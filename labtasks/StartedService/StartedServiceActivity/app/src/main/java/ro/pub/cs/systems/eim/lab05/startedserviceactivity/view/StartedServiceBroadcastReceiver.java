package ro.pub.cs.systems.eim.lab05.startedserviceactivity.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import ro.pub.cs.systems.eim.lab05.startedserviceactivity.general.Constants;

public class StartedServiceBroadcastReceiver extends BroadcastReceiver {

    private TextView messageTextView;

    // TODO: exercise 9 - default constructor

    public StartedServiceBroadcastReceiver(TextView messageTextView) {
        this.messageTextView = messageTextView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: exercise 7 - get the action and the extra information from the intent
        // and set the text on the messageTextView
        String action = intent.getAction();
        String data = null;
        if (Constants.ACTION_STRING.equals(action)) {
            data = intent.getStringExtra(Constants.DATA);
        }
        else if (Constants.ACTION_INTEGER.equals(action)) {
            Integer integer = intent.getIntExtra(Constants.DATA, 0);
            data = integer.toString();
        }
        else if (Constants.ACTION_ARRAY_LIST.equals((action))) {
            data = intent.getStringArrayListExtra(Constants.DATA).toString();
        }
        else {

        }
        // ...
        if (messageTextView != null) {
            messageTextView.setText(messageTextView.getText().toString() + "\n" + data);
        }
        // TODO: exercise 9 - restart the activity through an intent
        // if the messageTextView is not available
    }
}
