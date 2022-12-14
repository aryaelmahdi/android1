package com.example.firebasee;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{
    //kode yang ditambahkan 1
    private ListView listView;
    private Button btnAdd;
    private productAdapter adapter;
    private ArrayList<Product> productList;
    DatabaseReference dbProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//kode yang ditambahkan 2
        listView = findViewById(R.id.lv_list);
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);

        dbProduct = FirebaseDatabase.getInstance().getReference("produk");
        productList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbProduct.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productList.clear();
                for (DataSnapshot mahasiswaSnapshot : dataSnapshot.getChildren()) {
                    Product mahasiswa = mahasiswaSnapshot.getValue(Product.class);
                    productList.add(mahasiswa);
                }
                productAdapter adapter = new productAdapter(MainActivity.this);
                adapter.setProductList(productList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Terjadi kesalahan.", Toast.LENGTH_SHORT).show();
            }
        });


        listView.setOnItemClickListener(new
          AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView,
          View view, int i, long l) {
          Intent intent = new Intent(MainActivity.this,
          UpdateActivity.class);
          intent.putExtra(UpdateActivity.EXTRA_PRODUCT, productList.get(i));
          startActivity(intent);
          }
        });
    }
    //kode yang ditambahkan 3
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_add) {
            Intent intent = new Intent(MainActivity.this, CreateActivity.class);
            startActivity(intent);
        }
    }



}