package geometries;

import java.util.ArrayList;
import java.util.List;

import geometries.Intersectable.GeoPoint;
import primitives.*;

/**
 *Triangle class represents Triangle in 3D Cartesian coordinate system
 * 
 * @author hila
 */

public class Triangle extends Polygon {  //implements Geometry

	 final Point3D p1;
	 final Point3D p2;
	 final Point3D p3;
	 
	  /**
	   * primary constructor 
	   * */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
		this.p1 = new Point3D(p1);
		this.p2 = new Point3D(p2);
		this.p3 = new Point3D(p3);
    }
    
    /**
     *  Calculation of the normal
     * */
    @Override
	public Vector getNormal(Point3D point) {
		return plane.getNormal();
	}
    
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance) {
        List<GeoPoint> intersections = plane.findGeoIntersections(ray,maxDistance);
 
        if (intersections == null) return null;

        Point3D p0 = ray.getP0();
        Vector v = ray.getDir();

        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector v3 = vertices.get(2).subtract(p0);

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (Util.isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (Util.isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (Util.isZero(s3)) return null;

        if (!((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0))) return null;

        intersections.get(0).geometry = this;
 
        return intersections;
    }
    

}