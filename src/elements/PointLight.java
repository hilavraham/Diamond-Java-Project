package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
/**
 * class for point light
 */
public class PointLight extends Light implements LightSource {

    private final Point3D position;
    //Discount coefficients with default values
    //מקדם קבוע
    private double kc = 1;
    //מקדם לינארי
    private double kl = 0;
    //מקדם ריבועי
    private double kq = 0;

    //concatenation setters
    public PointLight setKc(double _kc) {
        kc = _kc;
        return this;
    }

    public PointLight setKl(double _kl) {
        kl = _kl;
        return this;
    }

    public PointLight setKq(double _kq) {
        kq = _kq;
        return this;
    }

    public double getKc() {
        return kc;
    }

    public double getKl() {
        return kl;
    }

    public double getKq() {
        return kq;
    }

    /**
     * constructor for point light
     * @param intensity
     * @param position
     */
    public PointLight(Color intensity, Point3D _position) {
        super(intensity);
        position = _position;
    }

    /**
     * get function
     * @param point3D
     * @return the intensity of the point
     */
    @Override
    public Color getIntensity(Point3D point3D) {
        double d = point3D.distance(position);
        double attenuationFactor = 1d / (kc + kl * d + kq * d * d);
        return intensity.scale(attenuationFactor);
    }

    /**
     *
     * @param point3D
     * @return the vector from the light to the point
     */
    @Override
    public Vector getL(Point3D point3D) {
        return point3D.subtract(position).normalize();
    }
}