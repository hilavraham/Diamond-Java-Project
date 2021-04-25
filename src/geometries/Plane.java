package geometries;

import java.util.ArrayList;


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
public class Plane implements Geometry {

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
			public ArrayList<Point3D> findIntersections(Ray ray) {
			  ArrayList<Point3D> intersections = new ArrayList<Point3D>();
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
		        intersections.add(point);
		        return intersections;
		    }

	}




