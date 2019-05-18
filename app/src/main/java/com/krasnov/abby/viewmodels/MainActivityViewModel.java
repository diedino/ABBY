package com.krasnov.abby.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.krasnov.abby.models.News;
import com.krasnov.abby.repositories.NewsRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<News>> mNews;
    private NewsRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if(mNews != null){
            return;
        }
        mRepo = NewsRepository.getInstance();
        mNews = mRepo.getNews();
    }

    public void addNewValue(final News news){
        mIsUpdating.setValue(true);
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<News> currentNews = mNews.getValue();
                currentNews.add(news);
                mNews.postValue(currentNews);
                mIsUpdating.postValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
    public LiveData<List<News>> getNews(){
        return mNews;
    }
    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}
