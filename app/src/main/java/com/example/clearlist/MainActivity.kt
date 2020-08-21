package com.example.clearlist

import android.os.Bundle
import android.view.View.GONE
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val REMOVE_DELAY = 100L
    }

    private val musicList by lazy { getItems() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupList()
        setupButton()
    }

    private fun setupButton() {
        btnClear.setOnClickListener { clearList() }
    }

    private fun clearList() {
        rcvList.suppressLayout(true)
        for (i in 0 until rcvList.childCount) {
            val anim = AnimationUtils.loadAnimation(this, R.anim.delete_anim)
            anim.startOffset = 100 + i * REMOVE_DELAY
            rcvList[i].startAnimation(anim)
            anim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}
                override fun onAnimationEnd(animation: Animation?) {
                    if (i == rcvList.childCount - 1) {
                        musicList.clear()
                        rcvList.adapter?.notifyDataSetChanged()
                        rcvList.suppressLayout(false)
                    } else {
                        rcvList[i].visibility = GONE
                    }
                }
                override fun onAnimationRepeat(animation: Animation?) {}
            })
        }
    }

    private fun setupList() {
        rcvList.adapter = ClearListAdapter(musicList) {
            Toast.makeText(this, it.first, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getItems(): ArrayList<Pair<String, String>> = arrayListOf(
        "Diamond Eyes" to "Diamond Eyes",
        "Royal" to "Diamond Eyes",
        "Ohms" to "Ohms",
        "Hears/Wires" to "Gore",
        "(L)MIRL" to "Gore",
        "Phantom Bride" to "Gore",
        "Beware" to "Saturday Night Wrist",
        "Rapture" to "Saturday Night Wrist",
        "Kimdracula" to "Saturday Night Wrist",
        "Lhabia" to "Around the Fur",
        "Rickets" to "Around the Fur",
        "Lotion" to "Around the Fur"
    )
}