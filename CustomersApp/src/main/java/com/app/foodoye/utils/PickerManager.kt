package com.app.foodoye.utils

import android.net.Uri
import com.app.foodoye.dataclass.fooditems
import com.app.foodoye.dataclass.ordersClass
import com.app.foodoye.dataclass.restaurantDetails

object PickerManager {
    var downloadStorageUri: Uri?=null
    var restaurantList: MutableList<restaurantDetails>?=null
    var restaurantDetails: MutableList<restaurantDetails>?=null
    var addCartList: MutableList<fooditems>?=null
    var allFoodList: MutableList<fooditems>?=null
    var restaurantFoodList: MutableList<fooditems>?=null
    var ordersList: MutableList<ordersClass>?=null
    var incrementNumberValue=1
    var chooseRestaurant:String?=null
    var checkIncrementValue:Boolean = false
}