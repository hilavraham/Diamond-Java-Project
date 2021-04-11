package geometries;

import java.util.List;

import primitives.*;

/**
 *Triangle class represents Triangle in 3D Cartesian coordinate system
 * 
 * @author hila
 */

public class Triangle extends Polygon {  //implements Geometry

	  /**
	   * primary constructor 
	   * */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }
    
    /**
     *  Calculation of the normal
     * */
    @Override
	public Vector getNormal(Point3D point) {
		return plane.getNormal();
	}
    
    @Override
	public List<Point3D> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
    

}