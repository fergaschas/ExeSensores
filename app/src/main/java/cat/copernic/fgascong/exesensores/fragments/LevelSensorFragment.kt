package cat.copernic.fgascong.exesensores.fragments

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import cat.copernic.fgascong.exesensores.databinding.FragmentLevelSensorBinding

class LevelSensorFragment : Fragment(), SensorEventListener {

    //Binding view
    private var _binding: FragmentLevelSensorBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    lateinit var fondo: Lienzo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentLevelSensorBinding.inflate(layoutInflater)
        navController = findNavController()

        fondo = Lienzo(this.requireContext())
        binding.root.addView(fondo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        startLevelSensor()
        binding.calibrate.setOnClickListener {
            fondo.posXCallibrated = -fondo.posx * 2
            fondo.posYCallibrated = -fondo.posy * 2
        }
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        stopLevelSensor()
    }

    override fun onResume() {
        super.onResume()
        startLevelSensor()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val sensor = event?.sensor
        if (sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            fondo.posx = (event.values[0] * -100)
            fondo.posy = (event.values[1] * 100)
            fondo.invalidate()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    private fun startLevelSensor() {
        val sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val levelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorManager.registerListener(this, levelSensor, SensorManager.SENSOR_DELAY_FASTEST)
    }

    private fun stopLevelSensor() {
        val sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(this)
    }


    class Lienzo(context: Context) : View(context) {

        //var maxim = 50f
        var posx: Float = 0f
        var posy: Float = 0f
        var posXCallibrated = 0f
        var posYCallibrated = 0f

        override fun onDraw(canvas: Canvas) {
            canvas.drawRGB(10, 10, 10)
            val ancho = width / 20f
            val calibradoAncho = width + posXCallibrated
            val calibradoAlto = height + posYCallibrated
            val pincel1 = Paint()
            pincel1.setARGB(255, 255, 100, 100)

            canvas.drawCircle(
                (calibradoAncho / 2) + posx,
                (calibradoAlto / 2) + posy,
                ancho,
                pincel1
            )
        }
    }

}