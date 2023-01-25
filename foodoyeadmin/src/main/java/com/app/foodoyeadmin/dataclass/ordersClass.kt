package com.app.foodoyeadmin.dataclass

data class ordersClass(
    var address: String? = null,
    var comments: String? = null,
    var date: String? = null,
    var fullName: String? = null,
    var orderNo: String? = null,
    var phone: String? = null,
    var status: String? = null,
    var time: String? = null,
    var totalPrice: String? = null,
    var uid: String? = null,
    var uniqueKey: String? = null,
    var foodList: MutableList<fooditems>?=null
)
