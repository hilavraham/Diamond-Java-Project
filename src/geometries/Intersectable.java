/**
 * 
 */
package geometries;

import primitives.Ray;
import primitives.Point3D;
import java.util.List;
import java.util.stream.Collectors;

//import jdk.dynalink.beans.StaticClass;

/**
 * @author hilaa
 *
 */

public interface Intersectable {
    /**
     * class GeoPoint internal help class
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        /**
         * A constructor that receives two parameters undergoes a boot of both fields
         *
         * @param geometry the geometry
         * @param point    the point in the geometry
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        /**
         * Check that the Holocaust is performed on the same geometric body and the points are the same
         *
         * @param o With him compare
         * @return if they equal or not
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }
    }

    /**
     *
     * @param ray
     * @return list of geoPoints
     */
    default List<Point3D> findIntersections(Ray ray) {
        List<GeoPoint> geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream()
                .map(gp -> gp.point)
                .collect(Collectors.toList());
    }
    List<GeoPoint> findGeoIntersections(Ray ray);

//    default List<GeoPoint> findGeoIntersections(Ray ray){
//    	return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
//    }
//    
//    //List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance);
}
