package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface for all the geometries that have a normal from them
 */
public abstract class Geometry implements Intersectable{
	
	protected Color emission=Color.BLACK;   
	
    /**
	 * @return the emission
	 */
	public Color getEmmission() {
		return emission;
	}


	/**
	 * @param emmission the emission to set
	 */
	public Geometry setEmission(Color _emission) {
		this.emission = _emission;
		return this;
	}


	/**
     *
     * @param point should be null for flat geometries
     * @return the normal to the geometry
     */
	abstract  Vector getNormal(Point3D point);
}