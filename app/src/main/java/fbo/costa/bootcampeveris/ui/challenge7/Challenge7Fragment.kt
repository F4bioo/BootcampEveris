package fbo.costa.bootcampeveris.ui.challenge7

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import fbo.costa.bootcampeveris.R
import fbo.costa.bootcampeveris.databinding.Challenge7FragmentBinding
import fbo.costa.bootcampeveris.util.OpenUrl
import fbo.costa.bootcampeveris.util.state.DataState
import fbo.costa.bootcampeveris.util.state.StateEventChallenge

@AndroidEntryPoint
class Challenge7Fragment : Fragment() {

    private var _binding: Challenge7FragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: Challenge7ViewModel by viewModels()

    private val sb = StringBuilder()
    private var size = 10
    private var speed = 10
    private var counter = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        _binding = Challenge7FragmentBinding.inflate(inflater, container, false)

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
            // listener for slider list size
            sliderListSize.addOnChangeListener { _, value, _ ->
                size = value.toInt()
                setText(counter)
            }

            // listener for slider speed
            sliderSpeed.addOnChangeListener { _, value, _ ->
                speed = value.toInt()
            }


            buttonAdd.setOnClickListener {
                // disable the slider list size
                sliderListSize.isEnabled = false

                // increment the counter and notify the user
                // setting the texts
                counter++
                setText(counter)

                // put the speed int the list
                viewModel.setList(speed)

                textIndividualValue.text = sb.append("$speed ")

                // every click on the Add button, make sure
                // the counter has reached the same size
                // from the list size
                if (counter == size) {
                    sliderSpeed.isEnabled = false
                    buttonRun.isEnabled = true
                    buttonAdd.isEnabled = false
                }
            }

            buttonRun.setOnClickListener {
                sliderListSize.isEnabled = true
                sliderSpeed.isEnabled = true
                counter = 0
                setText(counter)

                viewModel.setStateEvent(StateEventChallenge.StateEvent)
            }
        }
    }

    private fun observeViewModelEvents() {
        viewModel.challengeEvent.observe(viewLifecycleOwner) { _dataState ->
            when (_dataState) {
                is DataState.Success -> binding.textResult.text = _dataState.data
                is DataState.Error -> showMessage(_dataState.message)
            }
            binding.apply {
                buttonAdd.isEnabled = true
                buttonRun.isEnabled = false
                textIndividualValue.text = ""
            }
            sb.setLength(0)
        }
    }

    private fun showMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun setText(count: Int) {
        binding.apply {
            textSliderListSize.text =
                String.format(getString(R.string.text_list_size_challenge7), size)

            textSliderSpeed.text =
                String.format(getString(R.string.text_speed_challenge7), count, size)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.challenge_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_source) {
            OpenUrl.start(requireContext(), 7)
            true
        } else super.onOptionsItemSelected(item)
    }
}
