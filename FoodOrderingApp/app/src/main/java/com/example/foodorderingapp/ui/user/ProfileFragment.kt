package com.example.foodorderingapp.ui.user

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
import com.example.foodorderingapp.MainActivity
import com.example.foodorderingapp.R
import com.example.foodorderingapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    
    private var selectedImageUri: Uri? = null
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupUI()
        setupListeners()
        loadUserProfile()
    }

    private fun setupUI() {
        // Setup toolbar
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupListeners() {
        binding.changeProfileImageButton.setOnClickListener {
            selectImage()
        }

        binding.saveProfileButton.setOnClickListener {
            saveProfile()
        }

        // Menu item click listeners
        binding.menuLayout.findViewById<View>(R.id.ordersCard).setOnClickListener {
            // Navigate to orders
            (requireActivity() as MainActivity).loadFragment(OrdersFragment())
        }

        binding.menuLayout.findViewById<View>(R.id.favoritesCard).setOnClickListener {
            // Navigate to favorites
            (requireActivity() as MainActivity).loadFragment(FavoritesFragment())
        }

        binding.menuLayout.findViewById<View>(R.id.notificationsCard).setOnClickListener {
            // Navigate to notifications
            (requireActivity() as MainActivity).loadFragment(NotificationsFragment())
        }

        binding.menuLayout.findViewById<View>(R.id.settingsCard).setOnClickListener {
            // TODO: Navigate to settings
            Toast.makeText(context, "Settings feature coming soon!", Toast.LENGTH_SHORT).show()
        }

        binding.menuLayout.findViewById<View>(R.id.logoutCard).setOnClickListener {
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

    private fun loadUserProfile() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            binding.emailInput.setText(currentUser.email)
            
            // Load user profile from Firestore
            firestore.collection("users")
                .document(currentUser.uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        binding.nameInput.setText(document.getString("name") ?: "")
                        binding.phoneInput.setText(document.getString("phone") ?: "")
                        binding.addressInput.setText(document.getString("address") ?: "")
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "Error loading profile: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun saveProfile() {
        val name = binding.nameInput.text.toString().trim()
        val phone = binding.phoneInput.text.toString().trim()
        val address = binding.addressInput.text.toString().trim()

        if (validateInputs(name, phone, address)) {
            val currentUser = auth.currentUser
            if (currentUser != null) {
                val userData = hashMapOf<String, Any>(
                    "name" to name,
                    "phone" to phone,
                    "address" to address,
                    "updatedAt" to com.google.firebase.Timestamp.now()
                )

                firestore.collection("users")
                    .document(currentUser.uid)
                    .update(userData)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Profile updated successfully!", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Error updating profile: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    private fun validateInputs(name: String, phone: String, address: String): Boolean {
        if (name.isBlank()) {
            binding.nameInput.error = "Name is required"
            return false
        }
        if (phone.isBlank()) {
            binding.phoneInput.error = "Phone number is required"
            return false
        }
        if (address.isBlank()) {
            binding.addressInput.error = "Address is required"
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
                // TODO: Upload image to Firebase Storage
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }
}
