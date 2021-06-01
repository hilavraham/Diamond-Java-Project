/**
 * 
 */
package unittest.geometries;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import geometries.Tube;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author hilaa
 *
 */
public class TubeTest {


    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
     */
   
	@Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Tube tube = new Tube( new Ray(new Point3D(0, 0, 1), new Vector(0, -1, 0)),1.0);

        Vector normal = tube.getNormal(new Point3D(0, 0.5, 2)).normalize();

        double dotProduct = normal.dotProduct(tube.getAxysRay().getDir());
        assertEquals( "normal is not orthogonal to the tube",0,0d, dotProduct);

        boolean firstnormal = new Vector(0, 0, 1).equals(normal);
        boolean secondtnormal = new Vector(0, 0, -1).equals(normal);

        assertTrue( "Bad normal to tube",firstnormal || secondtnormal);

        assertEquals("Bad normal to tube",new Vector(0, 0, 1), normal);
    }
	 /**
     * Test method for {@link geometries.Tube#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersectionsRay() {
        Tube tube1 = new Tube(new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 0)), 1d);
        Vector vAxis = new Vector(0, 0, 1);
        Tube tube2 = new Tube(new Ray(new Point3D(1, 1, 1), vAxis), 1d);
        Ray ray;
        
        // creating the expected values
		List<Point3D> answerList = new ArrayList<Point3D>();
    	ArrayList<Point3D> expected = new ArrayList<Point3D>();// creating the expected values
    	
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the tube (0 points)
        ray = new Ray(new Point3D(1, 1, 2), new Vector(1, 1, 0));
        answerList=tube1.findIntersections(ray);
        assertNull( "Must not be intersections",answerList);

        // TC02: Ray's crosses the tube (2 points)
        ray = new Ray(new Point3D(0, 0, 0), new Vector(2, 1, 1));
        //answerList.clear();
        answerList = tube2.findIntersections(ray);
        expected.add(new Point3D(0.4, 0.2, 0.2));
        expected.add(new Point3D(2, 1, 1));
        
       
        assertEquals( "must be 2 intersections",2, answerList.size());
        Coordinate y1 =answerList.get(0).getY();
        Coordinate y2 =answerList.get(1).getY();
        Point3D temp1=new Point3D( answerList.get(0));
        Point3D temp2=new Point3D( answerList.get(1));
        if (y1.coord > y2.coord){
        	answerList.clear();
        	answerList.add(0,temp2);
        	answerList.add(1,temp1);}
        assertEquals( "Bad intersections",expected,answerList);

        // TC03: Ray's starts within tube and crosses the tube (1 point)
        ray = new Ray(new Point3D(1, 0.5, 0.5), new Vector(2, 1, 1));
        answerList.clear();
        answerList = tube2.findIntersections(ray);
        expected.clear();
       expected.add(new Point3D(2, 1, 1));
        assertEquals("Bad intersections",expected,answerList);

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line is parallel to the axis (0 points)
        // TC11: Ray is inside the tube (0 points)
        ray = new Ray(new Point3D(0.5, 0.5, 0.5), vAxis);
        assertNull("must not be intersections",tube2.findIntersections(ray));
        // TC12: Ray is outside the tube
        ray = new Ray(new Point3D(0.5, -0.5, 0.5), vAxis);
        assertNull("must not be intersections",tube2.findIntersections(ray));
        // TC13: Ray is at the tube surface
        ray = new Ray(new Point3D(2, 1, 0.5), vAxis);
        assertNull("must not be intersections",tube2.findIntersections(ray));
        // TC14: Ray is inside the tube and starts against axis head
        ray = new Ray(new Point3D(0.5, 0.5, 1), vAxis);
        assertNull( "must not be intersections",tube2.findIntersections(ray) );
        // TC15: Ray is outside the tube and starts against axis head
        ray = new Ray(new Point3D(0.5, -0.5, 1), vAxis);
        assertNull("must not be intersections",tube2.findIntersections(ray));
        // TC16: Ray is at the tube surface and starts against axis head
        ray = new Ray(new Point3D(2, 1, 1), vAxis);
        assertNull("must not be intersections",tube2.findIntersections(ray));
        // TC17: Ray is inside the tube and starts at axis head
        ray = new Ray(new Point3D(1, 1, 1), vAxis);
        assertNull( "must not be intersections",tube2.findIntersections(ray));

        // **** Group: Ray is orthogonal but does not begin against the axis head
        // TC21: Ray starts outside and the line is outside (0 points)
        ray = new Ray(new Point3D(0, 2, 2), new Vector(1, 1, 0));
        assertNull( "must not be intersections",tube2.findIntersections(ray));
        // TC22: The line is tangent and the ray starts before the tube (0 points)
        ray = new Ray(new Point3D(0, 2, 2), new Vector(1, 0, 0));
        assertNull( "must not be intersections",tube2.findIntersections(ray));
        // TC23: The line is tangent and the ray starts at the tube (0 points)
        ray = new Ray(new Point3D(1, 2, 2), new Vector(1, 0, 0));
        assertNull( "must not be intersections",tube2.findIntersections(ray));
        // TC24: The line is tangent and the ray starts after the tube (0 points)
        ray = new Ray(new Point3D(2, 2, 2), new Vector(1, 0, 0));
        assertNull("must not be intersections",tube2.findIntersections(ray));
        // TC25: Ray starts before (2 points)
        ray = new Ray(new Point3D(0, 0, 2), new Vector(2, 1, 0));
        answerList.clear();
        answerList = tube2.findIntersections(ray);
        assertNotNull( "must be intersections",answerList);
        assertEquals( "must be 2 intersections",2, answerList.size());
         y1 =answerList.get(0).getY();
         y2 =answerList.get(1).getY();
        temp1=new Point3D( answerList.get(0));
         temp2=new Point3D( answerList.get(1));
        if (y1.coord > y2.coord){
        	answerList.clear();
        	answerList.add(0,temp2);
        	answerList.add(1,temp1);}
        expected.clear();
        expected.add(new Point3D(0.4, 0.2, 2));
        expected.add(new Point3D(2, 1, 2));
        assertEquals("Bad intersections",expected,answerList );
        // TC26: Ray starts at the surface and goes inside (1 point)
        ray = new Ray(new Point3D(0.4, 0.2, 2), new Vector(2, 1, 0));
        answerList.clear();
        answerList = tube2.findIntersections(ray);
        expected.clear();
        expected.add(new Point3D(2, 1, 2));
        assertEquals( "Bad intersections",expected,answerList);
        // TC27: Ray starts inside (1 point)
        ray = new Ray(new Point3D(1, 0.5, 2), new Vector(2, 1, 0));
        answerList.clear();
        answerList = tube2.findIntersections(ray);
        expected.clear();
        expected.add(new Point3D(2, 1, 2));
        assertEquals( "Bad intersections",expected,answerList);
        // TC28: Ray starts at the surface and goes outside (0 points)
        ray = new Ray(new Point3D(2, 1, 2), new Vector(2, 1, 0));
        answerList = tube2.findIntersections(ray);
        assertNull( "Bad intersections",answerList);
        // TC29: Ray starts after
        ray = new Ray(new Point3D(4, 2, 2), new Vector(2, 1, 0));
        answerList = tube2.findIntersections(ray);
        assertNull( "Bad intersections",answerList);
        // TC30: Ray starts before and crosses the axis (2 points)
        ray = new Ray(new Point3D(1, -1, 2), new Vector(0, 1, 0));
        answerList = tube2.findIntersections(ray);
        assertNotNull( "must be intersections",answerList);
        assertEquals( "must be 2 intersections",2, answerList.size());
        y1 =answerList.get(0).getY();
        y2 =answerList.get(1).getY();
       temp1=new Point3D( answerList.get(0));
        temp2=new Point3D( answerList.get(1));
       if (y1.coord > y2.coord){
       	answerList.clear();
       	answerList.add(0,temp2);
       	answerList.add(1,temp1);}
       expected.clear();
       expected.add(new Point3D(1, 0, 2));
       expected.add(new Point3D(1, 2, 2));
       assertEquals("Bad intersections",expected,answerList );

        // TC31: Ray starts at the surface and goes inside and crosses the axis
        ray = new Ray(new Point3D(1, 0, 2), new Vector(0, 1, 0));
        answerList = tube2.findIntersections(ray);
        expected.clear();
        expected.add(new Point3D(1, 2, 2));
        assertEquals("Bad intersections",expected,answerList );
        // TC32: Ray starts inside and the line crosses the axis (1 point)
        ray = new Ray(new Point3D(1, 0.5, 2), new Vector(0, 1, 0));
        answerList = tube2.findIntersections(ray);
        expected.clear();
        expected.add(new Point3D(1, 2, 2));
        assertEquals("Bad intersections",expected,answerList );
        // TC33: Ray starts at the surface and goes outside and the line crosses the
        // axis (0 points)
        ray = new Ray(new Point3D(1, 2, 2), new Vector(0, 1, 0));
        answerList = tube2.findIntersections(ray);
        assertNotNull( "must be intersections",answerList);
        // TC34: Ray starts after and crosses the axis (0 points)
        ray = new Ray(new Point3D(1, 3, 2), new Vector(0, 1, 0));
        answerList = tube2.findIntersections(ray);
        assertNotNull( "must be intersections",answerList);
        // TC35: Ray start at the axis
        ray = new Ray(new Point3D(1, 1, 2), new Vector(0, 1, 0));
        answerList = tube2.findIntersections(ray);
        expected.clear();
        expected.add(new Point3D(1, 2, 2));
        assertEquals("Bad intersections",expected,answerList );

        // **** Group: Ray is orthogonal to axis and begins against the axis head
        // TC41: Ray starts outside and the line is outside (
        ray = new Ray(new Point3D(0, 2, 1), new Vector(1, 1, 0));
        assertNull( "must not be intersections",tube2.findIntersections(ray));
        // TC42: The line is tangent and the ray starts before the tube
        ray = new Ray(new Point3D(0, 2, 1), new Vector(1, 0, 0));
        assertNull( "must not be intersections",tube2.findIntersections(ray));
        // TC43: The line is tangent and the ray starts at the tube
        ray = new Ray(new Point3D(1, 2, 1), new Vector(1, 0, 0));
        assertNull( "must not be intersections",tube2.findIntersections(ray));
        // TC44: The line is tangent and the ray starts after the tube
        ray = new Ray(new Point3D(2, 2, 2), new Vector(1, 0, 0));
        assertNull( "must not be intersections",tube2.findIntersections(ray));
        // TC45: Ray starts before
        ray = new Ray(new Point3D(0, 0, 1), new Vector(2, 1, 0));
        answerList = tube2.findIntersections(ray);
        y1 =answerList.get(0).getY();
        y2 =answerList.get(1).getY();
       temp1=new Point3D( answerList.get(0));
        temp2=new Point3D( answerList.get(1));
       if (y1.coord > y2.coord){
       	answerList.clear();
       	answerList.add(0,temp2);
       	answerList.add(1,temp1);}
       expected.clear();
       expected.add(new Point3D(0.4, 0.2, 1));
       expected.add(new Point3D(2, 1, 1));
       assertEquals("Bad intersections",expected,answerList );
     
        // TC46: Ray starts at the surface and goes inside
        ray = new Ray(new Point3D(0.4, 0.2, 1), new Vector(2, 1, 0));
        answerList = tube2.findIntersections(ray);
        expected.clear();
        expected.add(new Point3D(2, 1, 1));
        assertEquals("Bad intersections",expected,answerList );
       
        // TC47: Ray starts inside
        ray = new Ray(new Point3D(1, 0.5, 1), new Vector(2, 1, 0));
        answerList = tube2.findIntersections(ray);
        expected.clear();
        expected.add(new Point3D(2, 1, 1));
        assertEquals("Bad intersections",expected,answerList );
        // TC48: Ray starts at the surface and goes outside
        ray = new Ray(new Point3D(2, 1, 1), new Vector(2, 1, 0));
        answerList = tube2.findIntersections(ray);
        assertNull( "Bad intersections",answerList);
        // TC49: Ray starts after
        ray = new Ray(new Point3D(4, 2, 1), new Vector(2, 1, 0));
        answerList = tube2.findIntersections(ray);
        assertNull( "Bad intersections",answerList);
        // TC50: Ray starts before and goes through the axis head
        ray = new Ray(new Point3D(1, -1, 1), new Vector(0, 1, 0));
        answerList = tube2.findIntersections(ray);
        y1 =answerList.get(0).getY();
        y2 =answerList.get(1).getY();
       temp1=new Point3D( answerList.get(0));
        temp2=new Point3D( answerList.get(1));
       if (y1.coord > y2.coord){
       	answerList.clear();
       	answerList.add(0,temp2);
       	answerList.add(1,temp1);}
       expected.clear();
       expected.add(new Point3D(1, 0, 1));
       expected.add(new Point3D(1, 2, 1));
       assertEquals("Bad intersections",expected,answerList );
       // TC51: Ray starts at the surface and goes inside and goes through the axis
        // head
        ray = new Ray(new Point3D(1, 0, 1), new Vector(0, 1, 0));
        answerList = tube2.findIntersections(ray);
        assertNotNull( "must be intersections",answerList);
        assertEquals( "must be 1 intersections",1, answerList.size());
        expected.clear();
        expected.add(new Point3D(1, 2, 1));
        assertEquals("Bad intersections",expected,answerList );
        // TC52: Ray starts inside and the line goes through the axis head
        ray = new Ray(new Point3D(1, 0.5, 1), new Vector(0, 1, 0));
        answerList = tube2.findIntersections(ray);
        assertNotNull( "must be intersections",answerList);
        assertEquals( "must be 1 intersections",1, answerList.size());
        expected.clear();
        expected.add(new Point3D(1, 2, 1));
        assertEquals("Bad intersections",expected,answerList );
      
        // TC53: Ray starts at the surface and the line goes outside and goes through
        // the axis head
        ray = new Ray(new Point3D(1, 2, 1), new Vector(0, 1, 0));
        answerList = tube2.findIntersections(ray);
        assertNull( "Bad intersections",answerList);
        // TC54: Ray starts after and the line goes through the axis head
        ray = new Ray(new Point3D(1, 3, 1), new Vector(0, 1, 0));
        answerList = tube2.findIntersections(ray);
        assertNull( "Bad intersections",answerList);
        // TC55: Ray start at the axis head
        ray = new Ray(new Point3D(1, 1, 1), new Vector(0, 1, 0));
        answerList = tube2.findIntersections(ray);
        assertNotNull( "must be intersections",answerList);
        assertEquals( "must be 1 intersections",1, answerList.size());
        expected.clear();
        expected.add(new Point3D(1, 2, 1));
        assertEquals("Bad intersections",expected,answerList );

        // **** Group: Ray's line is neither parallel nor orthogonal to the axis and
        // begins against axis head
        Point3D p0 = new Point3D(0, 2, 1);
        // TC61: Ray's line is outside the tube
        ray = new Ray(p0, new Vector(1, 1, 1));
        answerList = tube2.findIntersections(ray);
        assertNull( "Bad intersections",answerList);
        // TC62: Ray's line crosses the tube and begins before
        ray = new Ray(p0, new Vector(2, -1, 1));
        answerList = tube2.findIntersections(ray);
        assertNotNull( "must be intersections",answerList);
        assertEquals( "must be 2 intersections",2, answerList.size());
        y1 =answerList.get(0).getY();
        y2 =answerList.get(1).getY();
       temp1=new Point3D( answerList.get(0));
        temp2=new Point3D( answerList.get(1));
       if (y1.coord > y2.coord){
       	answerList.clear();
       	answerList.add(0,temp2);
       	answerList.add(1,temp1);}
       expected.clear();
       expected.add(new Point3D(2, 1, 2));
       expected.add(new Point3D(0.4, 1.8, 1.2));
       assertEquals("Bad intersections",expected,answerList );
       
      

    }

	

}
