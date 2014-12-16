package com.example.oldlibexample;

import com.statist.grap.pref.PreferenceFacade;
import com.statist.grap.service.NTPSyncService;
import com.statist.grap.service.RegisterClientIntentService;
import com.statist.grap.service.ScannerService;
import com.statist.grap.service.SenderService;
import com.statist.grap.util.ServiceHelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (!ServiceHelper.isServiceRunning(this, ScannerService.class)) {
            startService(new Intent(this, ScannerService.class));
        }
        if (!ServiceHelper.isServiceRunning(this, SenderService.class)) {
            startService(new Intent(this, SenderService.class));

        }

        if (!PreferenceFacade.getInstance(this).isUserRegistered()) {
            startService(new Intent(this, RegisterClientIntentService.class));

        }
        if (!PreferenceFacade.getInstance(this).wasTimeSynchronized()) {
            startService(new Intent(this, NTPSyncService.class));
        }
	}
}
