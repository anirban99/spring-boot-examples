package com.example.inventory.utils

enum class Condition {
    USED,
    NEW,
    ;

    companion object {
        fun from(condition: String) = when (condition) {
            "NEW" -> NEW
            "USED" -> USED
            else -> null
        }
    }
}