package cat.copernic.fgascong.exesensores.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TemperatureSensorViewModel : ViewModel() {

    private val _degrees: MutableLiveData<Float> = MutableLiveData(0f)
    val degrees : LiveData<Float> get() = _degrees

    fun setDegrees(degrees: Float){
        _degrees.value = degrees
    }
}