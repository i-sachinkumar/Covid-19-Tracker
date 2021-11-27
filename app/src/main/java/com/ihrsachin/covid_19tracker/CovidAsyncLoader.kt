package com.ihrsachin.covid_19tracker

import android.content.Context
import androidx.loader.content.AsyncTaskLoader
import com.ihrsachin.covid_19tracker.data.State
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
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
            for (i in 1..37) {

                val state_name: String? = getState(getCode(i).toString())

                val total : JSONObject = jsonObject.getJSONObject("total")
                val total_confirmed = total.getInt("confirmed")
                val total_cured = total.getInt("recovered")
                val total_death = total.getInt("deceased")

                val delta : JSONObject = jsonObject.getJSONObject("delta")
                val day_confirmed = delta.getInt("confirmed")
                val day_cured = delta.getInt("recovered")
                val day_death = delta.getInt("deceased")
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
            9 -> return  "DD"
            10 -> return "GA"
            11 -> return "GJ"
            12 -> return "DL"
            13 -> return "HR"
            14 -> return "HP"
            15 -> return "JK"
            16 -> return "JH"
            17 -> return "KA"
            18 -> return "KL"
            19 -> return "LA"
            20 -> return "LD"
            21 -> return "MP"
            22 -> return "MH"
            23 -> return "MN"
            24 -> return "ML"
            25 -> return "MZ"
            26 -> return "NL"
            27 -> return "OR"
            28 -> return "PB"
            29 -> return "PY"
            30 -> return "RJ"
            31 -> return "SK"
            32 -> return "TN"
            33 -> return "TG"
            34 -> return "TR"
            35 -> return "UP"
            36 -> return "UT"
            37 -> return "WB"
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