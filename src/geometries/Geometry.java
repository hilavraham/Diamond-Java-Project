package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * interface for all the geometries that have a normal from them
 */
public abstract class Geometry implements Intersectable{
    /**
     *
     * @param point should be null for flat geometries
     * @return the normal to the geometry
     */
	abstract  Vector getNormal(Point3D point);
}