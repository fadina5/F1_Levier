package fr.dfl.f1_levier.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import fr.dfl.f1_levier.database.AppDatabase;
import fr.dfl.f1_levier.database.dao.EquipeDao;
import fr.dfl.f1_levier.database.entity.Equipe;

public class EquipeRepository {

    private EquipeDao equipeDao;

    private LiveData<List<Equipe>> equipes;

    public EquipeRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        equipeDao = database.equipeDao();
        equipes=equipeDao.getAll();
    }

    public LiveData<List<Equipe>> getAllEquipes() {

        return equipes;
    }

    public void insertEquipe(Equipe equipe) {
        new insertEquipeTask(equipeDao).execute(equipe);

    }

    private static class insertEquipeTask extends AsyncTask<Equipe, Void, Void> {

        private EquipeDao dao;

        insertEquipeTask(EquipeDao dao) { this.dao = dao; }


        @Override
        protected Void doInBackground(Equipe... params) {
            dao.insert(params[0]);
            return null;
        }
    }
}
