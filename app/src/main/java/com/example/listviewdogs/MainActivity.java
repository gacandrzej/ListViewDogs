package com.example.listviewdogs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String [] dogs;
    private String [] dogsDesc;
    private Resources resources;
    private ListView listView;
    private FloatingActionButton addItemFAB;
    private HashMap<String,Object> hashMap;

    private ArrayList<HashMap<String,Object>> dogsList;
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

        dogsList = new ArrayList<>();
        listView = findViewById(R.id.simpleListView);

        for(int i=0; i<dogsPictures.length; i++){
            hashMap = new HashMap<>();
            hashMap.put("name",dogs[i]);
            hashMap.put("image",dogsPictures[i]);
            hashMap.put("description", dogsDesc[i]);
            dogsList.add(hashMap);
        }
        //--------------------------------------------
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
        //-----------------------------------------------
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getApplicationContext(),
                dogsList,
                R.layout.list_view_items,
                from,
                to
        );
        listView.setAdapter(simpleAdapter);

       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                Toast.makeText(getApplicationContext(), dogsDesc[i], Toast.LENGTH_LONG).show();
                                            }
                                        }

        );*/

        listView.setOnItemClickListener((adapterView,view,i,l)->{
            Intent intent = new Intent(this,PictureActivity.class);
            intent.putExtra("PICTURE_NAME", String.valueOf(dogsList.get(i).get("name")));
            intent.putExtra("PICTURE_DESC", String.valueOf(dogsList.get(i).get("description")));
            intent.putExtra("PICTURE_IMAGE", String.valueOf(dogsList.get(i).get("image")));
            startActivity(intent);
            //  Toast.makeText(getApplicationContext(), dogsDesc[i], Toast.LENGTH_LONG).show();
        });

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