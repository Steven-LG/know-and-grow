import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lorem.R
import com.example.lorem.Seminar
import com.example.lorem.SeminarAdapter

class MySeminarsFragment : Fragment() {

    private lateinit var seminarsRecyclerView: RecyclerView
    private lateinit var seminarAdapter: SeminarAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_my_seminars, container, false)
        seminarsRecyclerView = view.findViewById(R.id.seminarsRecyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadSeminars()
    }

    private fun setupRecyclerView() {
        seminarAdapter = SeminarAdapter(ArrayList()) // Initialize with an empty list
        seminarsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = seminarAdapter
        }
    }

    private fun loadSeminars() {
        // Dummy data for demonstration purposes
        val seminars = listOf<Seminar>(
//            Seminar(R.drawable.seminar_image1, conferences, mentors, 4.5, 100, keywords),
//            Seminar(R.drawable.seminar_image2, conferences, mentors, 3.8, 50, keywords),
//            Seminar(R.drawable.seminar_image3, conferences, mentors, 4.2, 75, keywords)
            // Add more seminars as needed
        )
        seminarAdapter.seminars = seminars
    }
}
