/**
 * 
 */
package geometries;

import primitives.Ray;
import primitives.Point3D;
import java.util.List;
import java.util.stream.Collectors;

import jdk.dynalink.beans.StaticClass;

/**
 * @author hilaa
 *
 */
public interface Intersectable {
	public static class  GeoPoint{
		public GeoPoint(Geometry _geometry, Point3D _point3d) {
			this.geometry=_geometry;
			this.point=_point3d;
		}
		public Geometry geometry;
		public Point3D point;
	}
	
	
	default List<Point3D> findIntersections(Ray ray) {
		List<GeoPoint> geoList = findGeoIntersections(ray);
		return geoList == null ? null
		: geoList .stream()
		.map(gp -> gp.point)
		.collect(Collectors.toList());
	}
	  public List<GeoPoint> findGeoIntersections(Ray ray);

}
