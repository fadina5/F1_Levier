package fr.dfl.f1_levier.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.Objects;

import fr.dfl.f1_levier.R;
import fr.dfl.f1_levier.database.entity.Equipe;
import fr.dfl.f1_levier.viewmodel.EquipeViewModel;

public class NewEquipeDialog extends AppCompatDialogFragment {

    private EditText nameEquipeEditText;

    private EquipeViewModel equipeViewModel;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_new_equipe, null);

        nameEquipeEditText = view.findViewById(R.id.equipe_nameEditText);

        equipeViewModel = ViewModelProviders.of(this).get(EquipeViewModel.class);


        builder.setView(view)
                .setTitle(R.string.new_equipe)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        equipeViewModel.insertEquipe(new Equipe(nameEquipeEditText.getText().toString()));
                    }
                });
        return builder.create();
    }
}
