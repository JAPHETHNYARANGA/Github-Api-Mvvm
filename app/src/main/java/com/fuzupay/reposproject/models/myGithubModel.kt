package com.fuzupay.reposproject.models

data class myGithubModel(
    val incomplete_results: Boolean,
    val items: ArrayList<Item>,
    val total_count: Int
)