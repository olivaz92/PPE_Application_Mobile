package fr.efficom.formation.ppe1719

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.efficom.formation.ppe1719.api.BornesService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val bornesService: BornesService

    init {
        bornesService = createBornesService()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        galery_button.setOnClickListener {
            val intent = Intent(this@MainActivity, PhotosActivity::class.java)
            intent.putExtra("CODE",codeEventEditText.text.toString());
            codeEventEditText.setText("")

            startActivity(intent)
        }

    }

    /*fun showPhotosAsList(){
        val codeEvent = codeEventEditText.text.toString()
        val request = bornesService.getPhotosForEventAsList(codeEvent)

        request.enqueue(object : Callback<List<Photo>>{
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Log.w("bornesService", "Echec loginUser")
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {

                var result = response?.body()

                if(result!=null){
                    var length = response?.body()!!.size
                    var str:String = ""

                    for (i in 0 until length) {
                        str = str + "\n" + result.get(i).url + " " + result.get(i).date_prise
                    }

                    token_text_view.text = str
                }
            }

        })
    }

    fun login() {
        val tokenRequest = bornesService.loginUser("toto")
        tokenRequest.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.w("bornesService", "Echec loginUser")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val body = response.body()
                if (body != null) {
                    token_text_view.text = body.string()
                }
            }
        })
    }*/

}