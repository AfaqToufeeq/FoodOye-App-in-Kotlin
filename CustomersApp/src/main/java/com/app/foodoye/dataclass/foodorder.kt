package com.app.foodoye.dataclass

data class foodorder(
    val uniqueKey: String? =null,
    val uid: String?=null,
    val date: String?=null,
    val time: String?=null,
    val fullName: String?=null,
    val phone: String?=null,
    val address: String?=null,
    val comments: String?=null,
    val status: String?=null,
    val TotalPrice: String?=null,
    val OrderNo: String?=null,
    val FoodList: MutableList<fooditems>?=null
)
