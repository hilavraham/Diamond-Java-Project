package geometries;

import java.util.List;

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
			public List<Point3D> findIntersections(Ray ray) {
				// TODO Auto-generated method stub
				return null;
			}
	}




