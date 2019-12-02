package com.android.photosapp.ui.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.photosapp.data.entity.PhotoEntity
import com.android.photosapp.data.viewModelPackage.MainViewModel
import com.android.photosapp.databinding.DialogFragmentLayoutBinding
import com.android.photosapp.services.UploadService
import com.google.android.material.snackbar.Snackbar

class AddDialogFragment : DialogFragment() {
    private var REQUEST_CODE = 101
    lateinit var dialogBinding: DialogFragmentLayoutBinding
    lateinit var mainViewModel: MainViewModel
    lateinit var navigator: NavController
    var image_uri: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialogBinding = DialogFragmentLayoutBinding.inflate(inflater, container, false)
        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        dialogBinding.addImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            if (intent.resolveActivity(activity!!.packageManager) != null) startActivityForResult(
                intent,
                REQUEST_CODE
            ) else showSnackBar("Oops! Something went wrong")
        }

        dialogBinding.okButton.setOnClickListener {
            if (dialogBinding.descriptionText.text.toString().isNotBlank() && dialogBinding.titleText.text.toString().isNotBlank() && image_uri !== null) {
                val intent: Intent = Intent(activity?.applicationContext, UploadService::class.java)
                intent.putExtra(
                    "upload_photo",
                    PhotoEntity(
                        dialogBinding.titleText.text.toString(),
                        image_uri.toString(),
                        dialogBinding.descriptionText.text.toString()
                    )
                )
                activity!!.startService(intent)
                navigator.navigateUp()
            } else showSnackBar("You have to fill everything :)")
        }
        return dialogBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = Navigation.findNavController(view)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data?.data != null && requestCode == 101 && resultCode == RESULT_OK) showSnackBar("Image is selected").also {
            image_uri = data.data!!
        } else return

    }


    fun showSnackBar(text: String) {
        Snackbar.make(dialogBinding.root, text, Snackbar.LENGTH_LONG)
            .show()
    }

}