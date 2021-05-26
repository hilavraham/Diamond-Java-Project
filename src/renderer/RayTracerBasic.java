/**
 * 
 */
package renderer;

import java.util.List;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

/**
 * @author hilaa
 *
 */

public class RayTracerBasic extends RayTracerBase {

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<Point3D> intersections = scene.geometries.findIntersections(ray);
        if (intersections != null) {
            Point3D closestPoint = ray.findClosestPoint(intersections);
            return calcColor(closestPoint);
        }
        //ray did not intersect any geometrical object
        return scene.background;
    }

    private Color calcColor(Point3D point) {
        return scene.ambientlight.getIntensity();
    }
}

