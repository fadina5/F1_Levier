package fr.dfl.f1_levier.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;

import java.util.List;

import fr.dfl.f1_levier.database.entity.Course;

@Dao
public interface CourseDao {

    @Query("SELECT * FROM COURSE")
    List<Course> getAll();

    @Insert
    void insert(Course course);

    @Delete
    void delete(Course course);

    @Query("SELECT *, MAX(ID_COURSE) FROM COURSE WHERE ID_PARTICIPANT = :participantId")
    Course getParticipantLastCourse(int participantId);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM COURSE WHERE ID_PARTICIPANT = :participantId")
    List<Course> getCourseByParticipant(int participantId);

}

