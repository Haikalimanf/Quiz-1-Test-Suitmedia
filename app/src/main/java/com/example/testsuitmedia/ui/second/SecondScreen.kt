package com.example.testsuitmedia.ui.second

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.testsuitmedia.R
import com.example.testsuitmedia.databinding.FragmentSecondScreenBinding

class SecondScreen : Fragment() {

    private var _binding: FragmentSecondScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSecondScreenBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromFirstScreen()
        navigateToThirdScreen()

        parentFragmentManager.setFragmentResultListener("selected_user", viewLifecycleOwner) { _, bundle ->
            val selectedName = bundle.getString("username")
            binding.txtSelectedUsername.text = selectedName
        }
    }

    private fun navigateToThirdScreen() {
        binding.btnChooseUser.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_secondScreen_to_thirdScreen)
        }
    }

    private fun getDataFromThirdScreen() {
        val username = arguments?.getString(USERNAME)
            ?: getString(R.string.selected_user_name)
        binding.txtSelectedUsername.text = username
    }

    private fun getDataFromFirstScreen() {
        val name = arguments?.getString(NAME)

        binding.txtName.text = name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val NAME = "name"
        const val USERNAME = "username"
    }


}