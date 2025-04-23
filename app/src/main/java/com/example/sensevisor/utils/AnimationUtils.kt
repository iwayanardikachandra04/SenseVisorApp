package com.example.sensevisor.utils

import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior

object AnimationUtils {

    fun setupBottomSheet(
        bottomSheet: View,
        peekHeight: Int = 150,
        expandOnStart: Boolean = true,
        hideable: Boolean = false,
        onSlide: ((Float) -> Unit)? = null
    ): BottomSheetBehavior<*> {
        val behavior = BottomSheetBehavior.from(bottomSheet)

        behavior.peekHeight = peekHeight
        behavior.isHideable = hideable

        if (expandOnStart) {
            bottomSheet.post {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                onSlide?.invoke(slideOffset)
            }
        })

        return behavior
    }

    fun toggleBottomSheet(behavior: BottomSheetBehavior<*>) {
        behavior.state = when (behavior.state) {
            BottomSheetBehavior.STATE_EXPANDED -> BottomSheetBehavior.STATE_COLLAPSED
            else -> BottomSheetBehavior.STATE_EXPANDED
        }
    }
}
