package com.idealista.android.challenge.list.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.idealista.android.challenge.list.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        findViewById<View>(android.R.id.content).transitionName = "shared_image"
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        title = "Detalle"


        window.sharedElementEnterTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 3000L
        }
        window.sharedElementReturnTransition = MaterialContainerTransform().apply {
            addTarget(android.R.id.content)
            duration = 2500L
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var strUser: String = intent.getStringExtra("keyImage")
        var price: String = intent.getStringExtra("keyPrice")
        var desciption: String = intent.getStringExtra("keyDescription")

        txtDescription.text = "$price  $desciption muy amplia y consta con bonitos acabados y muy buenas vistas"

        Picasso.with(ivAd.context).load(strUser).into(ivAd)
    }
}