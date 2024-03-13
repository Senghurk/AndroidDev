package com.csx4109.fragmentdemo

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import com.csx4109.fragmentdemo.databinding.ActivityCameraBinding
import com.csx4109.fragmentdemo.utils.PermissionHandler
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity() {
    private val view: ActivityCameraBinding by lazy { ActivityCameraBinding.inflate(layoutInflater) }
    private val cameraExecutor: ExecutorService by lazy { Executors.newSingleThreadExecutor() }

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
        PermissionHandler.requestPermissionIfRequired(this, PermissionHandler.CAMERA)

        val cameraController = LifecycleCameraController(this)
        cameraController.setEnabledUseCases(
            CameraController.IMAGE_CAPTURE or CameraController.VIDEO_CAPTURE
        )
//        if (cameraController.hasCamera(CameraSelector.DEFAULT_FRONT_CAMERA)) {
//            cameraController.cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
//        }
//        cameraController.isPinchToZoomEnabled = true
//        cameraController.isTapToFocusEnabled = true
//        cameraController.imageCaptureMode = ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY
//        cameraController.imageCaptureTargetSize = CameraController.OutputSize(Size(1024, 1024))

        view.pvCamera.controller = cameraController

        cameraController.bindToLifecycle(this)

//        cameraController.setLinearZoom(0.2f) // 0-1

//        view.btnCapture.setOnClickListener {
//            val outputOptions = ImageCapture.OutputFileOptions
//                .Builder(
//                    contentResolver,
//                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                    ContentValues()
//                )
//                .build()
//
//            cameraController.takePicture(
//                outputOptions,
//                cameraExecutor,
//                object : ImageCapture.OnImageSavedCallback {
//                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
//                        Log.e("OAT", "Capture Image Successful")
//                    }
//
//                    override fun onError(exception: ImageCaptureException) {
//                        Log.e("OAT", exception.message.toString())
//                    }
//
//                })
//        }

        view.btnCapture.setOnClickListener {
            cameraController.takePicture(cameraExecutor,
                object : ImageCapture.OnImageCapturedCallback() {
                    override fun onCaptureSuccess(image: ImageProxy) {
                        Log.e("OAT", "Capture Image Successful")
                        super.onCaptureSuccess(image)
                        showImage(image)
                    }

                    override fun onError(exception: ImageCaptureException) {
                        Log.e("OAT", exception.message.toString())
                        super.onError(exception)
                    }
                })
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        cameraExecutor.shutdown()
    }

    private fun showImage(image: ImageProxy) {
        val bitmap = imageProxyToBitmap(image)

        this@CameraActivity.runOnUiThread {
            AlertDialog.Builder(this@CameraActivity)
                .setView(ImageView(this@CameraActivity).apply {
                    setImageBitmap(bitmap)
                }).setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }.create()
                .show()
        }
    }

    private fun imageProxyToBitmap(image: ImageProxy): Bitmap {
        val byteBuffer = image.planes[0].buffer
        val bytes = ByteArray(byteBuffer.remaining())
        byteBuffer.get(bytes)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

}