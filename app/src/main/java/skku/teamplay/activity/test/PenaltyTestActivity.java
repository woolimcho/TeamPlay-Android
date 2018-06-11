package skku.teamplay.activity.test;

import android.app.Activity;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import skku.teamplay.R;
import skku.teamplay.service.PenaltyService;
import skku.teamplay.util.SharedPreferencesUtil;

public class PenaltyTestActivity extends Activity {

    @BindView(R.id.button_startService)
    Button startSvc;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penalty_test);
        //Check whether app has permission to ACTION_MANAGE_OVERLAY_PERMISSION
        ButterKnife.bind(this);
        startSvc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferencesUtil.putInt("leftTime", 120);
                Toast.makeText(PenaltyTestActivity.this, "Service started "+SharedPreferencesUtil.getInt("leftTime"), 0).show();
                Intent intent = new Intent(PenaltyTestActivity.this, PenaltyService.class);
                startService(intent);
            }
        });
    }
}
