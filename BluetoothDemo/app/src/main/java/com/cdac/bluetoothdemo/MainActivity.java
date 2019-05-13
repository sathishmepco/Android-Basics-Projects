package com.cdac.bluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter;
    Button buttonTurnOn, buttonTurnOff, buttonDiscoveryOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents(){
        ClickListener listener = new ClickListener();

        buttonTurnOn = findViewById(R.id.btnTurnOn);
        buttonTurnOff = findViewById(R.id.btnTurnOff);
        buttonDiscoveryOn = findViewById(R.id.btnDiscoveryOn);

        buttonTurnOn.setOnClickListener(listener);
        buttonTurnOff.setOnClickListener(listener);
        buttonDiscoveryOn.setOnClickListener(listener);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnTurnOn:
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent,23);
                    break;
                case R.id.btnTurnOff:
                    bluetoothAdapter.disable();
                    break;
                case R.id.btnDiscoveryOn:
                    if(!bluetoothAdapter.isDiscovering()){
                        bluetoothAdapter.startDiscovery();
                    }
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 23){
            if(bluetoothAdapter.isEnabled()){
                Toast.makeText(getApplicationContext(),"Bluetooth Enabled",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(),"Bluetooth not Enabled",Toast.LENGTH_LONG).show();
            }
        }
    }
}
