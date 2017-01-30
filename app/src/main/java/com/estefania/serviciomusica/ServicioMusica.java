package com.estefania.serviciomusica;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import static android.R.attr.y;

/**
 * Created by Fany on 30/01/2017.
 */

public class ServicioMusica extends Service {

    MediaPlayer reproductor;
    private NotificationManager notificationManager;
    private static final int ID_NOTIFICACION = 1;


    @Override
    public void onCreate() {
        Toast.makeText(this, "Servicio creado", Toast.LENGTH_SHORT).show();
        reproductor = MediaPlayer.create(this, R.raw.audio);

        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

/*
    @Override
    public int onStartCommand(Intent intent, int flags, int idArranque) {
        Toast.makeText(this,"Servicio arrancado "+ idArranque, Toast.LENGTH_SHORT).show();
        reproductor.start();
        return START_STICKY;

    }*/


    @Override
    public void onStart(Intent intent, int startId) {
        Toast.makeText(this, "Servicio arrancado " + startId, Toast.LENGTH_SHORT).show();
        reproductor.start();

        Intent intent2 = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        long vibrate[] = {0, 100, 200, 300};
        Notification notification = new Notification.Builder(this)
                .setContentTitle("Reproduciendo música")
                .setContentText("Información adicional")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setVibrate(vibrate)
                .setLights(0xffff00ff,0,0)
                .getNotification();
        notification.flags |= Notification.FLAG_SHOW_LIGHTS;
        notificationManager.notify(ID_NOTIFICACION, notification);
    }


    @Override
    public void onDestroy() {
        Toast.makeText(this,"Servicio detenido", Toast.LENGTH_SHORT).show();
        reproductor.stop();
        notificationManager.cancel(ID_NOTIFICACION);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
