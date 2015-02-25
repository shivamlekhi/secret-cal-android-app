package com.leagapps.secretcal;

import com.orm.SugarRecord;

/**
 * Created by Sam on 8/2/2014.
 */
public class Note extends SugarRecord<Note> {
    String NoteText;
    int Number;

    public Note() {

    }

    public Note (String NoteText, int Number) {
        this.NoteText = NoteText;
        this.Number = Number;
    }
}
