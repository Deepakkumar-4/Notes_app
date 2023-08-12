package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.EachRvBinding;

public class recycler_view_adapter extends ListAdapter< notes_struc,recycler_view_adapter.ViewHolder>
{

    public recycler_view_adapter(){

        super(CALLBACK);
    }

    public static final DiffUtil.ItemCallback<notes_struc> CALLBACK = new DiffUtil.ItemCallback<notes_struc>() {
        @Override
        public boolean areItemsTheSame(@NonNull notes_struc oldItem, @NonNull notes_struc newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull notes_struc oldItem, @NonNull notes_struc newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getText().equals(newItem.getText());
        }
    };

    public  class ViewHolder extends RecyclerView.ViewHolder{
        EachRvBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = EachRvBinding.bind(itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_rv,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recycler_view_adapter.ViewHolder holder, int position) {

        notes_struc notesStruc = getItem(position);
        holder.binding.title.setText(notesStruc.getTitle());
        holder.binding.data.setText(notesStruc.getText());



    }
    public  notes_struc getNote(int position){
        return getItem(position);    }

}
