package fr.dfl.f1_levier.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import fr.dfl.f1_levier.database.dao.CourseDao;
import fr.dfl.f1_levier.database.dao.EquipeDao;
import fr.dfl.f1_levier.database.dao.ParticipantDao;
import fr.dfl.f1_levier.database.entity.Course;
import fr.dfl.f1_levier.database.entity.Equipe;
import fr.dfl.f1_levier.database.entity.Participant;

@Database(entities = {Course.class, Equipe.class, Participant.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ParticipantDao participantDao();
    public abstract EquipeDao equipeDao();
    public abstract CourseDao courseDao();


    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "F1levier").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}