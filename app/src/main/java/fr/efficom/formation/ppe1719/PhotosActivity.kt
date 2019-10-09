package fr.efficom.formation.ppe1719

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import fr.efficom.formation.ppe1719.api.BornesService
import fr.efficom.formation.ppe1719.api.Photo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_photos.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosActivity : AppCompatActivity() {

    val bornesService: BornesService

    init{
        bornesService = createBornesService()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        val code = intent.getStringExtra("CODE")
        showPhotos(code)
    }

    fun showPhotos(codeEvent: String){

        val request = bornesService.getPhotosForEventAsList(codeEvent)

        request.enqueue(object : Callback<List<Photo>> {
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Log.w("bornesService", "Echec loginUser")
            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                val photo = response.body()
                if(photo!= null){
                    Log.d("PhotosActivity", "Photo: $photo")
                    val photosAdapter = PhotosAdapter(photo)
                    photosRecyclerView.adapter = photosAdapter
                    photosRecyclerView.layoutManager = LinearLayoutManager(this@PhotosActivity)
                }
            }
        })
    }
}
