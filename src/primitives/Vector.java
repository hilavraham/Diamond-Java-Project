package primitives;
/**
 * Basic class for representing a vector at an endpoint with respect to the beginning of the axes
 * @author Hila avraham

 */
public class Vector {
	 Point3D head;


		public Point3D getHead() {
			return head;
		}

		  /**
	     * primary constructor for vector
	     * @param _head value for head 
	     */
	public Vector(Point3D _head) {
		if (Point3D.ZERO.equals(_head)) {
			throw new IllegalArgumentException("head of vector cannot be zero ");
		}
		this.head = _head;
	}
	
	  /**
     * primary constructor for vector
     * @param  values for point head 
     */
	public Vector(double _x, double _y, double _z) {
		this(new Point3D(_x, _y, _z)); 
	}



	  /**
     *Connection of vectors
     * @param  values for point head 
     */
	public Vector add(Vector vector) {
		double _x =head.x.coord+ vector.head.x.coord;
		double _y =head.y.coord+ vector.head.y.coord;
		double _z =head.z.coord+ vector.head.z.coord;
		
		return new Vector(_x, _y,_z);
	}
	
	  /** Subtraction between vector
	     */
	public Vector subtract(Vector vector) {
		if (vector.equals(this)){
			throw new IllegalArgumentException("param vector cannot be equals ");
		}
		double _x =head.x.coord- vector.head.x.coord;
		double _y =head.y.coord- vector.head.y.coord;
		double _z =head.z.coord- vector.head.z.coord;
		
		return new Vector(_x, _y,_z);
	}

	 /**Vector multiplication in scalar
     * @param d values for Scalar
     */
	public Vector scale(double d) {
		double _x =head.x.coord*d;
		double _y =head.y.coord*d;
		double _z =head.z.coord*d;
	
		return new Vector(_x, _y,_z);
	}
	
	 /**Scalar product
     * @param d values for Scalar
     */
	public double dotProduct(Vector v) {
        double u1 = head.x.coord;
        double u2 = head.y.coord;
        double u3 = head.z.coord;

        double v1 = v.head.x.coord;
        double v2 = v.head.y.coord;
        double v3 = v.head.z.coord;

        return (u1 * v1 + u2 * v2 + u3 * v3);

    }
	
	 /**
     * Vector product
     */
	public Vector crossProduct(Vector v) {
        double u1 = head.x.coord;
        double u2 = head.y.coord;
        double u3 = head.z.coord;
        double v1 = v.head.x.coord;
        double v2 = v.head.y.coord;
        double v3 = v.head.z.coord;

        return new Vector(new Point3D(
                u2 * v3 - u3 * v2,
                u3 * v1 - u1 * v3,
                u1 * v2 - u2 * v1
        ));
    }

	/**
	 * Calculate the length of the vector squared
	 */
	  public double lengthSquared() {
	        double u1 = head.x.coord;
	        double u2 = head.y.coord;
	        double u3 = head.z.coord;

	        return u1 * u1 + u2 * u2 + u3 * u3;
	    }
	  
	  /**
		 * Calculate the length of the vector
		 * @return length of the vector
		 */
	  public double length() {
	        return Math.sqrt(lengthSquared());
	    }

 /**
  * Vector normalization operation Converting its length to 1*
  * @return Vector normal
  */
	  public Vector normalized() {
	        Vector result = new Vector(head);
	        result.normalize();
	        return result;
	    }

	  /**
	   * Vector normalization operation Converting its length to 1*
	   * @return Vector normal
	   */
	  public Vector normalize() {
	        double length = this.length();

	        //cannot divide by 0
	        if (length == 0)
	            throw new ArithmeticException("divide by Zero");

	        double x = this.head.x.coord;
	        double y = this.head.y.coord;
	        double z = this.head.z.coord;

	        this.head = new Point3D(x / length, y / length, z / length);

	        return this;
	    }
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (head == null) {
			if (other.head != null)
				return false;
		} else if (!head.equals(other.head))
			return false;
		return true;
	}
	
	 @Override
	    public String toString() {
	        return "{" + head + '}';
	    }
}
