package renderer;

import elements.LightSource;

import static geometries.Intersectable.GeoPoint;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import elements.*;

import java.time.LocalDateTime;
import java.util.Random;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;

public class ImproveSoftShadowRayTracer extends BasicRayTracer {
    /**
     * the radius size of the target circle through construct the beam of rays
     */
    private double RADIUS = 4;

    /**
     * the amount of rays in the beam
     */
    private double AMOUNT_OF_RAYS = 500;
    private static Random random = new Random(LocalDateTime.now().getNano());

    /**
     * constructor
     *
     * @param scene
     */
    public ImproveSoftShadowRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * chaining methode
     *
     * @param RADIUS
     * @return
     */
    public ImproveSoftShadowRayTracer setRADIUS(double RADIUS) {
        this.RADIUS = RADIUS;
        return this;
    }

    /**
     * chaining methode
     *
     * @param AMOUNT_OF_RAYS
     * @return
     */
    public ImproveSoftShadowRayTracer setAMOUNT_OF_RAYS(double AMOUNT_OF_RAYS) {
        this.AMOUNT_OF_RAYS = AMOUNT_OF_RAYS;
        return this;
    }

    /**
     * this function calculate the average transparency of beam of shadow rays
     *
     * @param lightSource    the
     * @param lightDirection
     * @param n              normal of the point
     * @param geoPoint
     * @return average transparency
     */
   // @Override
    protected double transparency1(LightSource lightSource, Vector lightDirection, Vector n, GeoPoint geoPoint) {
        //if lightSource is DirectionalLight' there is no soft shadow
        if (lightSource.getClass() == DirectionalLight.class) {
            return super.transparency(lightSource, lightDirection, n, geoPoint);
        }
        //We'll create the center point of the circle through which construct beam of vectors
        Point3D Pc = geoPoint.point.add(lightDirection.scale(lightSource.getDistance(geoPoint.point)));
        //create two vectors that orthogonal to lightDirection and they are also orthogonal
        //these vectors represent the plane of the target circle
        Vector X = new Vector(-lightDirection.getZ(), 0, lightDirection.getX()).normalize();
        Vector Y = X.crossProduct(lightDirection).normalize();
        List<Vector> vectorList = new LinkedList<>();
        vectorList.add(lightDirection);
        //create beam of vector
        for (int i = 0; i < AMOUNT_OF_RAYS; i++) {
            double cos = random.nextDouble() * 2 - 1;//value between -1 to 1
            double sin = Math.sqrt(1 - cos * cos);
            double d = random.nextDouble() * 2 * RADIUS - RADIUS;//value between -Radius to Radius
            double x = cos * d;
            double y = sin * d;

            Point3D targetPoint = Pc;
            if (alignZero(x) != 0) {
                targetPoint = targetPoint.add(X.scale(x));
            }
            if (alignZero(y) != 0) {
                targetPoint = targetPoint.add(Y.scale(y));
            }
            Vector v = targetPoint.subtract(geoPoint.point).normalize();
            //check if the vector is under the surface we wont add it
            double t = lightDirection.dotProduct(n) * v.dotProduct(n);
            if (alignZero(t) > 0) {
                vectorList.add(v);
            }
        }

        //calculate the average transparency
        double ktr = 0;
        for (Vector v : vectorList) {
            ktr += super.transparency(lightSource, v, n, geoPoint);
        }
        return ktr / vectorList.size();
    }
@Override
    protected double transparency(LightSource lightSource, Vector lightDirection, Vector n, GeoPoint geoPoint) {
        //if lightSource is DirectionalLight' there is no soft shadow
        if (lightSource.getClass() == DirectionalLight.class) {
            return super.transparency(lightSource, lightDirection, n, geoPoint);
        }
        //We'll create the center point of the circle through which construct beam of vectors
        Point3D Pc = geoPoint.point;
        //create two vectors that orthogonal to lightDirection and they are also orthogonal
        //these vectors represent the plane of the target circle
        Vector X = new Vector(-lightDirection.getZ(), 0, lightDirection.getX()).normalize();
        Vector Y = X.crossProduct(lightDirection).normalize();
        List<Vector> vectorList = new LinkedList<>();
        vectorList.add(lightDirection);
        //create beam of vector
        for (int i = 0; i < AMOUNT_OF_RAYS; i++) {
            double cos = random.nextDouble() * 2 - 1;//value between -1 to 1
            double sin = Math.sqrt(1 - cos * cos);
            double d = random.nextDouble() * 2 * RADIUS - RADIUS;//value between -Radius to Radius
            double x = cos * d;
            double y = sin * d;

            Point3D targetPoint = Pc;
            if (alignZero(x) != 0) {
                targetPoint = targetPoint.add(X.scale(x));
            }
            if (alignZero(y) != 0) {
                targetPoint = targetPoint.add(Y.scale(y));
            }
            Vector v = ((PointLight)lightSource).getPosition().subtract(targetPoint).normalize();
            //check if the vector escape from the light
            double t = lightDirection.dotProduct(n) * v.dotProduct(n);
            if (alignZero(t) > 0) {
                vectorList.add(v);
            }
        }

        //calculate the average transparency
        double ktr = 0;
        for (Vector v : vectorList) {
            ktr += super.transparency(lightSource, v, n, geoPoint);
        }
        return ktr / vectorList.size();
    }

}
