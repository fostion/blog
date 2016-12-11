package cc.fs.sample.waterfall;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import cc.fs.sample.R;

public class WaterFallAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Integer> list;
    private Context mContext;

    public WaterFallAdapter(List<Integer> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }

        return new ItemView(LayoutInflater.from(mContext).inflate(R.layout.item_waterfall, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemView) {
            ((ItemView) holder).bindView(position,list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ItemView extends RecyclerView.ViewHolder {

        LinearLayout rootView;

        public ItemView(View itemView) {
            super(itemView);

            rootView = (LinearLayout) itemView.findViewById(R.id.rootView);
        }

        public void bindView(int pos,int height) {
            itemView.setMinimumHeight(height);
            switch (pos%10){
                case 0:
                    itemView.setBackgroundColor(Color.RED);
                    break;
                case 1:
                    itemView.setBackgroundColor(Color.BLUE);
                    break;
                case 2:
                    itemView.setBackgroundColor(Color.GREEN);
                    break;
                case 3:
                    itemView.setBackgroundColor(Color.YELLOW);
                    break;
                case 4:
                    itemView.setBackgroundColor(Color.GRAY);

                    break;
                case 5:
                    itemView.setBackgroundColor(Color.argb(255,180,180,180));
                    break;
                case 6:
                    itemView.setBackgroundColor(Color.argb(255,25,180,10));

                    break;
                case 7:
                    itemView.setBackgroundColor(Color.argb(255,25,100,100));

                    break;
                case 8:
                    itemView.setBackgroundColor(Color.argb(255,100,100,25));

                    break;
                case 9:
                    itemView.setBackgroundColor(Color.argb(255,150,100,25));
                    break;
            }
        }
    }
}
