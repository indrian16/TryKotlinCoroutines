package io.indrian16.trykotlincoroutines.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.indrian16.trykotlincoroutines.R
import io.indrian16.trykotlincoroutines.ui.post.PostFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                    .replace(R.id.mainContainer, PostFragment.newInstance())
                    .commit()
        }
    }
}
