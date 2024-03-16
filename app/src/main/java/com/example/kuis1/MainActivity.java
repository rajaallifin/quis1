package com.example.kuis1;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNamaPembeli, etKodeBarang, etJumlahBarang;
    private RadioGroup rbMembership;
    private Button btnBeli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNamaPembeli = findViewById(R.id.etNamaPembeli);
        etKodeBarang = findViewById(R.id.etKodeBarang);
        etJumlahBarang = findViewById(R.id.etJumlahBarang);
        etJumlahBarang.setInputType(InputType.TYPE_CLASS_NUMBER); // Tambahkan baris ini
        rbMembership = findViewById(R.id.rbMembership);
        btnBeli = findViewById(R.id.btnBeli);

        btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaPembeli = etNamaPembeli.getText().toString();
                int selectedId = rbMembership.getCheckedRadioButtonId();
                RadioButton rbSelected = findViewById(selectedId);
                String tipePelanggan = rbSelected != null ? rbSelected.getText().toString() : "";
                String kodeBarang = etKodeBarang.getText().toString().toUpperCase();
                String jumlahBarangStr = etJumlahBarang.getText().toString();

                int jumlahBarang = Integer.parseInt(jumlahBarangStr);

                Intent intent = new Intent(MainActivity.this, TransaksiActivity.class);
                intent.putExtra("nama", namaPembeli);
                intent.putExtra("tipepembeli", tipePelanggan);
                intent.putExtra("kodeBarang", kodeBarang);
                intent.putExtra("jumlahBarang", jumlahBarang);

                startActivity(intent);
            }
        });

    }
}