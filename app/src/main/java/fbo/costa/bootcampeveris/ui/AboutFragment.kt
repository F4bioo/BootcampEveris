package fbo.costa.bootcampeveris.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fbo.costa.bootcampeveris.databinding.AboutFragmentBinding

class AboutFragment : Fragment() {

    private var _binding: AboutFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AboutFragmentBinding.inflate(inflater, container, false)

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
            buttonBootcamp.setOnClickListener {
                val url = "https://web.digitalinnovation.one/track/everis-kotlin-developer"
                start(url)
            }
        }

        binding.apply {
            buttonPortfolio.setOnClickListener {
                val url = "https://fappslab.com/"
                start(url)
            }
        }
    }

    fun start(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}
