package com.example.goodweather.presentation.viewmodel

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes contentLayoutId: Int = 0): Fragment(contentLayoutId) {
}