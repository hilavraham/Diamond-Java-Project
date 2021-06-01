/**
 * 
 */
package unittest.geometries;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import geometries.Plane;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;


/**
 * @author hilaa
 *
 */
public class PlaneTest {

	   /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormalPoint3D() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane pl = new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals( "Bad normal to plane",new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
    }
    /**
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testfindIntersectionsRay() {
        Plane Plane = new Plane(new Point3D(0, 0, 1), new Vector(1, 1, 1));
        // creating the expected values
		List<Point3D> answerList = new ArrayList<Point3D>();
    	ArrayList<Point3D> expected = new ArrayList<Point3D>();// creating the expected values
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray into plane
     	answerList= Plane.findIntersections(new Ray(new Point3D(0.5, 0, 0), new Vector(1, 0, 0)));
    	expected.add(new Point3D(1, 0, 0));
        assertEquals("Bad plane intersection",answerList,expected);

        // TC02: Ray out of plane (0 point)
        answerList.clear();
        answerList=Plane.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 0, 0)));
        assertNull("Must not be plane intersection",answerList);

        // =============== Boundary Values Tests ==================
        // TC11: Ray parallel to plane(0 point)
        //answerList.clear();
        answerList=Plane.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(0, 1, -1)));
        assertNull("Must not be plane intersection",answerList);

        // TC12: Ray in plane
        //answerList.clear();
        answerList=Plane.findIntersections(new Ray(new Point3D(0, 0.5, .5), new Vector(0, 1, -1)));
        assertNull("Must not be plane intersection",answerList);

        // TC13: Orthogonal ray into plane (1 point)
        //answerList.clear();
        answerList=Plane.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(-1, -1, -1)));
        assertEquals("Bad plane intersection",new Point3D(1d / 3, 1d / 3, 1d / 3),answerList.get(0));

        // TC14: Orthogonal ray out of plane (0 point)
        //answerList.clear();
        answerList=Plane.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(1,1,1)));
        assertNull("Must not be plane intersection",answerList);

        // TC15: Orthogonal ray out of plane (0 point)
        //answerList.clear();
        answerList=Plane.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(1, 1, 1)));
        assertNull("Must not be plane intersection",answerList);

        // TC16: Orthogonal ray from plane (0 point)
        //answerList.clear();
        answerList=Plane.findIntersections(new Ray(new Point3D(0, 0.5, 0.5), new Vector(1, 1, 1)));
        assertNull("Must not be plane intersection",answerList);


        // TC17: Ray from plane(0 point)
        //answerList.clear();
        answerList=Plane.findIntersections(new Ray(new Point3D(0, 0.5, 0.5), new Vector(1, 1, 0)));
        assertNull("Must not be plane intersection",answerList);

        // TC18: Ray from plane's Q point(0 point)
        //answerList.clear();
        answerList=Plane.findIntersections(new Ray(new Point3D(0, 0, 1), new Vector(1, 1, 0)));

    }
}
