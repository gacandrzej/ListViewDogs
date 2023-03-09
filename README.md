# ListViewDogs
![image](https://user-images.githubusercontent.com/82050305/223965542-6acfc03f-4df3-4027-85c1-68caedf8bd0b.png)

``` java
package com.example.listview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String [] dogs;
    String [] dogsDesc;
    Resources resources;

    Button addItemButton;
    FloatingActionButton addItemFAB;
    ListView listView;

    HashMap<String,Object> hashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int [] dogsPictures ={
                R.drawable.australian_silky_terrier,
                R.drawable.cairn_terrier,
                R.drawable.cesky_terrier,
                R.drawable.chihuahua_long_coat,
                R.drawable.golden_retriever,
                R.drawable.malamut,
                R.drawable.pudel_duzy,
                R.drawable.cavalier_king_charles_spaniel,
                R.drawable.labrador_retriever
        };
        resources = getResources();
        dogs = resources.getStringArray(R.array.dogs);
        dogsDesc = resources.getStringArray(R.array.dogsDescription);


        List dogsList = new ArrayList<>();
        listView = findViewById(R.id.simpleListView);

        for(int i=0; i<dogsPictures.length; i++){
            hashMap = new HashMap<>();
            hashMap.put("name",dogs[i]);
            hashMap.put("image",dogsPictures[i]);
            hashMap.put("description", dogsDesc[i]);
            dogsList.add(hashMap);
        }

        String [] from = new String[]{
                "name",
                "image",
                "description"
        };
        int [] to = new int[]{
                R.id.nameDog,
                R.id.imageView,
                R.id.dogDescription
        };
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getApplicationContext(),
                dogsList,
                R.layout.list_view_items,
                from,
                to
        );
        listView.setAdapter(simpleAdapter);

        addItemFAB = findViewById(R.id.addItemFAB);

        addItemFAB.setOnClickListener(v->{
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            EditText inputName = new EditText(this);
            inputName.setHint("Name");
            EditText inputDescription = new EditText(this);
            inputDescription.setHint("Description");
            linearLayout.addView(inputName);
            linearLayout.addView(inputDescription);
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Add new position")
                    .setMessage("Enter name and description")
                    .setView(linearLayout)
                    .setPositiveButton("Add",(dialogInterface, i) ->{
                        hashMap = new HashMap<>();
                        hashMap.put("name",inputName.getText().toString());
                        hashMap.put("description",inputDescription.getText().toString());
                        hashMap.put("image",R.drawable.australian_silky_terrier);
                        dogsList.add(hashMap);
                        listView.setAdapter(simpleAdapter);
                    })
                    .setNegativeButton("Cancel",null)
                    .create();
            dialog.show();
        });

    }
}
```
