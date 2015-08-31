package com.daililol.material.appbase.example;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.daililol.material.appbase.base.BaseListViewFragment;
import com.daililol.material.appbase.widget.ExtendableListView;

import java.util.Vector;

/**
 * Created by DennyShum on 8/21/15.
 */
public class ExampleListViewFragment extends BaseListViewFragment implements ListView.OnItemClickListener{


    private enum RequestType{
        LoadDataFromServer,
        LoadDataFromLocal
    }

    private enum RequestResult{
        ProgressUpdate,
        Success,
        Failed,
        Unknown

    }

    private Vector<String> dataSet;
    private ExampleListViewAdapter adapter;



    @Override
    protected Object onDoTaskInBackground(Object... params) {

        /** Simulating a non-ui-thread requests **/
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        RequestType requestType = (RequestType)params[0];

        if (requestType == RequestType.LoadDataFromServer){

            dataSet.add("Tabbed swipe view activity example");
            dataSet.add("Actionbar activity example");
            dataSet.add("SearchBar activity example");
            dataSet.add("Collapsing view activity example");


            //by calling sendUiMessage, handleUiMessage(Message msg) will be involved and
            //will be run in the UI thread
            this.sendUiMessage(requestType.ordinal(), RequestResult.Success.ordinal(), 0);

        }else if (requestType == RequestType.LoadDataFromLocal){
            //do something here
            this.sendUiMessage(requestType.ordinal(), RequestResult.Failed.ordinal(), 0);
        }

        return null;
    }

    @Override
    protected void onFragmentCreated(Bundle savedInstanceState) {
        getRefreshLayout().setEnabled(false);

        //by calling this method, onDoTaskInBackground(Object.. params) will
        //be involved and will be run in a background thread.
        this.doTaskInBackground(RequestType.LoadDataFromServer, "someParams", "anotherSomeParams");
    }

    @Override
    protected void setupListView(ExtendableListView listView) {
        listView.setOnItemClickListener(this);
        dataSet = new Vector<String>();
        adapter = new ExampleListViewAdapter(dataSet);
        listView.setAdapter(adapter);
        getEmptyView().setVisibility(View.GONE);
    }

    @Override
    protected void handleUiMessage(Message msg) {
        switch (RequestType.values()[msg.what]){
            case LoadDataFromServer:
                onRequestDataFromServerResult(msg);
                break;
            case LoadDataFromLocal:
                onRequestDataFromLocalResult(msg);
            default:
        }
    }

    private void onRequestDataFromServerResult(Message msg){
        switch (RequestResult.values()[msg.arg1]){
            case ProgressUpdate:
                //update progress state
                break;
            case Success:
                adapter.notifyDataSetChanged();
                getProgressBar().setVisibility(View.GONE);
                getRefreshLayout().setEnabled(true);
                break;
            case Failed:
            case Unknown:
            default:
        }

    }

    private void onRequestDataFromLocalResult(Message msg){
        switch (RequestResult.values()[msg.arg1]){
            case ProgressUpdate:
            case Success:
            case Failed:
            case Unknown:
            default:
            getRefreshLayout().setRefreshing(false);
        }

    }


    /**
     * This method will be involved when the ListView is pulled.
     */
    @Override
    public void onRefresh() {
        this.doTaskInBackground(RequestType.LoadDataFromLocal, "someParams", "anotherSomeParams");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position){
            case 0:
                ExampleTabbarActivity.launch(this.getActivity());
                break;
            case 1:
                ExampleActionbarActivity.launch(this.getActivity());
                break;
            case 2:
                ExampleSearchBarActivity.launch(this.getActivity());
                break;
            case 3:
                ExampleCollapsingViewActivity.launch((AppCompatActivity)this.getActivity());
                //ExampleCollapsingViewActivity.launchWithSharedElementTransition(this.getActivity(), view.findViewById(android.R.id.icon1));
            default:
        }
    }

}
