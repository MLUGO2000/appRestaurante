package com.lugo.manueln.apprestaurante.instancias;

public class utilidades {

    //Constantes campos tabla orden

    public static final String TABLA_ORDENES="orden";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_URL="url";
    public static final String CAMPO_CANTIDAD="cantidad";
    public static final String CAMPO_TOTAL="total";




    public static final String CREAR_TABLA_ORDEN="CREATE TABLE "
            + TABLA_ORDENES + " ("+ CAMPO_ID + " INTEGER, " + CAMPO_NOMBRE + " TEXT, "+ CAMPO_URL + " TEXT, " + CAMPO_CANTIDAD + " INTEGER,"
           + CAMPO_TOTAL + " REAL)"  ;




}
