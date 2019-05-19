

package com.example.abhi.moveon;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;

public class RentActivity extends AppCompatActivity {

    private EditText etstRate,etedRate,etstEngine,etedEngine,etstMilage,etedMilage;
    public Button searchEBtn,searchMBtn,searchRBtn;
    Boolean rentActivity=true;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;
    int startR=0,startE=0,startM=0,endR=5000,endE=5000,endM=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);
        etstRate=findViewById(R.id.etStRate);
        etedRate=findViewById(R.id.etEdRate);
        etstEngine=findViewById(R.id.etStEngine);
        etedEngine=findViewById(R.id.etEdEngine);
        etstMilage=findViewById(R.id.etStMilage);
        etedMilage=findViewById(R.id.etEdMilage);
        searchRBtn=findViewById(R.id.searchR);
        searchEBtn=findViewById(R.id.searchE);
        searchMBtn=findViewById(R.id.searchM);
        recyclerView = findViewById(R.id.list);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("rental");
        fetch(query);
        adapter.startListening();

        searchEBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etstEngine.getText().toString().trim().isEmpty()){
                    startE = Integer.valueOf(etstEngine.getText().toString());
                }
                else{
                    startE=0;
                }

                if(!etedEngine.getText().toString().trim().isEmpty()){
                    endE = Integer.valueOf(etedEngine.getText().toString());
                }
                else{
                    endE=5000;
                }
                etstEngine.setText("");
                etedEngine.setText("");
                Query query = FirebaseDatabase.getInstance()
                        .getReference()
                        .child("rental")
                        .orderByChild("engine").startAt(startE).endAt(endE);
                fetch(query);
                adapter.startListening();
            }
        });

        searchMBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etstMilage.getText().toString().trim().isEmpty()){
                    startM = Integer.valueOf(etstMilage.getText().toString());
                }
                else{
                    startM=0;
                }

                if(!etedMilage.getText().toString().trim().isEmpty()){
                    endM = Integer.valueOf(etedMilage.getText().toString());
                }
                else{
                    endM=5000;
                }
                etstMilage.setText("");
                etedMilage.setText("");
                Query query = FirebaseDatabase.getInstance()
                        .getReference()
                        .child("rental")
                        .orderByChild("milage").startAt(startM).endAt(endM);
                fetch(query);
                adapter.startListening();
            }
        });

        searchRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etstRate.getText().toString().trim().isEmpty()){
                    startR = Integer.valueOf(etstRate.getText().toString());
                }
                else{
                    startR=0;
                }

                if(!etedRate.getText().toString().trim().isEmpty()){
                    endR = Integer.valueOf(etedRate.getText().toString());
                }
                else{
                    endR=5000;
                }
                etstRate.setText("");
                etedRate.setText("");
                Query query = FirebaseDatabase.getInstance()
                        .getReference()
                        .child("rental")
                        .orderByChild("rate").startAt(startR).endAt(endR);
                fetch(query);
                adapter.startListening();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.stopListening();
    }
    private void fetch(Query query) {
        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(query, new SnapshotParser<Model>() {
                            @NonNull
                            @Override
                            public Model parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new Model(snapshot.child("id").getValue().toString(),
                                        snapshot.child("name").getValue().toString(),
                                        snapshot.child("company").getValue().toString(),
                                        snapshot.child("rate").getValue(Integer.class),
                                        snapshot.child("engine").getValue(Integer.class),
                                        snapshot.child("milage").getValue(Integer.class),
                                        snapshot.child("model").getValue(Integer.class),
                                        snapshot.child("contact").getValue().toString()
                                );
                            }
                        })
                        .build();


        adapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item, parent, false);

                return new ViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(ViewHolder holder, final int position, Model model) {
                holder.setTvCompany(model.getCompany());
                holder.setTvName(model.getBikeName());
                holder.setTvEngine(model.getCc());
                holder.setTvRate(model.getRpd());
                holder.setTvMilage(model.getMilage());
                holder.setTvModel(model.getModel());
                final String contact=model.getContact();
                holder.callBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        makeCall(contact);

                        Toast.makeText(RentActivity.this,"Calling Bike Owner",Toast.LENGTH_SHORT).show();
                    }
                });

            }

        };
        recyclerView.setAdapter(adapter);
    }

    public void  makeCall(String contact){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) ==
                PackageManager.PERMISSION_GRANTED)
        {
            contact = "tel:" + contact;
            this.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(contact)));
        }
        else{
            checkCallPermission();
        }
    }

    private void checkCallPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                new AlertDialog.Builder(this)
                        .setTitle("give permission")
                        .setMessage("give permission message")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(RentActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                            }
                        })
                        .create()
                        .show();
            } else {
                ActivityCompat.requestPermissions(RentActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            }
        }
        this.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:9495227127")));
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public Button callBtn;
        public TextView txtTitle;
        public TextView txtDesc,tvCompany,tvName,tvRate,tvMilage,tvEngine,tvModel;

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            tvCompany = itemView.findViewById(R.id.bkCompany);
            tvName= itemView.findViewById(R.id.bkName);
            tvRate= itemView.findViewById(R.id.ratepd);
            tvMilage= itemView.findViewById(R.id.milage);
            tvEngine = itemView.findViewById(R.id.engine);
            tvModel = itemView.findViewById(R.id.model);
            callBtn = itemView.findViewById(R.id.callBtn);
        }

        public void setTvCompany(String string){
            tvCompany.setText(string);
        }

        public void setTvName(String string){
            tvName.setText(string);
        }

        public void setTvRate(String string){
            tvRate.setText(string);
        }

        public void setTvEngine(String string){
            tvEngine.setText(string);
        }

        public void setTvModel(String string){
            tvModel.setText(string);
        }
        public void setTvMilage(String string){
            tvMilage.setText(string);
        }
     /*   public void setTxtDesc(String string) {
            txtDesc.setText(string);
        }*/
    }
}

