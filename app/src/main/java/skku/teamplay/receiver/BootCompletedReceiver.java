package skku.teamplay.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import skku.teamplay.service.PenaltyService;

public class BootCompletedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //context.startService(new Intent(context, PenaltyService.class));
    }
}
