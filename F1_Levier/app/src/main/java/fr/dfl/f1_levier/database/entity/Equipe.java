package fr.dfl.f1_levier.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "EQUIPE")
public class Equipe {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;

    @NonNull
    @ColumnInfo(name = "NOM_EQUIPE")
    private String nomEquipe;


    public Equipe(@NonNull String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(@NonNull String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }
}
