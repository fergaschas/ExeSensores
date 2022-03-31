package cat.copernic.fgascong.exesensores.fragments

import android.content.Context.SENSOR_SERVICE
import android.graphics.Typeface
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import cat.copernic.fgascong.exesensores.databinding.FragmentTemperatureSensorBinding
import cat.copernic.fgascong.exesensores.viewmodels.TemperatureSensorViewModel

class TemperatureSensorFragment : Fragment(), SensorEventListener{

    //Binding view
    private var _binding: FragmentTemperatureSensorBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val viewModel: TemperatureSensorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentTemperatureSensorBinding.inflate(layoutInflater)
        navController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        startTemperatureSensors()

        viewModel.degrees.observe(viewLifecycleOwner){ newTemp ->
            binding.ambientTemperatureValue.text = newTemp.toString()
        }

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        stopTemperatureSensors()
    }

    override fun onResume() {
        super.onResume()
        startTemperatureSensors()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val sensor = event?.sensor
        if (sensor?.type == Sensor.TYPE_AMBIENT_TEMPERATURE){
            viewModel.setDegrees(event.values[0])
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    private fun startTemperatureSensors() {
        val sensorManager = activity?.getSystemService(SENSOR_SERVICE) as SensorManager
        val temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        if (temperatureSensor == null){
            rellenaTemperaturaConError()
        }
        sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    private fun stopTemperatureSensors() {
        val sensorManager = activity?.getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(this)
    }

    private fun rellenaTemperaturaConError() {
        val string = SpannableString("No tienes sensor :(")
        string.setSpan(StyleSpan(Typeface.BOLD), 0, string.length, 0)
        string.setSpan(RelativeSizeSpan(1.5f), 0, string.length, 0)
        binding.ambientTemperatureValue.text = string
    }

}