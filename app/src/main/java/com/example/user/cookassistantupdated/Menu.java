package com.example.user.cookassistantupdated;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    public static final String fItem="fItem";
    ListView menu;
    EditText addMenu;
    Button button;
    DatabaseReference databaseMenu;
    ArrayList<String>ml;

    ArrayAdapter<String>mlAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent=getIntent();
        String name=intent.getStringExtra(MainActivity.fname);
        menu=findViewById(R.id.listView);
        addMenu=findViewById(R.id.editText);
        button=findViewById(R.id.button99);
        ml=new ArrayList<String>();

        mlAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ml);
        menu.setAdapter(mlAdapter);


        databaseMenu= FirebaseDatabase.getInstance().getReference("Item").child(name);

        databaseMenu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ml.clear();
                for (DataSnapshot d:dataSnapshot.getChildren()){

                    ml.add(d.getValue(Item.class).getItemName());
                    mlAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String send=ml.get(i);
                Intent intent1=new Intent(getApplicationContext(),RecipePage.class);
                intent1.putExtra(fItem,send);
                startActivity(intent1);
            }
        });





    }

    public void saveMenu(View view) {
        String itemName=addMenu.getText().toString().trim();
        if (!(TextUtils.isEmpty(itemName))){
            String id=databaseMenu.push().getKey();
            Item item=new Item(id,itemName);
            databaseMenu.child(id).setValue(item);

            Toast.makeText(this,"Item Added",Toast.LENGTH_LONG).show();
            addMenu.setText("");
        }
        else {
            Toast.makeText(this,"You have to enter an item",Toast.LENGTH_LONG).show();

        }

    }
}
