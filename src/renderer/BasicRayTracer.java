package renderer;

import elements.LightSource;
import geometries.Intersectable.GeoPoint;

import static geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;

import primitives.*;
import scene.Scene;

import java.util.List;

public class BasicRayTracer extends RayTracerBase {
	private static final double DELTA = 0.1;



    /**
     * BasicRayTracer constructor
     * @param scene
     */
    public BasicRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Look for cuts between the ray and the 3D model of the scene
     * @param ray
     * @return
     */
    @Override
    public  Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        if (closestPoint == null) return scene.backgroundColor;
        return calcColor(closestPoint, ray);
    }

    /**
     * Returns the fill / ambient lighting color of the scene
     * @param closestPoint point
     * @return color
     */
    protected Color calcColor(GeoPoint geoPoint, Ray ray) {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }


    /**
     * calculate the color of the point according to local and global effects
     *
     * @param geoPoint
     * @param ray
     * @param level
     * @param k
     * @return
     */
    protected Color calcColor(GeoPoint geoPoint, Ray ray, int level, double k) {
        Color color = geoPoint.geometry.getEmission();
        color = color.add(calcLocalEffects(geoPoint, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));

    }


	/**
     * @param intersection
     * @param ray
     * @return color of the point
     */
    protected Color calcLocalEffects(GeoPoint geoPoint, Ray ray, double k) {
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;//if the ray direction is orthogonal the normal
        Material material = geoPoint.geometry.getMaterial();
        int nShininess = material.nShininess;
        double kd = material.kD, ks = material.kS;
        Color color = Color.BLACK;
        //run all over the lightSources and calculate the diffusion, specular and shininess.
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(geoPoint.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv) the lightSources is affected on the point
                double ktr = transparency(lightSource, l, n, geoPoint);
                if (ktr * k > MIN_CALC_COLOR_K) {
                	//If the effect of the light is negligible then do not add it
                    Color lightIntensity = lightSource.getIntensity(geoPoint.point).scale(ktr);
                    //add the diffusion and specular and shininess.
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }


    
//    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
//   	Vector lightDirection = l.scale(-1); // from point to light source
//   	Ray lightRay=new Ray(geopoint.point, lightDirection,n,DELTA);
//   	List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
//   	if (intersections == null) return true;
//   	double lightDistance = light.getDistance(geopoint.point);
//   	for (GeoPoint gp : intersections) {
//   	if (alignZero(gp.point.distance(geopoint.point)-lightDistance) <= 0)
//   	return false;
//   	}
//   	return true;
// 
//    	}

    /**
     * calculate the intensity of lightSource on point
     *
     * @param lightSource
     * @param l           light direction
     * @param n           normal of the point
     * @param geoPoint
     * @return the intensity of lightSource on point
     */
    protected double transparency(LightSource lightSource, Vector l, Vector n, GeoPoint geoPoint) {
        Point3D point = geoPoint.point;
        Vector lightDirection = l.scale(-1).normalize();
        Ray lightRay = new Ray(point, lightDirection, n,DELTA);//ray from the point to the ray and moved by delta
        double lightDistance = lightSource.getDistance(point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, lightDistance);
        if (intersections == null) {//if there is no geometries between the point and the light, the transparency is 1
            return 1d;
        }
        double ktr = 1d;
        for (var gp : intersections) {//run over the intersection and calculate the transparency factor
            ktr *= gp.geometry.getMaterial().kt;
            if (ktr < MIN_CALC_COLOR_K) {
                return 0d;
            }
        }
        return ktr;
    }

    /**
     * calculate the specular color of specific light source
     *
     * @param ks             specular factor
     * @param l              vector from the light source to the intersection
     * @param n              normal to the intersection
     * @param v              vector from the camera to the intersection
     * @param nShininess     Shininess factor
     * @param lightIntensity the light color
     * @return the specular color
     */
    protected Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {

        Vector r = l.subtract(n.scale(2 * (l.dotProduct(n)))).normalize();//the reflected vector
        double minusVR = alignZero(v.normalized().scale(-1).dotProduct(r));
        return lightIntensity.scale(ks * Math.max(0, Math.pow(minusVR, nShininess)));
    }

    /**
     * calculate the diffusive color of specific light source
     *
     * @param kd             diffusive factor
     * @param l              vector from the light source to the intersection
     * @param n              normal to the intersection
     * @param lightIntensity the light color
     * @return the diffusive color
     */
    protected Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        return lightIntensity.scale(kd * Math.abs(l.dotProduct(n)));
    }

    /**
     * @param ray
     * @return the closest geoPoint intersection
     */
    protected GeoPoint findClosestIntersection(Ray ray) {
        return ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
    }
    /**
     * calculate the color of point according to refraction and reflection
     * @param geoPoint
     * @param ray
     * @param level depth of recursion
     * @param k intensity of the refraction or reflection
     * @return
     */
    protected Color calcGlobalEffects(GeoPoint geoPoint, Ray ray, int level, double k) {
        Color color = Color.BLACK;
        Material material = geoPoint.geometry.getMaterial();
        double kr = material.kr;
        double kkr = k * kr;
        //If the effect of refraction is negligible then do not add the color
        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay(geoPoint, ray);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null) {
                color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
            }
        }
        double kt = material.kt;
        double kkt = k * kt;
        //If the effect of refraction is negligible then do not add the color
        if (kkt > MIN_CALC_COLOR_K) {
            Ray refractedRay = constructRefractedRay(geoPoint, ray);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint != null) {
                color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
            }
        }
        return color;
    }
    /**
     * calculate the Refracted Ray to the point
     * @param geoPoint
     * @param ray
     * @return
     */
    protected Ray constructRefractedRay(GeoPoint geoPoint, Ray ray) {
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        return new Ray(geoPoint.point, ray.getDir(), n,DELTA);
    }

    /**
     * calculate the Reflected Ray to the point
     * @param geoPoint
     * @param ray
     * @return
     */
    protected Ray constructReflectedRay(GeoPoint geoPoint, Ray ray) {
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Vector v = ray.getDir();
        //if v is orthogonal to n the  reflected ray direction is in the same  direction of v
        if (alignZero(n.dotProduct(v)) == 0) {
            return new Ray(geoPoint.point, v, n,DELTA);
        }
        Vector r = v.subtract(n.scale(2 * v.dotProduct(n)));//the reflected ray
        return new Ray(geoPoint.point, r, n,DELTA);
    }
}