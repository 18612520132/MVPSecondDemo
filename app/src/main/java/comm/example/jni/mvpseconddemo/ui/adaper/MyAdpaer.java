package comm.example.jni.mvpseconddemo.ui.adaper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import comm.example.jni.mvpseconddemo.R;
import comm.example.jni.mvpseconddemo.service.enity.book;

public class MyAdpaer extends RecyclerView.Adapter<MyAdpaer.MyViewHolder>{
    private List<book.DataBean> list = new ArrayList<>();
    private Context context;

    public MyAdpaer(Context context) {
        this.context = context;
    }

    public void refresh(List<book.DataBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Glide.with(context).load(list.get(i).getPic()).into(myViewHolder.roundedimg);
        myViewHolder.tv.setText(list.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView roundedimg;
        TextView tv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedimg = itemView.findViewById(R.id.roundedview);
            tv = itemView.findViewById(R.id.tv);
        }

    }
}
