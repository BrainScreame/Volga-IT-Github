package com.osenov.mygithub.ui.login;

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView

import com.osenov.mygithub.R

class ProgressButton(view: View) {

    private var progressBarButtonProgress: ProgressBar =
        view.findViewById(R.id.progressBarButtonProgress)
    private var textViewButtonProgress: TextView = view.findViewById(R.id.textViewButtonProgress)

    fun buttonState() {
        progressBarButtonProgress.visibility = View.GONE;
        textViewButtonProgress.setText(R.string.button_login_web);
    }

    fun buttonActivated() {
        progressBarButtonProgress.visibility = View.VISIBLE;
        textViewButtonProgress.setText(R.string.button_load);
    }

    fun buttonFinished() {
        progressBarButtonProgress.visibility = View.GONE;
        textViewButtonProgress.setText(R.string.button_done);
    }
}
