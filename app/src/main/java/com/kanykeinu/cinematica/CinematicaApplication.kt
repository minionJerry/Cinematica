package com.kanykeinu.cinematica

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class CinematicaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}
