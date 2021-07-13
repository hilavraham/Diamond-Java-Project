package geometries;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
/**
 *Plane class represents Plane in 3D Cartesian coordinate system
 * by 3D Cartesian coordinate and normal
 * @author hila
 */
public class Plane extends Geometry {

	  final Point3D p0;
	  final Vector normal;
	  
	  /**
	   * primary constructor 
	   * */
	  public Plane(Point3D _p0, Vector _normal) {
	        p0 = _p0;
	        normal = _normal.normalized();
	    }
	  
	  /**
	     * Constructor of Plane from 3 points on its surface
	     * the points are ordered from right to left
	     * forming an arc in right direction
	     * @param p1
	     * @param p2
	     * @param p3
	     */
	  public Plane(Point3D p1, Point3D p2, Point3D p3) {
		  p0=p1;
		  
		  Vector U = p2.subtract(p1);
		  Vector V = p3.subtract(p1);
		  
		  Vector N = U.crossProduct(V);

		  normal =N.normalize();
		  
	}


	    @Override
	public String toString() {
		return "Plane [p0=" + p0 + ", normal=" + normal + "]";
	}

		public Vector getNormal() {
	        return normal;
	    }
		
		
		  public Vector getNormal(Point3D point) {
		        return normal;
		    }
		 
		@Override
		public List<GeoPoint> findGeoIntersections(Ray ray) {
			  ArrayList<GeoPoint> intersections = new ArrayList<GeoPoint>();
			  Point3D P0ray = ray.getP0();
		        Vector v = ray.getDir();

		        Vector n = normal;

		        if(p0.equals(P0ray)){
		            return  null;
		        }

		        Vector P0_Q0 =p0.subtract(P0ray);

		       //numerator
		        double nP0Q0  = alignZero(n.dotProduct(P0_Q0));

		        //
		        if (isZero(nP0Q0 )){
		            return null;
		        }

		        //denominator
		        double nv = alignZero(n.dotProduct(v));

		        // ray is lying in the plane axis
		        if(isZero(nv)){
		            return null;
		        }

		        double  t = alignZero(nP0Q0  / nv);

		        if (t <=0){
		            return  null;
		        }

		        Point3D point = ray.getPoint(t);
		        intersections.add(new GeoPoint(this, point));
		        return intersections;
		    }

		@Override
		 public List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance) {
	        if (p0.equals(ray.getP0()))//if the start of the ray equals to the plane point there is no intersections
	            return null;
	        Vector V=p0.subtract(ray.getP0());
	        double denominator=alignZero(normal.dotProduct(ray.getDir()));
	        if (denominator==0)//the ray is parallel to the plane
	            return null;
	        double t=alignZero(normal.dotProduct(V)/denominator);
	        if (t>0 && alignZero (t-maxDistance ) <= 0 )
	            return List.of(new GeoPoint(this,ray.getPoint(t)));

	        return null;
	    }
		

	}
