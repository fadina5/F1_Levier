package fr.dfl.f1_levier.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "PARTICIPANT",
        indices = {@Index(value = "ID_EQUIPE")},
        foreignKeys = @ForeignKey(entity = Equipe.class,
                parentColumns = "ID",
                childColumns = "ID_EQUIPE",
                onDelete = ForeignKey.SET_NULL,
                onUpdate = ForeignKey.CASCADE))
public class Participant {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;

    @ColumnInfo(name = "NOM")
    private String nom;

    @ColumnInfo(name = "ECHELON")
    private int echelon;

    @ColumnInfo(name = "ID_EQUIPE")
    private int idEquipe;

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", echelon=" + echelon +
                ", idEquipe=" + idEquipe +
                '}';
    }

    public Participant(String nom, int echelon, int idEquipe) {
        this.nom = nom;
        this.echelon = echelon;
        this.idEquipe = idEquipe;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEchelon() {
        return echelon;
    }

    public void setEchelon(int echelon) {
        this.echelon = echelon;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }
}
