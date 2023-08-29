package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBhelper dBhelper = new MyDBhelper(this);
        /*dBhelper.addContact("Meet","7898748574");
        dBhelper.addContact("Jigar","8598748574");
        dBhelper.addContact("Sarthik","6598748574");*/

        /*ContactModel nmodel = new ContactModel();
        nmodel.id = 1;
        nmodel.phoneNumber = "1231231235";

        dBhelper.update(nmodel);*/

        dBhelper.delete(2);

        ArrayList<ContactModel> allContact = dBhelper.fetchData();

        for(int i = 0;i<allContact.size();i++){
            Log.d("Cotact_Info","Name: "+allContact.get(i).name+", Number: "+allContact.get(i).phoneNumber);
        }
    }
}