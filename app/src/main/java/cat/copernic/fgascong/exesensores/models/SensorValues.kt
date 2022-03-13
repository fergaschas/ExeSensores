package cat.copernic.fgascong.exesensores.models

import android.hardware.Sensor

data class SensorValues(val sensor: Sensor, var values : Array<Float>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SensorValues

        if (sensor != other.sensor) return false

        return true
    }

    override fun hashCode(): Int {
        return sensor.hashCode()
    }
}