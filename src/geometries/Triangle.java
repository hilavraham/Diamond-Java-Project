package geometries;

import java.util.ArrayList;
import java.util.List;

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
    public ArrayList<Point3D> findIntersections(Ray ray) {
        return super.findIntersections(ray);
    }
    

}