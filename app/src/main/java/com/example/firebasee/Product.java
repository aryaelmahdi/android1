package com.example.firebasee;
import android.os.Parcel;
import android.os.Parcelable;
public class Product implements Parcelable {
    private String id;
    private String tahun;
    private String nama;
    private String seri;
    private String des;
    private String harga;
    private String photo;
    public Product() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getSeri() {
        return seri;
    }
    public void setSeri(String seri){
        this.seri = seri;
    }
    public String getTahun(){
        return tahun;
    }
    public void setTahun(String tahun){
        this.tahun = tahun;
    }
    public String getHarga(){
        return harga;
    }
    public void setHarga(String harga){
        this.harga = harga;
    }
    public String getDes(){
        return des;
    }
    public void setDes(String des){
        this.des = des;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.tahun);
        dest.writeString(this.nama);
        dest.writeString(this.seri);
        dest.writeString(this.des);
        dest.writeString(this.harga);
    }
    protected Product(Parcel in) {
        this.id = in.readString();
        this.tahun = in.readString();
        this.des = in.readString();
        this.harga = in.readString();
        this.seri = in.readString();
        this.nama = in.readString();
    }
    public static final Parcelable.Creator<Product> CREATOR
            = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }
        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}