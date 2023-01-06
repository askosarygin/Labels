package com.example.labels

class LabelInfo(
    val id: Long = getId(),
    val text: String = "",
    val isDeletable: Boolean = true
) {

    companion object {
        private var id = 0L
        fun getId() = run {
            id += 1L
            id
        }
    }
}