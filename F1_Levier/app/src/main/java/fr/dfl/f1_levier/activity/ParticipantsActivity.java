package fr.dfl.f1_levier.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import fr.dfl.f1_levier.R;
import fr.dfl.f1_levier.adapter.ParticipantsAdapter;
import fr.dfl.f1_levier.database.entity.Participant;
import fr.dfl.f1_levier.dialog.NewParticipantDialog;
import fr.dfl.f1_levier.viewmodel.ParticipantViewModel;

public class ParticipantsActivity extends AppCompatActivity {

    public final static String EXTRA_ID_EQUIPE = "idEquipe";

    private ParticipantViewModel participantViewModel;

    private int idEquipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        idEquipe = getIntent().getIntExtra(EXTRA_ID_EQUIPE, -1);

        if (idEquipe == -1)
            finish();

        participantViewModel = ViewModelProviders.of(this).get(ParticipantViewModel.class);

        RecyclerView participantsRecyclerView = findViewById(R.id.participants_recycler_view);
        final ParticipantsAdapter participantsAdapter = new ParticipantsAdapter();
        participantsRecyclerView.setAdapter(participantsAdapter);
        participantsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        participantViewModel.getAllParticipantsByIdEquipe(idEquipe).observe(this, new Observer<List<Participant>>() {
            @Override
            public void onChanged(@Nullable List<Participant> participants) {
                participantsAdapter.setParticipants(participants);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewParticipantDialog newParticipantDialog = NewParticipantDialog.newInstance(idEquipe);
                newParticipantDialog.show(getSupportFragmentManager(), "nouveau_participant");
            }
        });
    }

}
