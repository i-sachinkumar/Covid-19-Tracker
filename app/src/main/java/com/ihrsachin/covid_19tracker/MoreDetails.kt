package com.ihrsachin.covid_19tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ihrsachin.covid_19tracker.data.State
import java.text.DecimalFormat

class MoreDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_details)

        val intent = intent
        val state : State = intent.getSerializableExtra("State") as State

        title = state.state_name

        val total_confirmed_textView : TextView = findViewById(R.id.total_confirmed)
        total_confirmed_textView.text = state.total_confirmed.toString()

        val total_cured_textView : TextView = findViewById(R.id.total_cured)
        total_cured_textView.text = state.total_cured.toString()

        val total_death_textView : TextView = findViewById(R.id.total_death)
        total_death_textView.text = state.total_death.toString()

        val total_active_textView : TextView = findViewById(R.id.total_active)
        total_active_textView.text = (state.total_confirmed - state.total_cured - state.total_death).toString()

        val day_confirm_textView : TextView = findViewById(R.id.day_confirm)
        day_confirm_textView.text = "↑${state.day_confirm}"

        val day_cured_textView : TextView = findViewById(R.id.day_cured)
        day_cured_textView.text = "↑${state.day_cured}"

        val day_death_textView : TextView = findViewById(R.id.day_death)
        day_death_textView.text = "↑${state.day_death}"

        val day_active_textView : TextView = findViewById(R.id.day_active)
        day_active_textView.text = "↑${state.day_confirm}"

        val formatter = DecimalFormat("#.#")
        val total_population = state.total_population

        val total_vaccinated1_textView : TextView = findViewById(R.id.total_vaccinated1)
        total_vaccinated1_textView.text = "${state.total_vaccinated1} (${formatter.format(state.total_vaccinated1.toDouble()/total_population.toDouble()*100)}%)"

        val total_vaccinated2_textView : TextView = findViewById(R.id.total_vaccinated2)
        total_vaccinated2_textView.text = "${state.total_vaccinated2} (${formatter.format(state.total_vaccinated2.toDouble()/total_population.toDouble()*100)}%)"

        val day_vaccinated1_textView : TextView = findViewById(R.id.day_vaccinated1)
        day_vaccinated1_textView.text = "↑${state.day_vaccinated1}"

        val day_vaccinated2_textView : TextView = findViewById(R.id.day_vaccinated2)
        day_vaccinated2_textView.text = "↑${state.day_vaccinated2}"

    }
}