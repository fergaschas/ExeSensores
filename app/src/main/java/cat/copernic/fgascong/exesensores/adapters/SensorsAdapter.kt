package cat.copernic.fgascong.exesensores.adapters

import android.hardware.Sensor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.fgascong.exesensores.R

class SensorsAdapter(private val dataSet: List<Sensor>) :
    RecyclerView.Adapter<SensorsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.sensor_title)
        val vendor: TextView = view.findViewById(R.id.sensor_vendor)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.sensor_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.title.text =
            String.format("%s - %s", dataSet[position].vendor, dataSet[position].name)
        viewHolder.vendor.text = dataSet[position].stringType
    }

    override fun getItemCount() = dataSet.size

}