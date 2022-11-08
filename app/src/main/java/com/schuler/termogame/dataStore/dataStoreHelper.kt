package com.schuler.termogame.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.schuler.termogame.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map



/**
 * Add string data to data Store
 */
suspend fun Context.writeString(key: String, value: String) {
    dataStore.edit { pref -> pref[stringPreferencesKey(key)] = value }
}


/**
 * Read string from the data store preferences
 */
fun Context.readString(key: String): Flow<String> {
    return dataStore.data.map{ pref ->
        pref[stringPreferencesKey(key)] ?: ""
    }
}

/**
 * Add Integer to the data store
 */
suspend fun Context.writeInt(key: String, value: Int) {
    dataStore.edit { pref -> pref[intPreferencesKey(key)] = value }
}

/**
 * Reading the Int value from the data store
 */
fun Context.readInt(key: String): Flow<Int> {
    return dataStore.data.map { pref ->
        pref[intPreferencesKey(key)] ?: 0
    }
}

/**
 * Adding Double to the data store
 */
suspend fun Context.writeDouble(key: String, value: Double) {
    dataStore.edit { pref -> pref[doublePreferencesKey(key)] = value }
}

/**
 * Reading the double value from the data store
 */
fun Context.readDouble(key: String): Flow<Double> {
    return dataStore.data.map { pref ->
        pref[doublePreferencesKey(key)] ?: 0.0
    }
}

/**
 * Add Long to the data store
 */
suspend fun Context.writeLong(key: String, value: Long) {
    dataStore.edit { pref -> pref[longPreferencesKey(key)] = value }
}

/**
 * Reading the long from the data store
 */
fun Context.readLong(key: String): Flow<Long> {
    return dataStore.data.map { pref ->
        pref[longPreferencesKey(key)] ?: 0L
    }
}


/**
 * Add Boolean to the data store
 */
suspend fun Context.writeBool(key: String, value: Boolean) {
    dataStore.edit { pref -> pref[booleanPreferencesKey(key)] = value }
}

/**
 * Reading the Boolean from the data store
 */
fun Context.readBool(key: String): Flow<Boolean> {
    return dataStore.data.map { pref ->
        pref[booleanPreferencesKey(key)] ?: false
    }
}