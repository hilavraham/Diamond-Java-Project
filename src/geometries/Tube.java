package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 *Triangle class represents Triangle in 3D Cartesian coordinate system
 * by ray and radius
 * @author hila
 */

public class Tube implements Geometry {
    final Ray axysRay;
    final double radius;


	  /**
	   * primary constructor 
	   * */
    
    public Tube(Ray _axysRay, double _radius) {
        axysRay = _axysRay;
        radius = _radius;
    }

    public Ray getAxysRay() {
		return axysRay;
	}

	public double getRadius() {
		return radius;
	}

	@Override
	public String toString() {
		return "Tube [axysRay=" + axysRay + ", radius=" + radius + "]";
	}

    /**
     *  Calculation of the normal
     * */
	 @Override
	    public Vector getNormal(Point3D p) {
	    
	        Point3D P0 = axysRay.getP0();
	        Vector v = axysRay.getDir();

	        Vector P0_P = p.subtract(P0);

	        double t = alignZero(v.dotProduct(P0_P));

	        if (isZero(t)) {
	            return P0_P.normalize();
	        }

	        Point3D o = P0.add(v.scale(t));

	        if (p.equals(o)) {
	            throw new IllegalArgumentException("point cannot be on the tube axis");
	        }

	        Vector n = p.subtract(o).normalize();

	        return n;
	    }
}
