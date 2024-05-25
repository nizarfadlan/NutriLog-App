package com.nutrilog.app.utils.helpers

import android.view.View
import com.skydoves.powerspinner.PowerSpinnerView

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun PowerSpinnerView.setMonth(currentMonth: Int?) {
    currentMonth?.also { this.selectItemByIndex(it) }
}
