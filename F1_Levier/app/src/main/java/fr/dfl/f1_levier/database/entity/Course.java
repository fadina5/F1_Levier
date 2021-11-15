package fr.dfl.f1_levier.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "COURSE",
        indices = {@Index(value = "ID_PARTICIPANT")},
        foreignKeys = @ForeignKey(entity = Participant.class,
                parentColumns = "ID",
                childColumns = "ID_PARTICIPANT",
                onDelete = ForeignKey.CASCADE))
public class Course {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID_COURSE")
    private int id;

    @ColumnInfo(name = "ID_PARTICIPANT")
    private int idParticipant;

    @ColumnInfo(name = "SPRINT1")
    private long sprint1;

    @ColumnInfo(name = "FRACTION1")
    private long fraction1;

    @ColumnInfo(name = "PIT_STOP")
    private long pitStop;

    @ColumnInfo(name = "SPRINT2")
    private long sprint2;

    @ColumnInfo(name = "FRACTION2")
    private long fraction2;

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public int getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(int idParticipant) {
        this.idParticipant = idParticipant;
    }

    public long getSprint1() {
        return sprint1;
    }

    public void setSprint1(long sprint1) {
        this.sprint1 = sprint1;
    }

    public long getFraction1() {
        return fraction1;
    }

    public void setFraction1(long fraction1) {
        this.fraction1 = fraction1;
    }

    public long getPitStop() {
        return pitStop;
    }

    public void setPitStop(long pitStop) {
        this.pitStop = pitStop;
    }

    public long getSprint2() {
        return sprint2;
    }

    public void setSprint2(long sprint2) {
        this.sprint2 = sprint2;
    }

    public long getFraction2() {
        return fraction2;
    }

    public void setFraction2(long fraction2) {
        this.fraction2 = fraction2;
    }
}
