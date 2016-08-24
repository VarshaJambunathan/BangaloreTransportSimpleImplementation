package com.example.user.bangaloretransport21;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class LocateExplore extends AppCompatActivity {

    CardView mView,mView1,mView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_explore);

        mView=(CardView)findViewById(R.id.view);
        mView1=(CardView)findViewById(R.id.view1);
        mView2=(CardView)findViewById(R.id.view2);

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LocateExplore.this,MainActivity.class);
                startActivity(i);
            }
        });

        mView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LocateExplore.this, MapsActivity.class);
                startActivity(i);
            }
        });

        mView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LocateExplore.this, "Opening the metro maps", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
