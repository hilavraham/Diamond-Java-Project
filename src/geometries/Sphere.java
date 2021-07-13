package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 *Sphere class represents Sphere in 3D Cartesian coordinate system
 * by 3D Cartesian coordinate and radius
 * @author hila
 */
public class Sphere extends RadialGeometry {
    final Point3D center;



	  /**
	   * primary constructor 
	   * */
    public Sphere(Point3D _center, double _radius) {
        super(_radius);
    	center = _center;

    }

    public Point3D getCenter() {
        return center;
    }

    

	/**
     *  Calculation of the normal
     * */

    @Override
    public Vector getNormal(Point3D p) {
        Vector p0 = p.subtract(center);
        return p0.normalize();
    }

//	@Override
//	public List<GeoPoint> findGeoIntersections(Ray ray) {
//		ArrayList<GeoPoint> intersections = new ArrayList<GeoPoint>();
//        Point3D P0 = ray.getP0();
//        Vector v = ray.getDir();
//
//        if (P0.equals(center)) {
//        	intersections.add(new GeoPoint(this,center.add(v.scale(getRadius())) ));
//            return intersections;
//        }
//
//        Vector U = center.subtract(P0);
//
//        double tm = alignZero(v.dotProduct(U));
//        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));
//
//        // no intersections : the ray direction is above the sphere
//        if (d >= getRadius()) {
//            return null;
//        }
//
//        double th = alignZero(Math.sqrt(getRadius() * getRadius() - d * d));
//        double t1 = alignZero(tm - th);
//        double t2 = alignZero(tm + th);
//
//        if (t1 > 0 && t2 > 0) {
////            Point3D P1 = P0.add(v.scale(t1));
////            Point3D P2 = P0.add(v.scale(t2));
//            Point3D P1 =ray.getPoint(t1);
//            Point3D P2 =ray.getPoint(t2);
//            intersections.add(new GeoPoint(this,P1));
//            intersections.add(new GeoPoint(this,P2));
//            return intersections;
//        }
//        if (t1 > 0) {
////            Point3D P1 = P0.add(v.scale(t1));
//            Point3D P1 =ray.getPoint(t1);
//            intersections.add(new GeoPoint(this,P1));
//
//            return intersections;
//        }
//        if (t2 > 0) {
////            Point3D P2 = P0.add(v.scale(t2));
//            Point3D P2 =ray.getPoint(t2);
//            intersections.add(new GeoPoint(this,P2));
//            return intersections;
//        }
//        return null;
//    }

	 @Override
	    public List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance) {
	        Point3D p0=ray.getP0();
	        Point3D O=center;
	        Vector V=ray.getDir();
	        if (p0.equals(O)){
	            return (List.of(new GeoPoint(this,ray.getPoint(radius))));
	        }
	        Vector U=O.subtract(p0);
	        double tm= V.dotProduct(U);
	        double d=Math.sqrt((U.lengthSquared()-tm*tm));
	        if (d>=radius){ //there is no intersections
	            return null;
	        }
	        double th= Math.sqrt(radius*radius-d*d);
	        double t1=alignZero(tm-th);
	        double t2=alignZero(tm+th);

	        if(t1>0 && t2>0 &&alignZero(t1-maxDistance)<=0&&alignZero(t2-maxDistance)<=0){
	            Point3D p1= ray.getPoint(t1);
	            Point3D p2= ray.getPoint(t2);
	            return List.of(new GeoPoint(this,p1),new GeoPoint(this,p2));
	        }
	        if (t2>0&&alignZero(t2-maxDistance)<=0){
	            Point3D p2= ray.getPoint(t2);
	            return List.of(new GeoPoint(this,p2));
	        }
	        if (t1>0 &&alignZero(t1-maxDistance)<=0){
	            Point3D p1= ray.getPoint(t1);
	            return List.of(new GeoPoint(this,p1));
	        }
	        return null;
	    }

}
   
