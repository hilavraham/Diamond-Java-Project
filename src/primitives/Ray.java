package primitives;

import static primitives.Util.isZero;

import java.util.List;

import geometries.Intersectable.GeoPoint;
/**
 * Class Ray Representation of a beam by point and direction vector
 * @author Hila avraham

 */
public class Ray {
	final Point3D p0;	
	final Vector dir;
	
	public Point3D getP0() {
		return p0;
	}
	
	public Vector getDir() {
		return dir;
	}
	
	 public Point3D getPoint(double delta ){
	        if (isZero(delta)){
	            return  p0;
	        }
	        return p0.add(dir.scale(delta));
	    }
	
	   /**
     * primary constructor for ray
     */
	public Ray(Point3D _p0, Vector _dir) {
		this.p0 = _p0;
		this.dir = _dir.normalized();
	}
	
    public Ray(Point3D _point, Vector _lightDirection, Vector _n, double DELTA) {
    	Vector delta = _n.scale(_n.dotProduct(_lightDirection) > 0 ? DELTA : - DELTA);
		this.p0 =  _point.add(delta);
		this.dir = _lightDirection.normalized();
	
	}


	/**
     * find the closest Point to Ray origin
     * @param pointsList intersections point List
     * @return closest point
     */
	public GeoPoint findClosestGeoPoint (List<GeoPoint> pointsList) {
		GeoPoint result =null;
	        //The greatest distance (this will change)
	        double closestDistance = Double.MAX_VALUE;

	        if(pointsList== null){
	            return null;
	        }

	        for (GeoPoint geoP: pointsList) {
	            double temp = geoP.point.distance(p0);
	            if(temp < closestDistance){
	                closestDistance =temp;
	                result =geoP;
	            }
	        }

	        return  result;
	    }


    /**
     * find the closest Point to Ray origin
     * @param pointsList intersections point List
     * @return closest point
     */
    public Point3D findClosestPoint(List<Point3D> pointsList){
        Point3D result =null;
        //The greatest distance (this will change)
        double closestDistance = Double.MAX_VALUE;

        if(pointsList== null){
            return null;
        }

        for (Point3D p: pointsList) {
            double temp = p.distance(p0);
            if(temp < closestDistance){
                closestDistance =temp;
                result =p;
            }
        }

        return  result;
    }



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		if (dir == null) {
			if (other.dir != null)
				return false;
		} else if (!dir.equals(other.dir))
			return false;
		if (p0 == null) {
			if (other.p0 != null)
				return false;
		} else if (!p0.equals(other.p0))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ray [p0=" + p0 + ", dir=" + dir + "]";
	}


}
