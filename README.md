# ListaApp
Exemplo de App Android que implementa ListView e GridView.
Nesse app, animais de diversas raças são exibidos em forma de lista ou grid.

<table>
<tr align=center>
<td><img src="https://github.com/machadowma/ListaApp/blob/master/main.png" align="left" height="360" width="180" ></td>
<td><img src="https://github.com/machadowma/ListaApp/blob/master/listview.png" align="left" height="360" width="180" ></td>
<td><img src="https://github.com/machadowma/ListaApp/blob/master/gridview.png" align="left" height="360" width="180" ></td>
</tr>
<tr align=center>
<td>Tela Inicial</td>
<td>ListView</td>
<td>GridView</td>
</tr>
</table>

# Declarando o modelo de dados
Tanto para criar ListView ou GridView, é necessário criar uma classe para representar um modelo do dado tratado. Essa classe pode ser a mesma utilizada em ambos os casos. Abaixo, é criada a classe Animal, para representar um animal na lista ou na grid.

```
public class Animal {
    String nome, raca;
    Integer drawableId;

    public Animal(String nome, String raca) {
        this.nome = nome;
        this.raca = raca;
        switch (raca){
            case "gato":
                this.drawableId = R.drawable.gato;
                break;
            case "cachorro":
                this.drawableId = R.drawable.cachorro;
                break;
            case "coelho":
                this.drawableId = R.drawable.coelho;
                break;
            case "peixe":
                this.drawableId = R.drawable.peixe;
                break;
            default:
                this.drawableId = android.R.drawable.ic_menu_report_image;
                break;
        }

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Integer getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(Integer drawableId) {
        this.drawableId = drawableId;
    }
}

```

# Implementando ListView

## Criar layout de cada linha
Criar um novo layout para especificar a posição dos elementos de cada item na tela.

<table>
<tr align=center>
<td><img src="https://github.com/machadowma/ListaApp/blob/master/item_list.png" align="left" height="240" width="400" ></td>
</tr>
<tr align=center>
<td>item_list.xml</td>
</tr>
</table>


```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/textViewNome"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="8dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        android:text="TextView"
        android:textSize="24sp"
        android:textStyle="bold"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/textViewRaca"
        android:layout_width="wrap_content"
        android:layout_height="22dp"
        android:layout_marginLeft="8dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        android:text="TextView"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textViewNome" />

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="50dp"
        android:layout_height="50dp"
        android:layout_marginEnd="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginTop="8dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@android:drawable/ic_menu_report_image" />
</android.support.constraint.ConstraintLayout>
```

## Criar um adaptador
Vamos criar uma nova classe chamada CustomListAdapter.java e estendê-la a partir de BaseAdapter. Você deve substituir os seguintes métodos da classe BaseAdpter:
* getCount(): Este método retorna o número total de contagens de linhas para a exibição em lista. Normalmente, isso contém o tamanho da lista que você passa como entrada.
* getItem(): Retorna um objeto que representa dados para cada linha.
* getItemId(): Retorna o ID inteiro exclusivo que representa cada item de linha.
* getView(): O método getView () retorna uma instância de exibição que representa um único item na lista.

```
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    private ArrayList<Animal> listData;
    private LayoutInflater layoutInflater;

    static class ViewHolder {
        TextView textViewNome, textViewRaca;
        ImageView imageView;
    }

    public CustomListAdapter(Context aContext, ArrayList<Animal> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final int pos = position;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_list, null);
            holder = new ViewHolder();
            holder.textViewNome = (TextView) convertView.findViewById(R.id.textViewNome);
            holder.textViewRaca = (TextView) convertView.findViewById(R.id.textViewRaca);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textViewNome.setText(listData.get(position).getNome());
        holder.textViewRaca.setText(listData.get(position).getRaca());
        holder.imageView.setImageResource(listData.get(position).getDrawableId());

        return convertView;
    }
}

```

## Instanciar o adaptador, associar à ListView e incluir dados
Finalmente, devemos instanciar a listview e o adapter, e incluir objetos do tipo tratado.

```
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
```

# Implementando GridView

## Criar layout de cada linha
Criar um novo layout para especificar a posição dos elementos de cada item na tela.

<table>
<tr align=center>
<td><img src="https://github.com/machadowma/ListaApp/blob/master/item_grid.png" align="left" height="240" width="400" ></td>
</tr>
<tr align=center>
<td>item_grid.xml</td>
</tr>
</table>


```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="25dp"
        android:layout_height="25dp"
        android:layout_marginEnd="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@android:drawable/ic_menu_report_image" />

    <TextView
        android:id="@+id/textViewNome"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginEnd="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        android:text="TextView"
        android:textSize="18sp"
        android:textStyle="bold"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imageView" />

    <TextView
        android:id="@+id/textViewRaca"
        android:layout_width="wrap_content"
        android:layout_height="22dp"
        android:layout_marginEnd="4dp"
        android:layout_marginLeft="8dp"
        android:layout_marginRight="4dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        android:text="TextView"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textViewNome" />

</android.support.constraint.ConstraintLayout>
```

## Criar um adaptador
Vamos criar uma nova classe chamada CustomListAdapter.java e estendê-la a partir de BaseAdapter. Você deve substituir os seguintes métodos da classe BaseAdpter:
* getCount(): Este método retorna o número total de contagens de linhas para a exibição em lista. Normalmente, isso contém o tamanho da lista que você passa como entrada.
* getItem(): Retorna um objeto que representa dados para cada linha.
* getItemId(): Retorna o ID inteiro exclusivo que representa cada item de linha.
* getView(): O método getView () retorna uma instância de exibição que representa um único item na lista.

```
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomGridAdapter extends BaseAdapter {

    private ArrayList<Animal> listData;
    private LayoutInflater layoutInflater;

    static class ViewHolder {
        TextView textViewNome, textViewRaca;
        ImageView imageView;
    }

    public CustomGridAdapter(Context aContext, ArrayList<Animal> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final int pos = position;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_grid, null);
            holder = new ViewHolder();
            holder.textViewNome = (TextView) convertView.findViewById(R.id.textViewNome);
            holder.textViewRaca = (TextView) convertView.findViewById(R.id.textViewRaca);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textViewNome.setText(listData.get(position).getNome());
        holder.textViewRaca.setText(listData.get(position).getRaca());
        holder.imageView.setImageResource(listData.get(position).getDrawableId());

        return convertView;
    }
}

```

## Instanciar o adaptador, associar à GridView e incluir dados
Finalmente, devemos instanciar a gridview e o adapter, e incluir objetos do tipo tratado.

```
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

```

# License

MIT License

Copyright (c) 2019 machadowma

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
