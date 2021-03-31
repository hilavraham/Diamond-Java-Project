package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane {

	  final Point3D p0;
	  final Vector normal;
	  
	  public Plane(Point3D _p0, Vector _normal) {
	        p0 = _p0;
	        normal = _normal.normalized();
	    }
	  
	  public Plane(Point3D p1, Point3D p2, Point3D p3) {
		  p0=p1;
		  
		  Vector U = p2.subtract(p1);
		  Vector V = p3.subtract(p1);
		  
		  Vector N = U.crossProduct(V);

		  normal =N.normalize();
		  
	}

	public Vector getNormal() {
		// TODO Auto-generated method stub
		return null;
	}

}
