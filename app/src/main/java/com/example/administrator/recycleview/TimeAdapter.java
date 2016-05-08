package com.example.administrator.recycleview;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wt.wutang.main.entity.me.QNHistoryEntity;

import java.util.List;


/*******************************************************************************
 * Author  : Yankai
 * Date    : 2016-04-18 19:55
 * Email   : yk_yang@wesugarfree.com
 * Company : 上海无糖运动
 ******************************************************************************/
//   设备  Adapter

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.DeviceViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<QNHistoryEntity> list;
    private int indexBig;
    private View RView;
    private View lastView;

    /**
     * recycleView 没有onItemClick 事件，需要自定义事件
     */
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public TimeAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    //    因为设备可能一个一个搜索到，所以添加set数据方法，搜到一个刷新一次
    public void setQnData(List<QNHistoryEntity> list, int i) {
        indexBig = i;
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RView = inflater.inflate(R.layout.qn_item_time, parent, false);
        DeviceViewHolder h = new DeviceViewHolder(RView);
        h.setIsRecyclable(false);
        return h;
    }

    @Override
    public void onBindViewHolder(final DeviceViewHolder holder, final int position) {
        String strDate = list.get(position).createDate;
        if (strDate.length() > 5) {
            strDate = strDate.substring(5);
        }
        holder.textDate.setText("" + strDate);
        if (position == list.size() - 1) {
//            lastView = holder.relativeLayout.findViewById(R.id.img_ball_big);
//            lastView.setVisibility(View.VISIBLE);
//            lastView = holder.imgBall;
//            changBig(holder.imgBall);
        }
        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    lastView.setVisibility(View.GONE);
//                    lastView = v.findViewById(R.id.img_ball_big);
//                    lastView.setVisibility(View.VISIBLE);
//                    changSmall(lastView);
//                    lastView = v.findViewById(R.id.img_ball);
//                    changBig(lastView);
                    mOnItemClickLitener.onItemClick(holder.imgBall, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    //ViewHolder
    class DeviceViewHolder extends RecyclerView.ViewHolder {
        TextView textDate;
        View imgBall;
        View imgBall_big;
        RelativeLayout relativeLayout;

        public DeviceViewHolder(View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.item);
            textDate = (TextView) itemView.findViewById(R.id.tv_date);
            imgBall = itemView.findViewById(R.id.img_ball);
            imgBall_big = itemView.findViewById(R.id.img_ball_big);
        }

    }


    public void changSmall(View v) {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(v, "scaleX", 1.0f, 1.0f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(v, "scaleY", 1.0f, 1.0f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(500);
        animSet.setInterpolator(new LinearInterpolator());
        //两个动画同时执行
        animSet.playTogether(anim1, anim2);
        animSet.start();
    }

    public void changBig(View v) {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(v, "scaleX", 1.0f, 2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(v, "scaleY", 1.0f, 2f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(500);
        animSet.setInterpolator(new LinearInterpolator());
        //两个动画同时执行
        animSet.playTogether(anim1, anim2);
        animSet.start();
    }
}


