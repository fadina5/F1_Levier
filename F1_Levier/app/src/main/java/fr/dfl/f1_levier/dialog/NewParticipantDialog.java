package fr.dfl.f1_levier.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.Objects;

import fr.dfl.f1_levier.R;
import fr.dfl.f1_levier.database.entity.Participant;
import fr.dfl.f1_levier.viewmodel.ParticipantViewModel;

public class NewParticipantDialog extends AppCompatDialogFragment {

    private static final String ARG_ID_EQUIPE = "idEquipe";

    private EditText nameEditText;
    private EditText echelonEditText;

    private ParticipantViewModel participantViewModel;

    private int idEquipe = -1;

    public static NewParticipantDialog newInstance(int idEquipe) {
        NewParticipantDialog dialog = new NewParticipantDialog();
        Bundle args = new Bundle();
        args.putInt(ARG_ID_EQUIPE, idEquipe);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
            idEquipe = getArguments().getInt(ARG_ID_EQUIPE);
        if (idEquipe == -1)
            dismiss();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_new_participant, null);

        nameEditText = view.findViewById(R.id.participant_name_editText);
        echelonEditText = view.findViewById(R.id.participant_echelon_editText);

        participantViewModel = ViewModelProviders.of(this).get(ParticipantViewModel.class);

        builder.setView(view)
                .setTitle(R.string.new_participant)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        participantViewModel.insertParticipant(new Participant(
                                nameEditText.getText().toString(), Integer.valueOf(
                                        echelonEditText.getText().toString()), idEquipe));
                    }
                });
        return builder.create();
    }
}
