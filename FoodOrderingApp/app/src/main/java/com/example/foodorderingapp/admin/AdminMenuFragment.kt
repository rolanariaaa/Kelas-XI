package com.example.foodorderingapp.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodorderingapp.databinding.FragmentAdminMenuBinding
import com.google.firebase.firestore.FirebaseFirestore

class AdminMenuFragment : Fragment() {

    private var _binding: FragmentAdminMenuBinding? = null
    private val binding get() = _binding!!
    
    private val firestore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenuList()
    }

    private fun setupMenuList() {
        // TODO: Setup RecyclerView and fetch menu items
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
