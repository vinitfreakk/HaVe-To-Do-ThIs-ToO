package com.accidentaldeveloper.havetodothistoo;

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

import com.accidentaldeveloper.havetodothistoo.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
     ActivityMainBinding binding;
     private NotesViewModel notesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notesViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NotesViewModel.class);
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DataInsertActivity.class);
                intent.putExtra("type","addMode");
                startActivityForResult(intent,1);
            }
        });
         binding.Rv.setLayoutManager(new LinearLayoutManager(this));
         binding.Rv.setHasFixedSize(true);
         RvAdapter adapter = new RvAdapter(MainActivity.this);//edhar change kiya hai phele constructor empty tha
         binding.Rv.setAdapter(adapter);

         notesViewModel.getAllNotes().observe(this, new Observer<List<Notes>>() {
             @Override
             public void onChanged(List<Notes> notes) {
                 adapter.submitList(notes);
             }
         });
         new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
             @Override
             public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                 return false;
             }

             @Override
             public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                 if(direction==ItemTouchHelper.RIGHT){
                     notesViewModel.delete(adapter.getNotes(viewHolder.getAdapterPosition()));
                     Toast.makeText(MainActivity.this, "Task Deleted", Toast.LENGTH_SHORT).show();

                 }
                 else{
                         Intent intent = new Intent(getApplicationContext(),DataInsertActivity.class);
                         intent.putExtra("type","update");
                         intent.putExtra("Title",adapter.getNotes(viewHolder.getAdapterPosition()).getTitle());
                     intent.putExtra("disp",adapter.getNotes(viewHolder.getAdapterPosition()).getDisp());
                     intent.putExtra("id",adapter.getNotes(viewHolder.getAdapterPosition()).getId());
                     startActivityForResult(intent,2);
                 }

             }
         }).attachToRecyclerView(binding.Rv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            String Title = data.getStringExtra("Title");
            String Disp = data.getStringExtra("Disp");
            Notes notes = new Notes(Title,Disp);
            notesViewModel.insert(notes);
            Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode==2){
            String Title = data.getStringExtra("Title");
            String Disp = data.getStringExtra("Disp");
            Notes notes = new Notes(Title,Disp);
            notes.setId(data.getIntExtra("id",0));
            notesViewModel.update(notes);
            Toast.makeText(this, "Task Updated", Toast.LENGTH_SHORT).show();
        }
    }
}