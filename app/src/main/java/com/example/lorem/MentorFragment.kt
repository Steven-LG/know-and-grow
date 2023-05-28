package com.example.lorem

import ImagesAdapter
import android.app.DownloadManager.Request
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.lorem.databinding.FragmentMentorBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

private const val MENTOR_ID = "mentorId"

class MentorFragment : Fragment() {
    private var _binding: FragmentMentorBinding? = null
    private val binding get() = _binding!!

    private lateinit var mentor: Mentor
    private lateinit var mentorId: String
    private lateinit var glideRef: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mentorId = it.getString(MENTOR_ID).toString()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMentorBinding.inflate(inflater, container, false)

        glideRef = Glide.with(requireActivity())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener los datos del mentor desde la actividad o desde algún otro lugar
//        mentor = getMentorData()

        val db = Firebase.firestore

        val docRef = db.collection("mentors").document(mentorId)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val data = document.data as HashMap<*,*>;
                    mentor = Mentor(
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
                        arrayOf())

                    // Configurar los elementos del layout con los datos del mentor
                    val storageRef = Firebase.storage.reference

                    storageRef.child(mentor.profilePicture).downloadUrl.addOnSuccessListener { uri ->
                        glideRef.load(uri).into(binding.profilePictureImageView)
                    }

                    binding.nameTextView.text = mentor.name
                    binding.scheduleMentoringButton.setOnClickListener {
                        }
//        binding.introductoryVideoTextView.text = mentor.introductoryVideo
                    binding.workExperienceTextView.text = mentor.workExperience
                    // Configurar el RecyclerView para mostrar las imágenes del mentor
                    val imagesAdapter = ImagesAdapter(mentor.images)
                    binding.imagesRecyclerView.adapter = imagesAdapter
                    // Configurar el RecyclerView para mostrar los seminarios del mentor
//        val seminarsAdapter = SeminarsAdapter(mentor.seminars)
//        binding.seminarsRecyclerView.adapter = seminarsAdapter
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun getMentorData(): Mentor {
//
//
//        val mentorName = "John Doe"
//        val mentorProfilePicture = R.drawable.placeholder_profile_picture
//        val mentorIntroductoryVideo = "https://example.com/introductory_video"
//        val mentorWorkExperience = "Lorem ipsum dolor sit amet"
//        val mentorImages = listOf(R.drawable.placeholder_profile_picture, R.drawable.placeholder_profile_picture, R.drawable.placeholder_profile_picture)
//        val mentorSeminars = listOf("com.example.lorem.Seminar 1", "com.example.lorem.Seminar 2", "com.example.lorem.Seminar 3")
//
//        return Mentor(mentorName, mentorProfilePicture, mentorIntroductoryVideo, mentorWorkExperience, mentorImages, mentorSeminars)
//    }
}
