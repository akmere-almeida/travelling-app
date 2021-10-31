package com.akmere.travelling_app.domain

import kotlin.random.Random
import kotlin.random.nextInt

class LoadOfferFavoriteCount {
    fun execute(): Int {
        return Random.nextInt(10..3000)
    }
}