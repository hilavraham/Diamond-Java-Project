/**
 * 
 */
package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * this is an abstract class of rayTracer
 */
public abstract class RayTracerBase {
    /**
     * const for the first time in the calColor recursion
     */
    protected static final double INITIAL_K = 1.0;
    /**
     *  how much times  calColor recursion will run
     */
    protected static final int MAX_CALC_COLOR_LEVEL = 10;
    /**
     *the minimum attenuation factor that we wil calculate the color
     */
    protected static final double MIN_CALC_COLOR_K = 0.001;
    protected Scene scene;

    public RayTracerBase(Scene _scene) {
        scene = _scene;
    }

    public abstract Color traceRay(Ray ray);

}
