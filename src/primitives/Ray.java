package primitives;

public class Ray {
	final Point3D p0;	
	final Vector dir;
	
	public Point3D getP0() {
		return p0;
	}
	
	public Vector getDir() {
		return dir;
	}
	
	public Ray(Point3D p0, Vector dir) {
		super();
		this.p0 = p0;
		this.dir = dir.normalized();
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
