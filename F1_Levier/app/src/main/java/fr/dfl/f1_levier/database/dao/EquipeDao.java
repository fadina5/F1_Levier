package fr.dfl.f1_levier.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fr.dfl.f1_levier.database.entity.Equipe;

@Dao
public interface EquipeDao {

    @Query("SELECT * FROM EQUIPE")
    LiveData<List<Equipe>> getAll();

    @Query("SELECT * FROM EQUIPE WHERE NOM_EQUIPE LIKE :nomEquipe LIMIT 1")
   Equipe findByName(String nomEquipe);

    @Insert
    void insert(Equipe equipe);

    @Delete
    void deleteAll(List<Equipe> equipes);

    @Delete
    void delete(Equipe equipe);
}
