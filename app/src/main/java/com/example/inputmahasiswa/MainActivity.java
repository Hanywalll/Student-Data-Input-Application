package com.example.inputmahasiswa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText eNim;
    EditText eNama;
    Button bTambah;
    Button bTampil;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        eNim = (EditText) findViewById(R.id.eNim);
        eNama = (EditText) findViewById(R.id.eNama);
        bTambah = (Button) findViewById(R.id.bTambah);
        bTampil = (Button) findViewById(R.id.bTampil);

        viewAll();

        bTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean IsInserted = dbHelper.InsertData(eNim.getText().toString(), eNama.getText().toString());
                if (IsInserted == true) {
                    Toast.makeText(MainActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data Gagal", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

       private void viewAll(){
        bTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = dbHelper.getAllData();
                if (res.getCount()==0){
                    showMessage("Eror","Tidak Ditemukan");
                    return;
                }
                else{
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        buffer.append("Nim      : "+res.getString(0)+"\n");
                        buffer.append("Nama     : "+res.getString(1)+"\n");
                    }
                    showMessage("Mahasiswa : ",buffer.toString());
                }
            }
        });
    }
    public void showMessage (String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}