package com.example.artspace.model

import com.example.artspace.R

data class AnimationInfo(
    val painterId: Int,
    val title: Int,
    val description: Int,
) {

    companion object {
        fun first(): AnimationInfo {
            return AnimationInfo(
                painterId = R.drawable.kaguya,
                title = R.string.title_kaguya,
                description = R.string.ch_kaguya,
            )
        }

        fun second(): AnimationInfo {
            return AnimationInfo(
                painterId = R.drawable.milije,
                title = R.string.title_milije,
                description = R.string.ch_milije,
            )
        }

        fun list(): List<AnimationInfo> {
            return listOf(first(), second())
        }
    }
}
