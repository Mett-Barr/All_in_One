package com.example.allinone.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.allinone.R
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.coroutineContext

class MainAdapter(
    private val dataSet: Array<String>,
    private val viewModel: MainViewModel,
    private val mainFragment: MainFragment
) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val c = Calendar.getInstance()
    private val df = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
//    private val tf = SimpleDateFormat("hh:mm", Locale.getDefault())
    private val hf = SimpleDateFormat("hh", Locale.getDefault())
    private val mf = SimpleDateFormat("mm", Locale.getDefault())

    private val datePicker = MaterialDatePicker.Builder.datePicker()
        .setTitleText("Select dates")
        .build()

    private val timePicker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H)
        .setTitleText("Select Appointment time")
        .setHour(hf.format(c.time).toInt())
        .setMinute(mf.format(c.time).toInt())
        .build()


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tx)
        val cardView: CardView = view.findViewById(R.id.cv)

        init {
            // Define click listener for the ViewHolder's View.
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.main_cardview, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.apply {
            textView.text = dataSet[position]
            cardView.setOnClickListener {
                viewModel._goToState.value = position
                when (position) {
                    0 -> mainFragment.toast()
                    2 -> {
                        datePicker.show(mainFragment.childFragmentManager, "")
                        datePicker.addOnPositiveButtonClickListener {
                            c.timeInMillis = it
                            this.textView.text = df.format(c.time)
                        }
                    }
                    3 -> {
                        timePicker.show(mainFragment.childFragmentManager, "")
                        timePicker.addOnPositiveButtonClickListener {
                            this.textView.text = timePicker.hour.toString() + ":" + timePicker.minute.toString()
                        }
                    }

                    // 只需要導航不用其他操作
                    else -> {
                        viewModel.apply {
                            _goToState.value = position
                            goToPage2()
                        }
                    }
                }
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}