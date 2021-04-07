package primitives;

import static primitives.Util.isZero;
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
