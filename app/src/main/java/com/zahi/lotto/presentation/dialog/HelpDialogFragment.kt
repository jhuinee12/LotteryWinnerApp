package com.zahi.lotto.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.zahi.lotto.R
import com.zahi.lotto.databinding.DialogHelpTableBinding

class HelpDialogFragment: DialogFragment() {
    private lateinit var binding: DialogHelpTableBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogHelpTableBinding.bind(inflater.inflate(R.layout.dialog_help_table, container, false))
        return binding.root
    }
}