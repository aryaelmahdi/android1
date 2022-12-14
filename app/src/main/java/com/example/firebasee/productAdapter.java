package com.example.firebasee;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
public class productAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Product> productList = new
            ArrayList<>();
    public void setProductList(ArrayList<Product> mahasiswaList) {
        this.productList = mahasiswaList;
    }
    public productAdapter(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        return productList.size();
    }
    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        if (itemView == null) {
            itemView = LayoutInflater.from(context)
                    .inflate(R.layout.item_product, viewGroup, false);
        }
        ViewHolder viewHolder = new ViewHolder(itemView);
        Product mahasiswa = (Product) getItem(i);
        viewHolder.bind(mahasiswa);
        return itemView;
    }
    private class ViewHolder {
        private TextView txtTahun, txtName;
        ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_nama);
            txtTahun = view.findViewById(R.id.txt_tahun);
        }
        void bind(Product mahasiswa) {
            txtName.setText(mahasiswa.getNama());
            txtTahun.setText(mahasiswa.getTahun());
        }
    }
}