package fr.dfl.f1_levier.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import fr.dfl.f1_levier.R;
import fr.dfl.f1_levier.database.entity.Course;
import fr.dfl.f1_levier.viewmodel.CourseViewModel;

public class ChronometerActivity extends AppCompatActivity implements View.OnClickListener {

    private CourseViewModel courseViewModel;


    private Chronometer mChronometer;
    private Button mButtonStart;
    private Button mButtonLapTime;
    private Button mButtonStop;
    private TextView timeMessage;
    private int participantIndex;
    private int previousTime;
    String msg;
    private static final int SPRINT1 = 1;
    private static final int FRACTION1 = 2;
    private static final int PIT_STOP = 3;
    private static final int SPRINT2 = 4;
    private static final int FRACTION2 = 5;
    private int tour;




    Course newCourse;

    private long lastTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer);
           courseViewModel = ViewModelProviders.of(this).get(CourseViewModel.class);
        courseViewModel = ViewModelProviders.of(this).get(CourseViewModel.class);
        initView();

    }

    private void initView(){
        mChronometer = (Chronometer) findViewById(R.id.chronometer);
        mButtonStart = (Button) findViewById(R.id.buttonStart);
        mButtonStop = (Button) findViewById(R.id.buttonStop);
        timeMessage = findViewById(R.id.timetextView);

        mButtonStart.setOnClickListener(this);
        mButtonStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonStart:
                OnChronometerButtonClick();
                break;
            case R.id.buttonStop:
                mChronometer.stop();
                break;
            default:
                break;
        }

        }


    public void OnChronometerButtonClick()
        {
            setTemps();
        }

        public void setTemps() {
            switch (tour) {
                case 0:
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                    mChronometer.start();
                    mButtonStart.setText(R.string.sprint1);
                    newCourse = new Course();
                    tour = SPRINT1;
                    break;

                case SPRINT1:
                    long timeElapsedFromBeginning = SystemClock.elapsedRealtime() - mChronometer.getBase();
                    long lapTime = timeElapsedFromBeginning - lastTime;
                    lastTime = timeElapsedFromBeginning;
                    Log.d("laptime", String.valueOf(lapTime));
                    newCourse.setSprint1(computeSubstepTime(tour));
                    mButtonStart.setText(R.string.fraction1);
                    msg = "Sprint 1 = " + convertTime(newCourse.getSprint1());
                    timeMessage.setText(msg);
                    tour = FRACTION1;
                    break;

                case FRACTION1:
                    newCourse.setFraction1(computeSubstepTime(tour));
                    mButtonStart.setText(R.string.pit_stop);
                    msg = "Fraction 1 = " + convertTime(newCourse.getFraction1());
                    timeMessage.setText(msg);
                    tour=PIT_STOP;
                    break;
                case PIT_STOP:
                    newCourse.setPitStop(computeSubstepTime(tour));
                    mButtonStart.setText(R.string.sprint2);
                    msg = "Pit stop  = " + convertTime(newCourse.getPitStop());
                    timeMessage.setText(msg);
                    tour=SPRINT2;
                    break;
                case SPRINT2:
                    newCourse.setSprint2(computeSubstepTime(tour));
                    mButtonStart.setText(R.string.fraction2);
                    msg = "Sprint 2 = " + convertTime(newCourse.getSprint2());
                    timeMessage.setText(msg);
                    tour=FRACTION2;
                    break;
                case FRACTION2:
                    newCourse.setFraction2(computeSubstepTime(tour));
                    msg = "Fraction 2 = " + convertTime(newCourse.getFraction2());
                    timeMessage.setText(msg);
                    mButtonStart.setText(R.string.termine);
                    mChronometer.stop();
                    msg = "Terminé";

                    break;


            }
        }


    public long computeSubstepTime(int course) {
        int tempsEcoule = (int) (SystemClock.elapsedRealtime() - mChronometer.getBase());
        int temps = tempsEcoule - previousTime;
        if (course == FRACTION2) {
            previousTime = 0;
        } else {
            previousTime = tempsEcoule;
        }
        return (long) temps;
    }

    public static String convertTime(float temps) {
        int tps = (int) temps;
        int ms = tps % 1000;

        String msg = String.format(Locale.FRANCE, "%d : %d.%d",
                TimeUnit.MILLISECONDS.toMinutes(tps),
                TimeUnit.MILLISECONDS.toSeconds(tps) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(tps)), ms
        );
        if (TimeUnit.MILLISECONDS.toMinutes(tps) == 0) {
            msg = "0" + msg;
        }
        return msg;
    }



    }
