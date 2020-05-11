package com.kidusmik.roomwordssample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }

    public void insert(Word word){
        new insertAsyncTask(mWordDao).execute(word);
    }

    public void deleteAll(){
        new deleteAllWordsAsyncTask(mWordDao).execute();
    }

    public void deleteWord(Word word){
        new deleteWordAsyncTask(mWordDao).execute(word);
    }

    private class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        public insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;

        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.insert(words[0]);
            return null;
        }
    }

    private class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void>{

        private WordDao mAsyncTaskDao;

        deleteAllWordsAsyncTask(WordDao dao){
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private class deleteWordAsyncTask extends AsyncTask<Word, Void, Void>{

        private WordDao mAsyncTaskDao;

        deleteWordAsyncTask(WordDao dao){
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.deleteWord(words[0]);
            return null;
        }
    }
}
