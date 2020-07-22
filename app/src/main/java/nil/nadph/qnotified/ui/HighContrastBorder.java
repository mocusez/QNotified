/* QNotified - An Xposed module for QQ/TIM
 * Copyright (C) 2019-2020 xenonhydride@gmail.com
 * https://github.com/cinit/QNotified
 *
 * This software is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software.  If not, see
 * <https://www.gnu.org/licenses/>.
 */
package nil.nadph.qnotified.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

public class HighContrastBorder extends Drawable {
    private final Paint mPaint;

    public HighContrastBorder() {
        mPaint = new Paint();
    }

    public Paint getPaint() {
        return mPaint;
    }

    @Override
    public void draw(Canvas canvas) {
        int w = getBounds().width();
        int h = getBounds().height();
        mPaint.setStrokeWidth(0);
        mPaint.setAntiAlias(false);
        mPaint.setColor(Color.WHITE);
        canvas.drawLine(0.5f, 0.5f, w - 1.5f, 0.5f, mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(1.5f, 1.5f, w - 0.5f, 1.5f, mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawLine(0.5f, 0.5f, 0.5f, h - 1.5f, mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(1.5f, 1.5f, 1.5f, h - 0.5f, mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawLine(w - 1.5f, 0.5f, w - 1.5f, h - 1.5f, mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(w - 0.5f, 1.5f, w - 0.5f, h - 0.5f, mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawLine(0.5f, h - 1.5f, w - 1.5f, h - 1.5f, mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(1.5f, h - 0.5f, w - 0.5f, h - 0.5f, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        //throw new UnsupportedOperationException("Stub!");
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        //throw new UnsupportedOperationException("Stub!");
    }

    @Override
    public int getOpacity() {
        return android.graphics.PixelFormat.TRANSLUCENT;
    }
}
