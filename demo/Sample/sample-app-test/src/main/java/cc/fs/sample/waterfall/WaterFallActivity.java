package cc.fs.sample.waterfall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import java.util.ArrayList;
import java.util.List;
import cc.fs.sample.R;

/**
 * 瀑布流
 */
public class WaterFallActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    WaterFallAdapter adapter;
    List<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_waterfall);

        setupView();
        setupEvent();
        loadData();
    }

    private void setupView() {
        adapter = new WaterFallAdapter(list);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setupEvent() {
    }

    private void loadData() {
        list.add(200);
        list.add(150);
        list.add(60);
        list.add(380);
        list.add(120);
        list.add(220);
        list.add(90);
        list.add(300);
        list.add(250);
        list.add(120);
    }
}
