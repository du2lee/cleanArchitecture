package com.ehyundai.project.plays.view.club

import android.Manifest
import android.R
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ehyundai.project.plays.databinding.ActivityCreateClubBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

@AndroidEntryPoint
class CreateClubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateClubBinding
    private val viewModel: CreateClubViewModel by viewModels()
    private lateinit var context: Context

    private val PERMISSION_CODE_CAMERA = 100
    private val PERMISSION_CODE_GALLERY = 101
    private val REQUEST_IMAGE_CAPTURE = 102
    private val REQUEST_IMAGE_PICK = 103

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateClubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.vm = viewModel
        context = this

        binding.ivRegisterLogo.setOnClickListener {    // 이미지뷰를 클릭했을 때 갤러리 또는 카메라를 열기 위한 메소드 호출
            showCameraOrGalleryDialog()
        }

        binding.btnRegisterClub.setOnClickListener {
            viewModel.creatClub(context)
            finish()
        }

        initViewModelCallback()
    }

    private fun showCameraOrGalleryDialog() {
        val options = arrayOf("카메라로 사진 촬영", "갤러리에서 사진 선택")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("사진을 가져올 곳을 선택하세요")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> {
                    if (hasCameraPermission()) {
                        openCamera()
                    } else {
                        requestCameraPermission()
                    }
                }
                1 -> openGallery()
            }
        }
        builder.setNegativeButton("취소") { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun hasCameraPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    private fun requestCameraPermission() {
        requestPermissions(arrayOf(Manifest.permission.CAMERA), PERMISSION_CODE_CAMERA)
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_CODE_CAMERA && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCamera()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    viewModel.setCamera(context, data?.extras?.get("data") as Bitmap)
                }
                REQUEST_IMAGE_PICK -> {
                    viewModel.setGallery(data?.data)
                }
            }
        }
    }

    private fun initViewModelCallback() {
        with(viewModel){
            clubImg.observe(this@CreateClubActivity, Observer{binding.ivRegisterLogo.setImageURI(it)})
        }
    }

}