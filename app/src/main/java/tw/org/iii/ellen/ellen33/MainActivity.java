package tw.org.iii.ellen.ellen33;
//利用MyService繼承FirebaseMessagingService進行推播

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String token = instanceIdResult.getToken() ;
                //Log.v("ellen", token) ;
            }
        }) ;
    }

    int i ;
    public void test1(View view) {
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, NotifyActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("name","Lottery : " + (int)(Math.random()*49+1)) ;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        final String CHANNEL_ID = "ellen" ;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Brad";
            String description = "Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT; //重要程度
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.virus) ;

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.virus)
                .setContentTitle("武漢病毒")
                .setContentText("You are inflect !!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                        .setLargeIcon(bitmap)
                        .setLights(Color.RED, 1000, 1000)
                .setContentIntent(pendingIntent) ;

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(i++, builder.build());


    }
}
