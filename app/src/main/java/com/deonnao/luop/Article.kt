package com.deonnao.luop

import android.widget.ImageView

class Article {

    var title: String? = null
    var author: String? = null
    var imageUrl: String? = null

    constructor() {}

    constructor(title: String?, author: String?, imageUrl: String?) {
        this.title = title
        this.author = author
        this.imageUrl = imageUrl
    }
}
