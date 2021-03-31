package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Tube implements Geometry {
    final Ray axysRay;
    final double radius;

    public Tube(Ray _axysRay, double _radius) {
        axysRay = _axysRay;
        radius = _radius;
    }

    @Override
    public Vector getNormal(Point3D p) {
      
            return null;
        }
}
