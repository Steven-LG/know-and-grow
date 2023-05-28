import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lorem.R
import java.text.SimpleDateFormat
import java.util.*

class ScheduledMentoringAdapter(private val dates: List<Date>) : RecyclerView.Adapter<ScheduledMentoringAdapter.ScheduledMentoringViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduledMentoringViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.scheduled_mentoring_layout, parent, false)
        return ScheduledMentoringViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduledMentoringViewHolder, position: Int) {
        val date = dates[position]
        holder.bind(date)
    }

    override fun getItemCount(): Int {
        return dates.size
    }

    inner class ScheduledMentoringViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)

        fun bind(date: Date) {
            val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

            val formattedTime = timeFormat.format(date)
            val formattedDate = dateFormat.format(date)

            timeTextView.text = formattedTime
            dateTextView.text = formattedDate
        }
    }
}
