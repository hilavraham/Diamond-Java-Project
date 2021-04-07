package geometries;

import primitives.*;



public class Triangle extends Polygon {  //implements Geometry

    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }
    
    @Override
	public Vector getNormal(Point3D point) {
		return plane.getNormal();
	}

}