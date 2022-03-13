package cat.copernic.fgascong.exesensores.fragments

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import cat.copernic.fgascong.exesensores.adapters.SensorsAdapter
import cat.copernic.fgascong.exesensores.databinding.FragmentSensorListBinding


class SensorListFragment : Fragment() {

    //Binding view
    private var _binding: FragmentSensorListBinding? = null
    private val binding get() = _binding


    private lateinit var navController: NavController
    private lateinit var sensors: List<Sensor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentSensorListBinding.inflate(layoutInflater)
        navController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sensors = getSensors()
        binding?.recyclerView?.adapter = SensorsAdapter(sensors)
        return binding?.root
    }

    private fun getSensors(): List<Sensor> {
        val sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        return sensorManager.getSensorList(Sensor.TYPE_ALL)
    }

}