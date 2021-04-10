package geometries;

import primitives.Point3D;
import primitives.Vector;

public interface Geometry {

    /*
    * @return the normal to the geometry
    */
   Vector getNormal(Point3D point);

}
