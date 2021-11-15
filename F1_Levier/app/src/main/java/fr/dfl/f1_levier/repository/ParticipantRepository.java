package fr.dfl.f1_levier.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import fr.dfl.f1_levier.database.AppDatabase;
import fr.dfl.f1_levier.database.dao.ParticipantDao;
import fr.dfl.f1_levier.database.entity.Participant;

public class ParticipantRepository {

    private ParticipantDao participantDao;


    public ParticipantRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        participantDao = database.participantDao();
    }

    public LiveData<List<Participant>> getAllParticipantsByIdEquipe(int idEquipe) {
        return participantDao.getAllByIdEquipe(idEquipe);
    }

    public void insertParticipant(Participant participant) {
        new insertParticipantTask(participantDao).execute(participant);
    }

    private static class insertParticipantTask extends AsyncTask<Participant, Void, Void> {

        private ParticipantDao dao;

        insertParticipantTask(ParticipantDao dao) { this.dao = dao; }

        @Override
        protected Void doInBackground(Participant... params) {
            dao.insert(params[0]);
            return null;
        }
    }
}
