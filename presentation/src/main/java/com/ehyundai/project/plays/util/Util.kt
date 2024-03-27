package com.ehyundai.project.plays.util

import android.content.Context
import android.preference.PreferenceManager

object Util {
    /**
     * 프리퍼런스 값 저장 함수
     *
     * @param context
     * @param key
     * @param value
     */
    fun setPreference(context: Context, key: String?, value: String?) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).apply()
    }

    /**
     * 프리퍼런스 값 저장 함수
     *
     * @param context
     * @param key
     * @param value
     */
    fun setPreference(context: Context, key: String?, value: Boolean) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(key, value).apply()
    }

    /**
     * 프리퍼런스 값 호출 함수
     *
     * @param context
     * @param key
     * @param defValue
     */
    fun getPreference(context: Context, key: String?, defValue: String?): String? {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, defValue)
    }

    /**
     * 프리퍼런스 값 호출 함수
     *
     * @param context
     * @param key
     * @param defValue
     */
    fun getPreference(context: Context, key: String?, defValue: Boolean): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, defValue)
    }

    /**
     * 프리퍼런스 값 호출 함수
     *
     * @param context
     * @param key
     * @param defValue
     */
    fun getPreference(context: Context, key: String?, defValue: Set<String?>?): Set<String?>? {
        return PreferenceManager.getDefaultSharedPreferences(context).getStringSet(key, defValue)
    }

    /**
     * 프리퍼런스 값 삭제 함수
     *
     * @param context
     * @param key
     */
    fun removePreference(context: Context, key: String?) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().remove(key).apply()
    }
}