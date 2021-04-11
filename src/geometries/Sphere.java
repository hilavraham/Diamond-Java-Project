package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

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
	public List<Point3D> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
    }
