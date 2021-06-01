/**
 * 
 */
package unittest;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author hilaa
 *
 */
public class RayTest {

    @Test
    public void testFindClosestPoint() {
        Ray ray = new Ray(new Point3D(0, 0, 10), new Vector(1, 10, -100));

        List<Point3D> list = new LinkedList<Point3D>();
        list.add(new Point3D(1, 1, -100));
        list.add(new Point3D(-1, 1, -99));
        list.add(new Point3D(0, 2, -10));
        list.add(new Point3D(0.5, 0, -100));

        assertEquals(list.get(2), ray.findClosestPoint(list));

    }

    @Test
    public void testFindClosestPoint2() {
        Ray ray = new Ray(new Point3D(0, 0, 10), new Vector(1, 10, -100));

        List<Point3D> list = null;


        assertNull("try again",ray.findClosestPoint(list));

    }
    @Test
    public void getfindClosestGeoPoint() {
        Ray ray = new Ray(new Point3D(0, 0, 10), new Vector(1, 10, -100));

        List<Point3D> list = new LinkedList<Point3D>();
        list.add(new Point3D(1, 1, -100));
        list.add(new Point3D(-1, 1, -99));
        list.add(new Point3D(0, 2, -10));
        list.add(new Point3D(0.5, 0, -100));

        assertEquals(list.get(2), ray.findClosestPoint(list));

    }
}
