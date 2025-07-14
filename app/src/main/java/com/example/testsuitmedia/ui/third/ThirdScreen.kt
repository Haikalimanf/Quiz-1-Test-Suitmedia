package com.example.testsuitmedia.ui.third

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testsuitmedia.R
import com.example.testsuitmedia.databinding.FragmentThirdScreenBinding

class ThirdScreen : Fragment() {

    private var _binding: FragmentThirdScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ThirdViewModel by viewModels()
    private lateinit var adapter: UsernameAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
        sendDataToSecondScreen()

        viewModel.loadPage()
        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        }
    }

    private fun sendDataToSecondScreen() {
        val toolbar = binding.toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)

        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val backIcon = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        backIcon?.setTint(ContextCompat.getColor(requireContext(), R.color.black))
        (requireActivity() as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(backIcon)

        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        toolbar.title = getString(R.string.third_screen)
    }

    private fun setupRecyclerView() {
        adapter = UsernameAdapter { user ->

            val bundle = Bundle().apply {
                putString("username", "${user.firstName} ${user.lastName}")
            }
            setFragmentResult("selected_user", bundle)
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.rvUser.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUser.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.users.observe(viewLifecycleOwner) {
            adapter.submitList(it.toList())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}