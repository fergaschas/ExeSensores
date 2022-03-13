package cat.copernic.fgascong.exesensores.adapters

import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.fgascong.exesensores.R
import cat.copernic.fgascong.exesensores.models.SensorValues

class SensorsValuesAdapter(private val dataSet: List<SensorValues>) :
    RecyclerView.Adapter<SensorsValuesAdapter.ViewHolder>(), SensorEventListener {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.sensor_title)
        val type: TextView = view.findViewById(R.id.sensor_type)
        val showValues: Button = view.findViewById(R.id.btn_show_sensor_values)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.sensor_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.title.text = dataSet[position].sensor.name
        viewHolder.type.text = dataSet[position].sensor.stringType

        viewHolder.showValues.setOnClickListener {

        }
    }

    override fun getItemCount() = dataSet.size

}