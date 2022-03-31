package cat.copernic.fgascong.exesensores.fragments

import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import cat.copernic.fgascong.exesensores.adapters.SensorsValuesAdapter
import cat.copernic.fgascong.exesensores.databinding.FragmentMovementSensorsBinding
import cat.copernic.fgascong.exesensores.models.SensorWithValues
import cat.copernic.fgascong.exesensores.sensorsWithXYZValues
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.coroutineContext

class MovementSensorsFragment : Fragment(){

    //Binding view
    private var _binding: FragmentMovementSensorsBinding? = null
    private val binding get() = _binding
    private lateinit var navController: NavController
    private lateinit var moveSensors: List<SensorWithValues>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentMovementSensorsBinding.inflate(layoutInflater)
        navController = findNavController()
        moveSensors = getSensors()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        startSensors(moveSensors)
        binding?.recyclerView?.adapter = SensorsValuesAdapter(moveSensors)
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        startSensors(moveSensors)
    }

    override fun onDetach() {
        super.onDetach()
        stopSensors(moveSensors)
    }

    private fun getSensors(): List<SensorWithValues> {
        val sensorManager = activity?.getSystemService(SENSOR_SERVICE) as SensorManager
        val movementSensors: MutableList<SensorWithValues> = mutableListOf()
        var sensor: Sensor

        for (i in sensorsWithXYZValues) {
            sensor = sensorManager.getDefaultSensor(i)
            val sensorWithValues = SensorWithValues(sensor, requireActivity())
            Log.d("SISISI", sensorWithValues.sensor.name)
            movementSensors.add(sensorWithValues)
        }
        return movementSensors
    }

    private fun startSensors(sensors: List<SensorWithValues>){
        for(sensor in sensors){
            Log.d("SISISI", sensor.sensor.name + "iniciado")
            sensor.startListening()
        }
    }

    private fun stopSensors(sensors: List<SensorWithValues>){
        for(sensor in sensors){
            Log.d("SISISI", sensor.sensor.name + "parado")
            sensor.stopListening()
        }
    }
}