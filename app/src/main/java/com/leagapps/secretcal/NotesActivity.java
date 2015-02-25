package com.leagapps.secretcal;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nhaarman.listviewanimations.itemmanipulation.OnDismissCallback;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.SwipeDismissAdapter;

import java.util.List;

/**
 * Created by Sam on 7/16/2014.
 */
public class NotesActivity extends Activity implements View.OnClickListener {
    LinearLayout NewNoteButton;
    TextView Heading;
    NotesListAdapter adapter;
    List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_activity);

        Heading = (TextView) findViewById(R.id.NotesActivity_Heading);
        NewNoteButton = (LinearLayout) findViewById(R.id.new_note_button);
        NewNoteButton.setOnClickListener(this);

        Typeface Bebas = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue.otf");
        Heading.setTypeface(Bebas);

        FragmentTransaction trans = getFragmentManager().beginTransaction();
        notes = Note.listAll(Note.class);
        /*for(Note note : notes) {
            Fragment noteItem = new NoteListItem();
            Bundle args = new Bundle();
            args.putInt(NoteListItem.Passed_Title, note.Number);
            args.putString(NoteListItem.Passed_Description, note.NoteText);
            noteItem.setArguments(args);

            trans.add(R.id.notes_list_container, noteItem);
        }
        trans.commit();
*/

        ListView listview = (ListView) findViewById(R.id.notes_list);
        adapter = new NotesListAdapter(this, notes);

        listview.setAdapter(adapter);

        getActionBar().hide();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.new_note_button:
                Intent NewNote = new Intent(this, NewNoteActivity.class);
                NewNote.putExtra(NewNoteActivity.Mode, NewNoteActivity.Mode_New);
                startActivity(NewNote);

                NotesActivity.this.finish();
                break;
        }
    }
}

