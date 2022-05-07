package cat.copernic.fgascong.exesensores.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LevelSensorViewModel: ViewModel() {

    private val _positionX: MutableLiveData<Float> = MutableLiveData(0f)
    val positionX: LiveData<Float> get() = _positionX

    private val _positionY: MutableLiveData<Float> = MutableLiveData(0f)
    val positionY: LiveData<Float> get() = _positionY

    fun setPositionX(x: Float){
        _positionX.value = x
    }

    fun setPositionY(y: Float){
        _positionY.value = y
    }
}