package com.example.firebasee;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class CreateActivity extends AppCompatActivity implements
        View.OnClickListener {
    //kode yang ditambahkan 1
    private EditText edtNim, edtNama, edtTahun, edtSeri, edtHarga, edtDes;
    private Button btnSave;
    private Product product;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
//kode yang ditambahkan 2
        mDatabase = FirebaseDatabase.getInstance().getReference();
        edtNama = findViewById(R.id.edt_nama);
        edtTahun = findViewById(R.id.edt_tahun);
        edtSeri = findViewById(R.id.edt_series);
        edtHarga = findViewById(R.id.edt_harga);
        edtDes = findViewById(R.id.edt_desc);
        btnSave = findViewById(R.id.submit);
        btnSave.setOnClickListener(this);
        product = new Product();
    }

    //kode yang ditambahkan 3
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.submit) {
            saveProduct();
        }
    }
    private void saveProduct()
    {
        String nama = edtNama.getText().toString().trim();
        String tahun = edtTahun.getText().toString().trim();
        String des = edtDes.getText().toString().trim();
        String seri = edtSeri.getText().toString().trim();
        String harga = edtHarga.getText().toString().trim();
        boolean isEmptyFields = false;
        if (TextUtils.isEmpty(nama)) {
            isEmptyFields = true;
            edtNama.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(tahun)) {
            isEmptyFields = true;
            edtTahun.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(seri)) {
            isEmptyFields = true;
            edtSeri.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(harga)) {
            isEmptyFields = true;
            edtHarga.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(des)) {
            isEmptyFields = true;
            edtDes.setError("Field ini tidak boleh kosong");
        }
        if (! isEmptyFields) {
            Toast.makeText(CreateActivity.this, "Saving Data...", Toast.LENGTH_SHORT).show();
            DatabaseReference dbMahasiswa = mDatabase.child("produk");
            String id = dbMahasiswa.push().getKey();
            product.setId(id);
            product.setNama(nama);
            product.setSeri(seri);
            product.setTahun(tahun);
            product.setHarga(harga);
            product.setDes(des);
//insert data
            dbMahasiswa.child(id).setValue(product);

            finish();
        }
    }
}