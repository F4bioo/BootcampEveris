package fbo.costa.bootcampeveris.ui.challenge4

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import fbo.costa.bootcampeveris.R
import fbo.costa.bootcampeveris.databinding.Challenge4FragmentBinding
import fbo.costa.bootcampeveris.util.OpenUrl
import fbo.costa.bootcampeveris.util.state.DataState
import fbo.costa.bootcampeveris.util.state.StateEventChallenge

@AndroidEntryPoint
class Challenge4Fragment : Fragment(), TextWatcher {

    private var _binding: Challenge4FragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: Challenge4ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        _binding = Challenge4FragmentBinding.inflate(inflater, container, false)

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
        binding.input.addTextChangedListener(this)

        binding.buttonRun.setOnClickListener {
            if (isNotEmpty()) {
                val value = binding.input.text.toString().trim()
                viewModel.setStateEvent(value, StateEventChallenge.StateEvent)
            } else {
                showMessage(getString(R.string.text_conditional_challenge4))
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
        }
    }

    private fun showMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    // Make sure the value is not empty
    private fun isNotEmpty() = binding.input.text.toString().trim().isNotEmpty()

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // if the EditText is empty disable the button
        binding.apply {
            buttonRun.isEnabled = input.text.toString().trim().isNotEmpty()
        }
    }

    override fun afterTextChanged(p0: Editable?) {
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.challenge_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_source) {
            OpenUrl().start(requireContext(), 4)
            true
        } else super.onOptionsItemSelected(item)
    }
}
