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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String fname="fname";
    //public static final String fID="fID";
    EditText editText;
    Button button;
    DatabaseReference databaseFood;


    ListView listView;
    ArrayList<String> fl;

    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.edit);
        button=findViewById(R.id.addfood);
        listView=findViewById(R.id.list1);
        fl=new ArrayList<String>();

       adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,fl);
        listView.setAdapter(adapter);



        databaseFood= FirebaseDatabase.getInstance().getReference("Food");

        databaseFood.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                fl.clear();
                for (DataSnapshot d:dataSnapshot.getChildren()){

                    fl.add(d.getValue(Food.class).getFoodName());

                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String send=fl.get(i);
                Intent intent=new Intent(getApplicationContext(),Menu.class);
                intent.putExtra(fname,send);
                startActivity(intent);
            }
        });









    }



    public void addFood(View view) {
        String food=editText.getText().toString().trim();

        if (!(TextUtils.isEmpty(food))){
            String id=databaseFood.push().getKey();
            Food f=new Food(id,food);
            databaseFood.child(id).setValue(f);
            Toast.makeText(this,"Food Added",Toast.LENGTH_LONG).show();
            editText.setText("");


        }
        else {
            Toast.makeText(this,"Not entered anything",Toast.LENGTH_LONG).show();
        }
    }
}
