package com.example.foodorderingapp.admin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.foodorderingapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class AddFoodItemActivity : AppCompatActivity() {
    private lateinit var nameInput: EditText
    private lateinit var descriptionInput: EditText
    private lateinit var priceInput: EditText
    private lateinit var categoryInput: EditText
    private lateinit var imageView: ImageView
    private lateinit var uploadButton: Button
    private lateinit var submitButton: Button

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var storage: FirebaseStorage

    private var selectedImageUri: Uri? = null
    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food_item)

        // Initialize Firebase
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()

        // Initialize views
        nameInput = findViewById(R.id.foodNameInput)
        descriptionInput = findViewById(R.id.foodDescriptionInput)
        priceInput = findViewById(R.id.foodPriceInput)
        categoryInput = findViewById(R.id.foodCategoryInput)
        imageView = findViewById(R.id.foodImageView)
        uploadButton = findViewById(R.id.uploadImageButton)
        submitButton = findViewById(R.id.submitFoodItemButton)

        uploadButton.setOnClickListener {
            openImageChooser()
        }

        submitButton.setOnClickListener {
            if (validateInputs()) {
                uploadFoodItem()
            }
        }
    }

    private fun openImageChooser() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data
            imageView.setImageURI(selectedImageUri)
        }
    }

    private fun validateInputs(): Boolean {
        if (nameInput.text.toString().trim().isEmpty()) {
            nameInput.error = "Name is required"
            return false
        }
        if (descriptionInput.text.toString().trim().isEmpty()) {
            descriptionInput.error = "Description is required"
            return false
        }
        if (priceInput.text.toString().trim().isEmpty()) {
            priceInput.error = "Price is required"
            return false
        }
        if (categoryInput.text.toString().trim().isEmpty()) {
            categoryInput.error = "Category is required"
            return false
        }
        if (selectedImageUri == null) {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun uploadFoodItem() {
        // Show loading indicator
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        submitButton.isEnabled = false

        // Upload image first
        val imageRef = storage.reference.child("food_images/${UUID.randomUUID()}")
        imageRef.putFile(selectedImageUri!!)
            .addOnSuccessListener { taskSnapshot ->
                // Get the download URL
                imageRef.downloadUrl.addOnSuccessListener { uri ->
                    // Create food item in Firestore
                    val foodItem = hashMapOf(
                        "name" to nameInput.text.toString().trim(),
                        "description" to descriptionInput.text.toString().trim(),
                        "price" to priceInput.text.toString().toDouble(),
                        "category" to categoryInput.text.toString().trim(),
                        "imageUrl" to uri.toString(),
                        "restaurantId" to auth.currentUser?.uid,
                        "isAvailable" to true,
                        "createdAt" to com.google.firebase.Timestamp.now()
                    )

                    db.collection("food_items")
                        .add(foodItem)
                        .addOnSuccessListener {
                            progressBar.visibility = View.GONE
                            submitButton.isEnabled = true
                            Toast.makeText(this, "Food item added successfully", Toast.LENGTH_LONG).show()
                            finish()
                        }
                        .addOnFailureListener { e ->
                            progressBar.visibility = View.GONE
                            submitButton.isEnabled = true
                            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                }
            }
            .addOnFailureListener { e ->
                progressBar.visibility = View.GONE
                submitButton.isEnabled = true
                Toast.makeText(this, "Error uploading image: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }
}
