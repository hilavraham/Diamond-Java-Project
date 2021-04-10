package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;
/**
 *Cylinder class represents Cylinder in 3D Cartesian coordinate system
 * by height and two Plane
 * @author hila
 */
public class Cylinder extends Tube {   // implements Geometry
    final double height;
    final Plane base1;
    final Plane base2;
/**
 * primary constructor 
 * */
    public Cylinder(double _radius,Ray _axisRay, double _height) {
        super(_axisRay, _radius);
        height = _height;
        Vector v = axysRay.getDir();
        base1 = new Plane(axysRay.getP0(),v);
        base2= new Plane(axysRay.getPoint(_height),v);
    }

    
    @Override
public String toString() {
	return "Cylinder [height=" + height + ", base1=" + base1 + ", base2=" + base2 + "]";
}


	/**
     *  Calculation of the normal
     * */
    @Override
    public Vector getNormal(Point3D p) {
        Point3D o = axysRay.getP0();
        Vector v = axysRay.getDir();

        // projection of P-O on the ray:
        double t;
        try {
            t = alignZero(p.subtract(o).dotProduct(v));
        } catch (IllegalArgumentException e) { // P = O
            return v;
        }

        // if the point is at a base
        if (t == 0 || isZero(height - t)) // if it's close to 0, we'll get ZERO vector exception
            return v;

        o = o.add(v.scale(t));
        return p.subtract(o).normalize();
    }

}