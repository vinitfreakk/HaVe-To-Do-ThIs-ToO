package com.accidentaldeveloper.havetodothistoo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.accidentaldeveloper.havetodothistoo.databinding.EachRvBinding;

public class RvAdapter extends ListAdapter<Notes,RvAdapter.ViewHolder> {
public RvAdapter(MainActivity mainActivity){
   super(CALLBACK);
}
private static DiffUtil.ItemCallback<Notes> CALLBACK = new DiffUtil.ItemCallback<Notes>() {
    @Override
    public boolean areItemsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
        return oldItem.getId()==newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
        return oldItem.getTitle().equals(newItem.getTitle() )&& oldItem.getDisp().equals(newItem.getDisp()) ;
    }
};
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_rv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      Notes notes = getItem(position);
      holder.binding.titlerv.setText(notes.getTitle());
      holder.binding.disprv.setText(notes.getDisp());
    }
    public Notes getNotes(int position){
    return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        EachRvBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=EachRvBinding.bind(itemView);
        }
    }
}
