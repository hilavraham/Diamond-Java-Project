package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class for direction light
 */
public class DirectionalLight extends Light implements  LightSource {

	/**
     * direction light 
     */
    private final Vector direction;

    /**
     * constructor for Directional Light
     * @param intensity light color
     * @param direction common direction of the Light
     */
    public DirectionalLight(Color intensity, Vector _direction) {
        super(intensity);
        direction = _direction.normalized();
    }

    /**
     *
     * @param point3D
     * @return the intensity of the color in the point3D
     */
    @Override
    public Color getIntensity(Point3D point3D) {
        return intensity;
    }

    /**
     * Lighting direction value
     * @param point3D
     * @return the vector from the light to the point
     */
    @Override
    public Vector getL(Point3D point3D) {
        return direction;
    }
}
