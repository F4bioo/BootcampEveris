package fbo.costa.bootcampeveris.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fbo.costa.bootcampeveris.R
import fbo.costa.bootcampeveris.databinding.FragmentMainBinding
import fbo.costa.bootcampeveris.extension.navigateWithAnimations

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        actionButton()
    }

    private fun actionButton() {
        binding.apply {
            buttonChallenge1.setOnClickListener {
                findNavController()
                    .navigateWithAnimations(R.id.action_mainFragment_to_challenge1Fragment)
            }

            buttonChallenge2.setOnClickListener {
                findNavController()
                    .navigateWithAnimations(R.id.action_mainFragment_to_challenge2Fragment)
            }

            buttonChallenge3.setOnClickListener {
                findNavController()
                    .navigateWithAnimations(R.id.action_mainFragment_to_challenge3Fragment)
            }

            buttonChallenge4.setOnClickListener {
                findNavController()
                    .navigateWithAnimations(R.id.action_mainFragment_to_challenge4Fragment)
            }

            buttonChallenge5.setOnClickListener {
                findNavController()
                    .navigateWithAnimations(R.id.action_mainFragment_to_challenge5Fragment)
            }

            buttonChallenge6.setOnClickListener {
                findNavController()
                    .navigateWithAnimations(R.id.action_mainFragment_to_challenge6Fragment)
            }

            buttonChallenge7.setOnClickListener {
                findNavController()
                    .navigateWithAnimations(R.id.action_mainFragment_to_challenge7Fragment)
            }

            buttonChallenge8.setOnClickListener {
                findNavController()
                    .navigateWithAnimations(R.id.action_mainFragment_to_challenge8Fragment)
            }

            buttonChallenge9.setOnClickListener {
                findNavController()
                    .navigateWithAnimations(R.id.action_mainFragment_to_challenge9Fragment)
            }

            buttonChallenge10.setOnClickListener {
                findNavController()
                    .navigateWithAnimations(R.id.action_mainFragment_to_challenge10Fragment)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_about) {
            findNavController()
                .navigateWithAnimations(R.id.action_mainFragment_to_aboutFragment)
            true
        } else super.onOptionsItemSelected(item)
    }
}
