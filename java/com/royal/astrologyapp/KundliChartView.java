package com.royal.astrologyapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class KundliChartView extends View {

    private static final String[] PLANET_NAMES = {"Sun", "Moon", "Mars", "Mercury", "Jupiter", "Venus", "Saturn", "Rahu", "Ketu"};
    private static final int[] PLANET_COLORS = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.GRAY, Color.BLACK, Color.DKGRAY};
    private static final int CUSP_COLOR = Color.BLACK;
    private static final int ASC_COLOR = Color.RED;
    private static final int HOUSE_COLOR = Color.BLACK;

    private double[] cusps;
    private double[] asc;
    private double[] planets;
    private Paint paint;
    private Paint textPaint;

    public KundliChartView(Context context, double[] cusps, double[] asc, double[] planets) {
        super(context);
        this.cusps = cusps;
        this.asc = asc;
        this.planets = planets;
        init();
    }

    public KundliChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextAlign(Paint.Align.CENTER);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(20);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int radius = Math.min(width, height) / 2;

        // draw the cusps
        paint.setColor(CUSP_COLOR);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        for (int i = 1; i < cusps.length; i++) {
            int angle = (i - 1) * 30 + 15;
            int x = (int) (centerX + radius * Math.cos(Math.toRadians(angle)));
            int y = (int) (centerY + radius * Math.sin(Math.toRadians(angle)));
            canvas.drawCircle(x, y, 5, paint);
            String cuspLabel = "" + i;
            drawText(canvas, cuspLabel, x, y - 15);
        }

        // draw the houses
        for (int i = 1; i <= 12; i++) {
            int startAngle = (i - 1) * 30 + 15;
            int endAngle = i * 30 + 15;
            paint.setColor(HOUSE_COLOR);
            paint.setStrokeWidth(2);
            canvas.drawArc(new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius), startAngle, 30, false, paint);
            int angle = (startAngle + endAngle) / 2;
            int x = (int) (centerX + radius * 0.6 * Math.cos(Math.toRadians(angle)));
            int y = (int) (centerY + radius * 0.6 * Math.sin(Math.toRadians(angle)));
            String houseLabel = "" + i;
            drawText(canvas, houseLabel, x, y);
        }

        // draw the ascendant
        paint.setColor(ASC_COLOR);
        paint.setStyle(Paint.Style.FILL);
        int ascAngle = (int) (asc[0] * 30 + 15);
        int ascX = (int) (centerX + radius * 0.9 * Math.cos(Math.toRadians(ascAngle)));
        int ascY = (int) (centerY + radius * 0.9 * Math.sin(Math.toRadians(ascAngle)));
        canvas.drawCircle(ascX, ascY, 10, paint);

        // draw the planets
        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < planets.length; i++) {
            int angle = (int) (planets[i] * 30 + 15);
            int x = (int) (centerX + radius * 0.8 * Math.cos(Math.toRadians(angle)));
            int y = (int) (centerY + radius * 0.8 * Math.sin(Math.toRadians(angle)));
//            paint.setColor(PLANET_COLORS[i]);
            canvas.drawCircle(x, y, 10, paint);
//            drawText(canvas, PLANET_NAMES[i], x, y + 25);
        }
    }

    private void drawText(Canvas canvas, String text, int x, int y) {
        Rect bounds = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), bounds);
        canvas.drawText(text, x, y + bounds.height() / 2, textPaint);
    }
}
