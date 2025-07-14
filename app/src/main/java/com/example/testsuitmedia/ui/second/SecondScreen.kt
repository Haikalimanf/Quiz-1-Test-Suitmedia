package com.example.testsuitmedia.ui.second

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
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

        setToolbar()
        getDataFromFirstScreen()
        navigateToThirdScreen()
        sendDataToSecondScreen()
    }

    private fun sendDataToSecondScreen() {
        parentFragmentManager.setFragmentResultListener("selected_user", viewLifecycleOwner) { _, bundle ->
            val selectedName = bundle.getString("username")
            binding.txtSelectedUsername.text = selectedName
        }
    }

    private fun setToolbar() {
        val toolbar = binding.toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)

        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val backIcon = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        backIcon?.setTint(ContextCompat.getColor(requireContext(), R.color.blue_arrow))
        actionBar?.setHomeAsUpIndicator(backIcon)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun navigateToThirdScreen() {
        binding.btnChooseUser.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_secondScreen_to_thirdScreen)
        }
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