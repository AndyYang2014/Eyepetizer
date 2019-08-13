package com.andyyang.eyepetizer.utils

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 *Created by liuyang on 2019/8/13.
 */
class PermissionHelper(private val mActivity: Activity) {

    private var mPermissionModels = arrayOf<PermissionModel>()
    var onApplyPermission: (() -> Unit)? = null

    fun requestPermissions(models: Array<PermissionModel>) {
        mPermissionModels = models
        if (Build.VERSION.SDK_INT < 23) {
            onApplyPermission?.invoke()
        } else {
            if (isAllRequestedPermissionGranted) {
                onApplyPermission?.invoke()
            } else {
                applyPermissions()
            }
        }
    }

    private fun applyPermissions() {
        try {
            for (model in mPermissionModels) {
                if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(mActivity, model.permission)) {
                    ActivityCompat.requestPermissions(mActivity, arrayOf(model.permission), model.requestCode)
                    return
                }
            }
            onApplyPermission?.invoke()
        } catch (e: Throwable) {

        }
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        for (permissionModel in mPermissionModels.iterator()) {
            if (permissionModel.requestCode == requestCode) {
                if (PackageManager.PERMISSION_GRANTED != grantResults[0]) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permissions[0])) {
                        val builder = AlertDialog.Builder(mActivity).setTitle("权限申请").setMessage(findPermissionExplain(permissions[0]))
                            .setPositiveButton("确定") { dialog, which -> applyPermissions() }
                        builder.setCancelable(false)
                        builder.show()
                    } else {
                        val builder = AlertDialog.Builder(mActivity).setTitle("权限申请")
                            .setMessage("请在打开的窗口的权限中开启" + findPermissionName(permissions[0]) + "权限，以正常使用本应用")
                            .setPositiveButton("去设置") { dialog, which -> openApplicationSettings(REQUEST_OPEN_APPLICATION_SETTINGS_CODE) }.setNegativeButton("取消") { dialog, which -> mActivity.finish() }
                        builder.setCancelable(false)
                        builder.show()
                    }
                    return
                }

                if (isAllRequestedPermissionGranted) {
                    onApplyPermission?.invoke()
                } else {
                    applyPermissions()
                }
            }
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_OPEN_APPLICATION_SETTINGS_CODE -> if (isAllRequestedPermissionGranted) {
                onApplyPermission?.invoke()
            } else {
                mActivity.finish()
            }
        }
    }

    val isAllRequestedPermissionGranted: Boolean
        get() {
            return mPermissionModels.none { PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(mActivity, it.permission) }
        }

    private fun openApplicationSettings(requestCode: Int): Boolean {
        try {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + mActivity.packageName))
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            mActivity.startActivityForResult(intent, requestCode)
            return true
        } catch (e: Throwable) {
        }

        return false
    }

    private fun findPermissionExplain(permission: String): String? {
        return mPermissionModels
            .firstOrNull { it.permission == permission }
            ?.explain
    }

    private fun findPermissionName(permission: String): String? {
        return mPermissionModels
            .firstOrNull { it.permission == permission }
            ?.name
    }

    data class PermissionModel(val name: String, val permission: String, val explain: String, val requestCode: Int)

    companion object {
        private val REQUEST_OPEN_APPLICATION_SETTINGS_CODE = 12345
    }

}