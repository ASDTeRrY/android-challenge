package com.idealista.android.challenge.list.ui

import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.idealista.android.challenge.core.Addressable
import com.idealista.android.challenge.core.intentTo
import com.idealista.android.challenge.list.ListAssembler
import com.idealista.android.challenge.list.R

class  ListActivity : AppCompatActivity(), ListView {

    private lateinit var listAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        title = "Publicaciones"

        ListAssembler.presenter = ListPresenter(this)
        listAdapter = ListAdapter(this)
        findViewById<RecyclerView>(R.id.recycler).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ListActivity)
            adapter = listAdapter
        }
        ListAssembler.presenter.onListNeeded()
    }

    override fun render(list: ListModel) {
        listAdapter.set(list)
        listAdapter.listener(object : ListAdapter.AdListener {
            override fun onAdClicked(ad: AdModel) {
                ListAssembler.presenter.onAdClicked(ad)
            }
        })
    }

}