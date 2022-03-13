package cat.copernic.fgascong.exesensores.models

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData

class SensorWithValues(val sensor: Sensor, val context: Activity) : SensorEventListener {

    var x: MutableLiveData<Float> = MutableLiveData(0.0f)
    var y: MutableLiveData<Float> = MutableLiveData(0.0f)
    var z: MutableLiveData<Float> = MutableLiveData(0.0f)

    fun startListening() {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun stopListening(){
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (sensor.type == event?.sensor?.type) {
            x.value = event.values[0]
            y.value = event.values[1]
            z.value = event.values[2]
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

}