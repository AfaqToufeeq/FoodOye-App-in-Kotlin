package com.app.foodoyeadmin.utils

import android.net.Uri
import com.app.foodoyeadmin.dataclass.fooditems
import com.app.foodoyeadmin.dataclass.ordersClass

object PickerManager {
    var downloadStorageUri: Uri?=null
    var imageFileName:String?=null
    var ordersList: MutableList<ordersClass>?=null
    var checkOrderListRequest=1
}