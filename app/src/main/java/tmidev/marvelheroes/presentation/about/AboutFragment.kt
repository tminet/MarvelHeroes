package tmidev.marvelheroes.presentation.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import tmidev.marvelheroes.databinding.FragmentAboutBinding

@AndroidEntryPoint
class AboutFragment : Fragment() {
    private var _binding: FragmentAboutBinding? = null
//    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentAboutBinding.inflate(
        inflater, container, false
    ).apply {
        _binding = this
    }.root
}