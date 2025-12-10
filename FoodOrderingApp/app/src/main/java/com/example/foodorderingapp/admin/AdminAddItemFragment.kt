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
import com.example.foodorderingapp.databinding.FragmentAdminAddItemBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class AdminAddItemFragment : Fragment() {

    private var _binding: FragmentAdminAddItemBinding? = null
    private val binding get() = _binding!!
    private var selectedImageUri: Uri? = null
    
    private val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
    private val storage: FirebaseStorage by lazy { FirebaseStorage.getInstance() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminAddItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupImageSelection()
        setupAddItemButton()
    }

    private fun setupImageSelection() {
        binding.selectImageButton.setOnClickListener {
            val intent = Intent().apply {
                type = "image/*"
                action = Intent.ACTION_GET_CONTENT
            }
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                PICK_IMAGE_REQUEST
            )
        }
    }

    private fun setupAddItemButton() {
        binding.addItemButton.setOnClickListener {
            val name = binding.itemNameInput.text.toString()
            val description = binding.itemDescriptionInput.text.toString()
            val priceStr = binding.itemPriceInput.text.toString()

            if (validateInputs(name, description, priceStr)) {
                uploadImageAndSaveItem(name, description, priceStr.toDouble())
            }
        }
    }

    private fun validateInputs(name: String, description: String, price: String): Boolean {
        if (selectedImageUri == null) {
            showToast("Please select an image")
            return false
        }
        if (name.isBlank()) {
            showToast("Please enter item name")
            return false
        }
        if (description.isBlank()) {
            showToast("Please enter item description")
            return false
        }
        if (price.isBlank()) {
            showToast("Please enter item price")
            return false
        }
        return true
    }

    private fun uploadImageAndSaveItem(name: String, description: String, price: Double) {
        // TODO: Implement image upload and item saving to Firebase
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                selectedImageUri = uri
                binding.itemImage.setImageURI(uri)
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
