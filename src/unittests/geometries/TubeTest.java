/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Tube;
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

}
