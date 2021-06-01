/**
 * 
 */
package renderer;

import java.util.MissingResourceException;

import elements.Camera;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * @author hilaa
 *
 */
public class Render {
    ImageWriter imageWriter;
    Scene scene ;
    Camera camera ;
    RayTracerBase rayTracerBase;

    public Render setImageWriter(ImageWriter _imageWriter) {
        imageWriter = _imageWriter;
        return this;
    }

    public Render setScene(Scene _scene) {
        scene = _scene;
        return this;
    }

    public Render setCamera(Camera _camera) {
        camera = _camera;
        return this;
    }

    public Render setRayTracer(RayTracerBase _rayTracer) {
        rayTracerBase = _rayTracer;
        return this;
    }


    public void renderImage() {
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        // loop on viewPlane pixels
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                //For each pixel will be built a ray and for each ray we will get a color from the horn comb. Does the color women in the appropriate pixel of the image maker
                Ray ray = camera.constructRayThroughPixel(nX, nY, j, i);
                Color color = rayTracerBase.traceRay(ray);
                imageWriter.writePixel(j, i, color);
            }
        }
    }
    
    public void printGrid(int interval, Color color) {
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
    }

    public void writeToImage() {
        imageWriter.writeToImage();
    }
}