package ro.pub.cs.systems.eim.lab05.startedservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.content.Context;

import ro.pub.cs.systems.eim.lab05.startedservice.general.Constants;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class StartedService extends Service {


    /*
    private ServiceHandler serviceHandler;

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message message) {
            // ...


            Log.d(Constants.TAG, "Thread.run() was invoked, PID: "
                    + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
            stopSelf(message.arg1);
        }
    }
    */
    public class ProcessingThread extends Thread {

        private Context context;

        public ProcessingThread(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            while(true){
                sendMessage(Constants.MESSAGE_STRING);
                //Log.d(Constants.TAG, "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
                sleep();
                sendMessage(Constants.MESSAGE_INTEGER);
                //Log.d(Constants.TAG, "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
                sleep();
                sendMessage(Constants.MESSAGE_ARRAY_LIST);
                Log.d(Constants.TAG, "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
                sleep();
            }
        }

        private void sleep() {
            try {
                Thread.sleep(Constants.SLEEP_TIME);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }

        private void sendMessage(int messageType) {
            Intent intent = new Intent();
            switch(messageType) {
                case Constants.MESSAGE_STRING:
                    intent.setAction(Constants.ACTION_STRING);
                    intent.putExtra(Constants.DATA, Constants.STRING_DATA);
                    break;
                case Constants.MESSAGE_INTEGER:
                    intent.setAction(Constants.ACTION_INTEGER);
                    intent.putExtra(Constants.DATA, Constants.INTEGER_DATA);
                    break;
                case Constants.MESSAGE_ARRAY_LIST:
                    intent.setAction(Constants.ACTION_ARRAY_LIST);
                    intent.putExtra(Constants.DATA, Constants.ARRAY_LIST_DATA);
                    break;
                default:
                    break;
            }

            context.sendBroadcast(intent);
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(Constants.TAG, "onCreate() method was invoked");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(Constants.TAG, "onBind() method was invoked");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(Constants.TAG, "onUnbind() method was invoked");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(Constants.TAG, "onRebind() method was invoked");
    }

    @Override
    public void onDestroy() {
        Log.d(Constants.TAG, "onDestroy() method was invoked");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(Constants.TAG, "onStartCommand() method was invoked");
        // TODO: exercise 5 - implement and start the ProcessingThread

        ProcessingThread procThread = new ProcessingThread(getApplicationContext());
        procThread.start();


        int counter = 0;

        /*
        while (true) {
            Message message = serviceHandler.obtainMessage();
            message.arg1 = Constants.ACTION_STRING;
            message.
            //Thread.sleep(Constants.SLEEP_TIME);
        }
        */
        return START_REDELIVER_INTENT;
    }

}
