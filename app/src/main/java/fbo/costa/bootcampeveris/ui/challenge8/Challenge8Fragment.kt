package fbo.costa.bootcampeveris.ui.challenge8

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import fbo.costa.bootcampeveris.R
import fbo.costa.bootcampeveris.databinding.Challenge8FragmentBinding
import fbo.costa.bootcampeveris.util.OpenUrl
import fbo.costa.bootcampeveris.util.state.DataState
import fbo.costa.bootcampeveris.util.state.StateEventChallenge

@AndroidEntryPoint
class Challenge8Fragment : Fragment() {

    private var _binding: Challenge8FragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: Challenge8ViewModel by viewModels()

    private val sb = StringBuilder()
    private var canClear = false
    private var counter = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        _binding = Challenge8FragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        setText(counter)
        setListeners()
        observeViewModelEvents()
    }

    private fun setListeners() {
        binding.apply {
            inputLayout.setEndIconOnClickListener {
                binding.textResult.text = ""

                if (isNotEmpty()) {
                    stringBuilderManager()

                    // increment the counter and notify the user
                    // setting the texts
                    counter++
                    setText(counter)

                    val value = binding.input.text.toString().trim()
                    viewModel.setList(value)
                    sb.append("$value ")
                    binding.textIndividualValue.text = sb.toString()

                    binding.input.editableText.clear()

                    // every click on the Add button, make sure
                    // the counter has reached the same size
                    // from the list size
                    if (counter == 2) {
                        buttonRun.isEnabled = true
                        inputLayout.isEndIconVisible = false
                    }
                } else {
                    showMessage(getString(R.string.text_empty_input))
                }
            }

            buttonRun.setOnClickListener {
                buttonRun.isEnabled = false
                inputLayout.isEndIconVisible = true
                counter = 0
                setText(counter)

                viewModel.setStateEvent(StateEventChallenge.StateEvent)
            }
        }
    }

    private fun observeViewModelEvents() {
        viewModel.challengeEvent.observe(viewLifecycleOwner) { _dataState ->
            when (_dataState) {
                is DataState.Success -> {
                    binding.apply {
                        textResult.text = _dataState.data
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

    private fun setText(count: Int) {
        binding.apply {
            textInputTitle.text =
                String.format(getString(R.string.text_input_title_challenge), count)
        }
    }

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
            OpenUrl.start(requireContext(), 8)
            true
        } else super.onOptionsItemSelected(item)
    }
}
