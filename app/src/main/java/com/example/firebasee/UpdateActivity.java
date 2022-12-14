package com.example.firebasee;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class UpdateActivity extends AppCompatActivity implements
        View.OnClickListener {
    //tambahkan 1
    private EditText edtTahun, edtNama, edtSeri, edtHarga, edtDesc;
    private Button btnUpdate;
    public static final String EXTRA_PRODUCT = "extra_product";
    public final int ALERT_DIALOG_CLOSE = 10;
    public final int ALERT_DIALOG_DELETE = 20;
    private Product product;
    private Button deletebutton;
    private String productId;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
//tambahkan 2
        mDatabase = FirebaseDatabase.getInstance().getReference();
        edtNama = findViewById(R.id.up_harga);
        edtTahun = findViewById(R.id.up_tahun);
        edtSeri = findViewById(R.id.up_desc);
        edtHarga = findViewById(R.id.up_series);
        edtDesc = findViewById(R.id.up_nama);
        deletebutton = findViewById(R.id.up_dltbtn);
        btnUpdate = findViewById(R.id.submit);
        btnUpdate.setOnClickListener(this);
        product = getIntent().getParcelableExtra(EXTRA_PRODUCT);
        if (product != null) {
            productId = product.getId();
        } else {
            product = new Product();
        }
        if (product != null) {
            edtNama.setText(product.getNama());
            edtSeri.setText(product.getSeri());
            edtTahun.setText(product.getTahun());
            edtHarga.setText(product.getHarga());
            edtDesc.setText(product.getDes());
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("jancok");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    //tambahkan 3
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.submit) {
            updateProduct();
        }
        if(view.getId() == R.id.up_dltbtn){
            deleteProduct();
            }
        }

    private void updateProduct() {
        edtNama = findViewById(R.id.up_nama);
        edtSeri = findViewById(R.id.edt_series);
        edtTahun = findViewById(R.id.up_tahun);
        edtHarga = findViewById(R.id.up_harga);
        edtDesc = findViewById(R.id.up_desc);

        String nama = edtNama.getText().toString().trim();
        String seri = edtTahun.getText().toString().trim();
        String tahun = edtSeri.getText().toString().trim();
        String harga = edtHarga.getText().toString().trim();
        String des = edtDesc.getText().toString().trim();
        boolean isEmptyFields = false;
        if (TextUtils.isEmpty(nama)) {
            isEmptyFields = true;
            edtNama.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(seri)) {
            isEmptyFields = true;
            edtSeri.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(tahun)) {
            isEmptyFields = true;
            edtTahun.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(harga)) {
            isEmptyFields = true;
            edtHarga.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(des)) {
            isEmptyFields = true;
            edtDesc.setError("Field ini tidak boleh kosong");
        }
        if (! isEmptyFields) {
            Toast.makeText(UpdateActivity.this, "Updating Data...", Toast.LENGTH_SHORT).show();
            product.setNama(nama);
            product.setSeri(seri);
            product.setTahun(tahun);
            product.setHarga(harga);
            product.setDes(des);
            DatabaseReference dbMahasiswa = mDatabase.child("produk");
//update data
            dbMahasiswa.child(productId).setValue(product);
            finish();
        }
    }

    public void deleteProduct(){
        DatabaseReference dbMahasiswa = mDatabase.child("produk").child(productId);
        dbMahasiswa.removeValue();
        Toast.makeText(UpdateActivity.this, "Deleting data...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                showAlertDialog(ALERT_DIALOG_CLOSE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {

        showAlertDialog(ALERT_DIALOG_CLOSE);
    }
    private void showAlertDialog(int type) {
        final boolean isDialogClose = type == ALERT_DIALOG_CLOSE;
        String dialogTitle, dialogMessage;
        if (isDialogClose) {
            dialogTitle = "Batal";
            dialogMessage = "Apakah anda ingin membatalkan perubahan pada form?";
        } else {
            dialogTitle = "Hapus Data";
            dialogMessage = "Apakah anda yakin ingin menghapus item ini?";
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder.setMessage(dialogMessage).setCancelable(false).setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (isDialogClose) {
                                    finish();
                                } else {
                                    DatabaseReference dbMahasiswa = mDatabase.child("produk").child(productId);
                                    dbMahasiswa.removeValue();
                                    Toast.makeText(UpdateActivity.this, "Deleting data...", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {dialogInterface.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}