package com.andyyang.eyepetizer.ui.splash

import android.graphics.BitmapFactory
import android.os.Environment
import com.andyyang.common.base.repository.BaseRepositoryBoth
import com.andyyang.common.base.repository.ILocalDataSource
import com.andyyang.common.base.repository.IRemoteDataSource
import com.andyyang.eyepetizer.utils.FileUtils
import com.andyyang.network.api.ServiceManager
import com.andyyang.network.entity.GankInfo
import com.andyyang.network.entity.Result
import io.reactivex.Flowable
import io.reactivex.Observable
import zlc.season.rxdownload2.RxDownload
import zlc.season.rxdownload2.entity.DownloadStatus
import java.io.File

class SplashRepository(
    remoteDataSource: SplashRemoteDataSource,
    localDataSource: SplashLocalDataSource
) : BaseRepositoryBoth<SplashRemoteDataSource, SplashLocalDataSource>(remoteDataSource, localDataSource) {

    private fun createFileName(info: Result): String {
        val type = info.url.split(".")
        return info._id + "." + type[type.size - 1]
    }

    fun randomGirl(): Observable<DownloadStatus> {
        return remoteDataSource.randomGirl()
            .filter {
                it.results.isNotEmpty()
            }
            .map {
                val info = it.results[0]
                val savename = createFileName(info)
                val appDir = localDataSource.mkdirFile()
                localDataSource.isExists(savename)
                Triple(info.url, savename, appDir)
            }
            .toObservable()
            .flatMap {
                RxDownload.getInstance()
                    .download(it.first, it.second, it.third.absolutePath)
                    .doOnComplete {
                       localDataSource.saveGirl(it)
                    }
            }

    }


}

class SplashRemoteDataSource(
    private val serviceManager: ServiceManager
) : IRemoteDataSource {

    fun randomGirl(): Flowable<GankInfo> {
        return serviceManager.gankApi.randomGirl(1)
    }

}

class SplashLocalDataSource : ILocalDataSource {

    private var appDir: File? = null

    fun saveGirl(it: Triple<String, String, File>) {
        val cachefile = File(it.third, "cache.txt")
        if (cachefile.exists()) {
            cachefile.delete()
        }
        FileUtils.writeFileFromString(cachefile, it.second, true)

    }

    fun mkdirFile(): File {
        if (appDir == null) {
            appDir = File(Environment.getExternalStorageDirectory(), "Eyepetizer" + File.separator + "images")
            if (!appDir!!.exists()) {
                appDir!!.mkdirs()
            }
        }
        return appDir!!
    }

    fun isExists(savename: String) {
        val downloadfile = File(appDir, savename)
        if (downloadfile.exists()) {
            downloadfile.delete()
        }
    }

/*
    val cachefile = File(appDir, "cache.txt")
    if (cachefile.exists())
    {
        isCache = true
        val path = FileUtils.readFile2String(cachefile, "UTF-8")
        val imgpath = File(appDir!!.absolutePath + File.separator, path)
        if (imgpath.exists()) {
            val bitmap = BitmapFactory.decodeFile(appDir!!.absolutePath + File.separator + path.trim())
            splash_bg.setImageBitmap(bitmap)
            setAnimation()
        } else {
            showDefaultImg()
        }
    } else
    {
        showDefaultImg()
    }*/
}
