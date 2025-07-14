package com.example.testsuitmedia.ui.first

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.testsuitmedia.R
import com.example.testsuitmedia.databinding.FragmentFirstScreenBinding
import com.example.testsuitmedia.ui.second.SecondScreen

class FirstScreen : Fragment() {

    private var _binding: FragmentFirstScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFirstScreenBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPolindrome()
        nextScreen()
    }

    private fun nextScreen() {
        binding.btnNext.setOnClickListener {
            Log.d("NavCheck", "Navigating from FirstScreen to SecondScreen")
            val name = binding.edtUsername.text.toString().trim()
            if (name.isNotEmpty()) {
                val bundle = Bundle().apply {
                    putString(SecondScreen.NAME, name)
                }

                findNavController().navigate(
                    R.id.action_firstScreen_to_secondScreen,
                    bundle
                )
            } else {
                Toast.makeText(requireContext(), "Please enter some text", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkPolindrome() {
        binding.btnCheck.setOnClickListener {
            val inputText = binding.edtTextPolindrome.text.toString().trim()
            if (inputText.isNotEmpty()) {
                val isPalindrome = inputText == inputText.reversed()
                if (isPalindrome) {
                    Toast.makeText(requireContext(), "isPalindrome", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Not palindrome", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Please enter some text", Toast.LENGTH_SHORT).show()
            }
        }
    }


}