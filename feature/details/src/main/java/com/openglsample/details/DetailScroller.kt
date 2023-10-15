package com.openglsample.details

class DetailScroller {

    fun getToolBarState(offset:Float):AppBarState{

        return AppBarState.SHOW
    }
}

enum class AppBarState{
    SHOW,HIDE
}