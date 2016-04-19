package com.daililol.material.appbase.base;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

abstract public class BaseFragment extends Fragment{

    private boolean hasDestroyed = false;



    /**
     * Initialize a background task.
     * onDoTaskInBackground(Object... params) will be involved and run in a background thread.
     * @param params whatever you want
     */
    protected void doTaskInBackground(Object... params){

        AsyncTask asyncTask = new AsyncTask<Object, Integer, Object>(){

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

        };

        if (Build.VERSION.SDK_INT >= 11){
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
        }else{
            asyncTask.execute(params);
        }
    }


    /**
     * This method will be involved by call doTaskInBackground(Object...)
     * This method always runs in a background thread.
     * @param params Objects
     * @return Whatever you want
     */
    protected abstract Object onDoTaskInBackground(Object... params);


    /**
     * This method will be called by issuing one of below methods
     * sendUiMessage(int what, int arg1, int arg2, Object obj)
     * sendUiMessage(int what, int arg1, int arg)
     * sendUiMessage(int what, Object obj)
     * sendUiMessage(int what)
     * @param msg
     */
    abstract protected void handleUiMessage(Message msg);


    /**
     * Send a UI message. handleUiMessage(Message msg) will be involved
     * @param what
     * @param arg1
     * @param arg2
     * @param obj
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

    public void sendUiMessage(int what, int arg1, int arg2){
        sendUiMessage(what, arg1, arg2, null);
    }

    public void sendUiMessage(int what, Object obj){
        sendUiMessage(what, 0, 0, obj);
    }

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
    public void onResume(){
        super.onResume();
        hasDestroyed = false;
    }

    @Override
    public void onDestroy(){
        hasDestroyed = true;
        uiMessageHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

}
