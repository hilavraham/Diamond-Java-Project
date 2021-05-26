/**
 * 
 */
package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * @author hilaa
 *
 */
public abstract class RayTracerBase {

    protected Scene scene;

    public RayTracerBase(Scene _scene) {
        scene = _scene;
    }

    public abstract Color traceRay(Ray ray);

}
