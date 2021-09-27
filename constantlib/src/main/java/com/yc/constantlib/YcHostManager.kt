package com.yc.constantlib

/**
 * Creator: yc
 * Date: 2021/9/27 14:19
 * UseDes:
 * shadow 宿主跟manager交互的常量
 */
object YcHostManager {
    /**
     * 宿主注册的pps服务类名
     */
    const val KEY_PPS_SERVICE_NAME = "key_pps_service_name"

    /**
     * 插件压缩包路径（里面包含插件apk，runtime.apk，loader.apk，config.json）
     */
    const val KEY_PLUGIN_ZIP_PATH = "key_plugin_zip_path"

    /**
     * 子插件标识的partKey
     */
    const val KEY_PLUGIN_PART_KEY = "key_plugin_part_key"

    /**
     * 子插件启动Activity的类名
     */
    const val KEY_PLUGIN_START_ACTIVITY_CLASSNAME = "key_plugin_start_activity_classname"

    /**
     * 子插件传入的bundle（用于发送数据）
     */
    const val KEY_PLUGIN_DATA_BUNDLE = "key_plugin_data_bundle"

    /**
     * 是否是额外的插件（不在zip包里）
     */
    const val KEY_PLUGIN_EXTRA_IS_CONTAINS = "key_plugin_extra_is_contains"

    /**
     * 额外插件的apk路径
     */
    const val KEY_PLUGIN_EXTRA_APK_PATH = "key_plugin_extra_apk_path"

    /**
     * 额外插件的hash
     */
    const val KEY_PLUGIN_EXTRA_DATA_HASH = "key_plugin_extra_data_hash"

    /**
     * 额外插件的BusinessName
     */
    const val KEY_PLUGIN_EXTRA_BUSINESS_NAME = "key_plugin_extra_business_name"

    /**
     * 额外插件是否需要更新
     */
    const val KEY_PLUGIN_EXTRA_IS_UPGRADE = "key_plugin_extra_is_upgrade"
}