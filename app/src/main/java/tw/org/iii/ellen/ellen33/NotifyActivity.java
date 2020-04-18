package tw.org.iii.ellen.ellen33;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class NotifyActivity extends AppCompatActivity {
    private TextView tv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        tv = findViewById(R.id.tv) ;
        Intent intent = getIntent() ;
        String name = intent.getStringExtra("name") ;
        Log.v("ellen",name) ;
        tv.setText(name) ;

    }
}
