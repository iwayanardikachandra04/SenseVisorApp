package com.example.sensevisor.utils

import android.annotation.SuppressLint
import android.text.InputType
import android.view.MotionEvent
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.example.sensevisor.R

object ViewUtils {

    private var isPasswordVisible = false

    /**
     * Setup password visibility toggle on an EditText using drawableEnd.
     * Switches icon and inputType between visible and hidden password.
     */
    @SuppressLint("ClickableViewAccessibility")
    fun setupPasswordToggle(editText: EditText) {
        editText.setCompoundDrawablesWithIntrinsicBounds(
            null,
            null,
            ContextCompat.getDrawable(editText.context, R.drawable.ic_eye_off),
            null
        )

        editText.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = 2 // index for drawableEnd
                val drawable = editText.compoundDrawables[drawableEnd]
                if (drawable != null) {
                    val drawableWidth = drawable.bounds.width()
                    val touchAreaStart = editText.width - editText.paddingEnd - drawableWidth

                    if (event.x >= touchAreaStart) {
                        isPasswordVisible = !isPasswordVisible
                        val cursorPosition = editText.text.length

                        editText.inputType = if (isPasswordVisible) {
                            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                        } else {
                            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        }

                        val iconRes = if (isPasswordVisible) R.drawable.ic_eye else R.drawable.ic_eye_off
                        editText.setCompoundDrawablesWithIntrinsicBounds(
                            null,
                            null,
                            ContextCompat.getDrawable(editText.context, iconRes),
                            null
                        )
                        editText.setSelection(cursorPosition)

                        return@setOnTouchListener true
                    }
                }
            }
            false
        }
    }
}
