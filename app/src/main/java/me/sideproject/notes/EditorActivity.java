package me.sideproject.notes;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.sideproject.notes.DataBase.EditorViewModel;
import me.sideproject.notes.DataBase.Note;

public class EditorActivity extends AppCompatActivity {
//Start Acivity

    EditorViewModel viewModel;
    @BindView(R.id.editText)
    EditText text;
    CollapsingToolbarLayout collapsingToolbarLayout;
    private boolean newNote;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);

        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.white));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_done_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        inilializeViewModel();
    }

    private void inilializeViewModel() {
        viewModel = ViewModelProviders.of(this).get(EditorViewModel.class);
        viewModel.note.observe(this, new Observer<Note>() {
            @Override
            public void onChanged(@Nullable Note note) {
                text.setText(note != null ? note.getText() : "");
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            newNote = true;
            collapsingToolbarLayout.setTitle("New Note");
        } else {
            collapsingToolbarLayout.setTitle("Edit Note");

            int id = bundle.getInt("ID");
            viewModel.loadData(id);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SaveNote();
    }

    private void SaveNote() {
        viewModel.saveNote(text.getText().toString());
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!newNote) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.menu_editor, menu);
        }
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            SaveNote();
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            viewModel.deleteNote();
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}
