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
    ImageWriter imageWriter = null;
    Scene scene = null;
    Camera camera = null;
    RayTracerBase rayTracerBase = null;

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
        try {
            if (imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }
            if (scene == null) {
                throw new MissingResourceException("missing resource", Scene.class.getName(), "");
            }
            if (camera == null) {
                throw new MissingResourceException("missing resource", Camera.class.getName(), "");
            }
            if (rayTracerBase == null) {
                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
            }

            //rendering the image
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    Ray ray = camera.constructRayThroughPixel(nX, nY, j, i);
                    Color pixelColor = rayTracerBase.traceRay(ray);
                    imageWriter.writePixel(j, i, pixelColor);
                }
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
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