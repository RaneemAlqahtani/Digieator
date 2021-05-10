package com.example.digieator;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.digieator.database.DatabaseHelper;
import com.example.digieator.database.model.Answer;
import com.example.digieator.utils.MyDividerItemDecoration;
import com.example.digieator.utils.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;


public class HistoryActivity extends AppCompatActivity {
    private AnswersAdapter mAdapter;
    private List<Answer> answersList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noNotesView;
    public BottomNavigationView buttom_nav_view;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);


        coordinatorLayout = findViewById(R.id.coordinator_layout);
        recyclerView = findViewById(R.id.recycler_view);
        noNotesView = findViewById(R.id.empty_notes_view);

        db = new DatabaseHelper(this);

        answersList.addAll(db.getAllAnswers());


        mAdapter = new AnswersAdapter(this, answersList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        toggleEmptyNotes();

        /**
         * On long press on RecyclerView item, open alert dialog
         * with options to choose
         * Edit and Delete
         * */
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
            }
        }));


        initButtomNavigationMenu();
    }

    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
    private void deleteNote(int position) {
        // deleting the note from db
        db.deleteAnswer(answersList.get(position));

        // removing the note from the list
        answersList.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptyNotes();
    }

    /**
     * Opens dialog with Edit - Delete options
     * Edit - 0
     * Delete - 0
     */
    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                deleteNote(position);

            }
        });
        builder.show();
    }


    /**
     * Toggling list and empty notes view
     */
    private void toggleEmptyNotes() {

        if (db.getAnswersCount() > 0) {
            noNotesView.setVisibility(View.GONE);
        } else {
            noNotesView.setVisibility(View.VISIBLE);
        }
    }


    private void initButtomNavigationMenu() {
        buttom_nav_view = (BottomNavigationView) findViewById(R.id.buttom_nav_view);
        buttom_nav_view.setItemIconTintList(null);
        buttom_nav_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.btm_nav_mem:
                        break;
                    case R.id.btm_nav_calculator:
                        Intent myIntent = new Intent(com.example.digieator.HistoryActivity.this,
                                com.example.digieator.MainActivity.class);
                        startActivity(myIntent);
                        break;

                    case R.id.btm_nav_colors:
                        Intent myIntent1 = new Intent(com.example.digieator.HistoryActivity.this,
                                com.example.digieator.ThemeActivity.class);
                        startActivity(myIntent1);
                        break;

                    case R.id.btm_nav_ins:
                        Intent myIntent2 = new Intent(com.example.digieator.HistoryActivity.this,
                                InstructionsActivity.class);
                        startActivity(myIntent2);
                        break;
                    case R.id.btm_nav_ref:
                        Intent myIntent3 = new Intent(com.example.digieator.HistoryActivity.this,
                                com.example.digieator.ReferencesActivity.class);
                        startActivity(myIntent3);
                        break;


                }
                return true;
            }
        });

    }



}//end of History Activity class
