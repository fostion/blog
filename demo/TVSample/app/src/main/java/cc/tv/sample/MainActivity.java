package cc.tv.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cc.tv.sample.adapter.SectionAdapter;
import cc.tv.sample.adapter.TabAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView secctionView = (RecyclerView)findViewById(R.id.recyclerView1);
        RecyclerView tabView = (RecyclerView)findViewById(R.id.recyclerView2);


        List<String> sectionList = new ArrayList<>();
        sectionList.add("1");
        sectionList.add("1");
        sectionList.add("1");
        sectionList.add("1");
        sectionList.add("1");
        sectionList.add("1");
        sectionList.add("1");
        sectionList.add("1");
        sectionList.add("1");
        sectionList.add("1");
        sectionList.add("1");
        sectionList.add("1");
        sectionList.add("1");
        sectionList.add("1");
        sectionList.add("1");
        sectionList.add("1");

        List<String> tabList = new ArrayList<>();
        tabList.add("2");
        tabList.add("2");
        tabList.add("2");
        tabList.add("2");
        tabList.add("2");
        tabList.add("2");


        SectionAdapter sectionAdapter = new SectionAdapter(sectionList);
        TabAdapter tabAdapter = new TabAdapter(tabList);

        secctionView.setLayoutManager(new LinearLayoutManager(this));
        secctionView.setAdapter(sectionAdapter);

        tabView.setAdapter(tabAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        tabView.setLayoutManager(linearLayoutManager);


    }

    public void toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.e("--dispatchKeyEvent--","递归向下传递KeyEvent事件");
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Log.e("--key code--",""+keyCode);

        switch (keyCode){
            case KeyEvent.KEYCODE_DPAD_UP:
                toast("向上");
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                toast("向下");
                break;

            case KeyEvent.KEYCODE_DPAD_LEFT:
                toast("向左");
                break;

            case KeyEvent.KEYCODE_DPAD_RIGHT:
                toast("向右");
                break;

            case KeyEvent.KEYCODE_HOME:
                toast("Home键");
                break;

            case KeyEvent.KEYCODE_MENU:
                toast("菜单键");
                break;

            case KeyEvent.KEYCODE_BACK:
                toast("后退键");
                break;
        }

        return super.onKeyDown(keyCode, event);
    }
}
