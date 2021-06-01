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
		
		public Geometry geometry;
		public Point3D point;
		public GeoPoint(Geometry _geometry, Point3D _point3d) {
			this.geometry=_geometry;
			this.point=_point3d;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			GeoPoint other = (GeoPoint) obj;
			if (geometry == null) {
				if (other.geometry != null)
					return false;
			} else if (!geometry.equals(other.geometry))
				return false;
			if (point == null) {
				if (other.point != null)
					return false;
			} else if (!point.equals(other.point))
				return false;
			return true;
		}
		
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
