package com.jesse.pokedex

import android.app.Application
import be.appwise.core.core.CoreApp
import be.appwise.networking.Networking
import coil.ImageLoader
import coil.ImageLoaderFactory

class App: Application(), ImageLoaderFactory  {

    companion object {
        lateinit var instance: App
    }

    override fun newImageLoader() = ImageLoader.Builder(this).build()

    override fun onCreate() {
        super.onCreate()

        instance = this

        initCore()
    }

    private fun initCore() {
        CoreApp.init(this)
            .apply {
                if (BuildConfig.DEBUG) {
                    initializeErrorActivity(true)
                }
            }
            .initializeLogger(getString(R.string.app_name), BuildConfig.DEBUG)
            .build()

        Networking.Builder(this)
            .setPackageName(packageName)
            .setAppName(getString(R.string.app_name))
            .setVersionCode(BuildConfig.VERSION_CODE.toString())
            .setVersionName(BuildConfig.VERSION_NAME)
            .apply {
                if (BuildConfig.DEBUG) {
                    registerProxymanService(context = this@App)
                }
            }
            .build()
    }

}