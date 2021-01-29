package jp.k_ichiro.oss.wearos.availablesensors;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends WearableActivity {

	final static String TAG = "Sensors";
	private SensorManager sensorManager;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textView = findViewById(R.id.text_view);
		sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

		// Enables Always-on
		setAmbientEnabled();
	}


	@Override
	protected void onResume() {
		super.onResume();

		List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
		StringBuffer strListbuf =new StringBuffer("\n\n-- Available Sensors --\n");
		Log.d(TAG, "-- Available Sensors --");
		int count = 0;
		for(Sensor sensor : sensors) {
			count++;
			strListbuf.append(String.format("%2d %s\n", count, sensor.getName()));
			Log.d(TAG, String.format("%s", sensor.getName()));
		}
		strListbuf.append("\n\n");

		textView.setText(strListbuf);
	}
}
