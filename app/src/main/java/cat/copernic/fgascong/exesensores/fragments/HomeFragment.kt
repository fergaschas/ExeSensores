package cat.copernic.fgascong.exesensores.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import cat.copernic.fgascong.exesensores.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    //Binding view
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        navController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.navSensorList.setOnClickListener {
            goToSensorListFragment()
        }

        binding.navMovementSensors.setOnClickListener {
            goToMovementSensors()
        }

        binding.navTemperatureSensors.setOnClickListener {
            goToTemperatureSensors()
        }

        binding.navLevelSensor.setOnClickListener {
            goToLevelSensor()
        }
        binding.navVoiceText.setOnClickListener {
            goToVoiceText()
        }

        return binding.root
    }

    private fun goToMovementSensors() {
        val action = HomeFragmentDirections.actionHomeFragmentToMovementSensorsFragment()
        navController.navigate(action)
    }

    private fun goToSensorListFragment() {
        val action = HomeFragmentDirections.actionHomeFragmentToSensorListFragment()
        navController.navigate(action)
    }

    private fun goToTemperatureSensors() {
        val action = HomeFragmentDirections.actionHomeFragmentToTemperatureSensorFragment()
        navController.navigate(action)
    }

    private fun goToLevelSensor(){
        val action = HomeFragmentDirections.actionHomeFragmentToLevelSensorFragment()
        navController.navigate(action)
    }

    private fun goToVoiceText(){
        val action = HomeFragmentDirections.actionHomeFragmentToVoiceTextFragment()
        navController.navigate(action)
    }

}