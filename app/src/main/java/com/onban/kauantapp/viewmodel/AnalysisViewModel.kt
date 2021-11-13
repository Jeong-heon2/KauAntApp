package com.onban.kauantapp.viewmodel

import androidx.lifecycle.ViewModel
import com.onban.kauantapp.repo.Repository
import javax.inject.Inject

class AnalysisViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
}