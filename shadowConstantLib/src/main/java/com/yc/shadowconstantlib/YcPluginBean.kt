package com.yc.shadowconstantlib

import android.os.Bundle
import kotlin.jvm.Throws

/**
 * Creator: yc
 * Date: 2021/9/27 14:31
 * UseDes:宿主和manager交互数据
 * @property ppsServiceName String      宿主注册的pps服务类名
 * @property pluginZipPath String       插件zip路径
 * @property partKey String             插件标识
 * @property startActivityName String   插件启动的Activity类名
 * @property dataBundle Bundle          传过去的bundle
 * @property extraPlugin ExtraPlugin?   额外的插件（不在zip包里）
 * @constructor
 */
data class YcPluginBean(
    val ppsServiceName: String,
    val pluginZipPath: String,
    val partKey: String,
    val startActivityName: String,
    val dataBundle: Bundle? = null,
    val extraPlugin: ExtraPlugin? = null,
) {
    companion object {
        @Throws(Throwable::class)
        fun toBean(bundle: Bundle): YcPluginBean {
            val serviceName = bundle.getString(YcHostManager.KEY_PPS_SERVICE_NAME) ?: throw Throwable("pps服务为空")
            val pluginZipPath = bundle.getString(YcHostManager.KEY_PLUGIN_ZIP_PATH) ?: throw Throwable("pluginZipPath路径为空")
            val pluginPartKey = bundle.getString(YcHostManager.KEY_PLUGIN_PART_KEY) ?: throw Throwable("插件的PartKey为空")
            val activityClassName = bundle.getString(YcHostManager.KEY_PLUGIN_START_ACTIVITY_CLASSNAME) ?: throw Throwable("插件的启动的Activity类为空")
            val dataBundle = bundle.getBundle(YcHostManager.KEY_PLUGIN_DATA_BUNDLE)
            //是否有额外插件
            val isContainsExtra = bundle.getBoolean(YcHostManager.KEY_PLUGIN_EXTRA_IS_CONTAINS, false)

            return if (isContainsExtra) {
                val extraDataApkPath = bundle.getString(YcHostManager.KEY_PLUGIN_EXTRA_APK_PATH) ?: throw Throwable("额外插件的Apk路径为空")
                val extraDataBusinessName = bundle.getString(YcHostManager.KEY_PLUGIN_EXTRA_BUSINESS_NAME) ?: pluginPartKey
                val extraDataHash = bundle.getString(YcHostManager.KEY_PLUGIN_EXTRA_DATA_HASH) ?: throw Throwable("额外插件的Hash为空")
                val extraIsUpgrade = bundle.getBoolean(YcHostManager.KEY_PLUGIN_EXTRA_IS_UPGRADE, false)
                YcPluginBean(
                    serviceName, pluginZipPath, pluginPartKey, activityClassName, dataBundle, ExtraPlugin(
                        extraDataApkPath, extraDataHash, extraIsUpgrade, extraDataBusinessName
                    )
                )
            } else {
                YcPluginBean(serviceName, pluginZipPath, pluginPartKey, activityClassName, dataBundle, null)
            }
        }
    }

    /**
     * 额外的插件（不在zip包里）
     * @property extraApkPath String        插件apk路径
     * @property extraHash String           hash
     * @property extraIsUpgrade Boolean     插件apk是否需要更新
     * @property extraBusinessName String?  为空时，使用partKey
     * @constructor
     */
    data class ExtraPlugin(
        val extraApkPath: String,
        val extraHash: String,
        val extraIsUpgrade: Boolean,
        val extraBusinessName: String? = null,
    )

    fun toBundle(): Bundle {
        return Bundle().apply {
            putString(YcHostManager.KEY_PPS_SERVICE_NAME, ppsServiceName)
            putString(YcHostManager.KEY_PLUGIN_ZIP_PATH, pluginZipPath)
            putString(YcHostManager.KEY_PLUGIN_PART_KEY, partKey)
            putString(YcHostManager.KEY_PLUGIN_START_ACTIVITY_CLASSNAME, startActivityName)
            putBundle(YcHostManager.KEY_PLUGIN_DATA_BUNDLE, dataBundle)
            if (extraPlugin == null) {
                putBoolean(YcHostManager.KEY_PLUGIN_EXTRA_IS_CONTAINS, false)
            } else {
                putBoolean(YcHostManager.KEY_PLUGIN_EXTRA_IS_CONTAINS, true)
                putString(YcHostManager.KEY_PLUGIN_EXTRA_APK_PATH, extraPlugin.extraApkPath)
                putString(YcHostManager.KEY_PLUGIN_EXTRA_BUSINESS_NAME, extraPlugin.extraBusinessName ?: partKey)
                putString(YcHostManager.KEY_PLUGIN_EXTRA_DATA_HASH, extraPlugin.extraHash)
                putBoolean(YcHostManager.KEY_PLUGIN_EXTRA_IS_UPGRADE, extraPlugin.extraIsUpgrade)
            }
        }
    }

}