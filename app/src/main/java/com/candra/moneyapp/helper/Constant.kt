package com.candra.moneyapp.helper

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object Constant {
    /* for entity database village */
    const val TABLE_VILLAGE = "table_desa"
    const val REGENCY = "kecamatan"
    const val ID_VILLAGE = "id_desa"
    const val YEAR_VILLAGE = "tahun_desa"
    const val NAME_VILLAGE = "nama_desa"
    const val BUDGET = "anggaran"
    const val BLT_SUSKEUDES_VILLAGE_JUNY = "blt_suskeudes_desa_juni"
    const val BLT_SUSKEUDES_VILLAGE_DECEMBER = "blt_suskeudes_desa_desember"
    const val NAME_DATABASE = "village_database.db"


    /* for entity database budget channeled */
    const val TABLE_CHANNELED_BUDGET = "table_anggaran_disalurkan"
    const val CHANNELED = "disalurkan"
    const val RANGE_BUDGET = "jumlah_disalurkan"
    const val MONTH = "bulan"
    const val LOGIN_SESSION_USER = "session_login_user"

    /* prefreneces for user login */
    private const val KEY_USERNAME = "username"
    private const val KEY_PASSWORD = "password"
    private const val KEY_LOGIN = "boolean"

    val USERNAME = stringPreferencesKey(KEY_USERNAME)
    val PASSWORD = stringPreferencesKey(KEY_PASSWORD)
    val IS_LOGIN = booleanPreferencesKey(KEY_LOGIN)
    val ID_KEY_VILLAGE = stringPreferencesKey(ID_VILLAGE)
    val NAME_KEY_VILLAGE = stringPreferencesKey(NAME_VILLAGE)
    val TOTAL_KEY_BUDGET = stringPreferencesKey(BUDGET)
    /*Variabel const for need developer */
    const val TITLE_SPLASHSCREEN = "welcome to app money"
    const val TIME_OF_DELAYED = 3000L
    const val TITLE_LOGIN = "login"
    const val USERNAME_LOGIN = "admin"
    const val PASSWORD_LOGIN = "123456"
    const val TITLE_VILLAGE = "home"
    const val EXTRA_VILLAGE = "extra_village"
    const val EXTRA_POSITION = "extra_position"
    const val EXTRA_CHANNELED = "extra_channeled"
    const val EXTRA_ID_VILLAGE = "extra_id_village"
    const val TEXT_ADD_DATA = "Tambah data"
    const val SUCCESS_ADD_DATA = "Data berhasil di tambah"
    const val SUCCESS_CHANGE_DATA = "Data berhasil di ubah"
    const val HOME_DATA = "Home"

}