package com.ihrsachin.covid_19tracker

import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.ihrsachin.covid_19tracker.data.State

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<List<State>> {

    private var adapter : StateAdapter? = null

    val BASE_USGS_URL = "https://data.covid19india.org/v4/min/data.min.json"
    var progressBar : ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = StateAdapter(this,ArrayList<State>())
        val listView = findViewById<ListView>(R.id.listView)

        listView.adapter = adapter

        // Get a reference to the LoaderManager, in order to interact with loaders.
        // Get a reference to the LoaderManager, in order to interact with loaders.
        val loaderManager: LoaderManager = supportLoaderManager

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).

        val cm : ConnectivityManager = applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val nInfo : NetworkInfo? = cm.activeNetworkInfo
        if(nInfo != null && nInfo.isAvailable && nInfo.isConnected()) {
            loaderManager.initLoader(1, null, this)
        }
        else{
            progressBar = findViewById(R.id.progressBar)
            progressBar!!.visibility = View.GONE
            val starterText = findViewById<TextView>(R.id.StarterText)
            starterText.text = "No Network Connection"
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<List<State>> {
        progressBar = findViewById(R.id.progressBar)
        progressBar!!.visibility = View.VISIBLE
        return CovidAsyncLoader(this,BASE_USGS_URL)
    }

    override fun onLoadFinished(loader: Loader<List<State>>, data: List<State>?) {
        adapter!!.clear()
        if(data != null && data.isNotEmpty()){
            adapter!!.addAll(data)
        }
        else{
            val starterText = findViewById<TextView>(R.id.StarterText)
            starterText.text = "No Data Found"
        }
        progressBar = findViewById(R.id.progressBar)
        progressBar!!.visibility = View.GONE
    }

    override fun onLoaderReset(loader: Loader<List<State>>) {
        adapter!!.clear()
    }


}