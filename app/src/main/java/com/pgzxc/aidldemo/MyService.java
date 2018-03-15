package com.pgzxc.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by admin on 2018/3/15.
 */

public class MyService extends Service {
    private MyBinder myBinder=new MyBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }
    class MyBinder extends IMyAidl.Stub{

        @Override
        public String getString() throws RemoteException {
            return "我是字符串";
        }

        @Override
        public Student getStudent() throws RemoteException {
            return new Student("李磊",20);
        }
    }
}
