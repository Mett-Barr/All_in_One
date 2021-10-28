package com.example.allinone.page2.contentProvider

import android.Manifest
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.allinone.databinding.FragmentContentProviderBinding

class ContentProviderFragment : Fragment() {
    private lateinit var binding: FragmentContentProviderBinding

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.i("Permission: ", "Granted")
            } else {
                Log.i("Permission: ", "Denied")
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContentProviderBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        init()
        permission()

        return binding.root
    }

    private fun init() {
//        binding.rv.setOnClickListener {
//            permission()
//        }
    }

    private fun permission() {
        Log.d(TAG, "permission: 0")
        when {
            context?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.READ_SMS,
                )
            } == PackageManager.PERMISSION_GRANTED -> {
                recyclerViewInit()
                Log.d(TAG, "permission: 1")
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.READ_SMS,
            ) -> {
                Toast.makeText(context, "權限請求被拒絕後用來再次和用說明的回調", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "permission: 2")
            }

            else -> {
                requestPermissionLauncher.launch(Manifest.permission.READ_SMS)
                Log.d(TAG, "permission: 3")
            }
        }
    }

    private fun recyclerViewInit() {
    }

    companion object {
        fun newInstance() = ContentProviderFragment()
    }
}