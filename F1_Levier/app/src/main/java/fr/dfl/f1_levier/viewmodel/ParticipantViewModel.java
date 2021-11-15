package fr.dfl.f1_levier.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import fr.dfl.f1_levier.database.entity.Participant;
import fr.dfl.f1_levier.repository.ParticipantRepository;

public class ParticipantViewModel extends AndroidViewModel {

    private ParticipantRepository participantRepository;

    public ParticipantViewModel(@NonNull Application application) {
        super(application);
        participantRepository = new ParticipantRepository(application);
    }

    public void insertParticipant(Participant participant) {
        participantRepository.insertParticipant(participant);
    }


    public LiveData<List<Participant>> getAllParticipantsByIdEquipe(int idEquipe) {
        return participantRepository.getAllParticipantsByIdEquipe(idEquipe);
    }
}
