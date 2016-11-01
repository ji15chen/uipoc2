package com.example.dbman.ui.PowerIndicator.fragment.mediaBrowse;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dbman.ui.PowerIndicator.bean.ActorBean;
import com.example.dbman.ui.R;

import java.util.List;

class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<ActorBean> actors;

        private Context mContext;

        MyAdapter(Context context, List<ActorBean> actors) {
            this.mContext = context;
            this.actors = actors;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            ActorBean p = actors.get(i);
            viewHolder.mTextView.setText(p.name);
            viewHolder.mImageView.setImageDrawable(mContext.getDrawable(p.getImageResourceId(mContext)));
        }

        @Override
        public int getItemCount() {
            // 返回数据总数
            return actors == null ? 0 : actors.size();
        }


    }