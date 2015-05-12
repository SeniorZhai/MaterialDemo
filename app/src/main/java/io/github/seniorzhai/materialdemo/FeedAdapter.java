package io.github.seniorzhai.materialdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by zhaitao on 15/5/11.
 */
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    public FeedAdapter(Context context) {
        this.context = context;
    }

    // 创建View
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_feed, viewGroup, false);
        CellFeedViewHolder cellFeedViewHolder = new CellFeedViewHolder(view);
        return cellFeedViewHolder;
    }

    //数据与界面绑定
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        runEnterAnimation(viewHolder.itemView, position);
    }

    // 数据的数量
    @Override
    public int getItemCount() {
        return count;
    }

    // 持有Item的每个元素
    public static class CellFeedViewHolder extends RecyclerView.ViewHolder {
        public CellFeedViewHolder(View itemView) {
            super(itemView);
        }
    }


    private int lastAnimatedPosition = -1;
    private boolean animateItems = false;

    private void runEnterAnimation(View view, int position) {
        if (position >= 2 || !animateItems) {
            return;
        }
        if (position > lastAnimatedPosition) {
            view.setTranslationY(Utils.getScreenHeight(context));
            view.animate()
                    .translationY(0)
                    .setInterpolator(new DecelerateInterpolator(3f))
                    .setDuration(700)
                    .start();
        } else {
            animateItems = false;
        }
    }

    private int count;

    public void updateItems(boolean animated) {
        count = 10;
        animateItems = animated;
        notifyDataSetChanged();
    }
}
