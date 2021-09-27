package com.yc.constantlib

import android.os.Bundle

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
    val dataBundle: Bundle = Bundle(),
    val extraPlugin: ExtraPlugin? = null,
) {
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