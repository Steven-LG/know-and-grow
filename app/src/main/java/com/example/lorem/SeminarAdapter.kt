package com.example.lorem
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SeminarAdapter(var seminars: List<Seminar>) : RecyclerView.Adapter<SeminarAdapter.SeminarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeminarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.seminar_layout, parent, false)
        return SeminarViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeminarViewHolder, position: Int) {
        val seminar = seminars[position]
        holder.bind(seminar)
    }

    override fun getItemCount(): Int {
        return seminars.size
    }

    inner class SeminarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val seminarImageView: ImageView = itemView.findViewById(R.id.seminarImageView)
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val keywordsTextView: TextView = itemView.findViewById(R.id.keywordsTextView)
        private val ratingTextView: TextView = itemView.findViewById(R.id.ratingTextView)
        private val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)

        @SuppressLint("StringFormatInvalid")
        fun bind(seminar: Seminar) {
//            seminarImageView.setImageResource(seminar.image)
            nameTextView.text = seminar.name
            keywordsTextView.text = seminar.keywords.joinToString(", ")
            ratingTextView.text = seminar.rating.toString()
            priceTextView.text = itemView.context.getString(R.string.price_format, seminar.price)
        }
    }
}
