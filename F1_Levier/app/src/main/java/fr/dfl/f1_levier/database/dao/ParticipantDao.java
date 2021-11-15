package fr.dfl.f1_levier.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import fr.dfl.f1_levier.database.entity.Participant;

@Dao
public interface ParticipantDao {

    @Query("SELECT * FROM PARTICIPANT")
    LiveData<List<Participant>> getAll();

    @Query("SELECT * FROM PARTICIPANT WHERE ID_EQUIPE = :idEquipe")
    LiveData<List<Participant>> getAllByIdEquipe(int idEquipe);


    @Query("SELECT * FROM PARTICIPANT WHERE ID_EQUIPE IS NULL")
    List<Participant> getParticipantsWithoutCourse();

    @Query("SELECT * FROM PARTICIPANT WHERE echelon LIKE  NOM LIKE :nom LIMIT 1")
    Participant findByName(String nom);

    @Insert
    void insert(Participant participant);

    @Query("UPDATE PARTICIPANT SET ID_EQUIPE = :groupeId WHERE ID = :participantId")
    void update(int participantId, int groupeId);

    @Delete
    void delete(Participant participant);
}
