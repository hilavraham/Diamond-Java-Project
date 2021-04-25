package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;
/**
 *Sphere class represents Sphere in 3D Cartesian coordinate system
 * by 3D Cartesian coordinate and radius
 * @author hila
 */
public class Sphere implements Geometry {
    final Point3D center;
    final double radius;


	  /**
	   * primary constructor 
	   * */
    public Sphere(Point3D _center, double _radius) {
        center = _center;
        radius = _radius;
    }

    public Point3D getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }
    

	/**
     *  Calculation of the normal
     * */

    @Override
    public Vector getNormal(Point3D p) {
        Vector p0 = p.subtract(center);
        return p0.normalize();
    }

    @Override
    public ArrayList<Point3D> findIntersections(Ray ray) {
    	ArrayList<Point3D> intersections = new ArrayList<Point3D>();
        Point3D P0 = ray.getP0();
        Vector v = ray.getDir();

        if (P0.equals(center)) {
        	intersections.add(center.add(v.scale(radius)));
            return intersections;
        }

        Vector U = center.subtract(P0);

        double tm = alignZero(v.dotProduct(U));
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

        // no intersections : the ray direction is above the sphere
        if (d >= radius) {
            return null;
        }

        double th = alignZero(Math.sqrt(radius * radius - d * d));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if (t1 > 0 && t2 > 0) {
//            Point3D P1 = P0.add(v.scale(t1));
//            Point3D P2 = P0.add(v.scale(t2));
            Point3D P1 =ray.getPoint(t1);
            Point3D P2 =ray.getPoint(t2);
            intersections.add(P1);
            intersections.add(P2);
            return intersections;
        }
        if (t1 > 0) {
//            Point3D P1 = P0.add(v.scale(t1));
            Point3D P1 =ray.getPoint(t1);
            intersections.add(P1);

            return intersections;
        }
        if (t2 > 0) {
//            Point3D P2 = P0.add(v.scale(t2));
            Point3D P2 =ray.getPoint(t2);

            intersections.add(P2);
            return intersections;
        }
        return null;
    }
}
   
