package com.github.machadowma.listaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.listView);

        carregarDados();

    }

    public void carregarDados(){
        ArrayList<Animal> animalArray = new ArrayList<Animal>();
        CustomListAdapter customListAdapter = new CustomListAdapter(this, animalArray);
        Animal animal;

        animal = new Animal("Rex","cachorro");
        animalArray.add(animal);

        animal = new Animal("Babel","peixe");
        animalArray.add(animal);

        animal = new Animal("Bolinha","gato");
        animalArray.add(animal);

        animal = new Animal("Lassie","cachorro");
        animalArray.add(animal);

        animal = new Animal("Perna Longa","coelho");
        animalArray.add(animal);

        animal = new Animal("Pipoca","indefinida");
        animalArray.add(animal);

        listView.setAdapter(customListAdapter);
    }
}
