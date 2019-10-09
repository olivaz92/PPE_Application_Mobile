package fr.efficom.formation.ppe1719

import android.app.DownloadManager
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.telecom.Call
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stfalcon.imageviewer.StfalconImageViewer
import fr.efficom.formation.ppe1719.api.BornesService
import fr.efficom.formation.ppe1719.api.Photo
import okhttp3.ResponseBody
import retrofit2.Callback
import retrofit2.Response

class PhotosAdapter(val photos: List<Photo>): RecyclerView.Adapter<PhotoViewHolder>() {

    val bornesService = createBornesService()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val photoItemView = inflater.inflate(R.layout.photo_item_layout, parent, false)

        val photoViewHolder = PhotoViewHolder(photoItemView)
        return photoViewHolder
    }

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        holder.dateItemTextView.text = photo.date_prise

        if(photo.estAime == 1){
            holder.likeButton.setImageResource(R.drawable.ic_love_heart_symbol)
        }else{
            holder.likeButton.setImageResource(R.drawable.ic_not_love_heart_symbol)
        }

        holder.likeButton.setOnClickListener{
            val request = bornesService.toggleLike(photo.id)
            request.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {

                }

                override fun onResponse(call: retrofit2.Call<ResponseBody>, response: Response<ResponseBody>) {
                    val body = response.body()
                    if(body != null){
                        val isLiked = body.string().toInt()
                        photo.estAime = isLiked
                        if(isLiked == 1){
                            holder.likeButton.setImageResource(R.drawable.ic_love_heart_symbol)
                        }else{
                            holder.likeButton.setImageResource(R.drawable.ic_not_love_heart_symbol)
                        }

                    }
                }

            })
        }

        holder.itemView.setOnClickListener {
            StfalconImageViewer.Builder<Photo>(holder.itemView.context, listOf(photo)) { view, photo ->
                Glide.with(view.context).load(photo.url).into(view)
            }.show()
        }

        Glide.with(holder.itemView.context).load(photo.url).into(holder.photoImageView)
    }
}