package com.example.foodorderingapp.admin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.foodorderingapp.LoginActivity
import com.example.foodorderingapp.databinding.FragmentAdminProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class AdminProfileFragment : Fragment() {

    private var _binding: FragmentAdminProfileBinding? = null
    private val binding get() = _binding!!
    private var selectedImageUri: Uri? = null
    
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
    private val storage: FirebaseStorage by lazy { FirebaseStorage.getInstance() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        loadProfile()
    }

    private fun setupUI() {
        binding.changeProfileImageButton.setOnClickListener {
            selectImage()
        }

        binding.saveProfileButton.setOnClickListener {
            saveProfile()
        }

        binding.logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun selectImage() {
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        startActivityForResult(
            Intent.createChooser(intent, "Select Picture"),
            PICK_IMAGE_REQUEST
        )
    }

    private fun loadProfile() {
        // TODO: Load profile data from Firebase
    }

    private fun saveProfile() {
        val name = binding.restaurantNameInput.text.toString()
        val email = binding.emailInput.text.toString()
        val phone = binding.phoneInput.text.toString()
        val address = binding.addressInput.text.toString()

        if (validateInputs(name, email, phone, address)) {
            // TODO: Save profile data to Firebase
        }
    }

    private fun validateInputs(name: String, email: String, phone: String, address: String): Boolean {
        if (name.isBlank()) {
            showToast("Please enter restaurant name")
            return false
        }
        if (email.isBlank()) {
            showToast("Please enter email")
            return false
        }
        if (phone.isBlank()) {
            showToast("Please enter phone number")
            return false
        }
        if (address.isBlank()) {
            showToast("Please enter address")
            return false
        }
        return true
    }

    private fun logout() {
        auth.signOut()
        startActivity(Intent(requireContext(), LoginActivity::class.java))
        requireActivity().finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                selectedImageUri = uri
                binding.profileImage.setImageURI(uri)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }
}
