/**
 * 
 */
package unittest.geometries;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import geometries.Sphere;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author hilaa
 *
 */
public class SphereTest {

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Sphere sph = new Sphere( new Point3D(0, 0, 1),1.0);
        assertEquals( "Bad normal to sphere",new Vector(0, 0, 1), sph.getNormal(new Point3D(0, 0, 2)));
    }
    /**
    
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */

	@Test
	  public void testFindIntersectionsRay() {
		
        Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);// building the spheres
     // creating the expected values
		List<Point3D> answerList1 = new ArrayList<Point3D>();
		
        // ============ Equivalence Partitions Tests ==============
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
    	ArrayList<Point3D> expected = new ArrayList<Point3D>();// creating the expected values
        expected.add (p1);
        expected.add(p2);
        // TC01: Ray's line is outside the sphere (0 points)
        Ray ray1=new Ray(new Point3D(-1, 0, 0),new Vector(1, 1, 0));// building the spheres
        assertNull( "Ray's line out of sphere",
        		sphere.findIntersections(ray1));
     // TC02: Ray starts before and crosses the sphere (2 points)
        Ray ray2=new Ray(new Point3D(-1, 0, 0),new Vector(3, 1, 0));// building the spheres
        answerList1 = sphere.findIntersections(ray2);
        assertEquals("Wrong number of points",2, answerList1.size());
        Coordinate x1 =answerList1.get(0).getX();
        Coordinate x2 =answerList1.get(1).getX();
        if (x1.coord > x2.coord)
        assertEquals("Ray crosses sphere",expected, answerList1);
     // TC03: Ray starts inside the sphere (1 point)
        assertEquals( "Ray from inside sphere",p2,
                sphere.findIntersections(
                        new Ray(new Point3D(0.5, 0.5, 0),
                                new Vector(3, 1, 0))).get(0));
        // TC04: Ray starts after the sphere (0 points)
        assertNull(  "Sphere behind Ray",
                sphere.findIntersections(new Ray(new Point3D(2, 1, 0), new Vector(3, 1, 0))));
        
        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        assertEquals( "Ray inside of sphere ",new Point3D(2,0,0),
               sphere.findIntersections(new Ray(new Point3D(1, -1, 0), new Vector(1, 1, 0))).get(0));
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull("Ray outside of sphere ",
                sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 1, 0))));

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        answerList1 = sphere.findIntersections(new Ray(new Point3D(1, -2, 0), new Vector(0, 1, 0)));
        assertEquals( "Wrong number of points",2, answerList1.size());
        Coordinate y1 =answerList1.get(0).getY();
        Coordinate y2 =answerList1.get(1).getY();
        Point3D temp1=new Point3D( answerList1.get(0));
        Point3D temp2=new Point3D( answerList1.get(1));
        if (y1.coord > y2.coord){
        	answerList1.clear();
        	answerList1.add(0,temp2);
        	answerList1.add(1,temp1);}
        expected.clear();
        expected.add(new Point3D(1, -1, 0));
        expected.add(new Point3D(1, 1, 0));
        assertEquals("Line through O, ray crosses sphere",
        		expected,answerList1);
        // TC14: Ray starts at sphere and goes inside (1 points)
        assertEquals("Line through O, ray from and crosses sphere",new Point3D(1, 1, 0),
                sphere.findIntersections(new Ray(new Point3D(1, -1, 0), new Vector(0, 1, 0))).get(0));
        // TC15: Ray starts inside (1 points)
        assertEquals("Line through O, ray from inside sphere",new Point3D(1, 1, 0),
                sphere.findIntersections(new Ray(new Point3D(1, 0.5, 0), new Vector(0, 1, 0))).get(0));
        // TC16: Ray starts at the center (1 points)
        assertEquals("Line through O, ray from O",new Point3D(1, 1, 0),
        		sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 0))).get(0));
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull("Line through O, ray from sphere outside",
        		sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(0, 1, 0))));
        // TC18: Ray starts after sphere (0 points)
        assertNull("Line through O, ray outside sphere",
        		sphere.findIntersections(new Ray(new Point3D(1, 2, 0), new Vector(0, 1, 0))));

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull( "Tangent line, ray before sphere",
        		sphere.findIntersections(new Ray(new Point3D(0, 1, 0), new Vector(1, 0, 0))));
        // TC20: Ray starts at the tangent point
        assertNull( "Tangent line, ray at sphere",
        		sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 0, 0))));
        // TC21: Ray starts after the tangent point
        assertNull("Tangent line, ray after sphere",
        		sphere.findIntersections(new Ray(new Point3D(2, 1, 0), new Vector(1, 0, 0))));

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's
        // center line
        assertNull("Ray orthogonal to ray head -> O line",
        		sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(0, 0, 1))));
	}
    
}

