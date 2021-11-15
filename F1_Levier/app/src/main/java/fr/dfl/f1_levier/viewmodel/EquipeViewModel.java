package fr.dfl.f1_levier.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import fr.dfl.f1_levier.database.entity.Equipe;
import fr.dfl.f1_levier.repository.EquipeRepository;

public class EquipeViewModel extends AndroidViewModel {

    private EquipeRepository equipeRepository;

    private LiveData<List<Equipe>> equipes;

    public EquipeViewModel(@NonNull Application application) {
        super(application);
        equipeRepository = new EquipeRepository(application);
        equipes = equipeRepository.getAllEquipes();
    }

    public void insertEquipe(Equipe equipe) {
        equipeRepository.insertEquipe(equipe);
    }

    public LiveData<List<Equipe>> getAllEquipe() {
        return equipes;
    }
}
