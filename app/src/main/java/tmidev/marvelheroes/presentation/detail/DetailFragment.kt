package tmidev.marvelheroes.presentation.detail

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import tmidev.marvelheroes.databinding.FragmentDetailBinding
import tmidev.marvelheroes.framework.imageloader.ImageLoader
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentDetailBinding.inflate(
        inflater, container, false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailViewArg = args.detailViewArg

        binding.imageViewCharacter.apply {
            transitionName = detailViewArg.name
            imageLoader.load(this, detailViewArg.imageUrl)
        }

        setSharedElementTransitionOnEnter()

        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            binding.viewFlipper.displayedChild = when (uiState) {
                is DetailViewModel.UiState.Loading -> FLIPPER_LOADING
                is DetailViewModel.UiState.Success -> {
                    binding.recyclerViewParentDetail.apply {
                        setHasFixedSize(true)
                        adapter = DetailParentAdapter(uiState.detailParentList, imageLoader)
                    }
                    FLIPPER_DETAIL
                }
                is DetailViewModel.UiState.Error -> {
                    binding.viewDetailErrorState.buttonRetry.setOnClickListener {
                        viewModel.getCharacterCategories(detailViewArg.characterId)
                    }
                    FLIPPER_ERROR
                }
                is DetailViewModel.UiState.Empty -> FLIPPER_EMPTY
            }
        }

        viewModel.getCharacterCategories(detailViewArg.characterId)
    }

    private fun setSharedElementTransitionOnEnter() = TransitionInflater
        .from(requireContext())
        .inflateTransition(android.R.transition.move).apply {
            sharedElementEnterTransition = this
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FLIPPER_LOADING = 0
        private const val FLIPPER_DETAIL = 1
        private const val FLIPPER_ERROR = 2
        private const val FLIPPER_EMPTY = 3
    }
}