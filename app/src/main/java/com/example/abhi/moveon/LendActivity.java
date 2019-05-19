

package com.example.abhi.moveon;

import android.support.annotation.NonNull;
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

public class LendActivity extends AppCompatActivity {

    private EditText company,bikeName,rate,engine,milage,model;
    private Button button,searchBtn;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;
    int startRate=0,endRate=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lend);
        company =findViewById(R.id.etbkCompany);
        bikeName=findViewById(R.id.etbkName);
        rate=findViewById(R.id.etratepd);
        engine=findViewById(R.id.etengine);
        milage=findViewById(R.id.etmilage);
        model=findViewById(R.id.etmodel);
        searchBtn=findViewById(R.id.search);


        button = findViewById(R.id.btn);
        recyclerView = findViewById(R.id.list);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();
        adapter.startListening();

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRate = Integer.valueOf(rate.getText().toString());
                endRate = Integer.valueOf(engine.getText().toString());
                fetch();
                adapter.startListening();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("rental").push();
                Map<String, Object> map = new HashMap<>();
                map.put("id", databaseReference.getKey());
                map.put("company", company.getText().toString());
                map.put("name", bikeName.getText().toString());
                map.put("rate", Integer.parseInt(rate.getText().toString()));
                map.put("engine", Integer.parseInt(engine.getText().toString()));
                map.put("milage", Integer.parseInt(milage.getText().toString()));
                map.put("model", Integer.parseInt(model.getText().toString()));
                databaseReference.setValue(map);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //adapter.startListening();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.stopListening();
    }
    private void fetch() {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("rental").orderByChild("rate").startAt(startRate).endAt(endRate);

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
                                            snapshot.child("model").getValue(Integer.class),
                                            snapshot.child("milage").getValue(Integer.class),
                                            snapshot.child("engine").getValue(Integer.class));
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

                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(LendActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        };
        recyclerView.setAdapter(adapter);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
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
            //txtTitle = itemView.findViewById(R.id.list_title);
            //txtDesc = itemView.findViewById(R.id.list_desc);
        }

       /* public void setTxtTitle(String string) {
            txtTitle.setText(string);
        }*/

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

