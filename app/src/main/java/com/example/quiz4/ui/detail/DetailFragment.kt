package com.example.quiz4.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.bumptech.glide.Glide
import com.example.quiz4.App
import com.example.quiz4.R
import com.example.quiz4.data.Result
import com.example.quiz4.databinding.FragmentDetailBinding
import com.example.taskmanager.ui.home.viewmodel.CustomViewModelFactory
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var activityResultLauncher: ActivityResultLauncher<Void>

    private lateinit var binding: FragmentDetailBinding

    private lateinit var id: String

    private val viewModel: DetailViewModel by viewModels(factoryProducer = {
        CustomViewModelFactory((requireActivity().application as App).serviceLocator.repository)
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailBinding.bind(view)

        showUserInfo()

        id = arguments?.getString("detail") ?: ""

        viewModel.id.postValue(id)

        viewModel.getUserDetail(id)

        addNewImage()

    }

    private fun addNewImage() {
        val loadImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
            val stream = requireContext().getContentResolver().openInputStream(it)
            val bitmap = BitmapFactory.decodeStream(stream)

            binding.imageView.load(bitmap)

            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)
            val byteArray: ByteArray = bos.toByteArray()

            viewModel.uploadImage(id, byteArray)
        }

        binding.floatingActionButtonImage.setOnClickListener {
            loadImage.launch("image/*")
        }
    }

    private fun showUserInfo() {

        lifecycleScope.launch {
            viewModel.userDetailStateFlow.collect {
                Log.d("MINE", "message")
                when (it) {
                    is Result.Success -> {
                        binding.pb1.visibility = View.GONE
                        binding.imageView.load(it.data.image)

                        binding.detailUserTv.text =
                            "firstname: ${it.data.firstName} \nlastname: ${it.data.lastName} \nid: ${it.data._id} \nhobbies: ${it.data.hobbies} \nnational code: ${it.data.nationalCode}"

                        Log.d("MINE", "${it.data.image}")
                    }
                    is Result.Loading -> {
                        binding.pb1.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}