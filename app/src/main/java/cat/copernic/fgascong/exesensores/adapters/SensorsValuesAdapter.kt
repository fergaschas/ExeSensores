package cat.copernic.fgascong.exesensores.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import cat.copernic.fgascong.exesensores.R
import cat.copernic.fgascong.exesensores.models.SensorWithValues
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class SensorsValuesAdapter(private val dataSet: List<SensorWithValues>) :
    RecyclerView.Adapter<SensorsValuesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.sensor_title)
        val type: TextView = view.findViewById(R.id.sensor_type)
        val showValues: Button = view.findViewById(R.id.btn_show_sensor_values)

        val values: ChipGroup = view.findViewById(R.id.sensor_values)

        val xValue: Chip = view.findViewById(R.id.x_value)
        val yValue: Chip = view.findViewById(R.id.y_value)
        val zValue: Chip = view.findViewById(R.id.z_value)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.sensor_item_values, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.title.text = dataSet[position].sensor.name
        viewHolder.type.text = dataSet[position].sensor.stringType

        viewHolder.showValues.setOnClickListener {
            viewHolder.values.visibility =
                if (viewHolder.values.visibility == View.GONE) View.VISIBLE else View.GONE
        }

        viewHolder.xValue.text = String.format("X: %.2f", dataSet[position].x)
        viewHolder.yValue.text = String.format("Y: %.2f", dataSet[position].y)
        viewHolder.zValue.text = String.format("Z: %.2f", dataSet[position].z)

    }

    override fun getItemCount() = dataSet.size

}