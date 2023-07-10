package com.ej.mvvmtest.ui.main

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ej.mvvmtest.R
import com.ej.mvvmtest.data.models.Item
import com.ej.mvvmtest.databinding.FragmentMainBinding
import com.ej.mvvmtest.ui.main.adapters.ItemAdapter
import com.ej.mvvmtest.utils.UiState
import com.ej.mvvmtest.utils.view.textChanges
import com.ej.mvvmtest.utils.view.toGone
import com.ej.mvvmtest.utils.view.toVisible
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main), ItemAdapter.OnClickListener {
    private val viewModel by viewModels<MainViewModel>()
    private var itemAdapter: ItemAdapter? = null

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        itemAdapter = ItemAdapter(this)
        binding.mainVideosRV.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter = itemAdapter
        }
        initView()
        observeItems()
    }

    @OptIn(FlowPreview::class)
    private fun initView() {
        binding.mainVideoSearchET.textChanges().debounce(500).onEach {
                if (!it.isNullOrEmpty()) {
                    viewModel.getVideosByQuery(it.toString())
                }
            }.launchIn(lifecycleScope)
    }

    private fun observeItems() {
        viewModel.items.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.StringError -> {
                    binding.mainErrorText.toVisible()
                    setErrorText(binding.mainErrorText, state.errorText)
                    binding.mainProgress.toGone()
                    binding.mainVideosRV.toGone()
                }
                is UiState.StringResourceError -> {
                    binding.mainErrorText.toVisible()
                    setErrorText(binding.mainErrorText, getString(state.errorStringResource))
                    binding.mainProgress.toGone()
                    binding.mainVideosRV.toGone()
                }
                UiState.Loading -> {
                    binding.mainErrorText.toGone()
                    binding.mainProgress.toVisible()
                    binding.mainVideosRV.toGone()
                }
                is UiState.Success -> {
                    binding.mainErrorText.toGone()
                    binding.mainProgress.toGone()
                    binding.mainVideosRV.toVisible()
                    itemAdapter?.submitList(state.data)
                }
            }
        }
    }

    private fun setErrorText(view: TextView, errorText: String) {
        view.text = errorText
    }

    override fun onVideoClick(videoItem: Item) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.youtube_player)
        val youtubePlayerView = dialog.findViewById<YouTubePlayerView>(R.id.youTubePlayerView)
        lifecycle.addObserver(youtubePlayerView)
        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                if (videoItem.id != null && videoItem.id!!.videoId != null) {
                    youTubePlayer.loadVideo(videoItem.id!!.videoId!!, 0f)
                    youTubePlayer.play()
                }
            }
        })
        dialog.show()
    }
}