package com.example.kuis1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

public class TransaksiActivity extends AppCompatActivity {


    private final HashMap<String, String> namaBarangMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);


        initializeNamaBarangMap();


        TextView tvNama = findViewById(R.id.tvNama);
        TextView tvMembership = findViewById(R.id.tvMembership);
        TextView tvKodeBarang = findViewById(R.id.tvKodeBarang);
        TextView tvNamaBarang = findViewById(R.id.tvNamaBarang);
        TextView tvHarga = findViewById(R.id.tvHarga);
        TextView tvTotalHarga = findViewById(R.id.tvTotalHarga);
        TextView tvDiskon = findViewById(R.id.tvDiskon);
        TextView tvDiskonMember = findViewById(R.id.tvDiskonMember);
        TextView tvJumlahBayar = findViewById(R.id.tvJumlahBayar);
        Button btnShare = findViewById(R.id.btnShare);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("nama");
        String tipepembeli = intent.getStringExtra("tipepembeli");
        String kodeBarang = intent.getStringExtra("kodeBarang");
        int jumlahBarang = intent.getIntExtra("jumlahBarang", 0);

        double diskonMember = 0;
        double jumlahBayar = 0;
        double hargaBarang = 0;


        if (kodeBarang != null) {
            switch (kodeBarang) {
                case "PCO":
                    hargaBarang = 2730551;
                    break;
                case "AV4":
                    hargaBarang = 9150999;
                    break;
                case "AA5":
                    hargaBarang = 9999999;
                    break;
            }
        }

        double totalHarga = hargaBarang * jumlahBarang;
        double diskon = totalHarga > 10000000 ? 100000 : 0;


        if (tipepembeli != null) {
            switch (tipepembeli) {
                case "Gold":
                    diskonMember = 0.1 * totalHarga;
                    break;
                case "Silver":
                    diskonMember = 0.05 * totalHarga;
                    break;
                case "Bronze":
                    diskonMember = 0.02 * totalHarga;
                    break;
            }
        }


        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Isi pesan yang ingin dibagikan");
                startActivity(Intent.createChooser(intent, "Bagikan melalui"));
            }
        });

        jumlahBayar = totalHarga - diskon - diskonMember;


        tvNama.setText("Nama Pembeli : " + nama);
        tvMembership.setText("Membership Pembeli : " + tipepembeli);
        tvKodeBarang.setText("Kode Barang : " + kodeBarang);
        tvNamaBarang.setText("Nama Barang : " + namaBarangMap.getOrDefault(kodeBarang, ""));
        tvHarga.setText("Harga : Rp " + String.format("%,.0f", hargaBarang));
        tvTotalHarga.setText("Total Harga : Rp " + String.format("%,.0f", totalHarga));
        tvDiskon.setText("Diskon: Rp " + String.format("%,.0f", diskon));
        tvDiskonMember.setText("Diskon Member : Rp " + String.format("%,.0f", diskonMember));
        tvJumlahBayar.setText("Jumlah Bayar : Rp " + String.format("%,.0f", jumlahBayar));
    }

    private void initializeNamaBarangMap() {
        namaBarangMap.put("PCO", "POCO M3");
        namaBarangMap.put("AV4", "Asus Vivobook 14");
        namaBarangMap.put("AA5", "Acer Aspire 5");
    }
}
