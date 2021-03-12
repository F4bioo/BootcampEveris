package fbo.costa.bootcampeveris.ui.challenge6

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import fbo.costa.bootcampeveris.R
import fbo.costa.bootcampeveris.databinding.Challenge6FragmentBinding
import fbo.costa.bootcampeveris.util.OpenUrl
import fbo.costa.bootcampeveris.util.state.DataState
import fbo.costa.bootcampeveris.util.state.StateEventChallenge

@AndroidEntryPoint
class Challenge6Fragment : Fragment() {

    private var _binding: Challenge6FragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: Challenge6ViewModel by viewModels()

    private val sb = StringBuilder()
    private var canClear = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        _binding = Challenge6FragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        setListeners()
        observeViewModelEvents()
    }

    private fun setListeners() {
        binding.inputLayout.setEndIconOnClickListener {
            binding.textResult.text = ""

            if (isNotEmpty()) {
                stringBuilderManager()

                val value = binding.input.text.toString().trim()
                viewModel.setList(value)
                sb.append("$value ")
                binding.textIndividualValue.text = sb.toString()

                binding.input.editableText.clear()
                binding.buttonRun.isEnabled = true

            } else {
                showMessage(getString(R.string.text_input_amount))
            }
        }

        binding.buttonRun.setOnClickListener {
            sb.setLength(0)
            viewModel.setStateEvent(StateEventChallenge.StateEvent)
        }
    }

    private fun observeViewModelEvents() {
        viewModel.challengeEvent.observe(viewLifecycleOwner) { _dataState ->
            when (_dataState) {
                is DataState.Success -> {
                    binding.apply {
                        sb.append("${_dataState.data}\n")
                        textResult.text = sb.toString().trim()
                    }
                }
                is DataState.Error -> showMessage(_dataState.message)
            }
            binding.buttonRun.isEnabled = false
            canClear = true
        }
    }

    private fun showMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    // Make sure the value is not empty
    private fun isNotEmpty() = binding.input.text.toString().trim().isNotEmpty()

    // After result make sure clear StringBuilder
    private fun stringBuilderManager() {
        if (canClear) {
            sb.setLength(0)
            canClear = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.challenge_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_source) {
            OpenUrl().start(requireContext(), 6)
            true
        } else super.onOptionsItemSelected(item)
    }
}
