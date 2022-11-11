package com.deonnao.luop

class Post {

    var username: String? = null
    var textPost: String? = null
    var imageUrl: String? = null

    constructor() {}

    constructor(username: String?, textPost: String?, imageUrl: String?) {
        this.username = username
        this.textPost = textPost
        this.imageUrl = imageUrl
    }
}
