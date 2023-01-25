package com.app.foodoyeadmin.interfaces

interface onOrdersMenuItemClick {
    fun onItemClick(position:Int)
    fun onLongClick(position: Int)
    fun onLongMenuClick(status: Int,position: Int)
}