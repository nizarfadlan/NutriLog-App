package com.nutrilog.app.presentation.ui.analysis

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.nutrilog.app.R
import com.nutrilog.app.databinding.ActivityAnalysisBinding
import com.nutrilog.app.presentation.ui.base.BaseActivity
import com.nutrilog.app.presentation.ui.base.component.dialog.PhotoDialogFragment
import com.nutrilog.app.presentation.ui.camera.CameraActivity
import com.nutrilog.app.presentation.ui.camera.CameraActivity.Companion.CAMERAX_RESULT
import com.nutrilog.app.utils.constant.AppConstant.REQUIRED_CAMERA_PERMISSION
import com.nutrilog.app.utils.helpers.gone
import com.nutrilog.app.utils.helpers.reduceFileImage
import com.nutrilog.app.utils.helpers.show
import com.nutrilog.app.utils.helpers.showSnackBar
import com.nutrilog.app.utils.helpers.uriToFile
import java.io.File

class AnalysisActivity : BaseActivity<ActivityAnalysisBinding>() {
    private var imageFile: File? = null
    private var currentImageUri: Uri? = null

    override val bindingInflater: (LayoutInflater) -> ActivityAnalysisBinding =
        ActivityAnalysisBinding::inflate

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission(),
        ) { isGranted: Boolean ->
            if (!isGranted) binding.root.showSnackBar("Camera permission denied")
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this,
            REQUIRED_CAMERA_PERMISSION,
        ) == PackageManager.PERMISSION_GRANTED

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        super.onViewBindingCreated(savedInstanceState)

        initCheckPermission()
        initToolbar()
        initActions()
        initView()
    }

    private fun initCheckPermission() {
        if (!allPermissionsGranted()) requestPermissionLauncher.launch(REQUIRED_CAMERA_PERMISSION)
    }

    private fun initView() {
        showImage()
    }

    private fun initActions() {
        binding.apply {
            ivHolderPhoto.setOnClickListener {
                showImageDialog()
            }

            btnChangeImage.setOnClickListener {
                showImageDialog()
            }

            buttonAdd.setOnClickListener {
                uploadAnalysis()
            }
        }
    }

    private fun initToolbar() {
        binding.apply {
            toolbar.apply {
                tvPage.text = getString(R.string.header_analysis_title)
                backButton.setOnClickListener { finish() }
            }
        }
    }

    private fun uploadAnalysis() {
        when {
            currentImageUri == null -> binding.root.showSnackBar(getString(R.string.validation_photo_empty))
            else -> {
                uploadAnalysisProcess()
            }
        }
    }

    private fun uploadAnalysisProcess() {
        currentImageUri?.let { uri ->
            imageFile = uri.uriToFile(this).reduceFileImage()

            // Observe
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            binding.apply {
                ivHolderPhoto.gone()
                ivPhoto.show()
                ivPhoto.setImageURI(it)
                btnChangeImage.show()
            }
        }
    }

    private fun showImageDialog() {
        PhotoDialogFragment(::startCamera, ::startGallery)
            .show(supportFragmentManager, PhotoDialogFragment::class.java.simpleName)
    }

    private val launcherGallery =
        registerForActivityResult(
            ActivityResultContracts.PickVisualMedia(),
        ) { uri: Uri? ->
            if (uri != null) {
                currentImageUri = uri
                showImage()
            }
        }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherIntentCameraX =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) {
            if (it.resultCode == CAMERAX_RESULT) {
                currentImageUri =
                    it.data?.getStringExtra(CameraActivity.EXTRA_CAMERAX_IMAGE)?.toUri()
                showImage()
            }
        }

    private fun startCamera() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.loadingLayout.root.apply {
            if (isLoading) show() else gone()
        }
    }
}
