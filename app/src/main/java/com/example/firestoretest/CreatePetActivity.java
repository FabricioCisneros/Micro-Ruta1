package com.example.firestoretest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreatePetActivity extends AppCompatActivity {
    Button btn_add;
    EditText Nombre,Especie,Edad;
    private FirebaseFirestore mfirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pet);
        this.setTitle("Crear Mascota");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mfirestore=FirebaseFirestore.getInstance();

        Nombre=(EditText)findViewById(R.id.nombre);
        Especie=(EditText)findViewById(R.id.especie);
        Edad=(EditText)findViewById(R.id.edad);
        btn_add=findViewById(R.id.btn_addInsert);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namePet = Nombre.getText().toString();
                String typePet= Especie.getText().toString();
                String agePet = Edad.getText().toString();

                if(namePet.isEmpty()||typePet.isEmpty()||agePet.isEmpty()){
                    Toast.makeText(getApplicationContext(), "ingresar todos los datos", Toast.LENGTH_SHORT).show();
                }else{
                    InsertPet(namePet,typePet,agePet);
                }
            }
        });

    }
    public void InsertPet(String name, String kind, String age){
        String fecha="120394";
        CollectionReference pet = mfirestore.collection("pet");
        Map<String,Object> map =new HashMap<>();
        map.put("Nombre", name);
        map.put("Especie",kind);
        map.put("Edad", age);

        Map<String,Object> submap =new HashMap<>();
        submap.put("vacuna", "si");
        submap.put("desparacitacion","si");
        map.put("extras",submap);

        pet.document(fecha).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Conexion exitosa con la base de datos", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error con la base de datos", Toast.LENGTH_SHORT).show();
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
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}