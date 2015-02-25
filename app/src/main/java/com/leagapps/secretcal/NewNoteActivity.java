package com.leagapps.secretcal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Sam on 8/2/2014.
 */
public class NewNoteActivity extends Activity {
    EditText Number, NoteText;
    TextView SaveButton;
    LinearLayout BackButton;

    public static final String Mode = "Mode";
    public static final String Mode_New = "Mode_New";
    public static final String Mode_Edit = "Mode_Edit";

    public static final String Passed_Number = "Passed_Number";
    public static final String Passed_Text = "Passed_Text";

    private String CurrentNoteNumber;
    private String CurrentNoteText;
    Intent NotesActivity;

    Note CurrentNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_note_activity);

        getActionBar().hide();

        NotesActivity = new Intent(NewNoteActivity.this, NotesActivity.class);


        SaveButton = (TextView) findViewById(R.id.save_note_button);

        Typeface Bosun = Typeface.createFromAsset(getAssets(), "fonts/bosun03.otf");
        Typeface Bebas = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue.otf");

        Number = (EditText) findViewById(R.id.new_note_number);
        NoteText = (EditText) findViewById(R.id.new_note_text);
        BackButton = (LinearLayout) findViewById(R.id.new_note_back_button);

        Number.setTypeface(Bebas);
        NoteText.setTypeface(Bosun);

        String SelectedMode = getIntent().getExtras().getString(Mode);

        if(SelectedMode.equals(Mode_Edit)) {
            Bundle extras = getIntent().getExtras();
            CurrentNoteNumber = String.valueOf(extras.getInt(Passed_Number));
            CurrentNoteText = extras.getString(Passed_Text);

            List<Note> notes = Note.find(Note.class, "NUMBER = ? and NOTE_TEXT = ?", CurrentNoteNumber, CurrentNoteText);
            Note note = notes.get(0);
            CurrentNote = note;

            Number.setText(String.valueOf(note.Number));
            NoteText.setText(note.NoteText);
        }

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // New Note Back Button
                if (getIntent().getExtras().getString(Mode).equals(Mode_Edit)) {
                    if(CurrentNoteNumber.equals(Number.getText().toString()) && CurrentNoteText.equals(NoteText.getText().toString())) {
                        NewNoteActivity.this.finish();
                        startActivity(NotesActivity);
                    }
                } else if (getIntent().getExtras().getString(Mode).equals(Mode_New) && Number.getText().toString().equals("") && NoteText.getText().toString().equals("")) {
                        NewNoteActivity.this.finish();
                        startActivity(NotesActivity);
                } else {
                    new AlertDialog.Builder(NewNoteActivity.this)
                            .setTitle("Save Note")
                            .setMessage("Do you want to save this Note ? ")
                            .setIcon(null)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    SaveNote();
                                    NewNoteActivity.this.finish();
                                    startActivity(NotesActivity);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    NewNoteActivity.this.finish();
                                    startActivity(NotesActivity);
                                }
                            }).show();
                }
            }
        });


        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveNote();
            }
        });
    }

    private void SaveNote() {
        Intent notesIntent = new Intent(NewNoteActivity.this, NotesActivity.class);

        if (getIntent().getExtras().getString(Mode).equals(Mode_Edit)) {
            CurrentNote.delete();
            Note newNote = new Note(NoteText.getText().toString(), Integer.parseInt(Number.getText().toString()));
            newNote.save();
            startActivity(notesIntent);
            finish();
        } else {
            List<Note> notes = Note.find(Note.class, "NUMBER = ?", Number.getText().toString());
            if(notes.size() == 0) {
                Note newNote = new Note(NoteText.getText().toString(), Integer.parseInt(Number.getText().toString()));
                newNote.save();
                startActivity(notesIntent);
                finish();
            } else {
                Toast.makeText(this, "Choose Another Number, Already Taken", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NewNoteActivity.this.finish();
        startActivity(NotesActivity);
    }
}

