package fr.dfl.f1_levier.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import fr.dfl.f1_levier.adapter.EquipesAdapter;
import fr.dfl.f1_levier.database.entity.Equipe;
import fr.dfl.f1_levier.dialog.NewEquipeDialog;
import fr.dfl.f1_levier.viewmodel.EquipeViewModel;

public class EquipesActivity extends AppCompatActivity implements EquipesAdapter.EquipeInteractionListener {

    EquipeViewModel equipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       equipeViewModel = ViewModelProviders.of(this).get(EquipeViewModel.class);

        RecyclerView equipeRecyclerView = findViewById(R.id.equipes_recycler_view);
        final EquipesAdapter equipesAdapter = new EquipesAdapter(this);
        equipeRecyclerView.setAdapter(equipesAdapter);
        equipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        equipeViewModel.getAllEquipe().observe(this, new Observer<List<Equipe>>() {
            @Override
            public void onChanged(@Nullable List<Equipe> equipes) {
                equipesAdapter.setEquipes(equipes);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewEquipeDialog newEquipeDialog = new NewEquipeDialog();
                newEquipeDialog.show(getSupportFragmentManager(), "nouvelle_equipe");
            }
        });
    }

    @Override
    public void onEquipeRowClicked(Equipe equipe) {
        Intent intent = new Intent(this, ParticipantsActivity.class);
        intent.putExtra(ParticipantsActivity.EXTRA_ID_EQUIPE, equipe.getId());
        startActivity(intent);
    }
}
