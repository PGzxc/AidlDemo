package com.pgzxc.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pgzxc.aidldemo.IMyAidl;

public class MainActivity extends AppCompatActivity {

    private IMyAidl myAidl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent("service");
        intent.setPackage("com.pgzxc.aidldemo");
        bindService(intent,connection,BIND_AUTO_CREATE);

    }
     ServiceConnection connection=new ServiceConnection(){

         @Override
         public void onServiceConnected(ComponentName name, IBinder service) {
             myAidl=IMyAidl.Stub.asInterface(service);
         }

         @Override
         public void onServiceDisconnected(ComponentName name) {
          myAidl=null;

         }
     };
    public void clickString(View view) {
        try {
            Toast.makeText(this, myAidl.getString(), Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void clickObject(View view) {
        try {
            Toast.makeText(this, myAidl.getStudent().getName()+"::"+myAidl.getStudent().getAge(), Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
