package com.example.crypto_app;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Dialog extends AppCompatDialogFragment {

    private EditText editTextquantitaastar, editTextquantitabtt, editTextquantitaatom, editTextquantitadot, editTextquantitahuahua, editTextquantitajuno, editTextquantitaosmo, editTextquantitascrt;
    private DialogListener listener;

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AlertDialogStyle);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view).setTitle("Modifica quantità crypto").setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Modifica", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String dialogqastar = editTextquantitaastar.getText().toString();
                String dialogqbtt = editTextquantitabtt.getText().toString();
                String dialogqatom = editTextquantitaatom.getText().toString();
                String dialogqdot = editTextquantitadot.getText().toString();
                String dialogqhuahua = editTextquantitahuahua.getText().toString();
                String dialogqjuno = editTextquantitajuno.getText().toString();
                String dialogqosmo = editTextquantitaosmo.getText().toString();
                String dialogqscrt = editTextquantitascrt.getText().toString();


                listener.applyModify(dialogqastar, dialogqbtt, dialogqatom, dialogqdot, dialogqhuahua, dialogqjuno, dialogqosmo, dialogqscrt);

            }
        });
        editTextquantitaastar = view.findViewById(R.id.edittextastar);
        editTextquantitabtt = view.findViewById(R.id.edittextbtt);
        editTextquantitaatom = view.findViewById(R.id.edittextatom);
        editTextquantitadot = view.findViewById(R.id.edittextdot);
        editTextquantitahuahua = view.findViewById(R.id.edittexthuahua);
        editTextquantitajuno = view.findViewById(R.id.edittextjuno);
        editTextquantitaosmo = view.findViewById(R.id.edittextosmo);
        editTextquantitascrt = view.findViewById(R.id.edittextscrt);

        //editTextquantitaastar.setText("2.5");

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Devi implementare DialogListener");
        }

    }

    public interface DialogListener {
        void applyModify(String astar, String btt, String atom, String dot, String huahua, String juno, String osmo, String scrt);
    }
}

