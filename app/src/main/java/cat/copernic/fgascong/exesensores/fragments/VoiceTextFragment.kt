package cat.copernic.fgascong.exesensores.fragments

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import cat.copernic.fgascong.exesensores.databinding.FragmentVoiceTextBinding
import java.util.*

class VoiceTextFragment : Fragment(), TextToSpeech.OnInitListener {


    //Binding view
    private var _binding: FragmentVoiceTextBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentVoiceTextBinding.inflate(layoutInflater)
        navController = findNavController()

        enableVoice(false)

        textToSpeech = TextToSpeech(context, this)
        val speechListener = object : UtteranceProgressListener(){
            override fun onStart(utteranceId: String?) {
                binding.voiceApple.post{
                    enableVoice(false)
                }
            }

            override fun onDone(utteranceId: String?) {
                binding.voiceApple.post {
                    enableVoice(true)
                }
            }

            override fun onError(utteranceId: String?) {
                binding.voiceApple.post {
                    enableVoice(true)
                }
            }
        }
        textToSpeech.setOnUtteranceProgressListener(speechListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.apply {
            voiceHello.setOnClickListener {
                startVoice(binding.voiceHello.text.toString())
            }
            voiceBye.setOnClickListener {
                startVoice(binding.voiceBye.text.toString())
            }
            voiceOrange.setOnClickListener {
                startVoice(binding.voiceOrange.text.toString())
            }
            voiceApple.setOnClickListener {
                startVoice(binding.voiceApple.text.toString())
            }
            voiceStart.setOnClickListener {
                startVoice(binding.voiceMessage.text.toString())
            }
        }


        return binding.root
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = textToSpeech.setLanguage(Locale.getDefault())

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            } else {
                enableVoice(true)
            }

        }
    }

    private fun enableVoice(enable:Boolean){
        binding.apply {
            voiceStart.isEnabled = enable
            voiceApple.isEnabled = enable
            voiceBye.isEnabled = enable
            voiceHello.isEnabled = enable
            voiceOrange.isEnabled = enable
        }
    }

    private fun startVoice(sentence: String){
        textToSpeech.speak(sentence, TextToSpeech.QUEUE_FLUSH, null, "")
    }

}
