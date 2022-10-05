package com.example.firestoretest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreatePetFragment extends DialogFragment {
    Button btn_add;
    EditText Nombre,Especie,Edad;
    private FirebaseFirestore mfirestore;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_pet, container, false);
        mfirestore=FirebaseFirestore.getInstance();

        Nombre=v.findViewById(R.id.nombre);
        Especie=(EditText)v.findViewById(R.id.especie);
        Edad=(EditText)v.findViewById(R.id.edad);
        btn_add=v.findViewById(R.id.btn_addInsert);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namePet = Nombre.getText().toString();
                String typePet= Especie.getText().toString();
                String agePet = Edad.getText().toString();

                if(namePet.isEmpty()||typePet.isEmpty()||agePet.isEmpty()){
                    Toast.makeText(getContext(), "ingresar todos los datos", Toast.LENGTH_SHORT).show();
                }else{
                    InsertPet(namePet,typePet,agePet);
                }
            }
        });

        return v;
    }

    public void InsertPet(String name, String kind, String age){
        String fecha="120394";
        CollectionReference pet = mfirestore.collection("pet");
        Map<String,Object> map =new HashMap<>();
        Map<String,Object> especie =new HashMap<>();
        map.put("Nombre", name);
        map.put("Especie",kind);
        map.put("Edad", age);

        pet.document(fecha).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getContext(), "Conexion exitosa con la base de datos", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error con la base de datos", Toast.LENGTH_SHORT).show();
            }
        });
       /* mfirestore.collection("pet").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Conexion exitosa con la base de datos", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error con la base de datos", Toast.LENGTH_SHORT).show();
            }
        });*/

    }
}