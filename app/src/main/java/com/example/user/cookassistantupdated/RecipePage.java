package com.example.user.cookassistantupdated;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RecipePage extends AppCompatActivity {

    EditText recipee;
    EditText time;
    Button reB;
    //Button tB;
    ListView rList;
    RecipeAdapter recipeAdapter;
    DatabaseReference databaseRecipe;
    TextView tm;
    TextView tm2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_page);
        Intent intent=getIntent();
        String name=intent.getStringExtra(Menu.fItem);
        tm= findViewById(R.id.textView4);
        tm2= findViewById(R.id.textView5);

        recipee=findViewById(R.id.recText);
        time=findViewById(R.id.timeText);
        reB=findViewById(R.id.rec);
        //tB=findViewById(R.id.tim);
        rList=findViewById(R.id.rListView);
        recipeAdapter=new RecipeAdapter(this);
        rList.setAdapter(recipeAdapter);

        databaseRecipe= FirebaseDatabase.getInstance().getReference("Recipe").child(name);
        databaseRecipe.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                recipeAdapter.list.clear();
                for (DataSnapshot d:dataSnapshot.getChildren()){


                    recipeAdapter.list.add(d.getValue(Recipe.class));

                    recipeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void addRecipe(View view) {
        String reci=recipee.getText().toString().trim();
        String t=time.getText().toString().trim();

        if (!(TextUtils.isEmpty(reci))||!(TextUtils.isEmpty(t))){
           databaseRecipe.child(databaseRecipe.push().getKey()).setValue(new Recipe(reci,t));

            Toast.makeText(this,"Recipe Added",Toast.LENGTH_LONG).show();
            recipee.setText("");
            time.setText("");
        }
        else {
            Toast.makeText(this,"You have to enter recipee",Toast.LENGTH_LONG).show();

        }

    }
}
