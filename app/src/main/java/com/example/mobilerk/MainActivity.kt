package com.example.mobilerk

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.preference.Preference
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilerk.databinding.ActivityMainBinding
import java.lang.NumberFormatException
import java.util.prefs.PreferenceChangeListener
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() , SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var limit: Int = 10
    private var fsym: String = "BTC"
    private var tsym: String = "USD"
    private val sp: SharedPreferences = getSharedPreferences("activity_settings", MODE_PRIVATE)
    private val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    private val viewModel: HostViewModel by viewModels {
        HostViewModelFactory(fsym, tsym, limit)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sp.registerOnSharedPreferenceChangeListener(this)

        fillRecvView()
    }

    fun onClickSearch(v: View) {
        fillRecvView()
    }

    fun fillRecvView() {
        limit = sp.getInt("days", 10)
        tsym  = sp.getString("cur", "USD").toString()

        binding.viewModel = viewModel

        val dataSet = viewModel.data.value

        viewManager = LinearLayoutManager(this)
        viewAdapter = CustomAdapter(dataSet)

        recyclerView = findViewById<RecyclerView>(R.id.coins_recycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            val settings = Intent(this, Settings::class.java)
            startActivity(settings)
            // User chose the "Settings" item, show the app settings UI...
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    override fun onSharedPreferenceChanged(sp: SharedPreferences?, p1: String?) {
        if (sp != null) {
            limit = sp.getInt("days", 10)
            tsym  = sp.getString("cur", "USD").toString()
        }

        if (fsym != "") {
            fillRecvView()
        }
    }

    override fun onPreferenceChange(p: Preference?, newValue: Any?): Boolean {
        if (p != null && p.key.equals("days")) {
            try {
                val value: Int = newValue.toString().toInt()
                if (value !in 1..15) {
                    return false
                }
            } catch (e: NumberFormatException) {
                return false
            }
        }
        return true
    }
}