package com.example.allinone.page2.workManager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.allinone.databinding.FragmentWorkManagerBinding
import java.util.concurrent.TimeUnit

const val INPUT_DATA_KEY = "Data Input"
const val OUTPUT_DATA_KEY = "Data Output"

const val WORK_A = "Work A"
const val WORK_B = "Work B"

class WorkManagerFragment : Fragment() {

    private lateinit var binding: FragmentWorkManagerBinding
    private val viewModel: WorkManagerViewModel by viewModels()

    private lateinit var workManager: WorkManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorkManagerBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        init()

        return binding.root
    }

    private fun init() {
        workManager = context?.let { WorkManager.getInstance(it) }!!
//        switchConstraints()
//        switchDelay()
//        switchTag()
        chainA()
        chainB()
        buttonEnqueueA()
        buttonEnqueueB()
    }

    private fun chainA() {
        binding.chainA.apply {
            viewModel.chain.observe(viewLifecycleOwner, Observer {
                this.isChecked = it
            })
        }
    }

    private fun chainB() {
        binding.chainB.apply {
            viewModel.chain.observe(viewLifecycleOwner, Observer {
                this.isChecked = it
            })
        }
    }

    private fun buttonEnqueueA() {
        binding.button.setOnClickListener {

            val workRequestA = createWorkRequestA(WORK_A)
            val workRequestB = createWorkRequestB(WORK_B)

            if (viewModel.chain.value!!) {
                workManager.beginWith(workRequestA).then(workRequestB).enqueue()
                workObserver(WORK_B, workRequestB)
            } else workManager.enqueue(workRequestA)

            workObserver(WORK_A, workRequestA)
        }
    }

    private fun buttonEnqueueB() {
        binding.buttonB.setOnClickListener {

            val workRequestA = createWorkRequestA(WORK_A)
            val workRequestB = createWorkRequestB(WORK_B)

            if (viewModel.chain.value!!) {
                workManager.beginWith(workRequestA).then(workRequestB).enqueue()
                workObserver(WORK_A, workRequestA)
            } else workManager.enqueue(workRequestB)

            workObserver(WORK_B, workRequestB)

        }
    }

    private fun createWorkRequestA(name: String): OneTimeWorkRequest {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        return OneTimeWorkRequestBuilder<MyWorker>().run {
            if (binding.constraints.isChecked) setConstraints(constraints)
            if (binding.delay.isChecked) setInitialDelay(1, TimeUnit.SECONDS)
            if (binding.addtag.isChecked) addTag(name)
            build()
        }
    }

    private fun createWorkRequestB(name: String): OneTimeWorkRequest {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        return OneTimeWorkRequestBuilder<MyWorker>().run {
            if (binding.constraintsB.isChecked) setConstraints(constraints)
            if (binding.delayB.isChecked) setInitialDelay(1, TimeUnit.SECONDS)
            if (binding.addTagB.isChecked) addTag(name)
            build()
        }
    }

    private fun workObserver(name: String, workRequest: WorkRequest) {
        workManager.getWorkInfoByIdLiveData(workRequest.id).observe(viewLifecycleOwner, Observer {
            result(name, it.state)
        })
    }

    private fun result(name: String, state: WorkInfo.State) {
        viewModel.apply {
            when (name) {
                WORK_A -> {
                    when (state) {
                        WorkInfo.State.SUCCEEDED -> plusA()
                        WorkInfo.State.FAILED -> zeroA()
                        else -> {
                        }
                    }
                }
                WORK_B -> when (state) {
                    WorkInfo.State.SUCCEEDED -> plusB()
                    WorkInfo.State.FAILED -> zeroB()
                    else -> {
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = WorkManagerFragment()
    }
}