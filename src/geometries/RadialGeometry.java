  
package geometries;

public abstract class RadialGeometry extends Geometry{
    final double radius;

    public RadialGeometry(double _radius) {
        radius = _radius;
    }

    public double getRadius() {
        return radius;
    }
}
