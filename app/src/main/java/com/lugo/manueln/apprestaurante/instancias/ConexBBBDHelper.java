package com.lugo.manueln.apprestaurante.instancias;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.ParcelUuid;
import android.support.annotation.Nullable;

public class ConexBBBDHelper extends SQLiteOpenHelper {

    public ConexBBBDHelper( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(utilidades.CREAR_TABLA_ORDEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + utilidades.TABLA_ORDENES);
        onCreate(sqLiteDatabase);
    }
}




