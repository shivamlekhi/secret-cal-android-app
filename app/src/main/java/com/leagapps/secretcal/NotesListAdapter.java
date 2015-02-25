package com.leagapps.secretcal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Sam on 8/2/2014.
 */
public class NotesListAdapter extends ArrayAdapter<Note>{
    private final Context context;
    private final List<Note> notes;

    TextView Title, Description;

    public NotesListAdapter(Context context, List<Note> notes) {
        super(context, R.layout.notes_list_item, notes);
        this.context = context;
        this.notes = notes;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final Note CurNote = notes.get(position);
        View RowView = inflater.inflate(R.layout.notes_list_item, parent, false);

        Title = (TextView) RowView.findViewById(R.id.note_item_title);
        Description  = (TextView) RowView.findViewById(R.id.note_item_desc);

        Typeface Bebas = Typeface.createFromAsset(context.getAssets(), "fonts/BebasNeue.otf");
        Typeface Bosun = Typeface.createFromAsset(context.getAssets(), "fonts/bosun03.otf");

        final int Title_text = CurNote.Number;
        final String Description_text = CurNote.NoteText;

        Title.setTypeface(Bebas);
        Title.setText(String.valueOf(Title_text));

        Description.setText(Description_text);
        Description.setTypeface(Bosun);

        LinearLayout mainLayout = (LinearLayout) RowView.findViewById(R.id.note_item_main_layout);
        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editNote = new Intent(context, NewNoteActivity.class);
                Bundle extras = new Bundle();
                extras.putInt(NewNoteActivity.Passed_Number, Title_text);
                extras.putString(NewNoteActivity.Passed_Text, Description_text);
                extras.putString(NewNoteActivity.Mode, NewNoteActivity.Mode_Edit);
                editNote.putExtras(extras);
                context.startActivity(editNote);
            }
        });

        ImageView deleteNote = (ImageView) RowView.findViewById(R.id.delete_note_button);
        deleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Warning")
                        .setMessage("Do you really want Delete this Note?")
                        .setIcon(R.drawable.ic_launcher)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(context, "Note Deleted", Toast.LENGTH_SHORT).show();

                                CurNote.delete();
                                notes.remove(position);

                                remove(CurNote);

                            }})
                        .setNegativeButton("No", null).show();
            }
        });
        return RowView;
    }

}
