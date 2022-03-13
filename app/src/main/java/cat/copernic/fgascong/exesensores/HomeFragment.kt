package cat.copernic.fgascong.exesensores

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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        navController = findNavController()

        binding.navSensorList.setOnClickListener {
            goToSensorListFragment()
        }

        binding.navSensorList.setOnClickListener {
            goToMovementSensors()
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

}