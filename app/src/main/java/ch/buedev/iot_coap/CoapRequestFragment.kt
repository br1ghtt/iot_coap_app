package ch.buedev.iot_coap

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import ch.buedev.iot_coap.databinding.FragmentCoapRequestBinding
import org.eclipse.californium.core.CoapClient

private const val ARG_HOSTNAME = "hostname"
private const val TAG = "CoapRequestFragment"

/**
 * A simple [Fragment] subclass.
 * Use the [CoapRequestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CoapRequestFragment : Fragment() {

    private var _binding: FragmentCoapRequestBinding? = null
    private val binding get() = _binding!!

    private lateinit var hostname: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        if (savedInstanceState != null) {
            hostname = savedInstanceState.getString(ARG_HOSTNAME).toString()
        } else {
            arguments?.let {
                hostname = it.getString(ARG_HOSTNAME).toString()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        _binding = FragmentCoapRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreate")
        val spinner: Spinner = binding.spinnerCoapRequestMethod
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.coap_request_methods_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        binding.inputTextHostname.setText(hostname)
        binding.buttonMakeCoapRequest.setOnClickListener { makeCoapRequest() }
    }

    private fun makeCoapRequest() {
       val coapClient = CoapClient(hostname)
       val result = coapClient.discover()
        println(result)
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
         * @param hostname Parameter 1.
         * @return A new instance of fragment CoapRequestFragment.
         */
        @JvmStatic
        fun newInstance(hostname: String) =
            CoapRequestFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_HOSTNAME, hostname)
                }
            }
    }
}