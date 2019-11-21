package me.sideproject.notes;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.sideproject.notes.DataBase.MainViewModel;
import me.sideproject.notes.DataBase.Note;
import me.sideproject.notes.UI.NotesAdapter;
import me.sideproject.notes.Utilities.Samples;

public class MainActivity extends AppCompatActivity {
    NotesAdapter adapter;
    MainViewModel viewModel;

    @BindView(R.id.list)
    RecyclerView recyclerView;
    List<Note> noteList = new ArrayList<>() ;

    @OnClick(R.id.fab)
    void startEdiorActivity() {
        Intent i = new Intent(this, EditorActivity.class);
        startActivity(i);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        intializeRecycleView();
        intializeViewModel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            addSamples();
            return true;
        } else if (id == R.id.deleteAll) {
            deleteALl();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteALl() {
    viewModel.DelelteAll() ;
    }

    private void addSamples() {
        viewModel.addSampleData();
    }

    private void intializeViewModel() {
        final Observer<List<Note>> observer = new Observer<List<Note>>() {
            @Override
            public void onChanged(@NotNull List<Note> notes) {
                noteList.clear();
                noteList.addAll(notes);
                if (adapter == null) {
                    adapter = new NotesAdapter(noteList, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                }else
                    adapter.notifyDataSetChanged();
            }
        };
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    viewModel.listOfNotes.observe(this,observer);
    }

    private void intializeRecycleView() {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
