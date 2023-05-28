package com.example.lorem

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class MentorAdapter(var mentors: List<Mentor>, private val mentorCardClickListener: MentorCardClickListener) : RecyclerView.Adapter<MentorAdapter.MentorViewHolder>() {

    private lateinit var glideRef: RequestManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mentor_layout, parent, false)
        Log.d(TAG, "DEBUG HOPE THIS WORKS ")

        glideRef = Glide.with(view)


        return MentorViewHolder(view)
    }

    override fun onBindViewHolder(holder: MentorViewHolder, position: Int) {
        val mentor = mentors[position]

        Log.d(TAG, "DEBUG 2 " + mentor.toString())

        holder.bind(mentor)
    }

    override fun getItemCount(): Int {
        return mentors.size
    }

    inner class MentorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mentorLayout: CardView = itemView.findViewById(R.id.mentorLayout)
        private val profilePictureImageView: ImageView = itemView.findViewById(R.id.profilePictureImageView)
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val positionTextView: TextView = itemView.findViewById(R.id.positionTextView)
//        private val ratingBar: RatingBar = itemView.findViewById(R.id.ratingTextView)
//        private val ratingTextView: TextView = itemView.findViewById(R.id.ratingTextView)

        fun bind(mentor: Mentor) {
            // Cargar imagen de perfil del mentor utilizando Glide u otra biblioteca de imágenes
            Glide.with(itemView)
                .load(mentor.profilePicture)
                .into(profilePictureImageView)


            val storageRef = Firebase.storage.reference

            storageRef.child(mentor.profilePicture).downloadUrl.addOnSuccessListener { uri ->
                glideRef.load(uri).into(profilePictureImageView)
            }


            nameTextView.text = mentor.name
            positionTextView.text = mentor.position

            Log.d(TAG, mentor.toString())
//            ratingBar.rating = mentor.rating.toFloat()
//            ratingTextView.text = mentor.rating.toString()

            mentorLayout.setOnClickListener { mentorCardClickListener.onMentorCardClick(mentor.id) }

        }
    }
}
