package com.example.allinone.page2.vibration

import android.annotation.SuppressLint
import android.view.*
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.allinone.R


class CustomAdapter(private val dataSet: Array<String>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button: Button

        init {
            // Define click listener for the ViewHolder's View.
            button = view.findViewById(R.id.vibrator_bt)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.vibrator_button, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.button.apply {
            text = dataSet[position]
//            setOnClickListener {
//                it.performHapticFeedback(hapticFeedbackConstants(position))
//            }
            setOnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    v.performHapticFeedback(hapticFeedbackConstants(position))
                }
                false
            }
        }
    }

    private fun hapticFeedbackConstants(i: Int): Int {
        return when (i) {
            0 -> HapticFeedbackConstants.CLOCK_TICK
            1 -> HapticFeedbackConstants.CONFIRM
            2 -> HapticFeedbackConstants.CONTEXT_CLICK
            3 -> HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
            4 -> HapticFeedbackConstants.FLAG_IGNORE_VIEW_SETTING
            5 -> HapticFeedbackConstants.GESTURE_END
            6 -> HapticFeedbackConstants.GESTURE_START
            7 -> HapticFeedbackConstants.KEYBOARD_PRESS
            8 -> HapticFeedbackConstants.KEYBOARD_RELEASE
            9 -> HapticFeedbackConstants.KEYBOARD_TAP
            10 -> HapticFeedbackConstants.LONG_PRESS
            11 -> HapticFeedbackConstants.REJECT
            else -> HapticFeedbackConstants.CLOCK_TICK
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
