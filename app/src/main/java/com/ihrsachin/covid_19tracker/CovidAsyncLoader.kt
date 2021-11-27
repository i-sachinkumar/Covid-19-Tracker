package com.ihrsachin.covid_19tracker

import android.content.Context
import androidx.loader.content.AsyncTaskLoader
import com.ihrsachin.covid_19tracker.data.State
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.nio.charset.Charset

class CovidAsyncLoader(context: Context, private val baseUrl: String?) :
    AsyncTaskLoader<List<State>>(context) {


    override fun loadInBackground(): List<State> {
        val url = createUrl(baseUrl)
        /** Perform HTTP request to the URL and receive a JSON response back */
        var jsonResponse: String? = ""
        try {
            jsonResponse = makeHttpRequest(url!!)
        } catch (e: IOException) {
            // TODO Handle the IOException
        }


        /** Extract relevant fields from the JSON response and create an {@link Earthquake} object
         * Return the {@link List<Earthquake>} object as the result for the {@link MyAsyncTaskLoader}
         */

        return extractFeatureFromJson(jsonResponse!!)
    }

    private fun createUrl(urlString: String?): URL? {
        return try {
            URL(urlString)
        } catch (e: MalformedURLException) {
            null
        }
    }

    private fun makeHttpRequest(url: URL?): String {
        var jsonResponse = ""
        var urlConnection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        if (url == null) return jsonResponse
        try {
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.readTimeout = 10000
            urlConnection.connectTimeout = 15000
            urlConnection.connect()
            if(urlConnection.responseCode != 200) return jsonResponse
            inputStream = urlConnection.inputStream
            jsonResponse = readFromStream(inputStream)
        } catch (e: IOException) {
        } finally {
            inputStream?.close()
            urlConnection!!.disconnect()
        }
        return jsonResponse
    }

    private fun readFromStream(inputStream: InputStream?): String {
        val stringBuilder = StringBuilder()
        if (inputStream != null) {
            val inputStreamReader = InputStreamReader(inputStream, Charset.forName("UTF-8"))
            val bufferedReader = BufferedReader(inputStreamReader)
            var eachLine = bufferedReader.readLine()
            while (eachLine != null) {
                stringBuilder.append(eachLine)
                eachLine = bufferedReader.readLine()
            }
        }
        return stringBuilder.toString()
    }


    private fun extractFeatureFromJson(jsonResponse: String): List<State> {
        val list = ArrayList<State>()
        if(jsonResponse != "") {
            val jsonObject = JSONObject(jsonResponse)
            for (i in 1..36) {
                val state_name: String? = getState(getCode(i).toString())


                val total : JSONObject = jsonObject.getJSONObject(getCode(i).toString()).getJSONObject("total")

                var total_confirmed = 0
                try{
                    total_confirmed = total.getInt("confirmed")
                } catch (e : JSONException){}


                var total_cured = 0
                try{
                    total_cured = total.getInt("recovered")
                } catch (e : JSONException){}


                var total_death = 0
                try{
                    total_death = total.getInt("deceased")
                } catch (e : JSONException){}


                var total_vaccination1 = 0
                try{
                    total_vaccination1 = total.getInt("vaccinated1")
                } catch (e : JSONException){}


                var total_vaccination2 = 0
                try{
                    total_vaccination2 = total.getInt("vaccinated2")
                } catch (e : JSONException){}


                var total_population = 0
                try{
                    total_population = jsonObject.getJSONObject(getCode(i).toString()).getJSONObject("meta").getInt("population")
                } catch (e : JSONException){}


                val delta : JSONObject = jsonObject.getJSONObject(getCode(i).toString()).getJSONObject("delta")
                var day_confirmed = 0
                try{
                    day_confirmed = delta.getInt("confirmed")
                } catch (e : JSONException){}


                var day_cured = 0
                try{
                    day_cured = delta.getInt("recovered")
                } catch (e : JSONException){}


                var day_death = 0
                try{
                    day_death = delta.getInt("deceased")
                } catch (e : JSONException){}


                var day_vaccination1 = 0
                try{
                    day_vaccination1 = delta.getInt("vaccinated1")
                } catch (e : JSONException){}


                var day_vaccination2 = 0
                try{
                    day_vaccination2 = delta.getInt("vaccinated2")
                } catch (e : JSONException){}


                list.add(State(
                    state_name,
                    total_confirmed,
                    total_cured,
                    total_death,
                    total_population,
                    total_vaccination1,
                    total_vaccination2,
                    day_confirmed,
                    day_cured,
                    day_death,
                    day_vaccination1,
                    day_vaccination2
                ))
            }
        }
        return list
    }

    override fun onStartLoading() {
        forceLoad()
    }

    public fun getCode(int_code : Int) : String?{
        when(int_code){
            1 -> return  "AN"
            2 -> return  "AP"
            3 -> return  "AR"
            4 -> return  "AS"
            5 -> return  "BR"
            6 -> return  "CH"
            7 -> return  "CT"
            8 -> return  "DN"
            9 -> return "GA"
            10 -> return "GJ"
            11 -> return "DL"
            12 -> return "HR"
            13 -> return "HP"
            14 -> return "JK"
            15 -> return "JH"
            16 -> return "KA"
            17 -> return "KL"
            18 -> return "LA"
            19 -> return "LD"
            20 -> return "MP"
            21 -> return "MH"
            22 -> return "MN"
            23 -> return "ML"
            24 -> return "MZ"
            25 -> return "NL"
            26 -> return "OR"
            27 -> return "PB"
            28 -> return "PY"
            29 -> return "RJ"
            30 -> return "SK"
            31 -> return "TN"
            32 -> return "TG"
            33 -> return "TR"
            34 -> return "UP"
            35 -> return "UT"
            36 -> return "WB"
        }
        return null
    }

    public fun getState(state_code : String) : String? {
        when(state_code){
            "AN" -> return "Andaman & Nicobar"
            "AP" -> return "Andhra Pradesh"
            "AR" -> return "Arunachal Pradesh"
            "AS" -> return "Assam"
            "BR" -> return "Bihar"
            "CH" -> return "Chandigarh"
            "CT" -> return "Chhattisgarh"
            "DL" -> return "Delhi"
            "DN" -> return "Dadra and Nagar Haveli"
            "DD" -> return "Daman and Diu"
            "GA" -> return "Goa"
            "GJ" -> return "Gujarat"
            "HR" -> return "Haryana"
            "HP" -> return "Himachal Pradesh"
            "JK" -> return "Jammu and Kashmir"
            "JH" -> return "Jharkhand"
            "KA" -> return "Karnataka"
            "KL" -> return "Kerala"
            "LA" -> return "Ladakh"
            "LD" -> return "Lakshadweep"
            "MP" -> return "Madhya Pradesh"
            "MH" -> return "Maharashtra"
            "MN" -> return "Manipur"
            "ML" -> return "Meghalaya"
            "MZ" -> return "Mizoram"
            "NL" -> return "Nagaland"
            "OR" -> return "Odisha"
            "PB" -> return "Punjab"
            "PY" -> return "Pondicherry"
            "RJ" -> return "Rajasthan"
            "SK" -> return "Sikkim"
            "TN" -> return "Tamil Nadu"
            "TG" -> return "Telangana"
            "TR" -> return "Tripura"
            "UP" -> return "Uttar Pradesh"
            "UT" -> return "Uttarakhand"
            "WB" -> return "West Bengal"
        }
        return null
    }

}