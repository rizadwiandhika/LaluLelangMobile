package com.rizadwi.mandiri.android.lalulelang.util

import android.content.Context
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rizadwi.mandiri.android.lalulelang.databinding.DialogConfirmBinding
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ConfirmDialogUtil @Inject constructor(@ActivityContext private val context: Context) {

    fun show(
        title: String,
        description: String,
        buttonText: String,
        action: (() -> Unit)?
    ) {
        val dialog = BottomSheetDialog(context)
        val binding = DialogConfirmBinding.inflate(LayoutInflater.from(context))

        with(binding) {
            tvTitle.text = title
            tvDescription.text = description
            btnAction.text = buttonText
            btnAction.setOnClickListener { action?.invoke(); dialog.dismiss() }
        }

        dialog.setContentView(binding.root)
        dialog.show()
    }

    fun show(binding: ViewBinding, actionSetter: (close: () -> Unit) -> Unit) {
        val dialog = BottomSheetDialog(context)

        actionSetter.invoke(dialog::dismiss)

        dialog.setContentView(binding.root)
        dialog.show()
    }

}