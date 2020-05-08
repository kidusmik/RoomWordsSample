package com.kidusmik.roomwordssample;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "word_table")
public class Word {
    private String mWord;

    public Word(@NonNull String mWord) {
        this.mWord = mWord;
    }

    public String getmWord() {
        return mWord;
    }
}
