package ch.buedev.iot_coap

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import ch.buedev.iot_coap.databinding.FragmentCoapServerBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [CoapServerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val ARG_HOSTNAME = "hostname"
private const val TAG = "CoapServerFragment"

class CoapServerFragment : Fragment() {

    private var _binding: FragmentCoapServerBinding? = null
    private val binding get() = _binding!!

    private lateinit var hostname: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate has savedInstanceState")
            hostname = savedInstanceState.getString(ARG_HOSTNAME).toString()
        } else {
            Log.d(TAG, "onCreate sets default hostname")
           hostname = getString(R.string.default_coap_uri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCoapServerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            inputTextServerHostname.addTextChangedListener(HostnameChangeTextWatcher())
        }
        binding.buttonGotoRequest.setOnClickListener {
            view.findNavController().navigate(
                CoapServerFragmentDirections.actionCoapServerFragmentToCoapRequestFragment(
                    hostname = hostname
                )
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG, "onSaveInstanceState")
        outState.putString(ARG_HOSTNAME, hostname)
        super.onSaveInstanceState(outState)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment CoapServerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CoapServerFragment().apply {
            }
    }

    inner class HostnameChangeTextWatcher: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            Log.d(TAG, "beforeTextChanged")
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            Log.d(TAG, "onTextChanged")
        }

        override fun afterTextChanged(p0: Editable?) {
            Log.d(TAG, "afterTextChanged")
            hostname = binding.inputTextServerHostname.text.toString()
        }
    }
}
