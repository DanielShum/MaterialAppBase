package com.daililol.material.appbase.base;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

public abstract class BaseFragmentActivity extends AppCompatActivity{

    private boolean hasDestroyed = false;  //Use to indicate that if the activity has destroyed.


    /**
     * To shake a view
     * @param view
     */
	protected void shakeView(View view){
		float delta = 10.0f;
		CycleInterpolator cycle = new CycleInterpolator(6);
		AnimationSet animSet = new AnimationSet(false);
		
		Animation anim = new TranslateAnimation(0, delta, 0, 0);
		anim.setDuration(500);
		anim.setInterpolator(cycle);
		animSet.addAnimation(anim);
		view.startAnimation(animSet);
		
		try{
			Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			vibrator.vibrate(300);
		}catch(Exception e){}
		
	}



    /**
     * Initialize a background task.
     * onDoTaskInBackground(Object... params) will be involved and run in a background thread.
     * @param params whatever you want
     */
    protected void doTaskInBackground(Object... params){

        new AsyncTask<Object, Integer, Object>(){

            @Override
            protected Object doInBackground(Object... params) {
                // TODO Auto-generated method stub

                return onDoTaskInBackground(params);
            }

            @Override
            protected void onProgressUpdate(Integer... values) {

            }

            @Override
            protected void onPostExecute(Object result){

            }

        }.execute(params);
    }


    /**
     * This method will be involved by call doTaskInBackground(Object...)
     * This method always runs in a background thread.
     * @param params Objects
     * @return Whatever you want
     */
    protected abstract Object onDoTaskInBackground(Object... params);




     // TODO
     // Relationship between handleUiMessage and sendUiMessage
     // 1) You use message in a background thread via sendUiMessage.
     // 2) The uiMessageHandler (in foreground thread) will receive the message sent from the background thread.
     // 3) The uiMessageHandler will issue the message to handleUiMessage method.
     // 4) You do something with the message inside the handleUiMessage method.


    /**
     * This method will be involved when a message is sent via below methods
     * sendUiMessage(int what, int arg1, int arg2, Object obj)
     * sendUiMessage(int what, int arg1, int arg)
     * sendUiMessage(int what, Object obj)
     * sendUiMessage(int what)
     * @param msg
     */
    abstract protected void handleUiMessage(Message msg);


    /**
     * Send a message to the UI thread which will be handled by handleUiMessage(Message msg).
     * You should send messages in a background thread. Don't use this method to send message in foreground thread.
     * @param what  message identifier
     * @param arg1  argument 1
     * @param arg2  argument 2
     * @param obj   anything
     */
    public void sendUiMessage(int what, int arg1, int arg2, Object obj){
        if (hasDestroyed) return;
        Message msg = Message.obtain();
        msg.what = what;
        msg.arg1 = arg1;
        msg.arg2 = arg2;
        msg.obj = obj;
        uiMessageHandler.sendMessage(msg);
    }

    /**
     * Send a message to the UI thread which will be handled by handleUiMessage(Message msg).
     * You should send messages in a background thread. Don't use this method to send message in foreground thread.
     * @param what  message identifier
     * @param arg1  argument 1
     * @param arg2  argument 2
     */
    public void sendUiMessage(int what, int arg1, int arg2){
        sendUiMessage(what, arg1, arg2, null);
    }


    /**
     * Send a message to the UI thread which will be handled by handleUiMessage(Message msg).
     * You should send messages in a background thread. Don't use this method to send message in foreground thread.
     * @param what  message identifier
     * @param obj   anything
     */
    public void sendUiMessage(int what, Object obj){
        sendUiMessage(what, 0, 0, obj);
    }


    /**
     * Send an empty message to the UI thread which will be handled by handleUiMessage(Message msg).
     * You should send messages in a background thread. Don't use this method to send message in foreground thread.
     * @param what  message identifier
     */
    public void sendUiMessage(int what){
        sendUiMessage(what, null);
    }

    private Handler uiMessageHandler = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            if (hasDestroyed) return;
            handleUiMessage(msg);
        }
    };

    @Override
    public void onDestroy(){
        hasDestroyed = true;
        uiMessageHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
