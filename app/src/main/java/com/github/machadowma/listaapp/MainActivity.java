package com.github.machadowma.listaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button listView, gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (Button) findViewById(R.id.buttonList);
        gridView = (Button) findViewById(R.id.buttonGrid);

        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTela("listView");
            }
        });

        gridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTela("gridView");
            }
        });
    }

    public void abrirTela(String tela){
        Intent intent;
        switch(tela){
            case "listView":
                intent = new Intent(this,ListActivity.class);
                startActivity(intent);
                break;
            case "gridView":
                intent = new Intent(this,GridActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
