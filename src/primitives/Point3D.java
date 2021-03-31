package primitives;
/**
 * Class Point3D is the basic class representing a point in space 
 * @author Hila avraham

 */
public class Point3D {

	final Coordinate x;
	final Coordinate y;
	final Coordinate z;
	
	 //static Point3D for point (0,0,0)
	public final static Point3D ZERO = new Point3D(0, 0, 0);
	

    /**
     * primary constructor for Point3D
     * @param _x coordinate value for x 
     * @param _y coordinate value for y 
     * @param _z coordinate value for z 
     */
	public Point3D(double _x, double _y, double _z) {
		super();
		this.x = new Coordinate(_x);
		this.y = new Coordinate(_y);
		this.z = new Coordinate(_z);
	}
	
	   /**
     * Subtraction between points
     * @param point3d A point that represents the beginning of the vector 
     * and its copy to the beginning of the axes 
     */
	public Vector subtract(Point3D point3d) {
		double _x =x.coord- point3d.x.coord;
		double _y =y.coord- point3d.y.coord;
		double _z =z.coord- point3d.z.coord;
		
		return new Vector(_x, _y,_z);
	}
	
	   /**
     * Add between points
     * @param vector Add vector
     */
	public Point3D add(Vector vector) {
		double _x =x.coord+ vector.head.x.coord;
		double _y =y.coord+ vector.head.y.coord;
		double _z =z.coord+ vector.head.z.coord;
		
		return new Point3D(_x, _y,_z);
	}
	
	   /**
  * The distance between the vectors (power of 2) - an algebraic form of calculation
  * @param point3d A point that represents the beginning of the vector
  */
	public double distanceSquared(Point3D other) {
		double xx = (other.x.coord-x.coord)*(other.x.coord-x.coord);
		double yy = (other.y.coord-y.coord)*(other.y.coord-y.coord);
		double zz = (other.z.coord-z.coord)*(other.z.coord-z.coord);
		
		return (xx+yy+zz);
	}

	   /**
* The distance between the vectors - an algebraic form of calculation
* @param point3d A point that represents the beginning of the vector
*/
	public double distance(Point3D other) {
		
		return Math.sqrt(distanceSquared(other));
	}


	   /**
* Comparison of vectors
* @Override
* @param obj Vector for comparison 
*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point3D other = (Point3D) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		if (z == null) {
			if (other.z != null)
				return false;
		} else if (!z.equals(other.z))
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return "(" + x + "," + y + ","+ z + ")";
	}


}
