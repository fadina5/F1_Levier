package fr.dfl.f1_levier.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import fr.dfl.f1_levier.R;
import fr.dfl.f1_levier.database.entity.Equipe;

public class EquipesAdapter extends RecyclerView.Adapter<EquipesAdapter.ViewHolder> {

    private List<Equipe> equipes = Collections.emptyList();

    private EquipeInteractionListener listener;

    public EquipesAdapter(EquipeInteractionListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public EquipesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.equipe_row, viewGroup, false);
        return new EquipesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EquipesAdapter.ViewHolder viewHolder, int i) {
        final Equipe equipe = equipes.get(i);

        viewHolder.nameTextView.setText(equipe.getNomEquipe());

        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onEquipeRowClicked(equipe);
            }
        });
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return equipes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private View view;

        private ViewHolder(View view) {
            super(view);
            this.view = view;
            nameTextView = view.findViewById(R.id.equipe_name_textView);
        }
    }

    public interface EquipeInteractionListener {
        public void onEquipeRowClicked(Equipe equipe);
    }
}
