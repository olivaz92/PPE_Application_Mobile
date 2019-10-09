package fr.efficom.formation.ppe1719

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PhotoViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
    val likeButton:ImageView
    val photoImageView:ImageView
    val dateItemTextView:TextView

    init {
        likeButton = rootView.findViewById(R.id.likeButton)
        dateItemTextView = rootView.findViewById(R.id.dateItemTextView)
        photoImageView = rootView.findViewById(R.id.photoItemImageView)
    }

}