package com.akmere.travelling_app.data

import java.util.Scanner

object JsonReader {
    fun readFile(file: String): String {
        return Scanner(
            this.javaClass.classLoader
                ?.getResourceAsStream(file)
        ).useDelimiter("\\A").next()
    }
}
