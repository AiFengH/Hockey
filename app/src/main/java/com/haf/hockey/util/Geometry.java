package com.haf.hockey.util;

/**
 * Created by Aaron Mt on 2019/5/4.
 */
public class Geometry {
    public static class Point {
        public float x, y, z;
        public Point(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Point translateY(float distance) {
            return new Point(x, y + distance, z);
        }
    }

    public static class Circle {
        Point mPoint;
        float mRadius;
        public Circle (Point point, float radius) {
            this.mPoint = point;
            this.mRadius = radius;
        }

        public Circle scale(float scale) {
            return new Circle(mPoint, mRadius * scale);
        }
    }

    public static class Cylinder {
        Point mPoint;
        float mRadius;
        float mHeight;

        public Cylinder(Point point, float radius, float height) {
            this.mPoint = point;
            this.mRadius = radius;
            this.mHeight = height;
        }
    }
}
