package com.github.machadowma.listaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class GridActivity extends AppCompatActivity {
    public GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        gridView = (GridView) findViewById(R.id.gridView);

        carregarDados();
    }

    public void carregarDados(){
        ArrayList<Animal> animalArray = new ArrayList<Animal>();
        CustomGridAdapter customGridAdapter = new CustomGridAdapter(this, animalArray);
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

        gridView.setAdapter(customGridAdapter);
    }
}
