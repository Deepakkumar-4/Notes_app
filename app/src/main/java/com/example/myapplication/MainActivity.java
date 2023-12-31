package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private NoteViewModel noteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("My Notes App");

        noteViewModel = new ViewModelProvider(MainActivity.this,
                (ViewModelProvider.Factory) ViewModelProvider
                        .AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(NoteViewModel.class);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        InsertDataActivity.class);
                intent.putExtra("type","Add Mode");
                startActivityForResult(intent , 1);

            }
        });


        binding.notesRv.setLayoutManager(new LinearLayoutManager(this));
        binding.notesRv.setHasFixedSize(true);

        recycler_view_adapter adapter = new recycler_view_adapter();
        binding.notesRv.setAdapter(adapter);

        noteViewModel.getAllData().observe(this, new Observer<List<notes_struc>>() {
            @Override
            public void onChanged(List<notes_struc> notesStrucs) {
                adapter.submitList(notesStrucs);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return 0;
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                if(direction ==ItemTouchHelper.LEFT){
                    noteViewModel.delete(adapter.getNote(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "note deleted", Toast.LENGTH_SHORT).show();

                }else{

                    Intent intent = new Intent(MainActivity.this,InsertDataActivity.class);
                    intent.putExtra("type","update");
                    intent.putExtra("title",adapter.getNote(viewHolder.getAdapterPosition()).getTitle());
                    intent.putExtra("Text",adapter.getNote(viewHolder.getAdapterPosition()).getText());
                    intent.putExtra("id",adapter.getNote(viewHolder.getAdapterPosition()).getId());
                    startActivityForResult(intent,2);



                }
                 }
        }).attachToRecyclerView(binding.notesRv);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1){
            String title = data.getStringExtra("title");
            String note = data.getStringExtra("disp");

            notes_struc notes = new notes_struc(title, note);

            noteViewModel.insert(notes);

            Toast.makeText(this, "added", Toast.LENGTH_SHORT).show();
        }
        else  if(requestCode==2){
            String title = data.getStringExtra("title");
            String note = data.getStringExtra("disp");

            notes_struc notes = new notes_struc(title, note);
            notes.setId(data.getIntExtra("id",0));
            noteViewModel.update(notes);

            Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
        }

    }
}