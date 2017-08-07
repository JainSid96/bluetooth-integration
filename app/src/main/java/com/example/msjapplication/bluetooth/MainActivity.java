package com.example.msjapplication.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter BA ;

    public void turnOn(View view) {
        BA.disable();
        if (BA.isEnabled()) {
            Toast.makeText(getApplicationContext(), "BLUETOOTH CANNOT BE DISABLED", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "BLUETOOTH IS TURNED OFF", Toast.LENGTH_SHORT).show();
        }
    }

    public void findDevices(View view){
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(i);
    }

    public void viewDevices(View view){
        Set<BluetoothDevice> pairedDevices = BA.getBondedDevices();
        ListView listView = (ListView)findViewById(R.id.listView);
        ArrayList pairedList = new ArrayList();
        for (BluetoothDevice bluetoothDevices : pairedDevices){
            pairedList.add(bluetoothDevices.getName());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this , android.R.layout.simple_list_item_1 , pairedList);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(BA.isEnabled()){
            Toast.makeText(getApplicationContext() , "BLUETOOTH IS ON", Toast.LENGTH_SHORT).show();
        }else{
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(i);

            if (BA.isEnabled()){
                Toast.makeText(getApplicationContext(), "BLUETOOTH IS TURNED ON", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
