package com.lugo.manueln.apprestaurante;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;

public class RedondeaImagen {

    public static Bitmap suavizarEsquinas(Bitmap bitmap ,int pixels){

      /*  Bitmap bitmapFinal=Bitmap.createBitmap(miBit.getWidth(),miBit.getHeight(),Bitmap.Config.ARGB_8888);

        Canvas canvas=new Canvas(bitmapFinal);

        final int color=0xff424242;
        final Paint paint=new Paint();
        final Rect rect=new Rect();
        final RectF rectF=new RectF(rect);

        final float roundPx=pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0,0,0,0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF,roundPx,roundPx,paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(miBit,rect,rect,paint);

        return bitmapFinal;*/

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;



    }
}
