/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import geometries.Plane;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author hilaa
 *
 */
public class TriangleTest {

	 /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Triangle pl = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals( "Bad normal to triangle",
        		new Vector(sqrt3, sqrt3, sqrt3),
                pl.getNormal(new Point3D(0, 0, 1)));
    }
    
    /**
     * Test method for {@link geometries.Polygon#findIntersections(primitives.Point3D)}.
     */
    @Test
    public void testfindIntersectionsRay() {
    	ArrayList<Point3D> answerList = new ArrayList<Point3D>();// creating the expected values
    	ArrayList<Point3D> expected = new ArrayList<Point3D>();// creating the expected values
        Triangle triangle = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        Plane plane = new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        Ray ray;
        // ============ Equivalence Partitions Tests ==============
        // TC01: Inside triangle
        ray = new Ray(new Point3D(1, 1, 1), new Vector(-1, -1, -1));
        expected.add(new Point3D(1d / 3, 1d / 3, 1d / 3));
        answerList=triangle.findIntersections(ray);
        assertEquals( "Bad intersection",expected,answerList);

        // TC02: Against edge
        ray = new Ray(new Point3D(0, 0, -1), new Vector(1, 1, 0));
        expected.clear();
        answerList.clear();
        expected.add(new Point3D(1, 1, -1));
        answerList=plane.findIntersections(ray);
        assertEquals("Wrong intersection with plane",expected,answerList);
        answerList.clear();
        answerList=triangle.findIntersections(ray);
        assertNull("Bad intersection",answerList);

        // TC03: Against vertex
        ray = new Ray(new Point3D(0, 0, 2), new Vector(-1, -1, 0));
        expected.clear();
        //answerList.clear();
        expected.add(new Point3D(-0.5, -0.5, 2));
        answerList=plane.findIntersections(ray);
        assertEquals("Wrong intersection with plane",expected,answerList);
        //answerList.clear();
        answerList=triangle.findIntersections(ray);
        assertNull("Bad intersection",answerList);


        // =============== Boundary Values Tests ==================
        // TC11: In vertex
        ray = new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0));
        expected.clear();
        //answerList.clear();
        expected.add(new Point3D(0, 1, 0));
        answerList=plane.findIntersections(ray);
        assertEquals("Wrong intersection with plane",expected,answerList);
        //answerList.clear();
        answerList=triangle.findIntersections(ray);
        assertNull("Bad intersection",answerList);

        // TC12: On edge
        ray = new Ray(new Point3D(-1, -1, 0), new Vector(1, 1, 0));
        expected.clear();
       // answerList.clear();
        expected.add(new Point3D(0.5, 0.5, 0));
        answerList=plane.findIntersections(ray);
        assertEquals("Wrong intersection with plane",expected,answerList);
        //answerList.clear();
        answerList=triangle.findIntersections(ray);
        assertNull("Bad intersection",answerList);
       
        // TC13: On edge continuation
        ray = new Ray(new Point3D(-2, 0, 0), new Vector(1, 1, 0));
        expected.clear();
        //answerList.clear();
        expected.add(new Point3D(-0.5, 1.5, 0));
        answerList=plane.findIntersections(ray);
        assertEquals("Wrong intersection with plane",expected,answerList);
        //answerList.clear();
        answerList=triangle.findIntersections(ray);
        assertNull("Bad intersection",answerList);

    }

}
