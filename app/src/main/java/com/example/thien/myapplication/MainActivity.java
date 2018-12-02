package com.example.thien.myapplication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener  {

    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    ListView lsvMyList;
    TextView txtHien;
    Button btnHuy,btnAdd;
    Dialog dialogAdd;
    EditText edvNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        list.add("Ky thuat lap trinh");
        list.add("Co so du lieu");
        list.add("He dieu hanh");

        txtHien = (TextView) findViewById(R.id.txtHien);

        adapter = new ArrayAdapter<String>(this,R.layout.custom_item_list,R.id.txtItem,list);
        lsvMyList=(ListView)findViewById(R.id.lvwMylist);
        lsvMyList.setAdapter(adapter);
        lsvMyList.setOnItemClickListener(this);

        registerForContextMenu(lsvMyList);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        txtHien.setText(list.get(position));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.btnAddMenu :
                showdialog_add();
                break;

        }

        return super.onOptionsItemSelected(item);
}


    public void showdialog_add() {

        dialogAdd = new Dialog(MainActivity.this);
        dialogAdd.setContentView(R.layout.custom_dialog);
        dialogAdd.setTitle("Thêm");

        btnAdd = (Button) dialogAdd.findViewById(R.id.btnAdd);
        btnHuy = (Button) dialogAdd.findViewById(R.id.btnHuy);
        edvNhap = (EditText) dialogAdd.findViewById(R.id.edvNhap);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = edvNhap.getText().toString();
                if(a!="")
                {
                    list.add(a);
                    dialogAdd.dismiss();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdd.dismiss();
            }
        });

        dialogAdd.create();
        dialogAdd.show();
    }

    public void showdialog_edit(AdapterView.AdapterContextMenuInfo info) {

        dialogAdd = new Dialog(MainActivity.this);
        dialogAdd.setContentView(R.layout.custom_dialog);
        dialogAdd.setTitle("Sửa");

        final int pos = info.position;

        btnAdd = (Button) dialogAdd.findViewById(R.id.btnAdd);
        btnHuy = (Button) dialogAdd.findViewById(R.id.btnHuy);
        edvNhap = (EditText) dialogAdd.findViewById(R.id.edvNhap);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = edvNhap.getText().toString();
                if(a!="")
                {
                    list.set(pos,a);
                    dialogAdd.dismiss();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdd.dismiss();
            }
        });

        dialogAdd.create();
        dialogAdd.show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.custom_contextual_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos = info.position;

        switch(item.getItemId())
        {
            case R.id.itemXoa:
                list.remove(pos);
                adapter.notifyDataSetChanged();
                break;
            case R.id.itemSua:
                showdialog_edit(info);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
