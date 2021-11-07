package com.example.allinone.page2.contentProvider

import android.Manifest
import android.content.ContentResolver
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allinone.databinding.FragmentContentProviderBinding
import com.example.allinone.main.ApplicationToast

class ContentProviderFragment : Fragment() {
    private lateinit var binding: FragmentContentProviderBinding

    private lateinit var contentResolver: ContentResolver
    private lateinit var cursor: Cursor
    private val contactsUri = Uri.parse("content://com.android.contacts/contacts")
    private var contactsNameArray: ArrayList<String> = ArrayList()


    // 權限請求後調用
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            // 權限通過
            if (isGranted) {
                Log.i("Permission: ", "Granted")
                isGranted()
            }

            // 權限被拒
            else {
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
        permissionCheck()

        return binding.root
    }

    private fun init() {
//        recyclerView()
        button()
    }

    private fun button() {
        binding.button10.setOnClickListener {
            binding.rv.adapter?.notifyItemMoved(2, 3)
        }
    }

    private fun recyclerView() {
        binding.rv.apply {
//            adapter = ContentProviderAdapter(resources.getStringArray(R.array.test_array))
            adapter = ContentProviderAdapter(contactsNameArray)
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun permissionCheck() {
        Log.d(TAG, "permission: 0")
        when {
            // 權限已通過
            context?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.READ_SMS,
                )
            } == PackageManager.PERMISSION_GRANTED -> {
                
                isGranted()

//                Log.d(TAG, "permission: 1")
//
//                Toast.makeText(context, "!!!", Toast.LENGTH_SHORT).show()
//
////                binding.rv.adapter = ContentProviderAdapter(resources.getStringArray(R.array.test_array))
//
            }

            // 權限要求第一次被拒
            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.READ_SMS,
            ) -> {
                Toast.makeText(context, "權限請求被拒絕後用來再次和用說明的回調", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "permission: 2")
            }

            // 權限未開啟，請求權限並在結束後調用 registerForActivityResult
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
                Log.d(TAG, "permission: 3")
            }
        }
    }

    private fun isGranted() {
        getContent()
        recyclerView()
    }

    private fun getContent() {
        try {
            cursor = activity?.contentResolver?.query(
                contactsUri,
                null,
                null,
                null
            )!!

            while (cursor.moveToNext()) {
                contactsNameArray.add(
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                )
            }

//            Toast.makeText(context, "聯絡人已載入", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "getContent: ")
            cursor.close()

            context?.let { ApplicationToast.showToast(it, "聯絡人已載入") }

        } catch (e: NullPointerException) {
            Toast.makeText(context, "activity null!!!", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance() = ContentProviderFragment()
    }
}