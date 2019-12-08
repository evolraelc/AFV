package com.csy.afv.MVP.model.bean

import java.io.Serializable

data class CategoryBean(
    val alias: Any,
    val bgColor: String,
    val bgPicture: String,
    val defaultAuthorId: Int,
    val description: String,
    val headerImage: String,
    val id: Int,
    val name: String,
    val tagId: Int
): Serializable