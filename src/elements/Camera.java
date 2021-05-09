package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;
/**
 * @author hilaa
 *
 */
public class Camera {
    final Point3D p0;
    final Vector vTo;
    final Vector vUp;
    final Vector vRight;
    private double distance = 1d;
    private double width = 1d;
    private double height = 1d;

  //************************Constructors*************************//
    public Camera(Point3D _p0, Vector _vTo, Vector _vUp) {
    	 if (!isZero(_vTo.dotProduct(_vUp))) {
             throw new IllegalArgumentException(" vUp is not orthogonal to vTo");
         }
        p0 = _p0;
        vTo = _vTo.normalized();
        vUp = _vUp.normalized();

        vRight = vTo.crossProduct(vUp);
    }
  //************************Getters*************************//
	
    /**
	 * @return the p0
	 */
	public Point3D getP0() {
		return p0;
	}
	/**
	 * @return the vTo
	 */
	public Vector getvTo() {
		return vTo;
	}
	/**
	 * @return the vUp
	 */
	public Vector getvUp() {
		return vUp;
	}
	/**
	 * @return the vRight
	 */
	public Vector getvRight() {
		return vRight;
	}
	

	//************************Setters*************************//
	
	//Camera setter chaining methods
    public Camera setVpSize(double _width, double _height) {

        width = _width;
        height = _height;
        return this;
    }

    public Camera setVpDistance(double _distance) {
        distance = _distance;
        return this;

    }

    //************************Operations*****************************//
	 /***
	  *  @return constructing a ray passing through pixel(i,j) of the view plane 
	  * */
    
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        Point3D Pc = p0.add(vTo.scale(distance));

        double Rx = width / nX;
        double Ry = height / nY;

        Point3D Pij = Pc;

        double Xj = (j - (nX - 1) / 2d) * Rx;
        double Yi = -(i - (nY - 1) / 2d) * Ry;


        if (isZero(Xj) && isZero(Yi)) {

            return new Ray(p0, Pij.subtract(p0));
        }

        if (isZero(Xj)) {
            Pij = Pij.add(vUp.scale(Yi));
            return new Ray(p0, Pij.subtract(p0));

        }
        if (isZero(Yi)) {
            Pij = Pc.add(vRight.scale(Xj));
            return new Ray(p0, Pij.subtract(p0));


        }

        Pij = Pc.add(vRight.scale(Xj).add(vUp.scale(Yi)));
        Vector Vij = Pij.subtract(p0);

        return new Ray(p0, Vij);

    }

}
