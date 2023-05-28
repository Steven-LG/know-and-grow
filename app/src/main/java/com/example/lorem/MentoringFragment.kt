import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lorem.Mentor
import com.example.lorem.MentorAdapter
import com.example.lorem.MentorCardClickListener
import com.example.lorem.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class MentoringFragment : Fragment(), MentorCardClickListener {
    private lateinit var mentorRecyclerView: RecyclerView
    private lateinit var scheduledMentoringRecyclerView: RecyclerView

    private lateinit var mentorAdapter: MentorAdapter
    private lateinit var scheduledMentoringAdapter: ScheduledMentoringAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mentoring, container, false)
        mentorRecyclerView = view.findViewById(R.id.mentorRecyclerView)
//        scheduledMentoringRecyclerView = view.findViewById(R.id.scheduledMentoringRecyclerView)

        mentorAdapter = MentorAdapter(arrayListOf(), this)
        mentorRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mentorRecyclerView.adapter = mentorAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Firebase.firestore

        val mentors: ArrayList<Mentor> = arrayListOf();

        db.collection("mentors")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val data = document.data;

                    Log.d(TAG, "DATA DATA DATA " + data.toString());

                    val newMentor = Mentor(
                        document.id,
                        data["name"].toString(),
                        data["profilePicture"].toString(),
                        data["position"].toString(),
                        data["rating"].toString().toDouble(),
                        data["profileVideo"].toString(),
                        data["workExperience"].toString(),
                        arrayOf(),
                        data["company"].toString(),
                        data["priceRate"].toString().toDouble(),
                        arrayOf());

                    mentors.add(newMentor);
                }
                    mentorAdapter.mentors += mentors;
                    mentorAdapter.notifyItemRangeInserted(0, mentors.size);



                mentorAdapter.notifyItemInserted(0)

            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
//        val scheduledMentoring = getScheduledMentoring()

        // Configurar el RecyclerView de mentores


        // Configurar el RecyclerView de mentorías agendadas
//        scheduledMentoringAdapter = ScheduledMentoringAdapter(scheduledMentoring)
//        scheduledMentoringRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        scheduledMentoringRecyclerView.adapter = scheduledMentoringAdapter
    }

    override fun onMentorCardClick(mentorId: String) {
        val action = MentoringFragmentDirections.actionMentoringFragmentToMentorFragment(mentorId);
        requireView().findNavController().navigate(action)

    }

    private fun getMentors(): List<Mentor> {
        // Obtener la lista de mentores desde alguna fuente de datos
        // Aquí puedes reemplazar con tu lógica para obtener los mentores
        // y devolver una lista de objetos com.example.lorem.Mentor
        // Ejemplo:
        return listOf(
//            Mentor("com.example.lorem.Mentor 1", "Descripción del mentor 1"),
//            Mentor("com.example.lorem.Mentor 2", "Descripción del mentor 2"),
//            Mentor("com.example.lorem.Mentor 3", "Descripción del mentor 3")
        )
    }

    private fun getScheduledMentoring(): List<Date> {
        // Obtener la lista de mentorías agendadas desde alguna fuente de datos
        // Aquí puedes reemplazar con tu lógica para obtener las mentorías agendadas
        // y devolver una lista de objetos ScheduledMentoring
        // Ejemplo:
        return listOf(
//            ScheduledMentoring("Mentoría 1", "Fecha 1"),
//            ScheduledMentoring("Mentoría 2", "Fecha 2"),
//            ScheduledMentoring("Mentoría 3", "Fecha 3")
        )
    }
}
