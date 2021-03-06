package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Circle class represents Euclidean 2D circle in 3D Cartesian coordinate system
 * represented by its center, radius and orthogonal unit vector
 * 
 * @author Dan
 */
public class Circle extends RadialGeometry  {
    private final Point3D _center;
    private final Plane _plane;

    /**
     * Circle constructor given its center, radius and orthogonal unit vector
     * (normal)
     * 
     * @param center      center point
     * @param radius
     * @param n      orthogonal vector (will be normalized)
     */
    public Circle( double radius,Point3D center, Vector n) {
        super(radius);
        _center = center;
        _plane = new Plane(center, n);
    }

    /**
     * Center getter for the circle
     * @return the center point
     */
    public Point3D getCenter() {
    	return _center;
    }
    
    @Override
    public Vector getNormal(Point3D p) {
        return _plane.getNormal(null);
    }
    


	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> intersections = _plane.findGeoIntersections(ray);
        if (intersections == null)
        	return null;

        double pToEdge = alignZero(radius - _center.distance(new Point3D(	intersections.get(0))));

        if (pToEdge <=0 ) 
        	return null;
        return intersections;
	}

	@Override
	public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}
}
